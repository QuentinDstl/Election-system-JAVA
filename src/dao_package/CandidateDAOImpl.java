package dao_package;

import java.sql.*;

public class CandidateDAOImpl implements CandidateDAO
{
    private static final String CREATION_TABLE_CANDIDATE = "CREATE table candidate (lastname varchar(20),firstname varchar(20), nameState varchar(20), party varchar(20), nbrVoteTotal int);";
    
    private PreparedStatement creationTable;
    
    private Connection Connection;
    private String nameUser;
    
    public CandidateDAOImpl(String nameUserEnter) throws SQLException
    {   
        nameUser = nameUserEnter;
        
        if ((nameUser.equals("quentin")) || (nameUser.equals("Quentin")))
            Connection = DriverManager.getConnection(URL_QUENTIN,USER_QUENTIN,PASSWORD_QUENTIN);
        
        else if ((nameUser.equals("charles")) || (nameUser.equals("Charles")))
            Connection = DriverManager.getConnection(URL_CHARLES,USER_CHARLES,PASSWORD_CHARLES);
        
        else if ((nameUser.equals("clement")) || (nameUser.equals("Clement")))
            Connection = DriverManager.getConnection(URL_CLEMENT,USER_CLEMENT,PASSWORD_CLEMENT);
        
        creationTable = Connection.prepareStatement(CREATION_TABLE_CANDIDATE);
    }
    
    public void createTableCandidate() throws SQLException
    {   
        System.out.println(creationTable);
        creationTable.executeUpdate();
    }
}
