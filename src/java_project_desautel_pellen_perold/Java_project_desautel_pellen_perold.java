package java_project_desautel_pellen_perold;

import dao_package.*;
import view_package.*;


import java.sql.SQLException;
import java.util.Scanner;

public class Java_project_desautel_pellen_perold {
    
    public  static void main(String[] args) throws SQLException
    {
        
       // Candidate candidate = new Candidate(1);
        
        /*ElectorDAOImpl yo = new ElectorDAOImpl(CandidateDAO.CONNECTION_NAME);
        
        CandidateDAOImpl hey = new CandidateDAOImpl(CandidateDAO.CONNECTION_NAME);
        
        hey.dropTableCandidate();
        hey.createTableCandidate();
        hey.addCandidate("Trump", "Donald", "Looser", "Democrate");*/
      
        //yo.createTableElector();
        //yo.addCandidate("Dého", "qd", "puceauLand");
        
        
        /* PARTIE GRAPHIQUE EN TEST */
        
        //testGraphiqueAccueil();
        testGraphiqueElectors();
        
    }
    
    public static void testGraphiqueAccueil()
    {
        GraphicIdentification test1 = new GraphicIdentification();
        test1.startIdentification();
        boolean checkIdentification = false;
        
        /* Blindage */ 
        while (checkIdentification == false) {            
            checkIdentification = test1.getCheck();
            System.out.print("");
        } 
        
        System.out.println("hey");
    }
    
    public static void testGraphiqueElectors()
    {
        GraphicElectors test2 = new GraphicElectors();
        test2.startElectors();
        
        boolean checkElectors = false;
    }
    
    public static void testGraphiqueCandidats()
    {
        GraphicCandidates test3 = new GraphicCandidates();
        test3.startCandidates();
        
        boolean checkCandidates = false;
    }
}
