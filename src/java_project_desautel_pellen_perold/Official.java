package java_project_desautel_pellen_perold;

import dao_package.DAO;
import dao_package.OfficialDAOImpl;
import java.sql.SQLException;
import java.util.ArrayList;

public class Official extends Person {
    
    private OfficialDAOImpl m_official_from_db;
    
    private Election m_election_access;
    
    
    /* Constructeur */
    /* De la DATABASE */
    public Official(int num_case, OfficialDAOImpl official_from_db, Election election_access) throws SQLException {
        
        super();
        m_election_access = election_access;
        m_official_from_db = official_from_db;
        
        setLastNameFromDataBase(m_official_from_db.getLastNameOfficialIntoTable(num_case));
        setFirstNameFromDataBase(m_official_from_db.getFirstNameOfficialIntoTable(num_case));  
        setPasswordFromDataBase(m_official_from_db.getPasswordOfficialIntoTable(num_case));
        setIdFromDataBase(num_case + DAO.FIRST_ID_OFFICIAL); 
    }
    
    public void downLoadOfficialDataBase() throws SQLException {
        m_election_access.downloadDataBaseForOfficial();
    }
    
    
    public ArrayList<Elector> getElectors() {
        return m_election_access.getElectors();
    }
    
    public void deleteElector(Elector choosen_elector) throws SQLException {
        m_election_access.deleteElector(choosen_elector.getId() - DAO.FIRST_ID_ELECTOR);
    }
    
    public void addElector(String last_name, String first_name,String password, State state) throws SQLException {
        m_election_access.addElector(last_name, first_name,password, state);
    }
    
    public ArrayList<Candidate> getCandidates() {
        return m_election_access.getCandidates();
    }
    
    public void deleteCandidate(Candidate choosen_candidate) throws SQLException {
        m_election_access.deleteCandidate(choosen_candidate.getId() - DAO.FIRST_ID_CANDIDATE);
    }
    
    public void addCandidate(String last_name, String first_name, String password, String party) throws SQLException {
        m_election_access.addCandidate(last_name, first_name,password, party);
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
