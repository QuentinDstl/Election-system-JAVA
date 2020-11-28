package java_project_desautel_pellen_perold;

public abstract class Person {
    
    /* Variables */
    private String m_last_name;
    private String m_first_name;
    private String m_password;
    
    
    /* Constructor */
    public Person() {
        m_last_name = " ";
        m_first_name = " ";
        m_password = "0000";
    }
    
    public Person(String last_name, String first_name, String password) {
        m_last_name = last_name;
        m_first_name = first_name;
        m_password = password;
    }
    
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
    
    /* Setters pour la DATABASE */
    public void setLastNameFromDataBase(String last_name_db) {
        m_last_name = last_name_db;
    }
    
    public void setFirstNameFromDataBase(String first_name_db) {
        m_first_name = first_name_db;
    }
}
