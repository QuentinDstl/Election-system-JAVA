package java_project_desautel_pellen_perold;

import dao_package.DAO;
import dao_package.OfficialDAOImpl;
import java.sql.SQLException;
import java.util.ArrayList;

public class Official extends Person {
    
    private OfficialDAOImpl official_from_db = new OfficialDAOImpl();
    
    private Election election_access = new Election();
    
    
    /* Constructeur */
    /* De la DATABASE */
    public Official(int num_case) throws SQLException {
        
        super();
        setLastNameFromDataBase(official_from_db.getLastNameOfficialIntoTable(num_case));
        setFirstNameFromDataBase(official_from_db.getFirstNameOfficialIntoTable(num_case));  
        setPasswordFromDataBase(official_from_db.getPasswordOfficialIntoTable(num_case));
        setIdFromDataBase(num_case + DAO.FIRST_ID_OFFICIAL);   
    }
    
    public void downLoadOfficialDataBase() throws SQLException {
        election_access.downloadDataBaseForOfficial();
    }
    
    
    public ArrayList<Elector> getElectors() {
        return election_access.getElectors();
    }
    
    public void deleteElector(Elector choosen_elector) throws SQLException {
        election_access.deleteElector(choosen_elector.getId() - DAO.FIRST_ID_ELECTOR);
    }
    
    public void addElector(String last_name, String first_name, State state) throws SQLException {
        election_access.addElector(last_name, first_name, state);
    }
    
    public ArrayList<Candidate> getCandidates() {
        return election_access.getCandidates();
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
