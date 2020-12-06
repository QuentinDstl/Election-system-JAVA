package dao_package;

import config_package.Config;
import config_package.Log;
import java.sql.*;

public class ElectionDAOImpl implements DAO {
    
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
    
    private static final String ADD_ELECTION = "INSERT INTO `election`";
    
    /* Constructor */
    public ElectionDAOImpl() throws SQLException {   
        m_connection = DriverManager.getConnection(Config.getUrl(),Config.getLogin(),Config.getPassword());
        m_statement = m_connection.createStatement();
    }
    
    /* Méthodes de modification des tables */
    @Override
    public void createTable() throws SQLException {   
        m_statement.executeUpdate(CREATION_TABLE_ELECTION);
        Log.add(CREATION_TABLE_ELECTION);
    }
    
    @Override
    public void dropTable() throws SQLException {
        m_statement.executeUpdate(DROP_TABLE_ELECTION);
        Log.add(DROP_TABLE_ELECTION);
    }
    
    public void closeVote() throws SQLException {
        m_statement.executeUpdate("UPDATE `election` SET `openVote` = 0;");
        Log.add("UPDATE `election` SET `openVote` = 0;");
    }
    
    public void startVote() throws SQLException {
        m_statement.executeUpdate("UPDATE `election` SET `openVote` = 1;");
        Log.add("UPDATE `election` SET `openVote` = 1;");
    }
    
    /**
     * @param args
     * String (boolean) openVote
     * @throws java.sql.SQLException
    */
    @Override
    public void addToTable(String... args) throws SQLException, IllegalArgumentException {
        if(args.length!=1)
            throw new IllegalArgumentException("ElectionDAOImpl.addToTable() need 4 Strings");
        String query = ADD_ELECTION + "(`openVote`)" + "Values (" ;
        query += "'" + Integer.parseInt(args[0]) + "');";

        m_statement.executeUpdate(query);
        Log.add(ADD_ELECTION);
    }

    /* Méthodes de requêtes */
    public boolean getOpenVoteConditionIntoTable() throws SQLException {
        
        ResultSet resultLecture = m_statement.executeQuery("SELECT `openVote` FROM `election`;");
        resultLecture.next();
        //System.out.println("open vote : " +resultLecture.getBoolean(1));
        return resultLecture.getBoolean(1);
    }
}
