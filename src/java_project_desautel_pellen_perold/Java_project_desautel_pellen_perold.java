package java_project_desautel_pellen_perold;

//import dao_package.*;
import config_package.Log;
import static config_package.Start.start;
import dao_package.ElectorDAOImpl;
import view_package.*;
import java.sql.SQLException;

public class Java_project_desautel_pellen_perold {
    
    public  static void main(String[] args) throws SQLException
    {
       // Candidate candidate = new Candidate(1);

        Log log = new Log();
        /* start("name_config") -> to load with your config but chose the file to open */
        /* start("name_config","file_path") -> to load with yout config and the file */
        /* start() -> the program will ask you everything and save it in the config file */
        start("clement","src\\loader_package\\big_pre_load.xlsx");              // une fois que vous avez utilise ca vous pouvez juste utiliser start() ce sera sauvegarde

        
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
