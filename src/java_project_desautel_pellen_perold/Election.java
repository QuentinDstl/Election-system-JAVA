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
    
    public final ElectionDAOImpl election_from_db = new ElectionDAOImpl();
    public final StateDAOImpl state_from_db = new StateDAOImpl();
    public final CandidateDAOImpl candidate_from_db = new CandidateDAOImpl();
    public final OfficialDAOImpl official_from_db = new OfficialDAOImpl();
    public final ElectorDAOImpl elector_from_db = new ElectorDAOImpl();

    
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
    public final ArrayList<State> downLoadStatesListFromTable() throws SQLException { 
        ArrayList<State> states = new ArrayList<>();
        int nb_states = DAO.NUMBER_OF_STATES;
        for(int case_index=0; case_index<nb_states; ++case_index) {
            states.add(new State(case_index+1, state_from_db));
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
    
    public final ArrayList<Candidate> downLoadCandidatesListFromTable() throws SQLException { 
        ArrayList<Candidate> candidates = new ArrayList<>();
        int nb_candidates = candidate_from_db.getNumberOfCandidatesIntoTable();
        try {
            for(int case_index=0; case_index<nb_candidates; ++case_index) {
                candidates.add(new Candidate(case_index + DAO.FIRST_ID_CANDIDATE, candidate_from_db, this));
            }
        }
        catch(SQLException sql_except) {
            Log.add(sql_except.getMessage() + " Arret du chargement");
        }
        return candidates;
    }
    
    public final ArrayList<Official> downLoadOfficialsListFromTable() throws SQLException { 
        ArrayList<Official> officials = new ArrayList<>();
        int nb_officials = official_from_db.getNumberOfOfficialsIntoTable();
        try {
            for(int case_index=0; case_index<nb_officials; ++case_index) {
                officials.add(new Official(case_index + DAO.FIRST_ID_OFFICIAL, official_from_db, this));
            }
        }
        catch(SQLException sql_except) {
            Log.add(sql_except.getMessage() + " Arret du chargement");
        }
        return officials;
    }
    
    public final ArrayList<Elector> downLoadElectorsListFromTable() throws SQLException { 
        ArrayList<Elector> electors = new ArrayList<>();
        int nb_electors = elector_from_db.getNumberOfElectorsIntoTable();
        try {
            for(int case_index=0; case_index<nb_electors; ++case_index) {
                electors.add(new Elector(case_index + DAO.FIRST_ID_ELECTOR, m_candidates, elector_from_db, this));
            }
        }
        catch(SQLException sql_except) {
            Log.add(sql_except.getMessage() + " Arret du chargement");
        }
        catch(IllegalArgumentException ill_arg_except) {
            Log.add(ill_arg_except.getMessage() + " Arret du chargement");
        }
        return electors;
    }
   
    
    /* Méthodes de modification des listes */
    public void deleteElector(int num_case) throws SQLException {
        m_electors.remove(num_case);
        elector_from_db.deleteElector(num_case + DAO.FIRST_ID_ELECTOR);
    }
    
    public void addElector(String last_name, String first_name, String password, State state) throws SQLException {
        //try {
            m_electors.add( new Elector(last_name, first_name, password, state, elector_from_db, this));
            elector_from_db.addToTable(m_electors.get(m_electors.size()-1).getLastName(),
                                       m_electors.get(m_electors.size()-1).getFirstName(), 
                                       m_electors.get(m_electors.size()-1).getPassword(), 
                                       m_electors.get(m_electors.size()-1).getState().getName(),
                                       "NoOne", "0");
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
    
    public void deleteCandidate(int num_case) throws SQLException {
        m_candidates.remove(num_case);
        candidate_from_db.deleteCandidate(num_case + DAO.FIRST_ID_CANDIDATE);
    }
    
    public void addCandidate(String last_name, String first_name, String password, String party) throws SQLException {
        m_candidates.add( new Candidate(last_name, first_name,password, party, candidate_from_db, this));
        candidate_from_db.addToTable(m_candidates.get(m_candidates.size()-1).getLastName(),
                                     m_candidates.get(m_candidates.size()-1).getFirstName(),
                                     m_candidates.get(m_candidates.size()-1).getPassword(),
                                     m_candidates.get(m_candidates.size()-1).getParty(),
                                     "0");
        m_candidates.get(m_candidates.size()-1).setId(m_candidates.size()-1 + DAO.FIRST_ID_CANDIDATE);
    }
    
    
    /* Getters */
    public boolean getOpenVote() {
        return m_openVote;
    }
    
    public ArrayList<State> getStates() {
        return  m_states;
    }
    
    public ArrayList<Candidate> getCandidates() {
        return  this.m_candidates;
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
