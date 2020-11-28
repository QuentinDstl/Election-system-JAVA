package java_project_desautel_pellen_perold;

import dao_package.CandidateDAOImpl;
import java.sql.SQLException;
import java.util.ArrayList;

public class Candidate extends Person {
    
    /* Variables */
    ArrayList<State> buffer_states_win;
    ArrayList<Elector> buffer_elector_win;
    
    CandidateDAOImpl candidate_from_db = new CandidateDAOImpl("Clement");
    
    
    /* Constructeeur */
    /* De la DATABASE */
    public Candidate(int num_case) throws SQLException {
        super();
        setLastNameFromDataBase(candidate_from_db.getLastNameCandidateIntoTable(num_case));
        setFirstNameFromDataBase(candidate_from_db.getFirstNameCandidateIntoTable(num_case));  
    }
    
    
    /* De le saisie d'un officiel */
    
}
