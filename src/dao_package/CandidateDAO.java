package dao_package;

public interface CandidateDAO {
    
    /* variables de connexion de l'utilisateur actuel */ 
    static final String URL = "jdbc:mysql://localhost:3306/election_database";
    static final String USER= "root";
    static final String PASSWORD = "";
    static final String CONNECTION_NAME= "clement";
    
    /* variables de connexion de Clément */
    static final String URL_CLEMENT = "jdbc:mysql://localhost:3306/election_database";
    static final String USER_CLEMENT = "root";
    static final String PASSWORD_CLEMENT = "";
    static final String CONNECTION_NAME_CLEMENT = "clement";
    
    /* variables de connexion de Charles */
    static final String URL_CHARLES = "jdbc:mysql://localhost/projets5";
    static final String USER_CHARLES = "root";
    static final String PASSWORD_CHARLES = "";
    static final String DATABASE_CHARLES = "projets5";
    static final String CONNECTION_NAME_CHARLES = "charles";
    
    /* variables de connexion de Quentin */
    static final String URL_QUENTIN = "jdbc:mysql://localhost/projets5?autoReconnect=true&useSSL=false";
    static final String USER_QUENTIN = "root";
    static final String PASSWORD_QUENTIN = "43eyekbqDPRz9xzF8UF6";
    static final String DATABASE_QUENTIN = "projets5";
    static final String CONNECTION_NAME_QUENTIN = "quentin";
    
    
    static final int FIRST_ID_CANDIDATE = 0;
   
}
