/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view_package;

import java_project_desautel_pellen_perold.*;

import javax.swing.*; // Needed for Swing classes
import java.awt.*;    // Needed for GridLayout class
import java.awt.event.*;
import java.util.ArrayList;

public class GraphicOfficialsWinner extends JFrame 
{
    private int checkOfficialsWinner = 0;
    private final int WINDOW_WIDTH = 1500;
    private final int WINDOW_HEIGHT = 900;
    private final String imageBiden = "pictures\\" + "\\biden.jpg";
    private final String imageTrump = "pictures\\" + "\\trump.jpg";
    private final String imageJorgensen = "pictures\\" + "\\jorgensen.jpg";
    private final String imageHawkins = "pictures\\" + "\\hawkins.jpg";
    private final String imageWest = "pictures\\" + "\\west.jpg";
    private final String imageNew = "pictures\\" + "\\default.png";
    private final JButton buttonBack;
    private Election m_access_to_election;
    
    public GraphicOfficialsWinner()
    {
        buttonBack = new JButton("Back to menu officials");
        buttonBack.addActionListener(new PlayButtonBack());
    }
    
    public void startOfficialsWinner(Election myElection)
    {
        m_access_to_election = myElection;
        ArrayList<Integer> myTabScoreCandidates = new ArrayList<>();
        ArrayList<String> myTabNameCandidates = new ArrayList<>();
        String NameWinner ="";
        int ScoreWinner = 0;
        
        setTitle("See the potential winner");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setLocationRelativeTo(null); 
        
        for (int a=0; a<myElection.getCandidates().size(); a++)
        {
            myTabScoreCandidates.add(0);
            myTabNameCandidates.add(myElection.getCandidates().get(a).getLastName());
        }
        
        JPanel test = new JPanel();
        test.setPreferredSize(new Dimension(WINDOW_WIDTH,2500));
        JScrollPane scrollFrame = new JScrollPane(test);
        test.setAutoscrolls(true);
        scrollFrame.setPreferredSize(new Dimension(WINDOW_WIDTH,WINDOW_HEIGHT));
        
        JPanel box = new JPanel();
        box.setLayout(new BoxLayout(box,BoxLayout.Y_AXIS));
        
        
        for (int i=0; i<myElection.getStates().size(); i++)
        {
            if (myElection.getStates().get(i).isAllWin() == true)
            {
                JLabel messageState = new JLabel(myElection.getStates().get(i).getName() + " : " + myElection.getStates().get(i).getNbrElector() 
                    + " is win by " + getWinnerOfState(myElection.getStates().get(i)));
            
                for (int b=0; b<myTabNameCandidates.size(); b++)
                { 
                    if (getWinnerOfState(myElection.getStates().get(i)).equals(myTabNameCandidates.get(b)))
                    {
                        myTabScoreCandidates.set(b, myTabScoreCandidates.get(b) + myElection.getStates().get(i).getNbrElector());
                    }
                }
                JPanel panelState = new JPanel();
                panelState.add(messageState);
                box.add(panelState);
            }
            
            else if (myElection.getStates().get(i).isAllWin() == false)
            {
                System.out.println(" HEY CET ETAT EST PROPOR");
                ArrayList<Integer> myTabPro = getProportionnalityCandidatesOfState(myElection.getStates().get(i));
                JLabel messageState = new JLabel(myElection.getStates().get(i).getName() + " is noAllWin, there is : " + myElection.getStates().get(i).getNbrElector() + " great electors \n");
                JPanel panelState = new JPanel();
                panelState.add(messageState);
                
                for (int b=0; b<myTabNameCandidates.size(); b++)
                {
                    JLabel messageOneCandidate = new JLabel("- "+ myElection.getCandidates().get(b).getLastName() + " has " + myTabPro.get(b));
                    panelState.add(messageOneCandidate);
                    myTabScoreCandidates.set(b, myTabScoreCandidates.get(b) + myTabPro.get(b));
                }
                box.add(panelState);
            }     
        }
        
        JLabel textVoid1 = new JLabel("---------------------------------------------------------------------------");
        JPanel panelVoid1 = new JPanel();
        panelVoid1.add(textVoid1);
        box.add(panelVoid1);
        
        /* Calcul du candidat gagnant */
        for (int c=0; c<myTabNameCandidates.size(); c++)
        { 
            JLabel messageCandidate = new JLabel("Candidate : " + myTabNameCandidates.get(c) + " has " + myTabScoreCandidates.get(c) + " great electors");
            if (ScoreWinner < myTabScoreCandidates.get(c)) 
            {
                ScoreWinner = myTabScoreCandidates.get(c);
                NameWinner = myTabNameCandidates.get(c);
            }
            JPanel panelCandidate = new JPanel();
            panelCandidate.add(messageCandidate);
            box.add(panelCandidate);
        }
        
        JLabel textVoid2 = new JLabel("---------------------------------------------------------------------------");
        JPanel panelVoid2 = new JPanel();
        panelVoid2.add(textVoid2);
        box.add(panelVoid2);
        
        JLabel messageAnnonce1 = new JLabel("The futur president of the USA is : ");
        JLabel messageAnnonce2 = new JLabel(NameWinner);
        messageAnnonce2.setForeground(Color.BLUE);
        JLabel messageAnnonce3 = new JLabel(" with a number of great electors  :");
        JLabel messageAnnonce4 = new JLabel(" "+ ScoreWinner);
        messageAnnonce4.setForeground(Color.RED);
        JPanel panelMessageAnnonce = new JPanel();
        panelMessageAnnonce.add(messageAnnonce1);
        panelMessageAnnonce.add(messageAnnonce2);
        panelMessageAnnonce.add(messageAnnonce3);
        panelMessageAnnonce.add(messageAnnonce4);
        box.add(panelMessageAnnonce);
        
        JLabel pictureFinal;
        if(NameWinner.equals("Trump"))
            pictureFinal = new JLabel(new ImageIcon(imageTrump));
        else if(NameWinner.equals("Biden"))
            pictureFinal = new JLabel(new ImageIcon(imageBiden));
        else if(NameWinner.equals("Jorgensen"))
            pictureFinal = new JLabel(new ImageIcon(imageJorgensen));
        else if(NameWinner.equals("Hawkins"))
            pictureFinal = new JLabel(new ImageIcon(imageHawkins));
        else if(NameWinner.equals("West"))
            pictureFinal = new JLabel(new ImageIcon(imageWest));
        else
            pictureFinal = new JLabel(new ImageIcon(imageNew));
        JPanel panelPictureFinal = new JPanel();
        panelPictureFinal.add(pictureFinal);
        box.add(panelPictureFinal);
        
        JPanel panelButtonCancel = new JPanel();
        panelButtonCancel.add(buttonBack);
        box.add(buttonBack);
        
        test.add(box);
        add(scrollFrame);
        setVisible(true);
    }
         
    public int getCheckOfficialsWinner ()
    {
        return checkOfficialsWinner;
    }
    private String getWinnerOfState(State state) {
        String name_winner = "";
        int nb_votes_winner = 0;
        for(int i=0; i<m_access_to_election.getCandidates().size(); ++i) {
            if(state.getNbVotesCandidateInState(m_access_to_election.getCandidates().get(i).getLastName()) > nb_votes_winner) {
                nb_votes_winner = state.getNbVotesCandidateInState(m_access_to_election.getCandidates().get(i).getLastName());
                name_winner = m_access_to_election.getCandidates().get(i).getLastName();
            }
        }
        return name_winner;
    }
    
    private ArrayList<Integer> getProportionnalityCandidatesOfState(State state) {
        ArrayList<Integer> candidates_scores = new ArrayList<>();
        for(int i=0; i<m_access_to_election.getCandidates().size(); ++i) {
            candidates_scores.add(state.getNbVotesCandidateInState(m_access_to_election.getCandidates().get(i).getLastName())/ m_access_to_election.getCandidates().size());
        }
        return candidates_scores;
    }
    
    private class PlayButtonBack implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            setVisible(false);
            checkOfficialsWinner = -1;
        }
    }
}
