package dao_package;

import config_package.Config;
import java.sql.*;

public class ElectorDAOImpl implements  DAO {
    
    /* Variables */
    private final Connection m_connection;
    private final Statement m_statement;

    /* Constants */
    private static final String CREATION_TABLE_ELECTOR = "CREATE TABLE IF NOT EXISTS `elector`"
                                                        + "("
                                                        + " `lastname` VARCHAR(20) NOT NULL, "
                                                        + " `firstname` VARCHAR(20) NOT NULL, "
                                                        + " `password` VARCHAR(10) NOT NULL, "
                                                        + " `nameState` VARCHAR(20) NOT NULL, "
                                                        + " `nameCandidate` VARCHAR(20), "
                                                        + " `vote` BOOLEAN DEFAULT 0, "
                                                        + " `id` INT(6) NOT NULL AUTO_INCREMENT, "
                                                        + " PRIMARY KEY(`id`), "
                                                        + " CONSTRAINT `unique_person` UNIQUE (`lastname`, `firstname`, `password`) "
                                                        + ") "
                                                        + "ENGINE = InnoDB " 
                                                        + "CHARACTER SET utf8mb4 " 
                                                        + "COLLATE utf8mb4_unicode_ci;";

    private static final String DROP_TABLE_ELECTOR = "DROP TABLE IF EXISTS `elector`;";

    private static final String ADD_ELECTOR = "INSERT INTO `elector`";

    private static final String DELETE_ELECTOR = "DELETE FROM `elector`";
    
    private static final String DECREMENT_ID_ELECTOR = "UPDATE `elector` SET id=id-1";
    
    private static final String COUNT_NBR_OF_ELECTORS = "SELECT COUNT(*) FROM `elector`;";
    
    

    /* Constructor */
    public ElectorDAOImpl() throws SQLException {
        m_connection = DriverManager.getConnection(Config.getUrl(),Config.getLogin(),Config.getPassword());
        m_statement = m_connection.createStatement();
    }

    @Override
    public void createTable() throws SQLException {   
        m_statement.executeUpdate(CREATION_TABLE_ELECTOR);
        m_statement.executeUpdate("ALTER TABLE `elector` AUTO_INCREMENT = " +FIRST_ID_ELECTOR +";");
        System.out.println(CREATION_TABLE_ELECTOR);
    }
    
    @Override
    public void dropTable() throws SQLException {
        m_statement.executeUpdate(DROP_TABLE_ELECTOR);
        System.out.println(DROP_TABLE_ELECTOR);
    }
    
    
    /**
     * @param args
     * String lastName, String firstName, String password, String nameState, String nameCandidate, String (boolean) vote
     * @throws java.sql.SQLException
    */
    @Override
    public void addToTable(String... args) throws SQLException {       
        String query = ADD_ELECTOR + "(`lastname`, `firstname`, `password`, `nameState`, `nameCandidate`, `vote`)" + "Values (" ;
        for(int i=0; i<args.length-1; i++){
            query += "'" + args[i] + "', ";
        }
        query += "'" + Integer.parseInt(args[5]) + "');";
        m_statement.executeUpdate(query);
        System.out.println(ADD_ELECTOR);
    }
    
    public void deleteElector(int id) throws SQLException {
        m_statement.executeUpdate(DELETE_ELECTOR 
                                + "WHERE `id` = " +id + ";");
        System.out.println(DELETE_ELECTOR);
        decrementeIdElectors(id);
    }
    
    public void decrementeIdElectors(int id) throws SQLException {
        m_statement.executeUpdate(DECREMENT_ID_ELECTOR + "WHERE id > " +id + ";");
    }
    
    
    /* Méthodes de requêtes */
    public int getNumberOfElectorsIntoTable() throws SQLException {
        int number_of_electors;
        ResultSet resultLecture = m_statement.executeQuery(COUNT_NBR_OF_ELECTORS);
        resultLecture.next();
        number_of_electors = resultLecture.getInt(1);
        System.out.println("number electors : " +number_of_electors);
        return number_of_electors;
    }
    
