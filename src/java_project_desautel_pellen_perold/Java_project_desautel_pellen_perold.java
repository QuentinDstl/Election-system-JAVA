package java_project_desautel_pellen_perold;

import dao_package.*;
import java.sql.SQLException;

import java.util.Scanner;

public class Java_project_desautel_pellen_perold {
    
    public static void main(String[] args) throws SQLException
    {
        
        Candidate candidate = new Candidate(1);
        
        /*ElectorDAOImpl yo = new ElectorDAOImpl(CandidateDAO.CONNECTION_NAME);
        
        CandidateDAOImpl hey = new CandidateDAOImpl(CandidateDAO.CONNECTION_NAME);
        
        hey.dropTableCandidate();
        hey.createTableCandidate();
        hey.addCandidate("Trump", "Donald", "Looser", "Democrate");*/
      
        //yo.createTableElector();
        //yo.addCandidate("Dého", "qd", "puceauLand");
    }
}
