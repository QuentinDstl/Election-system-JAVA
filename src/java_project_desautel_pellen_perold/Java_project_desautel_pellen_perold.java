package java_project_desautel_pellen_perold;

import dao_package.*;
import config_package.*;
import view_package.*;

import java.sql.SQLException;
import static loader_package.Loader.loadXLSX;

public class Java_project_desautel_pellen_perold {
    
    public  static void main(String[] args) throws SQLException
    {
       // Candidate candidate = new Candidate(1);

        Log log = new Log();
        Config.initConfig("clement");
        loadXLSX("src\\loader_package\\big_pre_load.xlsx");

        //yo.createTableElector();
        //yo.addCandidate("Dého", "qd", "puceauLand");
        

        /* PARTIE GRAPHIQUE EN TEST */
        Election access_to_election = new Election();
        
        Controller THEController = new Controller(access_to_election);
        
        //THEController.startGraphiqueElectors();
        //THEController.startGraphiqueCandidats();
        //THEController.startGraphiqueOfficials();
        
        THEController.startGraphiqueAccueil();
        int resetOut = 0;
        
        do 
        {
            System.out.print("");
            resetOut = THEController.getReset();
            if (resetOut == 1)
            {
                THEController = new Controller(access_to_election);
                THEController.startGraphiqueAccueil();
                resetOut =0;
            }
        } while (resetOut == 0);
        System.exit(0);
    }
}
