package java_project_desautel_pellen_perold;

public class State {
    private boolean m_pause;
    private boolean m_allWin;
    private String m_name;
    private int m_nbrElector;
    
    /* Constructor */
    public State(boolean pause, boolean allWin, String nameState, int nbrElector) throws IllegalArgumentException {
        m_pause = pause;
        m_allWin = allWin;
        if(nameState.equals(""))
            throw new IllegalArgumentException("Constructeur State dans la class State : nameState est vide");
        else
            m_name = nameState;
        if(nbrElector <= 0)
            throw new IllegalArgumentException("Constructeur State dans la class State : nbrElector est inferieur ou egal a zero");
        else
            m_nbrElector = nbrElector;
    }

    /* Getters */
    public boolean isPause() {
        return m_pause;
    }
    
    public boolean isAllWin() {
        return m_allWin;
    }
    
    public String getName() {
        return m_name;
    }
    
    public int getNbrElector() {
        return m_nbrElector;
    }
    
    /* Setters */
    public void setPause(boolean pause) {
        m_pause = pause;
    }

    public void setM_allWin(boolean allWin) {
        m_allWin = allWin;
    }

    public void setName(String name) {
        m_name = name;
    }

    public void setNbrElector(int nbrElector) {
        m_nbrElector = nbrElector;
    }
    
    public void showVote() {
        // faire avec vue
    }
}
