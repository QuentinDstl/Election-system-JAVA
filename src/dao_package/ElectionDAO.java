package dao_package;

public interface ElectionDAO {
    
    /* variables de connexion de l'utilisateur actuel */ 
    static final String URL = "jdbc:mysql://localhost:3306/election_database";
    static final String USER= "root";
    static final String PASSWORD = "";
    static final String CONNECTION_NAME= "Clement";
    
    
    
    
    /* variables de connexion de Clément */
    static final String URL_CLEMENT = "jdbc:mysql://localhost:3306/election_database";
    static final String USER_CLEMENT = "root";
    static final String PASSWORD_CLEMENT = "";
    static final String CONNECTION_NAME_CLEMENT = "Clement";
    
    /* variables de connexion de Charles */
    static final String URL_CHARLES = "jdbc:mysql://localhost/projets5";
    static final String USER_CHARLES = "root";
    static final String PASSWORD_CHARLES = "";
    static final String DATABASE_CHARLES = "projets5";
    static final String CONNECTION_NAME_CHARLES = "Charles";
    
    /* variables de connexion de Quentin */
    static final String URL_QUENTIN = "";
    static final String USER_QUENTIN = "Quentin";
    static final String PASSWORD_QUENTIN = "";
    static final String DATABASE_QUENTIN = "";
    static final String CONNECTION_NAME_QUENTIN = "Quentin";
}
