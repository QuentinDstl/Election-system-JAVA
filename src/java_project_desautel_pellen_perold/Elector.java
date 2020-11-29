package java_project_desautel_pellen_perold;

import dao_package.ElectorDAO;
import dao_package.ElectorDAOImpl;
import java.sql.SQLException;
import java.util.ArrayList;

public class Elector extends Person {
    
    /* Variables */
    State m_state;
    Candidate m_candidate;
    boolean m_voteDone;
    
    ElectorDAOImpl elector_from_db = new ElectorDAOImpl(ElectorDAO.CONNECTION_NAME);
    
    Election election_access = new Election();
    
    
    /* Constructeeur */
    /* De la DATABASE */
    public Elector(int num_case) throws SQLException {
        
        super();
        setLastNameFromDataBase(elector_from_db.getLastNameElectorIntoTable(num_case));
        setFirstNameFromDataBase(elector_from_db.getFirstNameElectorIntoTable(num_case));  
        setPasswordFromDataBase(elector_from_db.getPasswordElectorIntoTable(num_case));
        setIdFromDataBase(num_case + ElectorDAO.FIRST_ID_ELECTOR);
        
        //m_state = elector_from_db.getNameStateOfElectorIntoTable(num_case);
        
        
    }

    
    /* méthodes de chargement */
    //public 
    
    
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
