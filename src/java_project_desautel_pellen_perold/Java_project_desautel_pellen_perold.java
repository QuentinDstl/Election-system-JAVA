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
        loadXLSX("src\\loader_package\\big_pre_load.xlsx");

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
        election.downloadDataBaseForOfficial();
        
        System.out.println("\n\n");
        for(int i=0; i<election.getStates().size(); ++i) {
            System.out.println(election.getStates().get(i).getName());
        }
        System.out.println("\n\n");
        for(int i=0; i<election.getCandidates().size(); ++i) {
            System.out.println(election.getCandidates().get(i).getLastName() + " "
                             + election.getCandidates().get(i).getFirstName() + " "
                             + election.getCandidates().get(i).getPassword() + " "
                             + election.getCandidates().get(i).getId());
        }
        System.out.println("\n\n");
        for(int i=0; i<election.getElectors().size(); ++i) {
            System.out.println(election.getElectors().get(i).getLastName() + " "
                             + election.getElectors().get(i).getFirstName() + " "
                             + election.getElectors().get(i).getPassword() + " "
                             + election.getElectors().get(i).getId());
        }
        
        System.out.println("\n\n");
        System.out.println("JE SUIS UN OFFICIAL ET JE M'APPELLE FERCOQ");
        final OfficialDAOImpl access_to_official_table = new OfficialDAOImpl();
        Official official = new Official(access_to_official_table.getIdUserWithConstraintUniquePerson("Fercoq", "Junior", "password"), election);
        
        System.out.println("\n\n");
        System.out.println("J'AJOUTE UN ELECTOR K's TU VAS FAIRE ?");
        official.addElector("Afif", "Lilireza", election.getStates().get(0));
        
        System.out.println("\n\n");
        System.out.println("JE SUPPRIME UN ELECTOR PARCE QU'IL EST MOCHE");
        official.deleteElector(election.getElectors().get(0));
        
        System.out.println("\n\n");
        System.out.println("J'AJOUTE UN CANDIDAT K's TU VAS FAIRE ?");
        official.addCandidate("West", "Kanye", "Isolated_party");
        
        System.out.println("\n\n");
        System.out.println("JE SUPPRIME UN CANDIDAT PARCE QU'IL EST MOCHE");
        official.deleteElector(election.getElectors().get(0));
    }
}
