package java_project_desautel_pellen_perold;

public abstract class Person {
    
    /* Variables */
    private String m_last_name;
    private String m_first_name;
    private String m_password;
    private int m_id;
    
    /* Constantes */
    private int ID_NOT_DEFINED = -1;
    
    
    /* Constructeeur */
    /* De la DATABASE */
    public Person() {
        m_last_name = null;
        m_first_name = null;
        m_password = null;
        m_id = ID_NOT_DEFINED;
    }
    
    /* De le saisie d'un officiel */
    public Person(String last_name, String first_name, String password) {
        m_last_name = last_name;
        m_first_name = first_name;
        m_password = password;
        m_id = ID_NOT_DEFINED;
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
    
    public int getId() {
        return m_id;
    }
    
    /* Setters pour la DATABASE */
    public void setLastNameFromDataBase(String last_name_db) {
        m_last_name = last_name_db;
    }
    
    public void setFirstNameFromDataBase(String first_name_db) {
        m_first_name = first_name_db;
    }
    
    public void setPasswordFromDataBase(String password_db) {
        m_password = password_db;
    }
    
    public void setIdFromDataBase(int id_db) {
        m_id = id_db;
    }
    
    
    /* Setters */
    public void setId(int id) {
        m_id = id;
    }
}
