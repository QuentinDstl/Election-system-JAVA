package java_project_desautel_pellen_perold;

import dao_package.DAO;
import dao_package.ElectorDAOImpl;
import java.sql.SQLException;
import java.util.ArrayList;


public class Elector extends Person {
    
    /* Variables */
    private State m_state;
    private String m_candidate_name;
    private boolean m_voteDone;
    
    private final Election m_election_access;
    
    private final ElectorDAOImpl m_elector_from_db;
    
    
    /* Constructeeur */
    /* De la DATABASE */
    public Elector(int num_case, ArrayList<Candidate> candidates, ElectorDAOImpl elector_from_db, Election election_access) throws SQLException {
        
        super();
        
        m_elector_from_db = elector_from_db;
        
        setLastNameFromDataBase(m_elector_from_db.getLastNameElectorIntoTable(num_case));
        setFirstNameFromDataBase(m_elector_from_db.getFirstNameElectorIntoTable(num_case));  
        setPasswordFromDataBase(m_elector_from_db.getPasswordElectorIntoTable(num_case));
        setIdFromDataBase(num_case);
        
        m_election_access = election_access;
        
        m_state = setStateFromDatabase(m_elector_from_db.getNameStateOfElectorIntoTable(num_case));
        
        m_candidate_name = m_elector_from_db.getNameCandidateOfElectorIntoTable(num_case);
        m_voteDone = m_elector_from_db.getTestVoteElector(num_case);
        if((m_voteDone == true && m_candidate_name.equals("NoOne")) || (m_voteDone == false && (!m_candidate_name.equals("NoOne")))) {
            throw new SQLException(": Base de donnée corrompue (le nom du candidat et la verification du vote ne correspondent pas)");
        }
    }
    
    /* De la saisie d'un Official */
    public Elector(String last_name, String first_name,String password, State state, ElectorDAOImpl elector_from_db, Election election_access) throws SQLException {
        
        super(last_name, first_name, password);
        
        m_election_access = election_access;
        
        m_elector_from_db = elector_from_db;
        
        m_state = state;
        m_candidate_name = "NoOne";
        m_voteDone = false;
    }

    
    /* méthodes de chargement */
    public final State setStateFromDatabase(String name_state_into_table) throws SQLException {
        State state;
        int index_valid_database = 0;
        for(int i=0; i<m_election_access.getStates().size(); ++i) {
            if(m_election_access.getStates().get(i).getName().equals(name_state_into_table)) {
                index_valid_database = i;
            }
        }
        state = m_election_access.getStates().get(index_valid_database);
        return state;
    }
    
    /*public final Candidate setCandidateFromDataBase(ArrayList<Candidate> candidates) throws SQLException {
        Candidate candidate;
        int index_valid_database = 0;
        for(int i=0; i<candidates.size(); ++i) {
            if(candidates.get(i).getId() == i + DAO.FIRST_ID_CANDIDATE) {
                index_valid_database = i;
            }
        }
        candidate = candidates.get(index_valid_database);
        return candidate;
    }*/
    
    
    public void downLoadElectionDataBase() throws SQLException {
        m_election_access.downLoadDataBaseForElector();
    }
    
    
    /* Getters */
    public State getState() {
        return m_state;
    }

    public String getCandidate() {
        return m_candidate_name;
    }

    public boolean isVoteDone() {
        return m_voteDone;
    }

    /* Setters */
    public void Votes(Candidate candidate) throws SQLException {
        boolean exist_candidate = false;
        for(int i=0; i<m_election_access.getCandidates().size(); ++i) {
            if(candidate.getId() == m_election_access.getCandidates().get(i).getId())
            {
                m_candidate_name = candidate.getLastName();
                m_voteDone = true;
                exist_candidate = true;
                m_election_access.getCandidates().get(i).incrementeNbrVotesTotal();
            }
        }
        if(exist_candidate == false) {
            throw new IllegalArgumentException("choosen candidate doesn't exist");
        }
        
        m_elector_from_db.saveVoteElector(this.getId(), m_candidate_name);
    }
}
