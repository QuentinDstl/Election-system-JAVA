package dao_package;

import config_package.Config;
import config_package.Log;
import java.sql.*;

public class StateDAOImpl implements DAO {
    
    /* Variables */
    private final Connection m_connection;
    private final Statement m_statement;
 
    
    /* Constants */
    private static final String CREATION_TABLE_STATE = "CREATE TABLE IF NOT EXISTS `state`"
                                                        + "("
                                                        + " `nameState` VARCHAR(20) NOT NULL, "
                                                        + " `nbrMaxElectors` SMALLINT(6) NOT NULL, "
                                                        + " `allWin` BOOLEAN NOT NULL, "
                                                        + " `pause` BOOLEAN NOT NULL, "
                                                        + " `numState` SMALLINT(6) AUTO_INCREMENT, "
                                                        + " UNIQUE(`nameState`), "
                                                        + " PRIMARY KEY (`numState`) "
                                                        + ") "
                                                        + "ENGINE = InnoDB " 
                                                        + "CHARACTER SET utf8mb4 " 
                                                        + "COLLATE utf8mb4_unicode_ci;";
    
    private static final String DROP_TABLE_STATE = "DROP TABLE IF EXISTS `state`;";
    
    private static final String ADD_STATE = "INSERT INTO `state`";
    
    private static final String COUNT_NBR_OF_STATES = "SELECT COUNT(*) FROM `state`;";
    
    /* Constructeur */
    public StateDAOImpl() throws SQLException { 
        m_connection = DriverManager.getConnection(Config.getUrl(),Config.getLogin(),Config.getPassword());
        m_statement = m_connection.createStatement();
    }
    
    @Override
    public void createTable() throws SQLException {   
        m_statement.executeUpdate(CREATION_TABLE_STATE);
        Log.add(CREATION_TABLE_STATE);
    }
    
    @Override
    public void dropTable() throws SQLException {
        m_statement.executeUpdate(DROP_TABLE_STATE);
        Log.add(DROP_TABLE_STATE);
    }
    
    /**
     * @param args
     * String nameState, String (short) nbrMaxElectors, String (boolean) allWin, String (boolean) pause
     * @throws java.sql.SQLException
    */
    @Override
    public void addToTable(String... args) throws SQLException {
        String query = ADD_STATE + "(`nameState`, `nbrMaxElectors`, `allWin`, `pause`)" + "Values (" ;
        query += "'" + args[0] + "', '" + Integer.parseInt(args[1]) + "', '" + Integer.parseInt(args[2]) + "', '" + Integer.parseInt(args[3]) + "');";
        m_statement.executeUpdate(query);
        //Log.add(ADD_STATE);
    }
    
    public void savePause(String nameState, boolean pause) throws SQLException {
        if(pause == true) {
            m_statement.executeUpdate("UPDATE `state` SET `pause` = 1" 
                                    + " WHERE `nameState` = '" + nameState + "';");
        }
        else {
            m_statement.executeUpdate("UPDATE `state` SET `pause` = 0" 
                                    + " WHERE `nameState` = '" + nameState + "';");
        }
    }
    
    public void saveAllPause(boolean pause) throws SQLException {
        if(pause == true) {
            m_statement.executeUpdate("UPDATE `state` SET `pause` = 1;");
        }
        else {
            m_statement.executeUpdate("UPDATE `state` SET `pause` = 0;");
        }
    }
    
    /* Méthodes de requêtes */
    public int getNumberOfStatesFromTable() throws SQLException {
        int numberOfStates;
        ResultSet resultLecture = m_statement.executeQuery(COUNT_NBR_OF_STATES);
        resultLecture.next();
        numberOfStates = resultLecture.getInt(1);
        return numberOfStates;
    }
    
    public String getNameStateIntoTable(int num_case) throws SQLException {
        
        ResultSet resultLecture = m_statement.executeQuery("SELECT `nameState` FROM `state` WHERE `numState` = " +num_case + ";");
        resultLecture.next();
        return resultLecture.getString(1);
    }
    
    public int getNumberMaxOfElectorsInStateIntoTable(int num_case) throws SQLException {
        
        
        ResultSet resultLecture = m_statement.executeQuery("SELECT `nbrMaxElectors` FROM `state` WHERE `numState` = " +num_case + ";");
        resultLecture.next();
        return resultLecture.getInt(1);
    }
    
    public boolean getConditionAllWinIntoTable(int num_case) throws SQLException {
        
        ResultSet resultLecture = m_statement.executeQuery("SELECT `allWin` FROM `state` WHERE `numState` = " +num_case + ";");
        resultLecture.next();
        return resultLecture.getBoolean(1);
    }
    
    public boolean getConditionPauseIntoTable(int num_case) throws SQLException {
        
        ResultSet resultLecture = m_statement.executeQuery("SELECT `pause` FROM `state` WHERE `numState` = " +num_case + ";");
        resultLecture.next();
        return resultLecture.getBoolean(1);
    }
}
