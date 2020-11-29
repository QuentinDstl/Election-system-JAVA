package dao_package;

import java.sql.*;

public class OfficialDAOImpl implements OfficialDAO {
    
    /* Variables */
    private Connection Connection;
    private Statement statement;
    
    private String m_nameUser;
    
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
    
    private static final String COUNT_NBR_OF_OFFICIALS = "SELECT COUNT(*) FROM `official`;";
    
    /* Constructeur */
    public OfficialDAOImpl(String nameUser) throws SQLException {   
        m_nameUser = nameUser;
        
        if ((m_nameUser.equals("quentin")) || (m_nameUser.equals("Quentin")))
            Connection = DriverManager.getConnection(URL_QUENTIN,USER_QUENTIN,PASSWORD_QUENTIN);
        
        else if ((m_nameUser.equals("charles")) || (m_nameUser.equals("Charles")))
            Connection = DriverManager.getConnection(URL_CHARLES,USER_CHARLES,PASSWORD_CHARLES);
        
        else if ((m_nameUser.equals("clement")) || (m_nameUser.equals("Clement")))
            Connection = DriverManager.getConnection(URL_CLEMENT,USER_CLEMENT,PASSWORD_CLEMENT);
        
        statement = Connection.createStatement();
    }
    
    public void createTableOfficial() throws SQLException {   
        statement.executeUpdate(CREATION_TABLE_OFFICIAL);
        statement.executeUpdate("ALTER TABLE `official` AUTO_INCREMENT = " +FIRST_ID_OFFICIAL +";");
        System.out.println(CREATION_TABLE_OFFICIAL);
    }
    
    public void dropTableOfficial() throws SQLException {
        statement.executeUpdate(DROP_TABLE_OFFICIAL);
        System.out.println(DROP_TABLE_OFFICIAL);
    }
    
    
    /* Méthodes de requêtes */
    public int getNumberOfOfficialsIntoTable() throws SQLException {
        int number_of_officials;
        ResultSet resultLecture = statement.executeQuery(COUNT_NBR_OF_OFFICIALS);
        resultLecture.next();
        number_of_officials = resultLecture.getInt(1);
        System.out.println("number officials : " +number_of_officials);
        return number_of_officials;
    }
    
    public String getLastNameOfficialIntoTable(int num_case) throws SQLException {
        
        ResultSet resultLecture = statement.executeQuery("SELECT `lastname` FROM `official` WHERE id = " +num_case + ";");
        resultLecture.next();
        System.out.println("last name : " +resultLecture.getString(1));
        return resultLecture.getString(1);
    }
    
    public String getFirstNameOfficialIntoTable(int num_case) throws SQLException {
        
        ResultSet resultLecture = statement.executeQuery("SELECT `firstname` FROM `official` WHERE id = " +num_case + ";");
        resultLecture.next();
        System.out.println("first name : " +resultLecture.getString(1));
        return resultLecture.getString(1);
    }
    
    public String getPasswordOfficialIntoTable(int num_case) throws SQLException {
        
        ResultSet resultLecture = statement.executeQuery("SELECT `password` FROM `official` WHERE id = " +num_case + ";");
        resultLecture.next();
        System.out.println("password : " +resultLecture.getString(1));
        return resultLecture.getString(1);
    }
    
}
