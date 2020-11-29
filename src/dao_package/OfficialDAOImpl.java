package dao_package;

import config_package.Config;
import java.sql.*;

public class OfficialDAOImpl implements OfficialDAO {
    
    /* Variables */
    private Connection m_connection;
    private Statement m_statement;
    
    /* Constants */
    private static final String CREATION_TABLE_OFFICIAL = "CREATE TABLE IF NOT EXISTS `official`"
                                                        + "("
                                                        + " `lastname` VARCHAR(20) NOT NULL, "
                                                        + " `firstname` VARCHAR(20) NOT NULL, "
                                                        + " `password` VARCHAR(10) NOT NULL, "
                                                        + " `id` INT(6) NOT NULL AUTO_INCREMENT, "
                                                        + " PRIMARY KEY(`id`) "
                                                        + ") "
                                                        + "ENGINE = InnoDB " 
                                                        + "CHARACTER SET utf8mb4 " 
                                                        + "COLLATE utf8mb4_unicode_ci;";
    
    private static final String DROP_TABLE_OFFICIAL = "DROP TABLE IF EXISTS `official`;";
    
    /* Constructor */
    public OfficialDAOImpl() throws SQLException {
        m_connection = DriverManager.getConnection(Config.getUrl(),Config.getLogin(),Config.getPassword());
        m_statement = m_connection.createStatement();
    }
    
    public void createTableOfficial() throws SQLException {   
        m_statement.executeUpdate(CREATION_TABLE_OFFICIAL);
        m_statement.executeUpdate("ALTER TABLE `official` AUTO_INCREMENT = " +FIRST_ID_OFFICIAL +";");
        System.out.println(CREATION_TABLE_OFFICIAL);
    }
    
    public void dropTableOfficial() throws SQLException {
        m_statement.executeUpdate(DROP_TABLE_OFFICIAL);
        System.out.println(DROP_TABLE_OFFICIAL);
    }
    
}
