package java_project_desautel_pellen_perold;

import dao_package.DAO;
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
        System.out.println("\n\nJE SUUUUUUIS ICI Nb Candidates : ");
        System.out.println(election_access.getCandidates().size());
        setLastNameFromDataBase(elector_from_db.getLastNameElectorIntoTable(num_case));
        setFirstNameFromDataBase(elector_from_db.getFirstNameElectorIntoTable(num_case));  
        setPasswordFromDataBase(elector_from_db.getPasswordElectorIntoTable(num_case));
        setIdFromDataBase(num_case + DAO.FIRST_ID_ELECTOR);
        
        m_state = setStateFromDatabase(elector_from_db.getNameStateOfElectorIntoTable(num_case), 
                                       num_case);
        System.out.println("\n\nCHARGEMENT STATE DONE");
        
        m_candidate = setCandidateFromDataBase(num_case);
    }
    
    /* De la saisie d'un Official */
    public Elector(String last_name, String first_name, State state) throws SQLException {
        
        super(last_name, first_name, "0000");
        m_state = state;
    }

    
    /* méthodes de chargement */
    public State setStateFromDatabase(String name_state_into_table, int num_case) throws SQLException {
        State state;
        int index_valid_database = 0;
        System.out.println("\n\nNb States : " +election_access.getStates().size());
        System.out.println(name_state_into_table);
        for(int i=0; i<election_access.getStates().size(); ++i) {
            if(election_access.getStates().get(i).getName().equals(name_state_into_table)) {
                index_valid_database = i + 1;
            }
        }
        state = new State((index_valid_database));
        return state;
    }
    
    public Candidate setCandidateFromDataBase(int num_case) throws SQLException {
        Candidate candidate;
        int index_valid_database = 0;
        for(int i=0; i<election_access.getCandidates().size(); ++i) {
            if(election_access.getCandidates().get(i).getId() == num_case) {
                index_valid_database = i + 1;
            }
        }
        candidate = new Candidate(index_valid_database);
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
