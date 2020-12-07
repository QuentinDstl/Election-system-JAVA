package view_package;

import java_project_desautel_pellen_perold.*;

import javax.swing.*; // Needed for Swing classes
import java.awt.*;    // Needed for GridLayout class
import java.awt.event.*;

public class GraphicOfficialsStartPause extends JFrame
{
    protected int checkOfficialsStartPause = 0;
    protected String m_nameState;
    private final int WINDOW_WIDTH = 1500;
    private final int WINDOW_HEIGHT = 900;
    private final JButton buttonBack;
    private final JButton buttonStartEndNational;
    private final JButton buttonPauseNational;
    
    
    public GraphicOfficialsStartPause()
    {
        checkOfficialsStartPause = 0;
        buttonBack = new JButton("Back to menu official");
        buttonBack.addActionListener(new PlayButtonBack());
        buttonStartEndNational = new JButton("Start / End vote");
        buttonStartEndNational.addActionListener(new PlayButtonStartEndNational());
        buttonPauseNational = new JButton("Pause / Unpause vote");
        buttonPauseNational.addActionListener(new PlayButtonPauseNational());
    }
    
    public void startOfficialsStartPause(Election myElection)
    {
        setTitle("Vote menu");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        int heightTab = myElection.getStates().size();
        int heightFinal = heightTab/10 +1;
        setLayout(new GridLayout(10, heightFinal));
        
        if (myElection.getOpenVote() == true)
            buttonStartEndNational.setBackground(Color.GREEN);
        else if (myElection.getOpenVote() == false)
            buttonStartEndNational.setBackground(Color.RED);
        JPanel panelButtonStartEndNational = new JPanel();
        panelButtonStartEndNational.add(buttonStartEndNational);
        
        if (myElection.getStates().get(0).isPause() == true)
            buttonPauseNational.setBackground(Color.RED);
        else if (myElection.getStates().get(0).isPause() == false)
            buttonPauseNational.setBackground(Color.GREEN);
        JPanel panelButtonPauseNational = new JPanel();
        panelButtonPauseNational.add(buttonPauseNational);
        
        JPanel panelButtonBack = new JPanel();
        panelButtonBack.add(buttonBack);
        
        add(panelButtonBack);
        add(panelButtonStartEndNational);
        add(panelButtonPauseNational);
        
        for(int i = 0; i< myElection.getStates().size(); i++)
        {
            JPanel panelButton = new JPanel();
            JButton button;
            if (myElection.getStates().get(i).isPause() == true)
            {
                button = new JButton(myElection.getStates().get(i).getName());
                button.setBackground(Color.RED);
            }
            else
            {
                button = new JButton(myElection.getStates().get(i).getName());
                button.setBackground(Color.GREEN);
            }    
            button.addActionListener(new PlayButtonState());
            panelButton.add(button);
            add(panelButton);
        }
        
        setVisible(true);
    }
    
    
    public int getCheckOfficialsStartPause()
    {
        return checkOfficialsStartPause;
    }
    public String getNameState()
    {
        return m_nameState;
    }
    
    private class PlayButtonStartEndNational implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            setVisible(false);
            checkOfficialsStartPause = 1;
        }
    }
    private class PlayButtonPauseNational implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            setVisible(false);
            checkOfficialsStartPause = 2;
        }
    }
    private class PlayButtonState implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            setVisible(false);
            String source = e.getActionCommand();
            m_nameState = source;
            checkOfficialsStartPause = 3;    
        }
    }
    private class PlayButtonBack implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            setVisible(false);
            checkOfficialsStartPause = -1;
        }
    }
}
