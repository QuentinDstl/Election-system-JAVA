package dao_package;

import config_package.Config;
import config_package.Log;
import java.sql.*;

public class CandidateDAOImpl implements DAO
{
    /* Variables */
    private final Connection m_connection;
    private final Statement m_statement;    
    
    /* Constantes */
    private static final String CREATION_TABLE_CANDIDATE = "CREATE TABLE IF NOT EXISTS `candidate`"
                                                        + "("
                                                        + " `lastname` VARCHAR(20) NOT NULL, "
                                                        + " `firstname` VARCHAR(20) NOT NULL, "
                                                        + " `password` VARCHAR(20) NOT NULL, "
                                                        + " `party` VARCHAR(20) NOT NULL, "
                                                        + " `nbrVoteTotal` INT DEFAULT 0, "
                                                        + " `id` INT(6) NOT NULL AUTO_INCREMENT, "
                                                        + " PRIMARY KEY(`id`), "
                                                        + " UNIQUE(`party`), "
                                                        + " CONSTRAINT `unique_person` UNIQUE (`lastname`, `firstname`, `password`) "
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
    @Override
    public void createTable() throws SQLException {   
        m_statement.executeUpdate(CREATION_TABLE_CANDIDATE);
        Log.add(CREATION_TABLE_CANDIDATE);
    }
    
    @Override
    public void dropTable() throws SQLException {
        m_statement.executeUpdate(DROP_TABLE_CANDIDATE);
        Log.add(DROP_TABLE_CANDIDATE);
    }    
    
    /**
     * @param args
     * String lastName, String firstName, String password, String party, String (int) nbrVoteTotal
     * @throws java.sql.SQLException
    */
    @Override
    public void addToTable(String... args) throws SQLException, IllegalArgumentException {
        String query = ADD_CANDIDATE + "(`lastname`, `firstname`, `password`, `party`, `nbrVoteTotal`)" + "Values (" ;
        for(int i= 0; i<args.length-1; i++){
            query += "'" + args[i] + "', ";
        }
        query +=  "'" + Integer.parseInt(args[4]) + "');";
        m_statement.executeUpdate(query);
        //Log.add(ADD_CANDIDATE);
    }
    
    public void deleteCandidate(int id) throws SQLException {
        m_statement.executeUpdate(DELETE_CANDIDATE 
                                + " WHERE `id` = " +id + ";");
        Log.add(DELETE_CANDIDATE);
        decrementeIdCandidates(id);
    }
    
    public void decrementeIdCandidates(int id) throws SQLException {
        m_statement.executeUpdate(DECREMENT_ID_CANDIDATE + " WHERE id > " +id + ";");
    } 
    
    public void incrementeNbrVotesTotal(String name) throws SQLException {
        m_statement.executeUpdate("UPDATE `candidate` SET `nbrVoteTotal` = `nbrVoteTotal` + 1"
                                + " WHERE `lastname` = '" + name + "';");
    }
    
    
    /* Méthodes de requêtes */
    public int getNumberOfCandidatesIntoTable() throws SQLException {
        int number_of_candidates;
        ResultSet resultLecture = m_statement.executeQuery(COUNT_NBR_OF_CANDIDATES);
        resultLecture.next();
        number_of_candidates = resultLecture.getInt(1);
        return number_of_candidates;
    }
    
    public String getLastNameCandidateIntoTable(int num_case) throws SQLException {
        
        ResultSet resultLecture = m_statement.executeQuery("SELECT `lastname` FROM `candidate` WHERE id = " +num_case + ";");
        resultLecture.next();
        return resultLecture.getString(1);
    }
    
    public String getFirstNameCandidateIntoTable(int num_case) throws SQLException {
        
        ResultSet resultLecture = m_statement.executeQuery("SELECT `firstname` FROM `candidate` WHERE id = " +num_case + ";");
        resultLecture.next();
        return resultLecture.getString(1);
    }
    
    public String getPasswordCandidateIntoTable(int num_case) throws SQLException {
        
        ResultSet resultLecture = m_statement.executeQuery("SELECT `password` FROM `candidate` WHERE id = " +num_case + ";");
        resultLecture.next();
        return resultLecture.getString(1);
    }
    
    public String getPartyCandidateIntoTable(int num_case) throws SQLException {
        
        ResultSet resultLecture = m_statement.executeQuery("SELECT `party` FROM `candidate` WHERE id = " +num_case + ";");
        resultLecture.next();
        return resultLecture.getString(1);
    }
    
    public int getNbrVoteTotalCandidateIntoTable(int num_case) throws SQLException {
        
        ResultSet resultLecture = m_statement.executeQuery("SELECT `nbrVoteTotal` FROM `candidate` WHERE id = " +num_case + ";");
        resultLecture.next();
        return resultLecture.getInt(1);
    }
    
    /* Méthodes de vérification des données de l'utilisateur */
    public boolean checkUserCandidateName(String last_name, String first_name) throws SQLException {
        return (getIdUserWIthConstrainLastNameFirstName(last_name, first_name) !=  NOT_IN_TABLE);
    }
    
    public boolean checkUserCandidatePassword(String last_name, String first_name, String password) throws SQLException {
        return (getIdUserWithConstraintUniquePerson(last_name, first_name, password)!= NOT_IN_TABLE);
    }    
            
    public int getIdUserWithConstraintUniquePerson(String last_name, String first_name, String password) throws  SQLException {
        
        ResultSet resultLecture = m_statement.executeQuery("SELECT `id` FROM `candidate` WHERE `lastname` = '" +last_name 
                                                                                       + "' AND `firstname` = '" +first_name
                                                                                       + "' AND `password` = '" + password + "';");
        if(resultLecture.next() == false)
            return NOT_IN_TABLE;
        return resultLecture.getInt(1);
    }
    
    public int getIdUserWIthConstrainLastNameFirstName(String last_name, String first_name) throws SQLException{
        ResultSet resultLecture = m_statement.executeQuery("SELECT `id` FROM `candidate` WHERE `lastname` = '" +last_name 
                                                                                       + "' AND `firstname` = '" +first_name+"';");
        if(resultLecture.next() == false)
            return NOT_IN_TABLE;
        return resultLecture.getInt(1);
    }
}
