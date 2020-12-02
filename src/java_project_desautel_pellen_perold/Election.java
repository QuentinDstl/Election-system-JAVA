package java_project_desautel_pellen_perold;

import config_package.Log;
import dao_package.CandidateDAOImpl;
import dao_package.ElectionDAOImpl;
import dao_package.DAO;
import dao_package.ElectorDAOImpl;
import dao_package.OfficialDAOImpl;
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
        int nb_states = DAO.NUMBER_OF_STATES;
        for(int case_index=0; case_index<nb_states; ++case_index) {
            states.add(new State(case_index));
        }
        return states;
    }
    
    public void downloadDataBaseForCandidate() throws SQLException {
        try {
            m_candidates = downLoadCandidatesListFromTable();
        }
        catch(IllegalArgumentException e) {
            Log.add(e.getMessage());
        }
    }
    
    public void downloadDataBaseForOfficial() throws  SQLException {
        m_candidates = downLoadCandidatesListFromTable();
        m_electors = downLoadElectorsListFromTable();
    }
    
    public void downLoadDataBaseForElector() throws  SQLException {
        m_candidates = downLoadCandidatesListFromTable();
    }
    
    public ArrayList<Candidate> downLoadCandidatesListFromTable() throws SQLException, IllegalArgumentException { 
        ArrayList<Candidate> candidates = new ArrayList<>();
        int nb_candidates;
        
        if(candidate_from_db.getNumberOfCandidatesIntoTable() < 0) {
            throw new IllegalArgumentException(": Negative number of candidates");
        } 
        else if(candidate_from_db.getNumberOfCandidatesIntoTable() > DAO.NUMBER_MAX_OF_CANDIDATES) {
            throw new IllegalArgumentException(": Too much candidates to add one more");
        }
        else {
            nb_candidates = candidate_from_db.getNumberOfCandidatesIntoTable();
        }
        
        for(int case_index=0; case_index<nb_candidates; ++case_index) {
            try {
                candidates.add(new Candidate(case_index));
            }
            catch(SQLException e) {
                try {
                    Log.add(e.getMessage() 
                        +candidate_from_db.getLastNameCandidateIntoTable(case_index)
                        +candidate_from_db.getFirstNameCandidateIntoTable(case_index));
                }
                catch(SQLException excep2) {
                    Log.add(excep2.getMessage());
                }
                case_index--;
                candidate_from_db.deleteCandidate(case_index);
            }
        }
        return candidates;
    }
    
    public ArrayList<Official> downLoadOfficialsListFromTable() throws SQLException { 
        ArrayList<Official> officials = new ArrayList<>();
        int nb_officials;
        
        if(official_from_db.getNumberOfOfficialsIntoTable()< 0) {
            throw new IllegalArgumentException(": Negative number of officials");
        } 
        else if(official_from_db.getNumberOfOfficialsIntoTable() > DAO.NUMBER_MAX_OFFICIALS) {
            throw new IllegalArgumentException(": Too much officials to add one more");
        }
        else {
            nb_officials = official_from_db.getNumberOfOfficialsIntoTable();
        }
        
        for(int case_index=0; case_index<nb_officials; ++case_index) {
            try {
                officials.add(new Official(case_index));
            }
            catch(SQLException e) {
                try {
                    Log.add(e.getMessage() 
                        +official_from_db.getLastNameOfficialIntoTable(case_index)
                        +official_from_db.getFirstNameOfficialIntoTable(case_index));
                }
                catch(SQLException excep2) {
                    Log.add(excep2.getMessage());
                }
                case_index--;
                official_from_db.deleteOfficial(case_index);
            }
        }
        return officials;
    }
    
    public ArrayList<Elector> downLoadElectorsListFromTable() throws SQLException { 
        ArrayList<Elector> electors = new ArrayList<>();
        int nb_electors;
        
        if(elector_from_db.getNumberOfElectorsIntoTable()< 0) {
            throw new IllegalArgumentException(": Negative number of electors");
        } 
        else {
            nb_electors = official_from_db.getNumberOfOfficialsIntoTable();
        }
        
        for(int case_index=0; case_index<nb_electors; ++case_index) {
            try {
                electors.add(new Elector(case_index));
            }
            catch(SQLException e) {
                try {
                    Log.add(e.getMessage() 
                        +elector_from_db.getLastNameElectorIntoTable(case_index)
                        +elector_from_db.getFirstNameElectorIntoTable(case_index));
                }
                catch(SQLException excep2) {
                    Log.add(excep2.getMessage());
                }
                case_index--;
                elector_from_db.deleteElector(case_index);
            }
        }
        return electors;
    }
   
    
    /* Méthodes de modification des listes */
    public void deleteElector(int num_case) throws SQLException {
        m_electors.remove(num_case);
        elector_from_db.deleteElector(num_case + DAO.FIRST_ID_ELECTOR);
    }
    
    public void addElector(String last_name, String first_name, State state) throws SQLException {
        //try {
            m_electors.add( new Elector(last_name, first_name, state));
            elector_from_db.addToTable(m_electors.get(m_electors.size()-1).getLastName(),
                                       m_electors.get(m_electors.size()-1).getFirstName(), 
                                       m_electors.get(m_electors.size()-1).getPassword(), 
                                       m_electors.get(m_electors.size()-1).getState().getName());
            //if(m_electors.size()-1 + DAO.FIRST_ID_ELECTOR < 100) {
                //throw IllegalArgumentException();
            //}
            //else {
                m_electors.get(m_electors.size()-1).setId(m_electors.size()-1 + DAO.FIRST_ID_ELECTOR);
            //}
        //} 
        //catch(IllegalArgumentException e) {
            
        //}
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
