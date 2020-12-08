package view_package;

import java_project_desautel_pellen_perold.*;

import javax.swing.*; // Needed for Swing classes
import java.awt.*;    // Needed for GridLayout class
import java.awt.event.*;

public class GraphicOfficials extends JFrame
{
    protected int checkOfficials = 0;
    private final int WINDOW_WIDTH = 1500;
    private final int WINDOW_HEIGHT = 900;
    private final JLabel messageEnter;
    private final JButton buttonAddCandidate;
    private final JButton buttonDelCandidate;
    private final JButton buttonAddElector;
    private final JButton buttonDelElector;
    private final JButton buttonShowNational;
    private final JButton buttonShowState;
    private final JButton buttonShowWinner;
    private final JButton buttonCancel;
    private final JButton buttonStartPause;
    
    public GraphicOfficials(Official m_user_official)
    {
        checkOfficials =0;
        messageEnter = new JLabel("You are the official " + m_user_official.getLastName() + " " + m_user_official.getFirstName());
        buttonAddCandidate = new JButton("Add candidate");
        buttonAddCandidate.addActionListener(new PlayButtonAddCandidate());
        buttonDelCandidate = new JButton("Delete candidate");
        buttonDelCandidate.addActionListener(new PlayButtonDelCandidate());
        buttonAddElector = new JButton("Add elector");
        buttonAddElector.addActionListener(new PlayButtonAddElector());
        buttonDelElector = new JButton("Delete elector");
        buttonDelElector.addActionListener(new PlayButtonDelElector());
        buttonShowNational = new JButton("See national scores");
        buttonShowNational.addActionListener(new PlayButtonShowNational());
        buttonShowState = new JButton("See states scores");
        buttonShowState.addActionListener(new PlayButtonShowStates());
        buttonShowWinner = new JButton("See the winner");
        buttonShowWinner.addActionListener(new PlayButtonShowWinner());
        buttonCancel = new JButton("Back to identification");
        buttonCancel.addActionListener(new PlayButtonCancel());
        buttonStartPause = new JButton("Start, pause, end vote");
        buttonStartPause.addActionListener(new PlayButtonStartPause());
    }
    
    public void startOfficials()
    {
        setTitle("You are here to enforce the law");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        setLayout(new GridLayout(4,3));
        
        JLabel textVoid1 = new JLabel("");
        JPanel panelVoid1 = new JPanel();
        panelVoid1.add(textVoid1);
        JLabel textVoid2 = new JLabel("");
        JPanel panelVoid2 = new JPanel();
        panelVoid2.add(textVoid2);
        
        JPanel panelMessageEnter = new JPanel();
        panelMessageEnter.add(messageEnter);
        
        JPanel panelButtonAddCandidate = new JPanel();
        panelButtonAddCandidate.add(buttonAddCandidate);
        JPanel panelButtonDelCandidate = new JPanel();
        panelButtonDelCandidate.add(buttonDelCandidate);
        JPanel panelButtonAddElector = new JPanel();
        panelButtonAddElector.add(buttonAddElector);
        JPanel panelButtonDelElector = new JPanel();
        panelButtonDelElector.add(buttonDelElector);
        JPanel panelButtonShowNational = new JPanel();
        panelButtonShowNational.add(buttonShowNational);
        JPanel panelButtonShowState = new JPanel();
        panelButtonShowState.add(buttonShowState);
        JPanel panelButtonShowWinner = new JPanel();
        panelButtonShowWinner.add(buttonShowWinner);
        JPanel panelButtonStartPause = new JPanel();
        panelButtonStartPause.add(buttonStartPause);
   
        
        add(panelMessageEnter);
        add(panelVoid1);
        add(buttonCancel);
        add(panelButtonAddCandidate);
        add(panelButtonShowNational);
        add(panelButtonAddElector);
        add(panelButtonDelCandidate);
        add(panelButtonShowState);
        add(panelButtonDelElector);
        add(panelButtonStartPause);
        add(panelButtonShowWinner);
        
        setVisible(true);
    }
    
    public int getCheckOfficials()
    {
        return checkOfficials;
    }
    
    private class PlayButtonAddCandidate implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            setVisible(false);
            checkOfficials = 1;
        }
    }
    private class PlayButtonDelCandidate implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            setVisible(false);
            checkOfficials = 2;
        }
    }
    private class PlayButtonAddElector implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            setVisible(false);
            checkOfficials = 3;
        }
    }
    private class PlayButtonDelElector implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            setVisible(false);
            checkOfficials = 4;
        }
    }
    private class PlayButtonShowNational implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            setVisible(false);
            checkOfficials = 5;
        }
    }
    private class PlayButtonShowStates implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            setVisible(false);
            checkOfficials = 6;
        }
    }
    private class PlayButtonShowWinner implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            setVisible(false);
            checkOfficials = 7;
        }
    }
    private class PlayButtonStartPause implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            setVisible(false);
            checkOfficials = 8;
        }
    }
    private class PlayButtonCancel implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            setVisible(false);
            checkOfficials = -1;
        }
    }
}
