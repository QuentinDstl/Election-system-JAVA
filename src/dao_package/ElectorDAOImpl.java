package dao_package;

import config_package.Config;
import java.sql.*;

public class ElectorDAOImpl implements  ElectorDAO {
    
    /* Variables */
    private Connection m_connection;
    private Statement m_statement;

    /* Constants */
    private static final String CREATION_TABLE_ELECTOR = "CREATE TABLE IF NOT EXISTS `elector`"
                                                        + "("
                                                        + " `lastname` VARCHAR(20) NOT NULL, "
                                                        + " `firstname` VARCHAR(20) NOT NULL, "
                                                        + " `password` VARCHAR(10) NOT NULL, "
                                                        + " `nameState` VARCHAR(20) NOT NULL, "
                                                        + " `nameCandidate` VARCHAR(20), "
                                                        + " `id` INT(6) NOT NULL AUTO_INCREMENT, "
                                                        + " PRIMARY KEY(`id`) "
                                                        + ") "
                                                        + "ENGINE = InnoDB " 
                                                        + "CHARACTER SET utf8mb4 " 
                                                        + "COLLATE utf8mb4_unicode_ci;";

    private static final String DROP_TABLE_ELECTOR = "DROP TABLE IF EXISTS `elector`;";

    private static final String ADD_ELECTOR = "INSERT INTO `elector`";

    private static final String DELETE_ELECTOR = "DELETE FROM `elector`";


    /* Constructor */
    public ElectorDAOImpl() throws SQLException {
        m_connection = DriverManager.getConnection(Config.getUrl(),Config.getLogin(),Config.getPassword());
        m_statement = m_connection.createStatement();
    }

    public void createTableElector() throws SQLException {   
        m_statement.executeUpdate(CREATION_TABLE_ELECTOR);
        m_statement.executeUpdate("ALTER TABLE `elector` AUTO_INCREMENT = " +FIRST_ID_ELECTOR +";");
        System.out.println(CREATION_TABLE_ELECTOR);
    }
    
    public void dropTableElector() throws SQLException {
        m_statement.executeUpdate(DROP_TABLE_ELECTOR);
        System.out.println(DROP_TABLE_ELECTOR);
    }
    
    public void addCandidate(String last_name, String first_name, String password, String name_state) throws SQLException {
        m_statement.executeUpdate(ADD_ELECTOR 
                                + "(`lastname`, `firstname`, `password`, `nameState`)"
                                + "Values (" 
                                + "'" +last_name + "', "
                                + "'" +first_name  + "', "
                                + "'" +password + "', "
                                + "'" +name_state + "'"
                                + ");");
        System.out.println(ADD_ELECTOR);
    }
    
    public void deleteCandidate(int id) throws SQLException {
        m_statement.executeUpdate(DELETE_ELECTOR 
                                + "WHERE `id` = " +id + ";");
        System.out.println(DELETE_ELECTOR);
    }
    
}
