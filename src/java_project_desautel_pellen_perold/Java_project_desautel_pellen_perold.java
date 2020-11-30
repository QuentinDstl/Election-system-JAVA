package java_project_desautel_pellen_perold;

import dao_package.*;
import config_package.*;
import view_package.*;

import java.sql.SQLException;

public class Java_project_desautel_pellen_perold {
    
    public  static void main(String[] args) throws SQLException
    {
       // Candidate candidate = new Candidate(1);

        Log log = new Log();
        Config.initConfig("quentin");
      
        //yo.createTableElector();
        //yo.addCandidate("Dého", "qd", "puceauLand");
        
        
        /* PARTIE GRAPHIQUE EN TEST */
        
        //testGraphiqueAccueil();
        //testGraphiqueElectors();
        testGraphiqueCandidats();
        
        System.exit(0);
        
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
        int checkCandidatesOut = 0;
        int checkCandidatesNationalOut = 0;
        
        while (checkCandidatesOut != -1) 
        {   
            checkCandidatesOut = test3.getCheckCandidates();
            System.out.print("");
            
            if(checkCandidatesOut == 1)
            {
                //System.out.println("HEY NIV1" + " Candidat : " + checkCandidatesOut);
                GraphicCandidatesNational test5 = new GraphicCandidatesNational();
                test5.startCandidatesNational();
                
                while(checkCandidatesNationalOut != -1)
                {
                    checkCandidatesNationalOut = test5.getCheckCandidatesNational();
                    System.out.print("");
                    
                    /*if(checkCandidatesNationalOut == -1)
                    {
                        System.out.println("HEY NIV2" + " Candidat : " + checkCandidatesOut);
                    }*/
                }
                test3 = new GraphicCandidates();
                test3.startCandidates();
                checkCandidatesOut = 0;
                checkCandidatesNationalOut = 0;
            }
        }
        System.out.println("SORTI");
    }
}
