package dao_package;

import java.sql.*;
import java.util.ArrayList;
import java_project_desautel_pellen_perold.Candidate;

public class CandidateDAOImpl implements CandidateDAO
{
    /* Variables */
    private Connection Connection;
    private Statement statement;
    
    private String m_nameUser;
    
    
    /* Constants */
    private static final String CREATION_TABLE_CANDIDATE = "CREATE TABLE IF NOT EXISTS `candidate`"
                                                        + "("
                                                        + " `lastname` VARCHAR(20) NOT NULL, "
                                                        + " `firstname` VARCHAR(20) NOT NULL, "
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
    
    
    /* Constructor */
    public CandidateDAOImpl(String nameUser) throws SQLException {   
        m_nameUser = nameUser;
        
        if ((m_nameUser.equals("quentin")) || (m_nameUser.equals("Quentin")))
            Connection = DriverManager.getConnection(URL_QUENTIN,USER_QUENTIN,PASSWORD_QUENTIN);
        
        else if ((m_nameUser.equals("charles")) || (m_nameUser.equals("Charles")))
            Connection = DriverManager.getConnection(URL_CHARLES,USER_CHARLES,PASSWORD_CHARLES);
        
        else if ((m_nameUser.equals("clement")) || (m_nameUser.equals("Clement")))
            Connection = DriverManager.getConnection(URL_CLEMENT,USER_CLEMENT,PASSWORD_CLEMENT);
            
        statement = Connection.createStatement();
    }
    
    
    /* Méthodes de modification des tables */
    public void createTableCandidate() throws SQLException {   
        statement.executeUpdate(CREATION_TABLE_CANDIDATE);
        System.out.println(CREATION_TABLE_CANDIDATE);
    }
    
    public void dropTableCandidate() throws SQLException {
        statement.executeUpdate(DROP_TABLE_CANDIDATE);
        System.out.println(DROP_TABLE_CANDIDATE);
    }
    
    public void addCandidate(String last_name, String first_name, String party) throws SQLException {
        statement.executeUpdate(ADD_CANDIDATE 
                                + "(`lastname`, `firstname`, `party`)"
                                + "Values (" 
                                + "'" +last_name + "', "
                                + "'" +first_name  + "', "
                                + "'" +party + "'"
                                + ");");
        System.out.println(ADD_CANDIDATE);
    }
    
    public void deleteCandidate(int id) throws SQLException {
        statement.executeUpdate(DELETE_CANDIDATE 
                                + "WHERE `id` = " +id + ";");
        System.out.println(DELETE_CANDIDATE);
        decrementeIdCandidates(id);
    }
    
    public void decrementeIdCandidates(int id) throws SQLException {
        statement.executeUpdate("UPDATE `candidate` SET id=id-1 WHERE id > " +id + ";");
    } 
    
    
    /* Méthodes de requêtes */
    public int getNumberOfCandidatesIntoTable() throws SQLException {
        int number_of_candidates;
        ResultSet resultLecture = statement.executeQuery("SELECT COUNT(*) FROM `candidate`;");
        resultLecture.next();
        number_of_candidates = resultLecture.getInt(1);
        System.out.println("number candidates : " +number_of_candidates);
        return number_of_candidates;
    }
    
    public String getLastNameCandidateIntoTable(int num_case) throws SQLException {
        
        ResultSet resultLecture = statement.executeQuery("SELECT `lastname` FROM `candidate` WHERE id = " +num_case + ";");
        resultLecture.next();
        System.out.println("last name : " +resultLecture.getString(1));
        return resultLecture.getString(1);
    }
    
    public String getFirstNameCandidateIntoTable(int num_case) throws SQLException {
        
        ResultSet resultLecture = statement.executeQuery("SELECT `firstname` FROM `candidate` WHERE id = " +num_case + ";");
        resultLecture.next();
        System.out.println("first name : " +resultLecture.getString(1));
        return resultLecture.getString(1);
    }
    
    public int getNbrVoteTotalCandidateIntoTable(int num_case) throws SQLException {
        
        ResultSet resultLecture = statement.executeQuery("SELECT `nbrVoteTotal` FROM `candidate` WHERE id = " +num_case + ";");
        resultLecture.next();
        System.out.println("last name : " +resultLecture.getInt(1));
        return resultLecture.getInt(1);
    }
}
