package java_project_desautel_pellen_perold;

import dao_package.CandidateDAOImpl;
import java.sql.SQLException;
import java.util.ArrayList;

public class Candidate extends Person {
    
    /* Variables */
    private String m_party;
    private int m_nb_votes_total;
    
    private ArrayList<State> buffer_states_win;
    private ArrayList<Elector> buffer_elector_win;
    
    private final CandidateDAOImpl m_candidate_from_db;
    
    private final Election m_election_access;
    
    
    /* Constructeur */
    /* De la DATABASE */
    public Candidate(int num_case, CandidateDAOImpl candidate_from_db, Election election_access) throws SQLException {
        
        super();
        m_candidate_from_db = candidate_from_db;
        m_election_access = election_access;
        
        setLastNameFromDataBase(m_candidate_from_db.getLastNameCandidateIntoTable(num_case));
        setFirstNameFromDataBase(m_candidate_from_db.getFirstNameCandidateIntoTable(num_case));  
        setPasswordFromDataBase(m_candidate_from_db.getPasswordCandidateIntoTable(num_case));
        setIdFromDataBase(num_case);
        
        m_party = m_candidate_from_db.getPartyCandidateIntoTable(num_case);
        m_nb_votes_total = m_candidate_from_db.getNbrVoteTotalCandidateIntoTable(num_case);
    }
    
    /* De le saisie d'un officiel */
    public Candidate(String last_name, String first_name, String party, CandidateDAOImpl candidate_from_db, Election election_access) throws SQLException {
        
        super(last_name, first_name, "0000");
        m_election_access = election_access;
        m_candidate_from_db = candidate_from_db;
        m_party = party;
        m_nb_votes_total = 0;
    }
    
    public void incrementeNbrVotesTotal() throws SQLException {
        m_nb_votes_total++;
        m_candidate_from_db.incrementeNbrVotesTotal();
    }
    
    
    public void downLoadElectionDataBase() throws SQLException {
        m_election_access.downloadDataBaseForCandidate();
    }
    
    public String getParty() {
        return m_party;
    }
    
    public int getNbVotesTotal() {
        return m_nb_votes_total;
    }
}
