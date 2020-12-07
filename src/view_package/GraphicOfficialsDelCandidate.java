package view_package;

import java_project_desautel_pellen_perold.*;

import javax.swing.*; // Needed for Swing classes
import java.awt.*;    // Needed for GridLayout class
import java.awt.event.*;

public class GraphicOfficialsDelCandidate extends JFrame
{
    private int checkOfficialsDelCandidate;
    private int m_intCandidate;
    private final int WINDOW_WIDTH = 1500;
    private final int WINDOW_HEIGHT = 900;

    private final JButton buttonCancel;
    
    public GraphicOfficialsDelCandidate()
    {
        checkOfficialsDelCandidate = 0;

        buttonCancel = new JButton("Back to menu officials");
        buttonCancel.addActionListener(new PlayButtonCancel());
    }
    
    public void startOfficialsDelCandidate(Election myElection)
    {
        /* Initialisation of the interface */
        setTitle("Delete a candidate");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        int heightTab = myElection.getCandidates().size();
        int heightFinal = heightTab/10 +1;
        
        setLayout(new GridLayout(10, heightFinal));
        
        for(int i = 0; i< heightTab; i++)
        {
            JPanel panelButton = new JPanel();
            JButton button = new JButton(myElection.getCandidates().get(i).getFirstName() + " "
                            + myElection.getCandidates().get(i).getLastName() + " "
                            + myElection.getCandidates().get(i).getParty() + " :" + i);
            button.addActionListener(new PlayButtonCandidate());
            panelButton.add(button);    
            add(panelButton);
        }
         
        JPanel panelButtonCancel = new JPanel();
        panelButtonCancel.add(buttonCancel);
        add(panelButtonCancel); 
 
        setVisible(true);
    }
    
    public int getCheckOfficialsDelCandidate()
    {
        return checkOfficialsDelCandidate;
    }
    public int getIntCandidate()
    {
        return m_intCandidate;
    }
    
    private class PlayButtonCandidate implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String source = e.getActionCommand();
            String[] parts = source.split(":");
            m_intCandidate = Integer.parseInt(parts[1]);          
            checkOfficialsDelCandidate = 1;
            setVisible(false);   
        }
    }
    private class PlayButtonCancel implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            setVisible(false);
            checkOfficialsDelCandidate = -1;
        }
    }
}
