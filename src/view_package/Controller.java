package view_package;

import dao_package.CandidateDAOImpl;
import dao_package.ElectorDAOImpl;
import dao_package.OfficialDAOImpl;
import java.sql.SQLException;
import java_project_desautel_pellen_perold.Candidate;
import java_project_desautel_pellen_perold.Election;
import java_project_desautel_pellen_perold.Elector;
import java_project_desautel_pellen_perold.Official;

public class Controller {
    
    /* Variables */
    private Candidate m_user_candidate;
    private Official m_user_official;
    private Elector m_user_elector;
    
    private int m_type_user;
    
    private int m_reset = 0;
    
    private final CandidateDAOImpl access_to_candidate_table = new CandidateDAOImpl();
    private final OfficialDAOImpl access_to_official_table = new OfficialDAOImpl();
    private final ElectorDAOImpl access_to_elector_table = new ElectorDAOImpl();
    
    private final Election access_to_election = new Election();
    
    /* Constantes */
    public final int CANDIDATE = 1;
    public final int OFFICIAL = 2;
    public final int ELECTOR = 3;
    
    
    public Controller() throws SQLException {
    }
    
    public void startGraphiqueAccueil() throws SQLException
    {
        GraphicIdentification myIdentification = new GraphicIdentification();
        myIdentification.startIdentification();
        int checkIdentificationOut = 0;
        
        System.out.print("NIV1");
        /* Blindage */ 
        while (checkIdentificationOut == 0) {            
            checkIdentificationOut = myIdentification.getCheckIdentification();
            System.out.print("");
        }
        System.out.print(" NIV2");
        createUser(myIdentification.getLastName(), myIdentification.getFirstName(), myIdentification.getPassword());
        executeProgram();
    }
    
    public void startGraphiqueElectors()
    {
        GraphicElectors myElectors = new GraphicElectors(m_user_elector);
        myElectors.startElectors(access_to_election);
        int checkElectorsOut = 0;
        
        do {            
            System.out.print("");
            checkElectorsOut = myElectors.getCheckElectors();
        } while (checkElectorsOut == 0);
        
        m_reset = 1;
    }
    
    public void startGraphiqueCandidats()
    {
        GraphicCandidates myCandidates = new GraphicCandidates(m_user_candidate);
        myCandidates.startCandidates();
        int checkCandidatesOut = 0;
        int checkCandidatesNationalOut = 0;
        int checkCandidatesStatesOut = 0;
        int checkCandidatesStatesUniqueOut = 0;
        
        while (checkCandidatesOut != -1) 
        {   
            checkCandidatesOut = myCandidates.getCheckCandidates();
            System.out.print("");
            
            if(checkCandidatesOut == 1)// DISPLAY NATIONAL
            {
                GraphicCandidatesNational myCandidatesNational = new GraphicCandidatesNational();
                myCandidatesNational.startCandidatesNational(m_user_candidate);
                
                while(checkCandidatesNationalOut != -1)
                {
                    checkCandidatesNationalOut = myCandidatesNational.getCheckCandidatesNational();
                    System.out.print("");
                }
                myCandidates = new GraphicCandidates(m_user_candidate);
                myCandidates.startCandidates();
                checkCandidatesOut = 0;
                checkCandidatesNationalOut = 0;
            }
            if(checkCandidatesOut == 2)// DISPLAY STATES
            {
                GraphicCandidatesStates myCandidatesStates = new GraphicCandidatesStates();
                myCandidatesStates.startCandidatesStates(m_user_candidate);
                
                while(checkCandidatesStatesOut != -1)
                {
                    checkCandidatesStatesOut = myCandidatesStates.getCheckCandidatesStates();
                    System.out.print("");
                    
                    if (checkCandidatesStatesOut == 1)
                    {
                        GraphicCandidatesStatesUnique myUniqueState = new GraphicCandidatesStatesUnique();
                        myUniqueState.startCandidatesStatesUnique(m_user_candidate);
                        
                        while(checkCandidatesStatesUniqueOut != -1)
                        {
                            checkCandidatesStatesUniqueOut = myUniqueState.getCheckCandidatesStatesUnique();
                            System.out.print("");
                        }
                        myCandidatesStates = new GraphicCandidatesStates();
                        myCandidatesStates.startCandidatesStates(m_user_candidate);
                        checkCandidatesStatesOut = 0;
                        checkCandidatesStatesUniqueOut = 0;
                    }
                }
                myCandidates = new GraphicCandidates(m_user_candidate);
                myCandidates.startCandidates();
                checkCandidatesOut = 0;
                checkCandidatesStatesOut = 0;
            }
        }
        m_reset = 1;
    }
    
