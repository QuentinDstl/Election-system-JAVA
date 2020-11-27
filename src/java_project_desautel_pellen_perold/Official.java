package java_project_desautel_pellen_perold;

public class Official extends Personne {
    
    /* Constructor */
    public Official () {
        super();
    }
    
    public void deleteElector() {
        
    }
    
    public void addElector() {
        
    }
    
    public void deleteCandidat() {
        
    }
    
    public void addCandidat() {
        
    }
    
    public void openVote() {
        
    }
    
    public void closeVote() {
        
    }
    
    /* Fonction that pause all states or unpause all states */
    public void pauseVote() {
        
    }
    
    /* Fonction that pause one state or unpause it */
    public void pauseVote(State state) throws NullPointerException {
        if(state == null)
            throw new NullPointerException("pauseVote in the class Official : the state value given to the method is null");
        else
            state.setPause(!state.isPause());                                   // We inverse the actual value in pause
    }
}
