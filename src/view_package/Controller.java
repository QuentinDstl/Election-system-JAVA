package view_package;

import dao_package.CandidateDAOImpl;
import dao_package.ElectorDAOImpl;
import dao_package.OfficialDAOImpl;
import java.sql.SQLException;
import java_project_desautel_pellen_perold.Candidate;
import java_project_desautel_pellen_perold.Elector;
import java_project_desautel_pellen_perold.Official;

public class Controller {
    
    /* Variables */
    private Candidate m_user_candidate;
    private Official m_user_official;
    private Elector m_user_elector;
    
    private int m_type_user;
    
    private final CandidateDAOImpl access_to_candidate_table = new CandidateDAOImpl();
    private final OfficialDAOImpl access_to_official_table = new OfficialDAOImpl();
    private final ElectorDAOImpl access_to_elector_table = new ElectorDAOImpl();
    
    
    /* Constantes */
    public final int CANDIDATE = 1;
    public final int OFFICIAL = 2;
    public final int ELECTOR = 3;
    
    
    public Controller() throws SQLException {}
    
    public void executeProgram() {
        ///interface acheminant les infos de la personne qui se connecte
        //createUser(last_name, first_name, password);
        
        if(m_type_user == CANDIDATE) {
            //INTERFACE DU CANDIDAT
        }
        else if(m_type_user == OFFICIAL) {
           //INTERFACE DE L'OFFICIAL
        }
        else if(m_type_user == ELECTOR) {
           //INTERFACE DE L'ELECTOR
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
                m_user_official = new Official(access_to_official_table.getIdUserWithConstraintUniquePerson(last_name, first_name, password));
                m_user_candidate = null;
                m_user_elector = null;
                m_type_user = OFFICIAL;
                ///POUR CHARLES : RAJOUTER CE QUE TU VEUX QUE TON INTERFACE FASSE QUAND L'UTILISATEUR EST CREE
           }
           else if(access_to_elector_table.checkUserElectorPassword(last_name, first_name, password)) {
                m_user_elector = new Elector(access_to_elector_table.getIdUserWithConstraintUniquePerson(last_name, first_name, password));
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