    public void startGraphiqueOfficials()
    {
        GraphicOfficials myOfficials = new GraphicOfficials(m_user_official);
        myOfficials.startOfficials();
        int checkOfficialsOut = 0;
        int checkOfficialsAddCandidatesOut = 0;
        int checkOfficialsDelCandidatesOut = 0;
        int checkOfficialsAddElectorsOut = 0;
        int checkOfficialsDelElectorsOut = 0;
        int checkOfficialsNationalOut = 0;
        int checkOfficialsStatesOut = 0;
        int checkOfficialsStatesUniqueOut = 0;
        int checkOfficialsWinnersOut = 0;
        int checkOfficialsStartPauseOut = 0;
        
        while (checkOfficialsOut != -1) 
        {   
            checkOfficialsOut = myOfficials.getCheckOfficials();
            System.out.print("");
            
            if(checkOfficialsOut == 1)// ADD CANDIDAT
            {
                GraphicOfficialsAddCandidate myOfficialsAddCandidate = new GraphicOfficialsAddCandidate();
                myOfficialsAddCandidate.startOfficialsAddCandidate();
                
                while(checkOfficialsAddCandidatesOut != -1)
                {
                    checkOfficialsAddCandidatesOut = myOfficialsAddCandidate.getCheckOfficialsAddCandidate();
                    System.out.print("");
                }
                myOfficials = new GraphicOfficials(m_user_official);
                myOfficials.startOfficials();
                checkOfficialsOut = 0;
                checkOfficialsAddCandidatesOut = 0;
            }
            if(checkOfficialsOut == 2)// DELETE CANDIDATE
            {
                GraphicOfficialsDelCandidate myOfficialsDelCandidate = new GraphicOfficialsDelCandidate();
                myOfficialsDelCandidate.startOfficialsDelCandidate();
                
                while(checkOfficialsDelCandidatesOut != -1)
                {
                    checkOfficialsDelCandidatesOut = myOfficialsDelCandidate.getCheckOfficialsDelCandidate();
                    System.out.print("");
                }
                myOfficials = new GraphicOfficials(m_user_official);
                myOfficials.startOfficials();
                checkOfficialsOut = 0;
                checkOfficialsDelCandidatesOut = 0;
            }
            if(checkOfficialsOut == 3)// ADD ELECTOR
            {
                GraphicOfficialsAddElector myOfficialsAddElector = new GraphicOfficialsAddElector();
                myOfficialsAddElector.startOfficialsAddElector();
                
                while(checkOfficialsAddElectorsOut != -1)
                {
                    checkOfficialsAddElectorsOut = myOfficialsAddElector.getCheckOfficialsAddElector();
                    System.out.print("");
                }
                myOfficials = new GraphicOfficials(m_user_official);
                myOfficials.startOfficials();
                checkOfficialsOut = 0;
                checkOfficialsAddElectorsOut = 0;
            }
            if(checkOfficialsOut == 4)// DELETE ELECTOR
            {
                GraphicOfficialsDelElector myOfficialsDelElector = new GraphicOfficialsDelElector();
                myOfficialsDelElector.startOfficialsDelElector();
                
                while(checkOfficialsDelElectorsOut != -1)
                {
                    checkOfficialsDelElectorsOut = myOfficialsDelElector.getCheckOfficialsDelElector();
                    System.out.print("");
                }
                myOfficials = new GraphicOfficials(m_user_official);
                myOfficials.startOfficials();
                checkOfficialsOut = 0;
                checkOfficialsDelElectorsOut = 0;
            }
            if(checkOfficialsOut == 5)// SHOW NATIONAL
            {
                GraphicOfficialsNational myOfficialsNational = new GraphicOfficialsNational();
                myOfficialsNational.startOfficialsNational(m_user_official);
                
                while(checkOfficialsNationalOut != -1)
                {
                    checkOfficialsNationalOut = myOfficialsNational.getCheckOfficialsNational();
                    System.out.print("");
                }
                myOfficials = new GraphicOfficials(m_user_official);
                myOfficials.startOfficials();
                checkOfficialsOut = 0;
                checkOfficialsNationalOut = 0;
            }
            if(checkOfficialsOut == 6)// DISPLAY STATES
            {
                GraphicOfficialsStates myOfficialsStates = new GraphicOfficialsStates();
                myOfficialsStates.startOfficialsStates(m_user_official);
                
                while(checkOfficialsStatesOut != -1)
                {
                    checkOfficialsStatesOut = myOfficialsStates.getCheckOfficialsStates();
                    System.out.print("");
                    
                    if (checkOfficialsStatesOut == 1)
                    {
                        GraphicOfficialsStatesUnique myUniqueState = new GraphicOfficialsStatesUnique();
                        myUniqueState.startOfficialsStatesUnique(m_user_official);
                        
                        while(checkOfficialsStatesUniqueOut != -1)
                        {
                            checkOfficialsStatesUniqueOut = myUniqueState.getCheckOfficialsStatesUnique();
                            System.out.print("");
                        }
                        myOfficialsStates = new GraphicOfficialsStates();
                        myOfficialsStates.startOfficialsStates(m_user_official);
                        checkOfficialsStatesOut = 0;
                        checkOfficialsStatesUniqueOut = 0;
                    }
                }
                myOfficials = new GraphicOfficials(m_user_official);
                myOfficials.startOfficials();
                checkOfficialsOut = 0;
                checkOfficialsStatesOut = 0;
            }
            if(checkOfficialsOut == 8)// START PAUSE MENU
            {
                GraphicOfficialsStartPause myOfficialsStartPause = new GraphicOfficialsStartPause();
                myOfficialsStartPause.startOfficialsStartPause();
                
                while(checkOfficialsStartPauseOut != -1)
                {
                    checkOfficialsStartPauseOut = myOfficialsStartPause.getCheckOfficialsStartPause();
                    System.out.print("");
                }
                myOfficials = new GraphicOfficials(m_user_official);
                myOfficials.startOfficials();
                checkOfficialsOut = 0;
                checkOfficialsStartPauseOut = 0;
            }
            
        }
        m_reset = 1;
    }
    
