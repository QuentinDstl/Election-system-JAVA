package dao_package;

import config_package.Config;
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
                                                        + " UNIQUE(`nameState`) "
                                                        + ") "
                                                        + "ENGINE = InnoDB " 
                                                        + "CHARACTER SET utf8mb4 " 
                                                        + "COLLATE utf8mb4_unicode_ci;";
    
    private static final String DROP_TABLE_STATE = "DROP TABLE IF EXISTS `state`;";
    
    private static final String ADD_STATE = "INSERT INTO `state`";
    
    /* Constructeur */
    public StateDAOImpl() throws SQLException {   
        m_connection = DriverManager.getConnection(Config.getUrl(),Config.getLogin(),Config.getPassword());
        m_statement = m_connection.createStatement();
    }
    
    @Override
    public void createTable() throws SQLException {   
        m_statement.executeUpdate(CREATION_TABLE_STATE);
        System.out.println(CREATION_TABLE_STATE);
    }
    
    @Override
    public void dropTable() throws SQLException {
        m_statement.executeUpdate(DROP_TABLE_STATE);
        System.out.println(DROP_TABLE_STATE);
    }
    
    /**
     * @param args
     * String nameState, String (short) nbrMaxElectors, String (boolean) allWin, String (boolean) pause
     * @throws java.sql.SQLException
    */
    @Override
    public void addToTable(String... args) throws SQLException, IllegalArgumentException {
        String query = ADD_STATE + "(`nameState`, `nbrMaxElectors`, `allWin`, `pause`)" + "Values (" ;
        query += "'" + args[0] + "', '" + Integer.parseInt(args[1]) + "', '" + Integer.parseInt(args[2]) + "', '" + Integer.parseInt(args[3]) + "');";
        m_statement.executeUpdate(query);
        System.out.println(ADD_STATE);
    }
    
    /* Méthodes de requêtes */
    public String getNameStateIntoTable(int num_case) throws SQLException {
        
        ResultSet resultLecture = m_statement.executeQuery("SELECT `nameState` FROM `state` WHERE id = " +num_case + ";");
        resultLecture.next();
        System.out.println("name state : " +resultLecture.getString(1));
        return resultLecture.getString(1);
    }
    
    public int getNumberMaxOfElectorsInStateIntoTable(int num_case) throws SQLException {
        
        ResultSet resultLecture = m_statement.executeQuery("SELECT `nbrMaxElectors` FROM `state` WHERE id = " +num_case + ";");
        resultLecture.next();
        System.out.println("number max of electors : " +resultLecture.getInt(1));
        return resultLecture.getInt(1);
    }
    
    public boolean getConditionAllWinIntoTable(int num_case) throws SQLException {
        
        ResultSet resultLecture = m_statement.executeQuery("SELECT `allWin` FROM `state` WHERE id = " +num_case + ";");
        resultLecture.next();
        System.out.println("all win : " +resultLecture.getBoolean(1));
        return resultLecture.getBoolean(1);
    }
    
    public boolean getConditionPauseIntoTable(int num_case) throws SQLException {
        
        ResultSet resultLecture = m_statement.executeQuery("SELECT `pause` FROM `state` WHERE id = " +num_case + ";");
        resultLecture.next();
        System.out.println("all win : " +resultLecture.getBoolean(1));
        return resultLecture.getBoolean(1);
    }
}
