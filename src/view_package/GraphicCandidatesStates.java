package view_package;

import java_project_desautel_pellen_perold.*;

import javax.swing.*; // Needed for Swing classes
import java.awt.*;    // Needed for GridLayout class
import java.awt.event.*;

public class GraphicCandidatesStates extends JFrame
{
    protected int checkCandidatesStates = 0;
    private int m_intState;
    private final int WINDOW_WIDTH = 1500;
    private final int WINDOW_HEIGHT = 900;
    private final JButton buttonBack;
    
    public GraphicCandidatesStates()
    {
        checkCandidatesStates = 0;
        buttonBack = new JButton("Back to menu candidat");
        buttonBack.addActionListener(new PlayButtonBack());
    }

    public void startCandidatesStates(Election myElection)
    {       
        setTitle("Score STATES");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setLocationRelativeTo(null);
        
        int heightTab = myElection.getStates().size();
        int heightFinal = heightTab/10 +1;
        setLayout(new GridLayout(10, heightFinal));
        
        for(int i = 0; i< heightTab; i++)
        {
            JPanel panelButton = new JPanel();
            JButton button;
            if (myElection.getStates().get(i).isAllWin() == true)
                button = new JButton(myElection.getStates().get(i).getName() + " AllWin" + " :" + i);
            else
                button = new JButton(myElection.getStates().get(i).getName() + " NoAllWin" + " :" + i);
            button.addActionListener(new PlayButtonState());
            panelButton.add(button);
            add(panelButton);
        }
        
        add(buttonBack);
        setVisible(true);
    }
    
    public int getCheckCandidatesStates()
    {
        return checkCandidatesStates;
    }
    public int getIntState()
    {
        return m_intState;
    }
    
    private class PlayButtonBack implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            setVisible(false);
            checkCandidatesStates = -1;
        }
    }
    private class PlayButtonState implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            setVisible(false);
            String source = e.getActionCommand();
            String[] parts = source.split(":");
            m_intState = Integer.parseInt(parts[1]);
            checkCandidatesStates = 1;    
        }
    }
}
