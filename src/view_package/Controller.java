//https://softwareengineering.stackexchange.com/questions/405038/result-object-vs-throwing-exceptions
package view_package;

import dao_package.CandidateDAOImpl;
import dao_package.ElectorDAOImpl;
import dao_package.OfficialDAOImpl;
import java.sql.SQLException;
import javax.swing.*;
import java_project_desautel_pellen_perold.*;

public class Controller {
    
    /* Variables */
    private Candidate m_user_candidate;
    private Official m_user_official;
    private Elector m_user_elector;
    
    private int m_type_user;
    
    private int m_reset = 0;
    
    private final CandidateDAOImpl access_to_candidate_table;
    private final OfficialDAOImpl access_to_official_table;
    private final ElectorDAOImpl access_to_elector_table;
    
    private final Election access_to_election = new Election();
    
    /* Constantes */
    public final int CANDIDATE = 1;
    public final int OFFICIAL = 2;
    public final int ELECTOR = 3;
    
    
    public Controller() throws SQLException {
        access_to_candidate_table = access_to_election.candidate_from_db;
        access_to_official_table = access_to_election.official_from_db;
        access_to_elector_table = access_to_election.elector_from_db;
    }
    
    public void startGraphiqueAccueil() throws SQLException
    {
        GraphicIdentification myIdentification = new GraphicIdentification();
        myIdentification.startIdentification();
        int checkIdentificationOut = 0;
        int checkExistence = 0;
        
        /* Blindage */ 
        while (checkExistence ==0) 
        {
            while (checkIdentificationOut == 0) 
            {            
                checkIdentificationOut = myIdentification.getCheckIdentification();
                System.out.print("");
            }
            
            checkExistence = createUser(myIdentification.getLastName(), myIdentification.getFirstName(), myIdentification.getPassword());
            
            if (checkExistence == 1) 
            {
                JOptionPane.showMessageDialog(null, "Your lastname or your firstname is incorrect");
                myIdentification = null;
                myIdentification = new GraphicIdentification();
                myIdentification.startIdentification();
                checkIdentificationOut = 0;
                checkExistence =0;
            }
            else if (checkExistence == 2) 
            {
                JOptionPane.showMessageDialog(null, "Your password is incorrect");
                myIdentification = null;
                myIdentification = new GraphicIdentification();
                myIdentification.startIdentification();
                checkIdentificationOut = 0;
                checkExistence =0;
            }
            else
            {
                executeProgram();
            } 
        }
    }
    
    public void startGraphiqueElectors()
    {
        if (m_user_elector.isVoteDone() == false)
        {
            GraphicElectors myElectors = new GraphicElectors(m_user_elector);
            myElectors.startElectors(access_to_election);
            int checkElectorsOut = 0;

            do {            
                System.out.print("");
                checkElectorsOut = myElectors.getCheckElectors();
            } while (checkElectorsOut == 0);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "You have already voted");
        }
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
    
