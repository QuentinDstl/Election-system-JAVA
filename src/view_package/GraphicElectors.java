 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view_package;

import dao_package.*;
import java_project_desautel_pellen_perold.*;

import javax.swing.*; // Needed for Swing classes
import java.awt.*;    // Needed for GridLayout class
import java.awt.event.*;
import java.util.ArrayList;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

public class GraphicElectors extends GraphicIdentification
{
    private int checkElectors = 0;
    private int m_intCandidate;
    private final int WINDOW_WIDTH = 3200;
    private final int WINDOW_HEIGHT = 1000;
    private final String imageBiden = "pictures\\" + "\\biden.jpg";
    private final String imageTrump = "pictures\\" + "\\trump.jpg";
    private final String imageJorgensen = "pictures\\" + "\\jorgensen.jpg";
    private final String imageHawkins = "pictures\\" + "\\hawkins.jpg";
    private final String imageWest = "pictures\\" + "\\west.jpg";
    private final String imageNew = "pictures\\" + "\\default.png";
    private final JLabel messageIntro1;
    private final JLabel messageIntro2;
    private final JLabel messageIntro3;
    private final JLabel messageIntro4;
    private final JLabel messageIntro5;
    private final JLabel messageIntro6;
    private String messageBiden;
    private String messageJorgensen;
    private String messageHawkins;
    private String messageWest;
    private String messageTrump;
    private JButton buttonBiden;
    private JButton buttonTrump;
    private JButton buttonJorgensen;
    private JButton buttonHawkins;
    private JButton buttonWest;
    private JButton buttonCancel;
    
    public GraphicElectors(Elector myElector)
    {
        /* Initialisation of the interface */
        setTitle("Vote for YOUR FUTUR PRESIDENT");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        checkElectors = 0;
        messageIntro1 = new JLabel("                                       Your lastname :                                    ");
        messageIntro2 = new JLabel("                                      " + myElector.getLastName()+ "                                ");
        messageIntro2.setForeground(Color.BLUE);
        messageIntro3 = new JLabel("                                       Your firstname :                                    ");
        messageIntro4 = new JLabel("                                      " + myElector.getFirstName() + "                                ");
        messageIntro4.setForeground(Color.RED);
        messageIntro5 = new JLabel("                                       Your state :                                    ");
        messageIntro6 = new JLabel("                                      " + myElector.getState().getName() + "                                ");
        messageIntro6.setForeground(Color.gray);
        
        buttonCancel = new JButton("Cancel my vote, and back to identification");
        buttonCancel.addActionListener(new PlayButtonCancel());
        
        setVisible(true);
    }
    
    public void startElectors(Election myElection)
    {
        int taille = myElection.getCandidates().size();
        setLayout(new GridLayout(1, taille+1));
        
        JPanel panelCancel = new JPanel();
        panelCancel.add(messageIntro1);
        panelCancel.add(messageIntro2);
        panelCancel.add(messageIntro3);
        panelCancel.add(messageIntro4);
        panelCancel.add(messageIntro5);
        panelCancel.add(messageIntro6);
        panelCancel.add(buttonCancel);
        add(panelCancel);
        
        for (int a = 0; a < taille; a++)
        {
            if (myElection.getCandidates().get(a).getLastName().equals("Trump")) 
            {
                messageTrump = "Donal Trump, Republican Party :" +a;
                buttonTrump = new JButton(messageTrump,new ImageIcon(imageTrump));
                buttonTrump.addActionListener(new PlayButtonVote());
                JPanel panelTrump = new JPanel();
                panelTrump.add(buttonTrump);

                add(panelTrump);
            }
            else if (myElection.getCandidates().get(a).getLastName().equals("Biden")) 
            {
                messageBiden = "Joe Biden, Democratic Party :" +a;
                buttonBiden = new JButton(messageBiden,new ImageIcon(imageBiden));
                buttonBiden.addActionListener(new PlayButtonVote());
                JPanel panelBiden= new JPanel();
                panelBiden.add(buttonBiden);
                add(panelBiden);
            }
            else if (myElection.getCandidates().get(a).getLastName().equals("West")) 
            {
                messageWest = "Kanye West, American Independent Party :" +a;
                buttonWest = new JButton(messageWest,new ImageIcon(imageWest));
                buttonWest.addActionListener(new PlayButtonVote());
                JPanel panelWest= new JPanel();
                panelWest.add(buttonWest);

                add(panelWest);
            }
            else if (myElection.getCandidates().get(a).getLastName().equals("Jorgensen")) 
            {
                messageJorgensen = "Jo Jorgensen, Libertarian Party :" +a;
                buttonJorgensen = new JButton(messageJorgensen,new ImageIcon(imageJorgensen));
                buttonJorgensen.addActionListener(new PlayButtonVote());
                JPanel panelJorgensen= new JPanel();
                panelJorgensen.add(buttonJorgensen);

                add(panelJorgensen);
            }
            else if (myElection.getCandidates().get(a).getLastName().equals("Hawkins")) 
            {
                messageHawkins = "Howie Hawkins, Green Party :" +a;
                buttonHawkins = new JButton(messageHawkins,new ImageIcon(imageHawkins));
                buttonHawkins.addActionListener(new PlayButtonVote());
                JPanel panelHawkins= new JPanel();
                panelHawkins.add(buttonHawkins);

                add(panelHawkins);
            }
            else
            {
                JButton buttonNew = new JButton(myElection.getCandidates().get(a).getFirstName() 
                        + " " + myElection.getCandidates().get(a).getLastName()
                        + " , " + myElection.getCandidates().get(a).getParty() + " :" + a,new ImageIcon(imageNew));
                buttonNew.addActionListener(new PlayButtonVote());
                JPanel panelNew = new JPanel();
                panelNew.add(buttonNew);
                add(panelNew);
            }
        }

        
        /*System.out.println("Hey de niv0" + myElection.getCandidates().size() + " " + taille);
        
        if (myElection.getCandidates().size() > 4)
        {
            System.out.println("Hey de niv1");
            for(int i = 4; i <myElection.getCandidates().size(); i++)
            {
                System.out.println("Hey de niv2");
                JButton buttonNew = new JButton(new ImageIcon(imageNew));
                JLabel messageNew = new JLabel(myElection.getCandidates().get(i).getFirstName());
                buttonNew.addActionListener(new PlayButtonNew());
                JPanel panelNew = new JPanel();
                panelNew.add(buttonNew);
                panelNew.add(messageNew);
                add(panelNew);
            }
        }*/
        
        setVisible(true);
    }
    
    public int getCheckElectors()
    {
        return checkElectors;
    }
    public int getIntCandidate()
    {
        return m_intCandidate;
    }
    
    private class PlayButtonVote implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            setVisible(false);
            String source = e.getActionCommand();
            System.out.println(source);
            String[] parts = source.split(":");
            System.out.println(parts[1]);
            m_intCandidate = Integer.parseInt(parts[1]);
            checkElectors = 1;
        }
    }

    private class PlayButtonCancel implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {   
            setVisible(false);
            checkElectors = -1;   
        }
    }
}
