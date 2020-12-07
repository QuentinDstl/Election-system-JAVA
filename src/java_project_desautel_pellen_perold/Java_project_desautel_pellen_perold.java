package java_project_desautel_pellen_perold;

import config_package.Log;
import static config_package.Start.start;
import view_package.*;
import java.sql.SQLException;
import java.util.Scanner;

public class Java_project_desautel_pellen_perold {
    
    public static void main(String[] args) throws SQLException
    {
        initialization();

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
                access_to_election = null;
                access_to_election = new Election();
                THEController = new Controller(access_to_election);
                THEController.startGraphiqueAccueil();
                resetOut =0;
            }
        } while (resetOut == 0);
        System.exit(0);
    }
    
    public static void initialization() {

        /* We init the logs.txt file in the project folder */
        Log log = new Log();
        
        System.out.println("Do you want to charge a different xlsx file ? (yes or no)");
        Scanner scanner = new Scanner(System.in);
        
        /* start("name_config") -> to load with your config but chose the file to open */
        /* start("name_config","file_path") -> to load with yout config and the file */
        /* start() -> the program will ask you everything and save it in the config file */
        /* if the config file have already saved your data it will not ask you */
        
        if(scanner.nextLine().equals("yes"))
            start("default");
        else
            start();
    }
}