    public String getLastNameElectorIntoTable(int num_case) throws SQLException {
        
        ResultSet resultLecture = m_statement.executeQuery("SELECT `lastname` FROM `elector` WHERE id = " +num_case + ";");
        resultLecture.next();
        System.out.println("last name : " +resultLecture.getString(1));
        return resultLecture.getString(1);
    }
    
    public String getFirstNameElectorIntoTable(int num_case) throws SQLException {
        
        ResultSet resultLecture = m_statement.executeQuery("SELECT `firstname` FROM `elector` WHERE id = " +num_case + ";");
        resultLecture.next();
        System.out.println("first name : " +resultLecture.getString(1));
        return resultLecture.getString(1);
    }
    
    public String getPasswordElectorIntoTable(int num_case) throws SQLException {
        
        ResultSet resultLecture = m_statement.executeQuery("SELECT `password` FROM `elector` WHERE id = " +num_case + ";");
        resultLecture.next();
        System.out.println("password : " +resultLecture.getString(1));
        return resultLecture.getString(1);
    }
    
    public String getNameStateOfElectorIntoTable(int num_case) throws SQLException {
        
        ResultSet resultLecture = m_statement.executeQuery("SELECT `nameState` FROM `elector` WHERE id = " +num_case + ";");
        resultLecture.next();
        System.out.println("name state : " +resultLecture.getString(1));
        return resultLecture.getString(1);
    }
    
    public String getNameCandidateOfElectorIntoTable(int num_case) throws SQLException {
        
        ResultSet resultLecture = m_statement.executeQuery("SELECT `nameCandidate` FROM `elector` WHERE id = " +num_case + ";");
        resultLecture.next();
        System.out.println("name candidate : " +resultLecture.getString(1));
        return resultLecture.getString(1);
    }
    
    
    /* Méthodes de vérification des données de l'utilisateur */
    public boolean checkUserElectorName(String last_name, String first_name) throws SQLException {
        return (getIdUserWithLastName(last_name) == getIdUserWithFirstName(first_name));
    }
    
    public boolean checkUserElectorPassword(String last_name, String first_name, String password) throws SQLException {
        return (getIdUserWithPassword(password) == getIdUserWithLastName(last_name) &&
                getIdUserWithPassword(password) == getIdUserWithFirstName(first_name));
    }
            
            
    public int getIdUserWithConstraintUniquePerson(String last_name, String first_name, String password) throws  SQLException {
        
        ResultSet resultLecture = m_statement.executeQuery("SELECT `id` FROM `elector` WHERE lastname = " +last_name 
                                                                                       + " AND firstname = " +first_name
                                                                                       + ",AND password = " +password + ";");
        resultLecture.next();
        System.out.println("id : " +resultLecture.getInt(1));
        return resultLecture.getInt(1);
    }
    
    public int getIdUserWithLastName(String last_name) throws SQLException {
        
        ResultSet resultLecture = m_statement.executeQuery("SELECT `id` FROM `elector` WHERE lastname = " +last_name + ";");
        resultLecture.next();
        System.out.println("id : " +resultLecture.getInt(1));
        return resultLecture.getInt(1);
    }
    
    public int getIdUserWithFirstName(String first_name) throws SQLException {
        
        ResultSet resultLecture = m_statement.executeQuery("SELECT `id` FROM `elector` WHERE firstname = " +first_name + ";");
        resultLecture.next();
        System.out.println("id : " +resultLecture.getInt(1));
        return resultLecture.getInt(1);
    }
    
    public int getIdUserWithPassword(String password) throws SQLException {
        
        ResultSet resultLecture = m_statement.executeQuery("SELECT `id` FROM `elector` WHERE password = " +password + ";");
        resultLecture.next();
        System.out.println("id : " +resultLecture.getInt(1));
        return resultLecture.getInt(1);
    }
}
