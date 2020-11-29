package dao_package;

import config_package.Config;
import java.sql.*;

public class CandidateDAOImpl implements CandidateDAO
{
    /* Variables */
    private Connection m_connection;
    private Statement m_statement;    
    
    /* Constantes */
    private static final String CREATION_TABLE_CANDIDATE = "CREATE TABLE IF NOT EXISTS `candidate`"
                                                        + "("
                                                        + " `lastname` VARCHAR(20) NOT NULL, "
                                                        + " `firstname` VARCHAR(20) NOT NULL, "
                                                        + " `password` VARCHAR(10) NOT NULL, "
                                                        + " `party` VARCHAR(20) NOT NULL, "
                                                        + " `nbrVoteTotal` INT DEFAULT 0, "
                                                        + " `id` INT(6) NOT NULL AUTO_INCREMENT, "
                                                        + " PRIMARY KEY(`id`), "
                                                        + " UNIQUE(`party`) "
                                                        + ") "
                                                        + "ENGINE = InnoDB " 
                                                        + "CHARACTER SET utf8mb4 " 
                                                        + "COLLATE utf8mb4_unicode_ci;";
    
    private static final String DROP_TABLE_CANDIDATE = "DROP TABLE IF EXISTS `candidate`;";
    
    private static final String ADD_CANDIDATE = "INSERT INTO `candidate`";
    
    private static final String DELETE_CANDIDATE = "DELETE FROM `candidate`";
    
    private static final String DECREMENT_ID_CANDIDATE = "UPDATE `candidate` SET id=id-1";
    
    private static final String COUNT_NBR_OF_CANDIDATES = "SELECT COUNT(*) FROM `candidate`;";
    
    
    /* Constructor */
    public CandidateDAOImpl() throws SQLException {   
        m_connection = DriverManager.getConnection(Config.getUrl(),Config.getLogin(),Config.getPassword());            
        m_statement = m_connection.createStatement();
    }
    
    
    /* Méthodes de modification des tables */
    public void createTableCandidate() throws SQLException {   
        m_statement.executeUpdate(CREATION_TABLE_CANDIDATE);
        System.out.println(CREATION_TABLE_CANDIDATE);
    }
    
    public void dropTableCandidate() throws SQLException {
        m_statement.executeUpdate(DROP_TABLE_CANDIDATE);
        System.out.println(DROP_TABLE_CANDIDATE);
    }
    
    public void addCandidate(String last_name, String first_name, String password, String party) throws SQLException {
        m_statement.executeUpdate(ADD_CANDIDATE 
                                + "(`lastname`, `firstname`, `password`, `party`)"
                                + "Values (" 
                                + "'" +last_name + "', "
                                + "'" +first_name  + "', "
                                + "'" +password + "', "
                                + "'" +party + "'"
                                + ");");
        System.out.println(ADD_CANDIDATE);
    }
    
    public void deleteCandidate(int id) throws SQLException {
        m_statement.executeUpdate(DELETE_CANDIDATE 
                                + "WHERE `id` = " +id + ";");
        System.out.println(DELETE_CANDIDATE);
        decrementeIdCandidates(id);
    }
    
    public void decrementeIdCandidates(int id) throws SQLException {
        m_statement.executeUpdate(DECREMENT_ID_CANDIDATE + "WHERE id > " +id + ";");
    } 
    
    
    /* Méthodes de requêtes */
    public int getNumberOfCandidatesIntoTable() throws SQLException {
        int number_of_candidates;
        ResultSet resultLecture = m_statement.executeQuery(COUNT_NBR_OF_CANDIDATES);
        resultLecture.next();
        number_of_candidates = resultLecture.getInt(1);
        System.out.println("number candidates : " +number_of_candidates);
        return number_of_candidates;
    }
    
    public String getLastNameCandidateIntoTable(int num_case) throws SQLException {
        
        ResultSet resultLecture = m_statement.executeQuery("SELECT `lastname` FROM `candidate` WHERE id = " +num_case + ";");
        resultLecture.next();
        System.out.println("last name : " +resultLecture.getString(1));
        return resultLecture.getString(1);
    }
    
    public String getFirstNameCandidateIntoTable(int num_case) throws SQLException {
        
        ResultSet resultLecture = m_statement.executeQuery("SELECT `firstname` FROM `candidate` WHERE id = " +num_case + ";");
        resultLecture.next();
        System.out.println("first name : " +resultLecture.getString(1));
        return resultLecture.getString(1);
    }
    
    public String getPasswordCandidateIntoTable(int num_case) throws SQLException {
        
        ResultSet resultLecture = m_statement.executeQuery("SELECT `password` FROM `candidate` WHERE id = " +num_case + ";");
        resultLecture.next();
        System.out.println("password : " +resultLecture.getString(1));
        return resultLecture.getString(1);
    }
    
    public String getPartyCandidateIntoTable(int num_case) throws SQLException {
        
        ResultSet resultLecture = m_statement.executeQuery("SELECT `party` FROM `candidate` WHERE id = " +num_case + ";");
        resultLecture.next();
        System.out.println("party : " +resultLecture.getString(1));
        return resultLecture.getString(1);
    }
    
    public int getNbrVoteTotalCandidateIntoTable(int num_case) throws SQLException {
        
        ResultSet resultLecture = m_statement.executeQuery("SELECT `nbrVoteTotal` FROM `candidate` WHERE id = " +num_case + ";");
        resultLecture.next();
        System.out.println("nbr vote total : " +resultLecture.getInt(1));
        return resultLecture.getInt(1);
    }
}
