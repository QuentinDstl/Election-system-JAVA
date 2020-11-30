package java_project_desautel_pellen_perold;

import dao_package.ElectorDAO;
import dao_package.ElectorDAOImpl;
import java.sql.SQLException;


public class Elector extends Person {
    
    /* Variables */
    private State m_state;
    private Candidate m_candidate;
    private boolean m_voteDone;
    
    private final ElectorDAOImpl elector_from_db = new ElectorDAOImpl();
    
    private final Election election_access = new Election();
    
    
    /* Constructeeur */
    /* De la DATABASE */
    public Elector(int num_case) throws SQLException {
        
        super();
        setLastNameFromDataBase(elector_from_db.getLastNameElectorIntoTable(num_case));
        setFirstNameFromDataBase(elector_from_db.getFirstNameElectorIntoTable(num_case));  
        setPasswordFromDataBase(elector_from_db.getPasswordElectorIntoTable(num_case));
        setIdFromDataBase(num_case + ElectorDAO.FIRST_ID_ELECTOR);
        
        m_state = setStateFromDatabase(elector_from_db.getNameStateOfElectorIntoTable(num_case), 
                                        num_case);
        
        m_candidate = setCandidateFromDataBase(num_case);
    }
    
    /* De la saisie d'un Official */
    public Elector(String last_name, String first_name, State state) throws SQLException {
        
        super(last_name, first_name, "0000");
        m_state = state;
    }

    
    /* méthodes de chargement */
    public State setStateFromDatabase(String name_state_into_table, int num_case) throws SQLException {
        State state = new State(num_case);
        for(int i=0; i<election_access.getStates().size(); ++i) {
            if(election_access.getStates().get(i).getName().equals(name_state_into_table)) {
                state = election_access.getStates().get(i);
            }
        }
        return state;
    }
    
    public Candidate setCandidateFromDataBase(int num_case) throws SQLException {
        Candidate candidate = new Candidate(num_case);
        for(int i=0; i<election_access.getCandidates().size(); ++i) {
            if(election_access.getCandidates().get(i).getId() == num_case) {
                candidate = election_access.getCandidates().get(i);
            }
        }
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
