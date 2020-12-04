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
        start("quentin","src\\loader_package\\big_pre_load.xlsx");              // une fois que vous avez utilise ca vous pouvez juste utiliser start() ce sera sauvegarde


        //yo.createTableElector();
        //yo.addCandidate("Dého", "qd", "puceauLand");
        

        /* PARTIE GRAPHIQUE EN TEST */
        Controller THEController = new Controller();
        
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
                THEController = new Controller();
                THEController.startGraphiqueAccueil();
                resetOut =0;
            }
        } while (resetOut == 0);
        System.exit(0);
        
        Election election = new Election();
        election.downLoadDataBaseForElector();
        
        final ElectorDAOImpl access_to_elector_table = new ElectorDAOImpl();
        Elector elector = new Elector(access_to_elector_table.getIdUserWithConstraintUniquePerson("Rayan", "Parrot", "haoh"), election.getCandidates(), access_to_elector_table, election);
        
        elector.Votes(election.getCandidates().get(1));
        System.out.println("\n\n" + elector.getCandidate() + elector.isVoteDone());
    }
}
