package java_project_desautel_pellen_perold;

import java.util.ArrayList;

public class Election {
    private boolean m_openVote;
    private ArrayList<State> m_states;

    public Election(boolean openVote) {
        m_openVote = openVote;
        m_states = new ArrayList<>();
    }

    public boolean getOpenVote() {
        return m_openVote;
    }

    public void setOpenVote(boolean openVote) {
        m_openVote = openVote;
    }
    
    public void addState(State state) throws NullPointerException {
        if(state == null) {
            throw new NullPointerException("dans addState de la class Election : le state est null");
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
