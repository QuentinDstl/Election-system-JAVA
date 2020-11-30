package java_project_desautel_pellen_perold;

import dao_package.CandidateDAOImpl;
import java.sql.SQLException;
import java.util.ArrayList;

public class Candidate extends Person {
    
    /* Variables */
    private String party;
    private int nb_votes_total;
    
    private ArrayList<State> buffer_states_win;
    private ArrayList<Elector> buffer_elector_win;
    
    private final CandidateDAOImpl candidate_from_db = new CandidateDAOImpl();
    
    private final Election election_access = new Election();
    
    
    /* Constructeur */
    /* De la DATABASE */
    public Candidate(int num_case) throws SQLException {
        
        super();
        setLastNameFromDataBase(candidate_from_db.getLastNameCandidateIntoTable(num_case));
        setFirstNameFromDataBase(candidate_from_db.getFirstNameCandidateIntoTable(num_case));  
        setPasswordFromDataBase(candidate_from_db.getPasswordCandidateIntoTable(num_case));
        setIdFromDataBase(num_case);
        
        party = candidate_from_db.getPartyCandidateIntoTable(num_case);
        nb_votes_total = candidate_from_db.getNbrVoteTotalCandidateIntoTable(num_case);
    }
    
    
    /* De le saisie d'un officiel */
    
    
    public void downLoadElectionDataBase() throws SQLException {
        election_access.downloadDataBaseForCandidate();
    }
}
