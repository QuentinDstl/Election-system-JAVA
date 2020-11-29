package config_package;

import java.text.SimpleDateFormat;  
import java.util.Date;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Log {
    
    public Log() {
        try {
            File logFile = new File("logs.txt");
            logFile.delete();                                                   // We delete the previous logs
            logFile.createNewFile();                                            // We recreate the log file
        } 
        catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    
    private static String getTime() throws Exception {
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss.SSS");  
            Date date = new Date();  
            return formatter.format(date);                               
    }
    
    public static void add(String errorMessage) {
        try (FileWriter myWriter = new FileWriter("logs.txt",true)) {
            myWriter.write("[" + getTime() + "] " + errorMessage + "\n");
        } 
        catch(IOException ioe) {
            System.err.println("cant handle exception : " + ioe.getMessage());
        }
        catch(Exception e) {
            System.err.println("cant handle exception : " + e.getMessage());
        }
    }
}