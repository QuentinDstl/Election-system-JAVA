package java_project_desautel_pellen_perold;

import dao_package.*;
import java.sql.SQLException;

import java.util.Scanner;

public class Java_project_desautel_pellen_perold {
    
    public static void main(String[] args) throws SQLException
    {
        
        ElectorDAOImpl yo = new ElectorDAOImpl("Clement");
        
        CandidateDAOImpl hey = new CandidateDAOImpl("Clement");
        
        hey.dropTableCandidate();
        hey.createTableCandidate();
        hey.addCandidate("Trump", "Donald", "Democrate");
      
        yo.createTableElector();
        yo.addCandidate("Dého", "qd", "puceauLand");
    }
}
