package dao_package;

public interface CandidateDAO {
    
    /* Clement's connexion variables */
    static final String URL_CLEMENT = "jdbc:mysql://localhost:3306/election_database";
    static final String USER_CLEMENT = "root";
    static final String PASSWORD_CLEMENT = "";
    
    /* Charles' connexion variables */
    static final String URL_CHARLES = "jdbc:mysql://localhost/projets5";
    static final String USER_CHARLES = "root";
    static final String PASSWORD_CHARLES = "";
    static final String DATABASE_CHARLES = "projets5";
    
    /* Quentin's connexion variables */
    static final String URL_QUENTIN = "";
    static final String USER_QUENTIN = "Quentin";
    static final String PASSWORD_QUENTIN = "";
    static final String DATABASE_QUENTIN = "";
    
    static final int FIRST_ID_CANDIDATE = 0;
   
}
