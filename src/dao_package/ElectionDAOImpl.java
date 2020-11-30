package dao_package;

import config_package.Config;
import java.sql.*;

public class ElectionDAOImpl implements ElectionDAO {
    
    /* Variables */
    private final Connection m_connection;
    private final Statement m_statement;
    
    
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
    public ElectionDAOImpl() throws SQLException {   
        m_connection = DriverManager.getConnection(Config.getUrl(),Config.getLogin(),Config.getPassword());
        m_statement = m_connection.createStatement();
    }
    
    /* Méthodes de modification des tables */
    public void createTableCandidate() throws SQLException {   
        m_statement.executeUpdate(CREATION_TABLE_ELECTION);
        System.out.println(CREATION_TABLE_ELECTION);
    }
    
    public void dropTableCandidate() throws SQLException {
        m_statement.executeUpdate(DROP_TABLE_ELECTION);
        System.out.println(DROP_TABLE_ELECTION);
    }
    
    
    /* Méthodes de requêtes */
    public boolean getOpenVoteConditionIntoTable() throws SQLException {
        
        ResultSet resultLecture = m_statement.executeQuery("SELECT `openVote` FROM `election;");
        resultLecture.next();
        System.out.println("open vote : " +resultLecture.getBoolean(1));
        return resultLecture.getBoolean(1);
    }
}
