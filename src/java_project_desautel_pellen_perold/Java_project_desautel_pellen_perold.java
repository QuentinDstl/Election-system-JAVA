package java_project_desautel_pellen_perold;

import dao_package.*;
import config_package.*;
import java.io.IOException;
import view_package.*;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLStreamException;
import static loader_package.Loader.loadXLSX;

public class Java_project_desautel_pellen_perold {
    
    public  static void main(String[] args) throws SQLException
    {
       // Candidate candidate = new Candidate(1);

        Log log = new Log();
        Config.initConfig("clement");
        loadXLSX("src\\loader_package\\pre_load.xlsx");

        //yo.createTableElector();
        //yo.addCandidate("Dého", "qd", "puceauLand");
        

        /* PARTIE GRAPHIQUE EN TEST */
        Controller THEController = new Controller();
        
        //THEController.startGraphiqueElectors();
        //THEController.startGraphiqueCandidats();
        //THEController.startGraphiqueOfficials();
        //THEController.startGraphiqueAccueil();
        int resetOut = 0;
        
        /*do 
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
        System.exit(0);*/
        
        Election election = new Election();
        election.downLoadCandidatesListFromTable();
        //election.downloadDataBaseForOfficial();
        for(int i=0; i<election.getCandidates().size(); ++i) {
            System.out.println(election.getCandidates().get(i).getLastName()
                             + election.getCandidates().get(i).getFirstName()
                             + election.getCandidates().get(i).getPassword()
                             + election.getCandidates().get(i).getId());
        }
    }
}
