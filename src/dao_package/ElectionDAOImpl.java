package dao_package;

import java.sql.*;
import java.util.ArrayList;

public class ElectionDAOImpl implements ElectionDAO {
    
    /* Variables */
    private Connection Connection;
    private Statement statement;
    
    private String m_nameUser;
    
    /* Constantes */
    private static final String CREATION_TABLE_ELECTION = "CREATE TABLE IF NOT EXISTS `election`"
                                                        + "("
                                                        + " openVote BOOLEAN DEFAULT 0 "
                                                        + ") "
                                                        + "ENGINE = InnoDB " 
                                                        + "CHARACTER SET utf8mb4 " 
                                                        + "COLLATE utf8mb4_unicode_ci;";
    
    private static final String DROP_TABLE_ELECTION = "DROP TABLE IF EXISTS `election`;";
    
    /* Constructor */
    public ElectionDAOImpl(String nameUser) throws SQLException {   
        m_nameUser = nameUser;
        
        if ((m_nameUser.equals("quentin")) || (m_nameUser.equals("Quentin")))
            Connection = DriverManager.getConnection(URL_QUENTIN,USER_QUENTIN,PASSWORD_QUENTIN);
        
        else if ((m_nameUser.equals("charles")) || (m_nameUser.equals("Charles")))
            Connection = DriverManager.getConnection(URL_CHARLES,USER_CHARLES,PASSWORD_CHARLES);
        
        else if ((m_nameUser.equals("clement")) || (m_nameUser.equals("Clement")))
            Connection = DriverManager.getConnection(URL_CLEMENT,USER_CLEMENT,PASSWORD_CLEMENT);
        
        statement = Connection.createStatement();
    }
    
    /* Méthodes de modification des tables */
    public void createTableCandidate() throws SQLException {   
        statement.executeUpdate(CREATION_TABLE_ELECTION);
        System.out.println(CREATION_TABLE_ELECTION);
    }
    
    public void dropTableCandidate() throws SQLException {
        statement.executeUpdate(DROP_TABLE_ELECTION);
        System.out.println(DROP_TABLE_ELECTION);
    }
    
    
    /* Méthodes de requêtes */
    public boolean getOpenVoteConditionIntoTable() throws SQLException {
        
        ResultSet resultLecture = statement.executeQuery("SELECT `openVote` FROM `election;");
        resultLecture.next();
        System.out.println("open vote : " +resultLecture.getBoolean(1));
        return resultLecture.getBoolean(1);
    }
}
