package java_project_desautel_pellen_perold;

public abstract class Personne {
    
    /* Variables */
    private String m_first_name;
    private String m_last_name;
    private String m_password;
    
    
    /* Constructor */
    public Personne() {}
    
    /* Getter */
    public String getFirstName() {
        return  m_first_name;
    }
    
    public String getLastName() {
        return m_last_name;
    }
    
    public String getPassword() {
        return m_password;
    }
}
