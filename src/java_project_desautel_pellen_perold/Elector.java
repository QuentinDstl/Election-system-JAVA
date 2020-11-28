package java_project_desautel_pellen_perold;

public class Elector extends Person {
    
    /* Variables */
    State m_state;
    Candidate m_candidate;
    boolean m_voteDone;
    
    /* Constructor */
    public Elector() {}

    /* Getters */
    public State getState() {
        return m_state;
    }

    public Candidate getCandidate() {
        return m_candidate;
    }

    public boolean isVoteDone() {
        return m_voteDone;
    }

    /* Setters */
    public void hadVoted() {
        m_voteDone = true;
    }
}
