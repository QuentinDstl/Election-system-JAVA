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
        Config.initConfig("charles");
      
        //yo.createTableElector();
        //yo.addCandidate("Dého", "qd", "puceauLand");
        
        
        
        
        
        /* PARTIE GRAPHIQUE EN TEST */
        Controller THEController = new Controller();
        
        //THEController.startGraphiqueElectors();
        //THEController.startGraphiqueCandidats();
        THEController.startGraphiqueOfficials();
        //THEController.startGraphiqueAccueil();
        int resetOut = 0;
        
        do 
        {            
            System.out.print("");
            resetOut = THEController.getReset();
            if (resetOut == 1)
            {
                THEController = new Controller();
                THEController.startGraphiqueAccueil();
                resetOut =0;
            }
        } while (resetOut == 0);

        
        System.exit(0);
    }
    
    
}
