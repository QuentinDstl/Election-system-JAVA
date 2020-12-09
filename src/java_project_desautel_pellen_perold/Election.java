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
import loader_package.LoadingInfo;

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

    public static boolean COMPLETE_ELECTOR_PERSON = true;
    public static boolean SIMPLE_ELECTOR_VOTER = false;

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
        int nb_states = state_from_db.getNumberOfStatesFromTable();
        for(int case_index=0; case_index<nb_states; ++case_index) {
            states.add(new State(case_index+1, state_from_db));
        }
        return states;
    }

    public void downloadDataBaseForCandidate() throws SQLException {
        m_candidates = downLoadCandidatesListFromTable();
        m_electors = downLoadElectorsListFromTable(SIMPLE_ELECTOR_VOTER);
    }

    public void downloadDataBaseForOfficial() throws  SQLException {
        m_candidates = downLoadCandidatesListFromTable();
        m_electors = downLoadElectorsListFromTable(COMPLETE_ELECTOR_PERSON);
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
            System.out.println(sql_except.getMessage() + " Arret du chargement");
            System.exit(0);
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
            System.out.println(sql_except.getMessage() + " Arret du chargement");
            System.exit(0);
        }
        return officials;
    }

    public final ArrayList<Elector> downLoadElectorsListFromTable(boolean complete_electors) throws SQLException { 
        ArrayList<Elector> electors = new ArrayList<>();
        int nb_electors = elector_from_db.getNumberOfElectorsIntoTable();
        try {
            for(int case_index=0; case_index<nb_electors; ++case_index) {
                electors.add(new Elector(case_index + DAO.FIRST_ID_ELECTOR, m_candidates, elector_from_db, this, complete_electors));
                electors.get(electors.size()-1).addElectorToState();
            }
        }
        catch(SQLException sql_except) {
            Log.add(sql_except.getMessage() + " Arret du chargement");
            System.out.println(sql_except.getMessage() + " Arret du chargement");
            System.exit(0);
        }
        return electors;
    }

    /* Méthodes de modification des listes */
    public void deleteElector(int num_case) throws SQLException {
        m_electors.remove(num_case);
        elector_from_db.deleteElector(num_case + DAO.FIRST_ID_ELECTOR);
    }
    
    public void addElector(String last_name, String first_name, String password, State state) throws SQLException {
        m_electors.add( new Elector(last_name, first_name, password, state, elector_from_db, this));
        m_electors.get(m_electors.size()-1).addElectorToState();
        elector_from_db.addToTable(m_electors.get(m_electors.size()-1).getLastName(),
                                   m_electors.get(m_electors.size()-1).getFirstName(), 
                                   m_electors.get(m_electors.size()-1).getPassword(), 
                                   m_electors.get(m_electors.size()-1).getState().getName(),
                                   "NoOne", "0");
        m_electors.get(m_electors.size()-1).setId(m_electors.size()-1 + DAO.FIRST_ID_ELECTOR);
    }
    
    public void deleteCandidate(int num_case) throws SQLException {
        for(int i=0; i<m_electors.size(); ++i) {
            m_electors.get(i).DeleteVote(m_candidates.get(num_case).getLastName());
        }
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

    public String getWinnerOfState(State state) {
        String name_winner = "";
        int nb_votes_winner = 0;
        for(int i=0; i<m_candidates.size(); ++i) {
            if(state.getNbVotesCandidateInState(m_candidates.get(i).getLastName()) > nb_votes_winner) {
                nb_votes_winner = state.getNbVotesCandidateInState(m_candidates.get(i).getLastName());
                name_winner = m_candidates.get(i).getLastName();
            }
        }
        return name_winner;
    }
    
    public ArrayList<Integer> getProportionnalityCandidatesOfState(State state) {
        ArrayList<Integer> candidates_scores = new ArrayList<>();
        
        for(int i=0; i<m_candidates.size(); ++i) {
            double pourcentageOfElector = (state.getNbVotesCandidateInState(m_candidates.get(i).getLastName())/ (double) state.getNbVotesInState());
            /* we round numbreOfGreatVoterVoting by adding 0.5 to the double value and casting it to int (it will floor it)*/
            /* if the variable is over 0.5 : 0.5+0.5=1 it will be floor to the int++ and if not it stay the same */
            int numbreOfGreatVoterVoting = (int)(state.getNbrElector()*pourcentageOfElector + 0.5); 
            candidates_scores.add(numbreOfGreatVoterVoting);
        }
        return candidates_scores;
    }

     public void pauseState(String nameState) throws SQLException {
        State state = this.getState(nameState);
        if(state != null) {
            boolean pause = state.isPause();
            state.setPause(!pause);                                   // we reverse the  pause value
            state_from_db.savePause(nameState, !pause);
        }
    }
     
    public void pauseAllStates(boolean pause) throws SQLException {
        for(State state : m_states) {
            state.setPause(!pause);
        }
       state_from_db.saveAllPause(!pause);
    }

    public boolean getOpenVote() {
        return m_openVote;
    }

    public ArrayList<State> getStates() {
        return  m_states;
    }

    public State getState(String nameState) {
        for(State state: m_states){
            if(nameState.equals(state.getName()))
		return state;
        }
        return null;
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
    public void setOpenVote() throws SQLException
    {
        if (m_openVote == true)
        {
            m_openVote = false;
            election_from_db.closeVote();
        } 
        else if (m_openVote == false)
        {
            m_openVote = true;
            election_from_db.startVote();
        }
    }
}
