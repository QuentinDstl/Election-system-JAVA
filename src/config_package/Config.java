package config_package;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;

public class Config implements ConfigInterface {

    private static String m_url;
    private static String m_login;
    private static String m_password;
    private static String m_database;
    
    /* Constructor */
    public static void initConfig(String owner) {
        NewProperties properties = new NewProperties(new Properties());
        try {
            InputStream inputStream = new FileInputStream(FILE_NAME);
            properties.load(inputStream);
            
        } 
        catch (FileNotFoundException e) {
            Log.add(e.getMessage() + ": fileNotFoundExeception");
            SecureLoad();                                                       // if we cant load normaly we do it the easy and secure way
        }
        catch (IOException | IllegalArgumentException ioe) {
            Log.add(ioe.getMessage() + ": IOException | IllegalArgumentException");
            SecureLoad();                                                       // if we cant load normaly we do it the easy and secure way
        }
        
        String errorMessage = "";
        try {
            m_url = properties.getProperty(owner + URL);
            errorMessage += "url load -> ";
            m_login = properties.getProperty(owner + LOGIN);
            errorMessage += "login load -> ";
            m_database = properties.getProperty(owner + DATABASE);
            errorMessage += "database load -> ";
            m_password = properties.getProperty(owner + PASSWORD);
            errorMessage += "password load -> ";
        }
        catch (IllegalArgumentException e) {
            Log.add(e.getMessage() + ": IllegalArgumentException :");
            Log.add(errorMessage + "then throw exception");
            SecureLoad(); 
        }
    }
    
    /* If on of the config value do not load properly we do a secure load for all data */
    private static void SecureLoad() {
        System.out.println("config_package.Config.SecureLoad()");
        Scanner scanner = new Scanner(System.in);
        System.out.println("\tURL=");
        m_url = scanner.nextLine();
        System.out.println("\tLOGIN=");
        m_login = scanner.nextLine();
        System.out.println("\tDATABASE=");
        m_database = scanner.nextLine();
        System.out.println("\tPASSWORD=");
        m_password = scanner.nextLine();
    }

    public static String getUrl() {
        return m_url;
    }

    public static String getLogin() {
        return m_login;
    }

    public static String getPassword() {
        return m_password;
    }

    public static String getDatabase() {
        return m_database;
    }
}
