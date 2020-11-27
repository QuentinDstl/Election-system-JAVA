package java_project_desautel_pellen_perold;

import dao_package.*;
import java.sql.SQLException;

import java.util.Scanner;

public class Java_project_desautel_pellen_perold {
    
    private static final Scanner KEYBOARD = new Scanner(System.in);
    
    public static void main(String[] args) throws SQLException
    {
        System.out.print("Coucou toi, entre ton joli prénom : ");
        String nameUserEnter = KEYBOARD.nextLine();
        
        CandidateDAOImpl hey = new CandidateDAOImpl(nameUserEnter);
        
        hey.createTableCandidate();
        
    }
}
