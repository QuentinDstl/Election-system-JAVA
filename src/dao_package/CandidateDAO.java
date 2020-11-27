package dao_package;

import java.sql.*;

public interface CandidateDAO {
    
    /* Clement's connexion variables */
    static final String DB_URL_CLEMENT = "";
    static final String DB_USER_CLEMENT = "Clement";
    static final String DB_PASSWORD_CLEMENT = "";
    
    /* Charles' connexion variables */
    static final String DB_URL_CHARLES = "";
    static final String DB_USER_CHARLES = "Charles";
    static final String DB_PASSWORD_CHARLES = "";
    
    /* Quentin's connexion variables */
    static final String DB_URL_QUENTIN = "";
    static final String DB_USER_QUENTIN = "Quentin";
    static final String DB_PASSWORD_QUENTIN = "";
    
    
    public void createConnectionDataBase(String name_user);
    
    public void initDataBase();
}
