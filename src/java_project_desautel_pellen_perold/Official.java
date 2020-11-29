package java_project_desautel_pellen_perold;

import dao_package.OfficialDAO;
import dao_package.OfficialDAOImpl;
import java.sql.SQLException;

public class Official extends Person {
    
    private OfficialDAOImpl official_from_db = new OfficialDAOImpl(OfficialDAO.CONNECTION_NAME);
    
    private Election election_access = new Election();
    
    
    /* Constructeur */
    /* De la DATABASE */
    public Official(int num_case) throws SQLException {
        
        super();
        setLastNameFromDataBase(official_from_db.getLastNameOfficialIntoTable(num_case));
        setFirstNameFromDataBase(official_from_db.getFirstNameOfficialIntoTable(num_case));  
        setPasswordFromDataBase(official_from_db.getPasswordOfficialIntoTable(num_case));
        setIdFromDataBase(num_case + OfficialDAO.FIRST_ID_OFFICIAL);
       
    }
    
    public void downLoadOfficialDataBase() throws SQLException {
        election_access.downloadDataBaseForOfficial();
    }
    
    
    public void deleteElector() {
        
    }
    
    public void addElector() {
        
    }
    
    public void deleteCandidate() {
        
    }
    
    public void addCandidate() {
        
    }
    
    public void openVote() {
        
    }
    
    public void closeVote() {
        
    }
    
    /* Fonction that pause all states or unpause all states */
    public void pauseVote() {
        
    }
    
    /* Fonction that pause one state or unpause it */
    public void pauseVote(State state) throws NullPointerException {
        if(state == null)
            throw new NullPointerException("pauseVote in the class Official : the state value given to the method is null");
        else
            state.setPause(!state.isPause());                                   // We inverse the actual value in pause
    }
}
