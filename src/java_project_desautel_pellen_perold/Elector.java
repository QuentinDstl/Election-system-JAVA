package java_project_desautel_pellen_perold;

public class Elector extends Personne {
    
    /* Variables */
    State m_state;
    Candidate m_candidate;
    boolean m_vote_done;
    
    
    /* Constructor */
    public Elector() {}
    
    /* Getters */
    public State getState() {
        return m_state;
    }
    
    public String getNameState() {
        return m_state.getName;
    }
    
    public Candidate getCandidate() {
        return m_candidate;
    }
    
    public String getNameCandidate() {
        return m_candidate.getName();
    }
    
    public boolean has_already_voted() {
        return m_vote_done;
    }
    
    
    /* Setters */
    public void is_voting() {
        m_vote_done = true;
    }
}