    public int getReset()
    {
        return m_reset;
    }
    
    public void executeProgram() {
        ///interface acheminant les infos de la personne qui se connecte
        //createUser(last_name, first_name, password);
        
        if(m_type_user == CANDIDATE) {
            startGraphiqueCandidats();
        }
        else if(m_type_user == OFFICIAL) {
            startGraphiqueOfficials();
        }
        else if(m_type_user == ELECTOR) {
           startGraphiqueElectors();
        }
    }
    
    private void createUser(String last_name, String first_name, String password) throws  SQLException {
        
        if(checkUserName(last_name, first_name)) {
           
            if(access_to_candidate_table.checkUserCandidatePassword(last_name, first_name, password)) {
                m_user_candidate = new Candidate(access_to_candidate_table.getIdUserWithConstraintUniquePerson(last_name, first_name, password));
                m_user_official = null;
                m_user_elector = null;
                m_type_user = CANDIDATE;
                ///POUR CHARLES : RAJOUTER CE QUE TU VEUX QUE TON INTERFACE FASSE QUAND L'UTILISATEUR EST CREE
            }
           else if(access_to_official_table.checkUserOfficialPassword(last_name, first_name, password)) {
                m_user_official = new Official(access_to_official_table.getIdUserWithConstraintUniquePerson(last_name, first_name, password), access_to_election);
                m_user_candidate = null;
                m_user_elector = null;
                m_type_user = OFFICIAL;
                ///POUR CHARLES : RAJOUTER CE QUE TU VEUX QUE TON INTERFACE FASSE QUAND L'UTILISATEUR EST CREE
           }
           else if(access_to_elector_table.checkUserElectorPassword(last_name, first_name, password)) {
                m_user_elector = new Elector(access_to_elector_table.getIdUserWithConstraintUniquePerson(last_name, first_name, password), access_to_election.getCandidates(), access_to_election.elector_from_db, access_to_election);
                m_user_candidate = null;
                m_user_official = null;
                m_type_user = ELECTOR;
                ///POUR CHARLES : RAJOUTER CE QUE TU VEUX QUE TON INTERFACE FASSE QUAND L'UTILISATEUR EST CREE
           } 
           else {
               ///POUR CHARLES : RAJOUTER CE QUE TU VEUX QUE L'INTERFACE FASSE QUAND LE PASSWORD N'EST PAS BON
           }
            
        }
        else {
            ///POUR CHARLES : RAJOUTER ICI CE QUE TU VEUX QUE TON INTERFACE FASSE QUAND LE NOM ET LE PRENOM NE CORRESPONDENT PAS A QUELQU'UN
        }
    }
    
    private boolean checkUserName(String last_name, String first_name) throws SQLException {
        return  access_to_candidate_table.checkUserCandidateName(last_name, first_name) || 
                access_to_official_table.checkUserOfficialName(last_name, first_name) ||
                access_to_elector_table.checkUserElectorName(last_name, first_name);
    }
}
