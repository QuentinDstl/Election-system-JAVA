package java_project_desautel_pellen_perold;

import dao_package.CandidateDAOImpl;
import dao_package.ElectionDAOImpl;
import dao_package.ElectorDAO;
import dao_package.ElectorDAOImpl;
import dao_package.OfficialDAOImpl;
import dao_package.StateDAO;
import dao_package.StateDAOImpl;
import java.util.ArrayList;
import java.sql.SQLException;


public class Election {
    private boolean m_openVote;
    private ArrayList<State> m_states;
    
    private ArrayList<Candidate> m_candidates;
    private ArrayList<Official> m_officials;
    private ArrayList<Elector> m_electors;
    
    private final ElectionDAOImpl election_from_db = new ElectionDAOImpl();
    private final StateDAOImpl state_from_db = new StateDAOImpl();
    private final CandidateDAOImpl candidate_from_db = new CandidateDAOImpl();
    private final OfficialDAOImpl official_from_db = new OfficialDAOImpl();
    private final ElectorDAOImpl elector_from_db = new ElectorDAOImpl();

    
    /* Constructeeur */
    /* De la DATABASE */
    public Election() throws SQLException {
        
        m_openVote = election_from_db.getOpenVoteConditionIntoTable();
        m_states = downLoadStatesListFromTable();
        m_candidates = null;
        m_officials = null;
        m_electors = null;
    }
    
    /* Méthodes de chargement de la DataBase */
    public ArrayList<State> downLoadStatesListFromTable() throws SQLException { 
        ArrayList<State> states = new ArrayList<>();
        int nb_states = StateDAO.NUMBER_OF_STATES;
        for(int case_index=0; case_index<nb_states; ++case_index) {
            states.add(new State(case_index));
        }
        return states;
    }
    
    public void downloadDataBaseForCandidate() throws SQLException {
        m_candidates = downLoadCandidatesListFromTable();
    }
    
    public void downloadDataBaseForOfficial() throws  SQLException {
        m_candidates = downLoadCandidatesListFromTable();
        m_electors = downLoadElectorsListFromTable();
    }
    
    public void downLoadDataBaseForElector() throws  SQLException {
        m_candidates = downLoadCandidatesListFromTable();
    }
    
    public ArrayList<Candidate> downLoadCandidatesListFromTable() throws SQLException { 
        ArrayList<Candidate> candidates = new ArrayList<>();
        int nb_candidates = candidate_from_db.getNumberOfCandidatesIntoTable();
        for(int case_index=0; case_index<nb_candidates; ++case_index) {
            candidates.add(new Candidate(case_index));
        }
        return candidates;
    }
    
    public ArrayList<Official> downLoadOfficialsListFromTable() throws SQLException { 
        ArrayList<Official> officials = new ArrayList<>();
        int nb_officials = official_from_db.getNumberOfOfficialsIntoTable();
        for(int case_index=0; case_index<nb_officials; ++case_index) {
            officials.add(new Official(case_index));
        }
        return officials;
    }
    
    public ArrayList<Elector> downLoadElectorsListFromTable() throws SQLException { 
        ArrayList<Elector> electors = new ArrayList<>();
        int nb_electors = elector_from_db.getNumberOfElectorsIntoTable();
        for(int case_index=0; case_index<nb_electors; ++case_index) {
            electors.add(new Elector(case_index));
        }
        return electors;
    }
   
    
    /* Méthodes de modification des listes */
    public void deleteElector(int num_case) throws SQLException {
        m_electors.remove(num_case);
        elector_from_db.deleteElector(num_case + ElectorDAO.FIRST_ID_ELECTOR);
    }
    
    public void addElector(String last_name, String first_name, State state) throws SQLException {
        m_electors.add( new Elector(last_name, first_name, state));
        elector_from_db.addElector(m_electors.get(m_electors.size()-1).getLastName(),
                                   m_electors.get(m_electors.size()-1).getFirstName(), 
                                   m_electors.get(m_electors.size()-1).getPassword(), 
                                   m_electors.get(m_electors.size()-1).getState().getName());
        m_electors.get(m_electors.size()-1).setId(m_electors.size()-1 + ElectorDAO.FIRST_ID_ELECTOR);
    }
    
    
    /* Getters */
    public boolean getOpenVote() {
        return m_openVote;
    }
    
    public ArrayList<State> getStates() {
        return  m_states;
    }
    
    public ArrayList<Candidate> getCandidates() {
        return  m_candidates;
    }
    
    public ArrayList<Official> getOfficials() {
        return m_officials;
    }
    
    public ArrayList<Elector> getElectors() {
        return  m_electors;
    }
    
    
    /* Setters */
    public void setOpenVote(boolean openVote) {
        m_openVote = openVote;
    }
    
    public void addState(State state) throws NullPointerException {
        if(state == null) {
            throw new NullPointerException("addState in the class Election : the state value given to the method is null");
        }  
        else
            m_states.add(state);
    }
    
    public void showVoteNational() {
        // Appeler Vue
    }
    
    public void showWinner() {
        // Appeler Vue
    }
    
}
