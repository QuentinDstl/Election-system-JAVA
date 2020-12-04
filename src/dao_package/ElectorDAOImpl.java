package dao_package;

import config_package.Config;
import java.sql.*;

public class ElectorDAOImpl implements  DAO {
    
    /* Variables */
    private final Connection m_connection;
    private final Statement m_statement;
    private ResultSet m_resultLecture;
    private int m_previous_num_case;

    /* Constants */
    private static final String CREATION_TABLE_ELECTOR = "CREATE TABLE IF NOT EXISTS `elector`"
                                                        + "("
                                                        + " `lastname` VARCHAR(20) NOT NULL, "
                                                        + " `firstname` VARCHAR(20) NOT NULL, "
                                                        + " `password` VARCHAR(20) NOT NULL, "
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
    
    private static final String DECREMENT_ID_ELECTOR = "UPDATE `elector` SET `id`=`id`-1";
    
    private static final String COUNT_NBR_OF_ELECTORS = "SELECT COUNT(*) FROM `elector`;";
    
    

    /* Constructor */
    public ElectorDAOImpl() throws SQLException {
        m_connection = DriverManager.getConnection(Config.getUrl(),Config.getLogin(),Config.getPassword());
        m_statement = m_connection.createStatement();
        m_resultLecture = null;
        m_previous_num_case = 0;
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
        //System.out.println(ADD_ELECTOR);
    }
    
    public void deleteElector(int id) throws SQLException {
        m_statement.executeUpdate(DELETE_ELECTOR + " WHERE `id` = " +id + ";");
        System.out.println(DELETE_ELECTOR);
        decrementeIdElectors(id);
        m_connection.close();
    }
    
    public void decrementeIdElectors(int id) throws SQLException {
        m_statement.executeUpdate(DECREMENT_ID_ELECTOR + " WHERE `id` > " +id + ";");
    }
    
    /*public void saveVoteElector(int id, String name_candidate) throws SQLException {
        m_statement.executeUpdate("UPDATE `elector` SET `nameCandidate` = '" + name_candidate
                                + "', ")
    }*/
    
    
    /* Méthodes de requêtes */
    public int getNumberOfElectorsIntoTable() throws SQLException {
        int number_of_electors;
        ResultSet resultLecture = m_statement.executeQuery(COUNT_NBR_OF_ELECTORS);
        resultLecture.next();
        number_of_electors = resultLecture.getInt(1);
        System.out.println("number electors : " +number_of_electors);
        return number_of_electors;
    }
    
    private ResultSet getInformationsElectorIntoTable(int num_case) throws SQLException {
        
        return m_statement.executeQuery("SELECT * FROM `elector` WHERE `id` = " +num_case + ";");
    }
    
    public String getLastNameElectorIntoTable(int num_case) throws SQLException {
        
        if(num_case != m_previous_num_case) 
        {
            m_resultLecture = getInformationsElectorIntoTable(num_case);
            m_resultLecture.next();
            m_previous_num_case = num_case;
        }
        //System.out.println("last name : " +m_resultLecture.getString(1));
        return m_resultLecture.getString(1);
    }
    
    public String getFirstNameElectorIntoTable(int num_case) throws SQLException {
        
        if(num_case != m_previous_num_case) 
        {
            m_resultLecture = getInformationsElectorIntoTable(num_case);
            m_resultLecture.next();
            m_previous_num_case = num_case;
        }  
        //System.out.println("first name : " +m_resultLecture.getString(2));
        return m_resultLecture.getString(2);
    }
    
    public String getPasswordElectorIntoTable(int num_case) throws SQLException {
        
        if(num_case != m_previous_num_case) 
        {
            m_resultLecture = getInformationsElectorIntoTable(num_case);
            m_resultLecture.next();
            m_previous_num_case = num_case;
        }        
        //System.out.println("password : " +m_resultLecture.getString(3));
        return m_resultLecture.getString(3);
    }
    
    public String getNameStateOfElectorIntoTable(int num_case) throws SQLException {
        
        if(num_case != m_previous_num_case) 
        {
            m_resultLecture = getInformationsElectorIntoTable(num_case);
            m_resultLecture.next();
            m_previous_num_case = num_case;
        }        
        //System.out.println("name state : " +m_resultLecture.getString(4));
        return m_resultLecture.getString(4);
    }
    
    public String getNameCandidateOfElectorIntoTable(int num_case) throws SQLException {
        
        if(num_case != m_previous_num_case) 
        {
            m_resultLecture = getInformationsElectorIntoTable(num_case);
            m_resultLecture.next();
            m_previous_num_case = num_case;
        }     
        System.out.println("name candidate : " +m_resultLecture.getString(5));
        return m_resultLecture.getString(5);
    }
    
    public boolean getTestVoteElector(int num_case) throws SQLException {
        
        if(num_case != m_previous_num_case)
        {
            m_resultLecture = getInformationsElectorIntoTable(num_case);
            m_resultLecture.next();
            m_previous_num_case = num_case;
        }
        System.out.println("has voted : " +m_resultLecture.getString(6));
        return m_resultLecture.getBoolean(6);
    }
    
    
    /* Méthodes de vérification des données de l'utilisateur */
    public boolean checkUserElectorName(String last_name, String first_name) throws SQLException {
        return (getIdUserWithLastName(last_name) == getIdUserWithFirstName(first_name) &&
                getIdUserWithLastName(last_name) != NOT_IN_TABLE);
    }
    
    public boolean checkUserElectorPassword(String last_name, String first_name, String password) throws SQLException {
        return (getIdUserWithPassword(password) == getIdUserWithLastName(last_name) &&
                getIdUserWithPassword(password) == getIdUserWithFirstName(first_name) && 
                getIdUserWithPassword(password) != NOT_IN_TABLE);
    }
            
            
    public int getIdUserWithConstraintUniquePerson(String last_name, String first_name, String password) throws  SQLException {
        
        ResultSet resultLecture = m_statement.executeQuery("SELECT `id` FROM `elector` WHERE `lastname` = '" +last_name 
                                                                                       + "' AND `firstname` = '" +first_name
                                                                                       + "' AND `password` = '" +password + "';");
        if(resultLecture.next() == false)
            return NOT_IN_TABLE;
        System.out.println("id : " +resultLecture.getInt(1));
        return resultLecture.getInt(1);
    }
    
    public int getIdUserWithLastName(String last_name) throws SQLException {
        
        ResultSet resultLecture = m_statement.executeQuery("SELECT `id` FROM `elector` WHERE lastname = '" +last_name + "';");
        if(resultLecture.next() == false)
            return NOT_IN_TABLE;
        System.out.println("id : " +resultLecture.getInt(1));
        return resultLecture.getInt(1);
    }
    
    public int getIdUserWithFirstName(String first_name) throws SQLException {
        
        ResultSet resultLecture = m_statement.executeQuery("SELECT `id` FROM `elector` WHERE firstname = '" +first_name + "';");
        if(resultLecture.next() == false)
            return NOT_IN_TABLE;
        System.out.println("id : " +resultLecture.getInt(1));
        return resultLecture.getInt(1);
    }
    
    public int getIdUserWithPassword(String password) throws SQLException {
        
        ResultSet resultLecture = m_statement.executeQuery("SELECT `id` FROM `elector` WHERE password = '" +password + "';");
        if(resultLecture.next() == false)
            return NOT_IN_TABLE;
        System.out.println("id : " +resultLecture.getInt(1));
        return resultLecture.getInt(1);
    }
}
