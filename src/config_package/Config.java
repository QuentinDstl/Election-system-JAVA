package config_package;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;

public class Config implements ConfigInterface {

    private String m_url;
    private String m_login;
    private String m_password;
    private String m_database;
    
    /* Constructor */
    public Config(String owner) {
        NewProperties properties = new NewProperties(new Properties());
        try(InputStream inputStream = new FileInputStream(FILE_NAME)) {
            properties.load(inputStream);
            
        } 
        catch (FileNotFoundException e) {
            Log.add(e.getMessage());
            SecureLoad();                                                       // if we cant load normaly we do it the easy and secure way
        }
        catch (IOException | IllegalArgumentException ioe) {
            Log.add(ioe.getMessage());
            SecureLoad();                                                       // if we cant load normaly we do it the easy and secure way
        }
        try {
            m_url = properties.getProperty(owner + URL);
            m_login = properties.getProperty(owner + LOGIN);
            m_database = properties.getProperty(owner + DATABASE);
            m_password = properties.getProperty(owner + PASSWORD);
        }
        catch (IllegalArgumentException e) {
            Log.add(e.getMessage());
            SecureLoad(); 
        }
    }
    
    private void SecureLoad() {
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

    public String getUrl() {
        return m_url;
    }

    public String getLogin() {
        return m_login;
    }

    public String getPassword() {
        return m_password;
    }

    public String getDatabase() {
        return m_database;
    }
}
