package java_project_desautel_pellen_perold;


import dao_package.StateDAOImpl;
import java.sql.SQLException;
import java.util.ArrayList;


public class State {
    private boolean m_pause;
    private boolean m_all_Win;
    private String m_name;
    private int m_nbr_max_electors;
   
    private ArrayList<Elector> m_list_electors; 
    
    private final StateDAOImpl m_state_from_db;
    
    
    /* Constructeur */
    /* De la Database */
    public State(int num_case, StateDAOImpl state_from_db) throws SQLException {
       
        m_state_from_db = state_from_db;
        
        m_name = m_state_from_db.getNameStateIntoTable(num_case);
        m_nbr_max_electors = m_state_from_db.getNumberMaxOfElectorsInStateIntoTable(num_case);
        m_all_Win = m_state_from_db.getConditionAllWinIntoTable(num_case);
        m_pause = m_state_from_db.getConditionPauseIntoTable(num_case);
        
        m_list_electors = new ArrayList<>();
    }
    
    
    
    /*public State(boolean pause, boolean allWin, String nameState, int nbrElector) throws IllegalArgumentException {
        m_pause = pause;
        m_allWin = allWin;
        if(nameState.equals(""))
            throw new IllegalArgumentException("State constructor in the class State : nameState value is empty");
        else
            m_name = nameState;
        if(nbrElector <= 0)
            throw new IllegalArgumentException("State constructor in the class State : nbrElector value <= 0");
        else
            m_nbrElector = nbrElector;
    }*/
    
    public void addElectorToList(Elector elector) {
            m_list_electors.add(elector); 
    }
    
    public int getNbVotesCandidateInState(String name_candidate) {
        int nb_votes_candidate = 0;
        for(int i=0; i<m_list_electors.size(); ++i) {
            if(m_list_electors.get(i).getCandidate().equals(name_candidate)) {
                nb_votes_candidate++;
            }
        }
        return nb_votes_candidate;
    }

    public int getNbVotesInState() {
        int nbVote = 0;
        for(int i=0; i<m_list_electors.size(); ++i) {
            if(!m_list_electors.get(i).getCandidate().equals("noOne"))
                nbVote++;
        }
        return nbVote;
    }

    /* Getters */
    public boolean isPause() {
        return m_pause;
    }
    
    public boolean isAllWin() {
        return m_all_Win;
    }
    
    public String getName() {
        return m_name;
    }
    
    public int getNbrElector() {
        return m_nbr_max_electors;
    }
    
    public ArrayList<Elector> getListElector() {
        return m_list_electors;
    }
    
    /* Setters */
    public void setPause(boolean pause) {
        m_pause = pause;
    }

    public void setAllWin(boolean all_Win) {
        m_all_Win = all_Win;
    }

    public void setName(String name) {
        m_name = name;
    }

    public void setNbrElector(int nbr_elector) {
        m_nbr_max_electors= nbr_elector;
    }
    
    public void showVote() {
        // faire avec vue
    }
}
