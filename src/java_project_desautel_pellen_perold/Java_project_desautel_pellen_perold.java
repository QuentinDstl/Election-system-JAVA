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
        
        THEController.startGraphiqueAccueil();
        int resetOut;
        
        do 
        {
            System.out.print("");
            resetOut = THEController.getReset();
            if (resetOut == 1)
            {
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
        String config;
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Do you want to change the config ? (yes or no)");
        if(scanner.nextLine().equals("no"))
            /* start("name_config") -> to load with your config but chose the file to open */
            /* start("name_config","file_path") -> to load with yout config and the file */
            /* start() -> the program will ask you everything and save it in the config file */
            /* if the config file have already saved your data it will not ask you */
            start();
        else {
            System.out.println("\n(write the name of your config and if you don't have one write: new)");
            config = scanner.nextLine();
            if(config.equals("new"))
                start("");
            else
                start(config);
        }
    }
}
