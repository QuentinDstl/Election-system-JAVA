//https://github.com/dita-ot/docs/issues/102
package config_package;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;
import static loader_package.Loader.loadXLSX;
import static loader_package.Loader.loadDataBase;

public class Config implements ConfigInterface {

    private static String m_xlxs;
    private static String m_config;
    
    private static String m_url;
    private static String m_login;
    private static String m_password;

    /**
     * @param args
     * String configName, String xlsxName
    */
    public static void initConfig(String ...args) {
        NewProperties properties = new NewProperties(new Properties());
        m_xlxs = "";
        m_config = "";

        if(args.length>1) {
            m_config = args[0];
            m_xlxs = args[1];
            if(m_xlxs.equals(""))
                m_xlxs = askForXLSX();
            if(m_config.equals("")) {
                m_config = "default";
                
                /* we need to load config file anyway so other properties will not be erase when storing in the FileOutputStream */
                try {
                    InputStream inputStream = new FileInputStream(FILE_NAME);
                    properties.load(inputStream);
                } catch (FileNotFoundException  e) {
                    Log.add(e.getMessage() + ": fileNotFoundExeception -> the previous config data was erase");
                } catch (IOException ioe) {
                    Log.add(ioe.getMessage() + ": IOException -> the previous config data was erase");
                }
                setNewConfig(properties);
            }
            else
                loadConfig(properties);
        }
        else {
            loadInfoConfig(properties);
            if(m_config.equals("") && m_xlxs.equals(""))
            {
                m_xlxs = askForXLSX();
                m_config = "default";
                setNewConfig(properties);
            }
            else
                loadConfig(properties);
        }
        if(m_xlxs.equals(DATABASE_LOADING_STRING))
            loadDataBase();
        else
            loadXLSX(m_xlxs);
        saveInfoConfig(properties);
    }

    /* If on of the config value do not load properly we do a secure load for all data */
    private static void SecureLoad() {
        System.out.println("config_package.Config.SecureLoad()");
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("\tDATABASE URL=");
        m_url = scanner.nextLine();
        System.out.println("\tDATABASE LOGIN=");
        m_login = scanner.nextLine();
        System.out.println("\tDATABASE PASSWORD=");
        m_password = scanner.nextLine();
    }

    private static String askForXLSX() {
        System.out.println("What is the name of the excel you want to load from the src\\loader_package\\xlsx (dont add .xlsx):");
        PrintFilesInFolder(new File(XLSX_FOLDER));
        System.out.println("\t- if you want to use actual database enter : " + DATABASE_LOADING_STRING);
        Scanner scanner = new Scanner(System.in);
        String buffer = scanner.nextLine();
        if(buffer.equals(DATABASE_LOADING_STRING))
            return DATABASE_LOADING_STRING;
        return XLSX_FOLDER + "\\" + buffer + ".xlsx";
    }

    private static void saveInfoConfig(NewProperties properties) {
        try (FileOutputStream outputStream = new FileOutputStream(FILE_NAME)) {
            properties.setProperty(CONFIG, m_config);
            properties.setProperty(XLSX, m_xlxs);
            properties.store(outputStream);
            Log.add("default properties have been saved in: " + FILE_NAME);
        }
        catch (IOException e) {
            Log.add("default properties haven't been saved : " + e.getLocalizedMessage());
        }
    }

    private static void setNewConfig(NewProperties properties) {
        SecureLoad();
        m_url = m_url.replace("\\", "");                                        // when writing property is adding \\\ so we need to delete 2 of them
        properties.setProperty(m_config + URL, m_url);
        properties.setProperty(m_config + LOGIN, m_login);
        properties.setProperty(m_config + PASSWORD, m_password);
    }
    
    private static void loadConfig(NewProperties properties) {
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

        String errorMessage = "start load : ";
        try {
            m_url = properties.getProperty(m_config + URL);
            errorMessage += "url load -> ";
            m_login = properties.getProperty(m_config + LOGIN);
            errorMessage += "login load -> ";
            m_password = properties.getProperty(m_config + PASSWORD);
            errorMessage += "password load -> ";
        }
        catch (IllegalArgumentException e) {
            errorMessage += "then crash";
            Log.add(e.getMessage() + ": IllegalArgumentException :");
            Log.add(errorMessage + "then throw exception");
            SecureLoad(); 
        }
    }

    private static void loadInfoConfig(NewProperties properties) {
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
            m_config = properties.getProperty(CONFIG);
            errorMessage += "config load -> ";
            m_xlxs = properties.getProperty(XLSX);
            errorMessage += "xlxs load -> ";
        }
        catch (IllegalArgumentException e) {
            Log.add(e.getMessage() + ": IllegalArgumentException :");
            Log.add(errorMessage + "then throw exception");
            System.err.println("CONIFG FILE IS CORRUPTED : check logs");
        }
    }
    
    public static void PrintFilesInFolder(final File folder) {
        for (File file : folder.listFiles()) {
            if (!file.isDirectory())
                System.out.println("\t- " + file.getName());
        }
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
}
