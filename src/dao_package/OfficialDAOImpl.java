package dao_package;

import config_package.Config;
import config_package.Log;
import java.sql.*;

public class OfficialDAOImpl implements DAO {
    
    /* Variables */
    private final Connection m_connection;
    private final Statement m_statement;
    
    /* Constants */
    private static final String CREATION_TABLE_OFFICIAL = "CREATE TABLE IF NOT EXISTS `official`"
                                                        + "("
                                                        + " `lastname` VARCHAR(20) NOT NULL, "
                                                        + " `firstname` VARCHAR(20) NOT NULL, "
                                                        + " `password` VARCHAR(20) NOT NULL, "
                                                        + " `id` INT(6) NOT NULL AUTO_INCREMENT, "
                                                        + " PRIMARY KEY(`id`), "
                                                        + " CONSTRAINT `unique_person` UNIQUE (`lastname`, `firstname`, `password`) "
                                                        + ") "
                                                        + "ENGINE = InnoDB " 
                                                        + "CHARACTER SET utf8mb4 " 
                                                        + "COLLATE utf8mb4_unicode_ci;";
    
    private static final String DROP_TABLE_OFFICIAL = "DROP TABLE IF EXISTS `official`;";
    
    private static final String ADD_OFFICIAL = "INSERT INTO `official`";
    
    private static final String COUNT_NBR_OF_OFFICIALS = "SELECT COUNT(*) FROM `official`;";
    
    /* Constructeur */
    public OfficialDAOImpl() throws SQLException {
        m_connection = DriverManager.getConnection(Config.getUrl(),Config.getLogin(),Config.getPassword());
        m_statement = m_connection.createStatement();
    }
    
    @Override
    public void createTable() throws SQLException {   
        m_statement.executeUpdate(CREATION_TABLE_OFFICIAL);
        m_statement.executeUpdate("ALTER TABLE `official` AUTO_INCREMENT = " +FIRST_ID_OFFICIAL +";");
        Log.add(CREATION_TABLE_OFFICIAL);
    }
    
    @Override
    public void dropTable() throws SQLException {
        m_statement.executeUpdate(DROP_TABLE_OFFICIAL);
        Log.add(DROP_TABLE_OFFICIAL);
    }
    
    /**
     * @param args
     * String lastName, String firstName, String password
     * @throws java.sql.SQLException
    */
    @Override
    public void addToTable(String... args) throws SQLException, IllegalArgumentException {      
        String query = ADD_OFFICIAL + "(`lastname`, `firstname`, `password`)" + "Values (" ;
        for(String str : args){
                query += "'" + str + "', ";
        }
        query = query.substring(0, query.length() - 2);
        query += ");";
        m_statement.executeUpdate(query);
       //Log.add(ADD_OFFICIAL);
    }
    
    /* Méthodes de requêtes */
    public int getNumberOfOfficialsIntoTable() throws SQLException {
        int number_of_officials;
        ResultSet resultLecture = m_statement.executeQuery(COUNT_NBR_OF_OFFICIALS);
        resultLecture.next();
        number_of_officials = resultLecture.getInt(1);
        return number_of_officials;
    }
    
    public String getLastNameOfficialIntoTable(int num_case) throws SQLException {
        
        ResultSet resultLecture = m_statement.executeQuery("SELECT `lastname` FROM `official` WHERE id = " +num_case + ";");
        resultLecture.next();
        return resultLecture.getString(1);
    }
    
    public String getFirstNameOfficialIntoTable(int num_case) throws SQLException {
        
        ResultSet resultLecture = m_statement.executeQuery("SELECT `firstname` FROM `official` WHERE id = " +num_case + ";");
        resultLecture.next();
        return resultLecture.getString(1);
    }
    
    public String getPasswordOfficialIntoTable(int num_case) throws SQLException {
        
        ResultSet resultLecture = m_statement.executeQuery("SELECT `password` FROM `official` WHERE id = " +num_case + ";");
        resultLecture.next();
        return resultLecture.getString(1);
    }
    
    
    /* Méthodes de vérification des données de l'utilisateur */
    public boolean checkUserOfficialName(String last_name, String first_name) throws SQLException {
        return (getIdUserWIthConstrainLastNameFirstName(last_name, first_name) != NOT_IN_TABLE);
    }
    
    public boolean checkUserOfficialPassword(String last_name, String first_name, String password) throws SQLException {
        return (getIdUserWithConstraintUniquePerson(last_name, first_name, password)!= NOT_IN_TABLE);
    }
            
            
    public int getIdUserWithConstraintUniquePerson(String last_name, String first_name, String password) throws  SQLException {
        
        ResultSet resultLecture = m_statement.executeQuery("SELECT `id` FROM `official` WHERE `lastname` = '" +last_name 
                                                                                       + "' AND `firstname` = '" +first_name
                                                                                       + "' AND `password` = '" +password + "';");
        if(resultLecture.next() == false)
            return NOT_IN_TABLE;
        return resultLecture.getInt(1);
    }
    
    public int getIdUserWIthConstrainLastNameFirstName(String last_name, String first_name) throws SQLException{
        ResultSet resultLecture = m_statement.executeQuery("SELECT `id` FROM `official` WHERE `lastname` = '" +last_name 
                                                                                       + "' AND `firstname` = '" +first_name+"';");
        if(resultLecture.next() == false)
            return NOT_IN_TABLE;
        return resultLecture.getInt(1);
    }
    
}