    public void startGraphiqueOfficials() throws SQLException
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
                m_user_official.addCandidate(myOfficialsAddCandidate.getLastName(), myOfficialsAddCandidate.getFirstName(),myOfficialsAddCandidate.getParty());
                myOfficials = new GraphicOfficials(m_user_official);
                myOfficials.startOfficials();
                checkOfficialsOut = 0;
                checkOfficialsAddCandidatesOut = 0;
            }
            if(checkOfficialsOut == 2)// DELETE CANDIDATE
            {
                GraphicOfficialsDelCandidate myOfficialsDelCandidate = new GraphicOfficialsDelCandidate();
                myOfficialsDelCandidate.startOfficialsDelCandidate(access_to_election);
                
                while(checkOfficialsDelCandidatesOut != -1)
                {
                    checkOfficialsDelCandidatesOut = myOfficialsDelCandidate.getCheckOfficialsDelCandidate();
                    System.out.print("");
                }
                m_user_official.deleteCandidate(access_to_election.getCandidates().get(myOfficialsDelCandidate.getIntCandidate()));
                
                myOfficials = new GraphicOfficials(m_user_official);
                myOfficials.startOfficials();
                checkOfficialsOut = 0;
                checkOfficialsDelCandidatesOut = 0;
            }
            if(checkOfficialsOut == 3)// ADD ELECTOR
            {
                GraphicOfficialsAddElector myOfficialsAddElector = new GraphicOfficialsAddElector();
                myOfficialsAddElector.startOfficialsAddElector(access_to_election);
                
                while(checkOfficialsAddElectorsOut != -1)
                {
                    checkOfficialsAddElectorsOut = myOfficialsAddElector.getCheckOfficialsAddElector();
                    System.out.print("");
                }
                m_user_official.addElector(myOfficialsAddElector.getLastName(), myOfficialsAddElector.getFirstName(), access_to_election.getStates().get(myOfficialsAddElector.getIntState()));
                myOfficials = new GraphicOfficials(m_user_official);
                myOfficials.startOfficials();
                checkOfficialsOut = 0;
                checkOfficialsAddElectorsOut = 0;
            }
            if(checkOfficialsOut == 4)// DELETE ELECTOR
            {
                GraphicOfficialsDelElector myOfficialsDelElector = new GraphicOfficialsDelElector();
                myOfficialsDelElector.startOfficialsDelElector(access_to_election);
                
                while(checkOfficialsDelElectorsOut != -1)
                {
                    checkOfficialsDelElectorsOut = myOfficialsDelElector.getCheckOfficialsDelElector();
                    System.out.print("");
                }
                m_user_official.deleteElector(access_to_election.getElectors().get(myOfficialsDelElector.getIntElector()));
                
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
    
    public void executeProgram() throws  SQLException{
        ///interface acheminant les infos de la personne qui se connecte
        //createUser(last_name, first_name, password);
        
        if(m_type_user == CANDIDATE) {
            access_to_election.downloadDataBaseForCandidate();
            startGraphiqueCandidats();
        }
        else if(m_type_user == OFFICIAL) {
            access_to_election.downloadDataBaseForOfficial();
            startGraphiqueOfficials();
        }
        else if(m_type_user == ELECTOR) {
            access_to_election.downLoadDataBaseForElector();
            startGraphiqueElectors();
        }
    }
    
    private int createUser(String last_name, String first_name, String password) throws  SQLException {
        
        if(checkUserName(last_name, first_name)) {
           
            if(access_to_candidate_table.checkUserCandidatePassword(last_name, first_name, password)) {
                m_user_candidate = new Candidate(access_to_candidate_table.getIdUserWithConstraintUniquePerson(last_name, first_name, password), access_to_election);
                m_user_official = null;
                m_user_elector = null;
                m_type_user = CANDIDATE;
                return -1;
            }
            else if(access_to_official_table.checkUserOfficialPassword(last_name, first_name, password)) {
                m_user_official = new Official(access_to_official_table.getIdUserWithConstraintUniquePerson(last_name, first_name, password), access_to_election);
                m_user_candidate = null;
                m_user_elector = null;
                m_type_user = OFFICIAL;
                return -1;
            }
            else if(access_to_elector_table.checkUserElectorPassword(last_name, first_name, password)) {
                m_user_elector = new Elector(access_to_elector_table.getIdUserWithConstraintUniquePerson(last_name, first_name, password), access_to_election.getCandidates(), access_to_election.elector_from_db, access_to_election);
                m_user_candidate = null;
                m_user_official = null;
                m_type_user = ELECTOR;
                return -1;
            } 
           else {
                return 2;
            }
            
        }
        else {
            return 1;
        }
    }
    
    private boolean checkUserName(String last_name, String first_name) throws SQLException {
        return  access_to_candidate_table.checkUserCandidateName(last_name, first_name) ||
                access_to_official_table.checkUserOfficialName(last_name, first_name)||
                access_to_elector_table.checkUserElectorName(last_name, first_name);
    }
}
