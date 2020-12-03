package java_project_desautel_pellen_perold;

import dao_package.DAO;
import dao_package.ElectorDAOImpl;
import java.sql.SQLException;
import java.util.ArrayList;


public class Elector extends Person {
    
    /* Variables */
    private State m_state;
    private Candidate m_candidate;
    private boolean m_voteDone;
    
    private final ElectorDAOImpl elector_from_db = new ElectorDAOImpl();
    
    private final Election election_access = new Election();
    
    
    /* Constructeeur */
    /* De la DATABASE */
    public Elector(int num_case, ArrayList<Candidate> candidates) throws SQLException {
        
        super();
        setLastNameFromDataBase(elector_from_db.getLastNameElectorIntoTable(num_case));
        setFirstNameFromDataBase(elector_from_db.getFirstNameElectorIntoTable(num_case));  
        setPasswordFromDataBase(elector_from_db.getPasswordElectorIntoTable(num_case));
        setIdFromDataBase(num_case);
        
        m_state = setStateFromDatabase(elector_from_db.getNameStateOfElectorIntoTable(num_case));
        
        m_candidate = setCandidateFromDataBase(candidates);
    }
    
    /* De la saisie d'un Official */
    public Elector(String last_name, String first_name, State state) throws SQLException {
        
        super(last_name, first_name, "0000");
        m_state = state;
        m_voteDone = false;
    }

    
    /* méthodes de chargement */
    public final State setStateFromDatabase(String name_state_into_table) throws SQLException {
        State state;
        int index_valid_database = 0;
        for(int i=0; i<election_access.getStates().size(); ++i) {
            if(election_access.getStates().get(i).getName().equals(name_state_into_table)) {
                index_valid_database = i;
            }
        }
        state = election_access.getStates().get(index_valid_database);
        return state;
    }
    
    public final Candidate setCandidateFromDataBase(ArrayList<Candidate> candidates) throws SQLException {
        Candidate candidate;
        int index_valid_database = 0;
        for(int i=0; i<candidates.size(); ++i) {
            if(candidates.get(i).getId() == i + DAO.FIRST_ID_CANDIDATE) {
                index_valid_database = i;
            }
        }
        candidate = candidates.get(index_valid_database);
        return candidate;
    }
    
    
    public void downLoadElectionDataBase() throws SQLException {
        election_access.downLoadDataBaseForElector();
    }
    
    
    /* Getters */
    public State getState() {
        return m_state;
    }

    public Candidate getCandidate() {
        return m_candidate;
    }

    public boolean isVoteDone() {
        return m_voteDone;
    }

    /* Setters */
    public void hadVoted() {
        m_voteDone = true;
    }
}
