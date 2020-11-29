package dao_package;

import java.sql.*;

public class StateDAOImpl implements StateDAO {
    
    /* Variables */
    private Connection Connection;
    private Statement statement;
    
    private String m_nameUser;
    
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
    
    
    /* Constructeur */
    public StateDAOImpl(String nameUser) throws SQLException {   
        m_nameUser = nameUser;
        
        if ((m_nameUser.equals("quentin")) || (m_nameUser.equals("Quentin")))
            Connection = DriverManager.getConnection(URL_QUENTIN,USER_QUENTIN,PASSWORD_QUENTIN);
        
        else if ((m_nameUser.equals("charles")) || (m_nameUser.equals("Charles")))
            Connection = DriverManager.getConnection(URL_CHARLES,USER_CHARLES,PASSWORD_CHARLES);
        
        else if ((m_nameUser.equals("clement")) || (m_nameUser.equals("Clement")))
            Connection = DriverManager.getConnection(URL_CLEMENT,USER_CLEMENT,PASSWORD_CLEMENT);
        
        statement = Connection.createStatement();
    }
    
    public void createTableState() throws SQLException {   
        statement.executeUpdate(CREATION_TABLE_STATE);
        System.out.println(CREATION_TABLE_STATE);
    }
    
    public void dropTableState() throws SQLException {
        statement.executeUpdate(DROP_TABLE_STATE);
        System.out.println(DROP_TABLE_STATE);
    }
    
    
    /* Méthodes de requêtes */
    public String getNameStateIntoTable(int num_case) throws SQLException {
        
        ResultSet resultLecture = statement.executeQuery("SELECT `nameState` FROM `state` WHERE id = " +num_case + ";");
        resultLecture.next();
        System.out.println("name state : " +resultLecture.getString(1));
        return resultLecture.getString(1);
    }
    
    public int getNumberMaxOfElectorsInStateIntoTable(int num_case) throws SQLException {
        
        ResultSet resultLecture = statement.executeQuery("SELECT `nbrMaxElectors` FROM `state` WHERE id = " +num_case + ";");
        resultLecture.next();
        System.out.println("number max of electors : " +resultLecture.getInt(1));
        return resultLecture.getInt(1);
    }
    
    public boolean getConditionAllWinIntoTable(int num_case) throws SQLException {
        
        ResultSet resultLecture = statement.executeQuery("SELECT `allWin` FROM `state` WHERE id = " +num_case + ";");
        resultLecture.next();
        System.out.println("all win : " +resultLecture.getBoolean(1));
        return resultLecture.getBoolean(1);
    }
    
    public boolean getConditionPauseIntoTable(int num_case) throws SQLException {
        
        ResultSet resultLecture = statement.executeQuery("SELECT `pause` FROM `state` WHERE id = " +num_case + ";");
        resultLecture.next();
        System.out.println("all win : " +resultLecture.getBoolean(1));
        return resultLecture.getBoolean(1);
    }
}
