package java_project_desautel_pellen_perold;

import java.util.ArrayList;

public class Election {
    private boolean m_openVote;
    private ArrayList<State> m_states;
    private ArrayList<Person> m_persons;

    /* Constructor */
    public Election(boolean openVote) {
        m_openVote = openVote;
        m_states = new ArrayList<>();
    }
    
    /* Getters */
    public boolean getOpenVote() {
        return m_openVote;
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
