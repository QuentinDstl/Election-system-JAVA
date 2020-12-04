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
    private final int WINDOW_WIDTH = 1900;
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
    private final JLabel messageBiden;
    private final JLabel messageJorgensen;
    private final JLabel messageHawkins;
    private final JLabel messageWest;
    private final JLabel messageTrump;
    private final JButton buttonBiden;
    private final JButton buttonTrump;
    private final JButton buttonJorgensen;
    private final JButton buttonHawkins;
    private final JButton buttonWest;
    private final JButton buttonCancel;
    
    public GraphicElectors(Elector m_user_elector)
    {
        /* Initialisation of the interface */
        setTitle("Vote for YOUR FUTUR PRESIDENT");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        checkElectors = 0;
        messageIntro1 = new JLabel("                                       Your lastname :                                    ");
        messageIntro2 = new JLabel("                                      " + m_user_elector.getLastName()+ "                                ");
        messageIntro2.setForeground(Color.BLUE);
        messageIntro3 = new JLabel("                                       Your firstname :                                    ");
        messageIntro4 = new JLabel("                                      " + m_user_elector.getFirstName() + "                                ");
        messageIntro4.setForeground(Color.RED);
        messageIntro5 = new JLabel("                                       Your state :                                    ");
        messageIntro6 = new JLabel("                                      " + m_user_elector.getState().getName() + "                                ");
        messageIntro6.setForeground(Color.gray);
        buttonBiden = new JButton(new ImageIcon(imageBiden));
        messageBiden = new JLabel("Joe Biden, Democratic Party");
        buttonBiden.addActionListener(new PlayButtonBiden());
        
        buttonTrump = new JButton(new ImageIcon(imageTrump));
        messageTrump = new JLabel("Donald Trump, Republican Party");
        buttonTrump.addActionListener(new PlayButtonTrump());
        
        buttonJorgensen = new JButton(new ImageIcon(imageJorgensen));
        messageJorgensen = new JLabel("Jo Jorgensen, Liertarian Party");
        buttonJorgensen.addActionListener(new PlayButtonJorgensen());
        
        buttonHawkins = new JButton(new ImageIcon(imageHawkins));
        messageHawkins = new JLabel("Howie Hawkins, Green Party");
        buttonHawkins.addActionListener(new PlayButtonHawkins());
        
        buttonWest = new JButton(new ImageIcon(imageWest));
        messageWest = new JLabel("Kanye West, American Independent Party");
        buttonWest.addActionListener(new PlayButtonWest());
        
        buttonCancel = new JButton("Cancel my vote, and back to identification");
        buttonCancel.addActionListener(new PlayButtonCancel());
        
        setVisible(true);
    }
    
    public void startElectors(Election access_to_election)
    {
        int taille = access_to_election.getCandidates().size()+1;
        setLayout(new GridLayout(1, taille));
        
        JPanel panelCancel = new JPanel();
        panelCancel.add(messageIntro1);
        panelCancel.add(messageIntro2);
        panelCancel.add(messageIntro3);
        panelCancel.add(messageIntro4);
        panelCancel.add(messageIntro5);
        panelCancel.add(messageIntro6);
        panelCancel.add(buttonCancel);
        JPanel panelBiden= new JPanel();
        panelBiden.add(buttonBiden);
        panelBiden.add(messageBiden);
        JPanel panelTrump = new JPanel();
        panelTrump.add(buttonTrump);
        panelTrump.add(messageTrump);
        JPanel panelJorgensen= new JPanel();
        panelJorgensen.add(buttonJorgensen);
        panelJorgensen.add(messageJorgensen);
        JPanel panelHawkins= new JPanel();
        panelHawkins.add(buttonHawkins);
        panelHawkins.add(messageHawkins);
        JPanel panelWest= new JPanel();
        panelWest.add(buttonWest);
        panelWest.add(messageWest);
        
        add(panelCancel);
        add(panelBiden);
        add(panelTrump);
        add(panelHawkins);
        add(panelJorgensen);
        add(panelWest);
        
        System.out.println("Hey de niv0" + access_to_election.getCandidates().size() + " " + taille);
        
        if (access_to_election.getCandidates().size() > 1)
        {
            System.out.println("Hey de niv1");
            for(int i = 1; i <access_to_election.getCandidates().size(); i++)
            {
                System.out.println("Hey de niv2");
                JButton buttonNew = new JButton(new ImageIcon(imageNew));
                JLabel messageNew = new JLabel(access_to_election.getCandidates().get(i).getFirstName());
                buttonNew.addActionListener(new PlayButtonNew());
                JPanel panelNew = new JPanel();
                panelNew.add(buttonNew);
                panelNew.add(messageNew);
                add(panelNew);
            }
        }
        
        setVisible(true);
    }
    
    public int getCheckElectors()
    {
        return checkElectors;
    }
    
    private class PlayButtonTrump implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            System.out.println("JE VOTE POUR LE FOU");
        }
    }
    private class PlayButtonBiden implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            System.out.println("JE VOTE POUR L'AVENIR");
        }
    }
    private class PlayButtonWest implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            System.out.println("JE VOTE POUR UN INCONNU");
        }
    }
    private class PlayButtonJorgensen implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            System.out.println("JE VOTE POUR LA LIERTE");
        }
    }
    private class PlayButtonHawkins implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            System.out.println("JE VOTE POUR L'ECOLOGIE");
        }
    }
    private class PlayButtonNew implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String source = e.getActionCommand();
            System.out.println("JE VOTE ????" + source);
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
