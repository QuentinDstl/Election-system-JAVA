package dao_package;

import java.sql.*;

public class ElectorDAOImpl implements  ElectorDAO {
    
    /* Variables */
    private Connection Connection;
    private Statement statement;

    private String m_nameUser;

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
    public ElectorDAOImpl(String nameUser) throws SQLException {   
        m_nameUser = nameUser;
        
        if ((m_nameUser.equals("quentin")) || (m_nameUser.equals("Quentin")))
            Connection = DriverManager.getConnection(URL_QUENTIN,USER_QUENTIN,PASSWORD_QUENTIN);
        
        else if ((m_nameUser.equals("charles")) || (m_nameUser.equals("Charles")))
            Connection = DriverManager.getConnection(URL_CHARLES,USER_CHARLES,PASSWORD_CHARLES);
        
        else if ((m_nameUser.equals("clement")) || (m_nameUser.equals("Clement")))
            Connection = DriverManager.getConnection(URL_CLEMENT,USER_CLEMENT,PASSWORD_CLEMENT);
        
        statement = Connection.createStatement();
    }
    
    public void createTableElector() throws SQLException {   
        statement.executeUpdate(CREATION_TABLE_ELECTOR);
        statement.executeUpdate("ALTER TABLE `elector` AUTO_INCREMENT = " +FIRST_ID_ELECTOR +";");
        System.out.println(CREATION_TABLE_ELECTOR);
    }
    
    public void dropTableElector() throws SQLException {
        statement.executeUpdate(DROP_TABLE_ELECTOR);
        System.out.println(DROP_TABLE_ELECTOR);
    }
    
    public void addCandidate(String last_name, String first_name, String password, String name_state) throws SQLException {
        statement.executeUpdate(ADD_ELECTOR 
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
        statement.executeUpdate(DELETE_ELECTOR 
                                + "WHERE `id` = " +id + ";");
        System.out.println(DELETE_ELECTOR);
    }
    
}
