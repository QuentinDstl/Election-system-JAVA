package view_package;

import java_project_desautel_pellen_perold.*;

import javax.swing.*; // Needed for Swing classes
import java.awt.*;    // Needed for GridLayout class
import java.awt.event.*;
import org.jfree.chart.*; 
import org.jfree.data.general.*;

public class GraphicCandidatesStatesUnique extends JFrame
{
    protected int checkCandidatesStatesUnique = 0;
    private final int WINDOW_WIDTH = 1500;
    private final int WINDOW_HEIGHT = 900;
    private final JPanel panelCamembert;
    private final JButton buttonBack;
    
    public GraphicCandidatesStatesUnique()
    {
        checkCandidatesStatesUnique = 0;
        panelCamembert = new JPanel(new BorderLayout());
        buttonBack = new JButton("Back to menu officials");
        buttonBack.addActionListener(new PlayButtonBack());
    }
    
    public void startCandidatesStatesUnique(Election myElection, int IntState)
    {   
        JFreeChart pieChart;
        
        System.out.println(" Mon id State est : " + IntState);
        setTitle("Score STATES");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 
        
        DefaultPieDataset pieDataset = new DefaultPieDataset(); 
        for (int i = 0; i < myElection.getCandidates().size(); i++)
        {
            pieDataset.setValue(myElection.getCandidates().get(i).getLastName() + " " + myElection.getCandidates().get(i).getFirstName()
                    , myElection.getStates().get(IntState).getNbVotesCandidateInState(myElection.getCandidates().get(i).getLastName())); 
        }

        if (myElection.getStates().get(IntState).isAllWin() == true)
            pieChart = ChartFactory.createPieChart("State : " + myElection.getStates().get(IntState).getName() + " is AllWin : " +  
                +  myElection.getStates().get(IntState).getNbrElector() + " great voters", pieDataset, true, true, true); 
        else 
            pieChart = ChartFactory.createPieChart("State : " + myElection.getStates().get(IntState).getName() + " is NoAllWin : " +   
                +  myElection.getStates().get(IntState).getNbrElector() + " great voters", pieDataset, true, true, true); 
        ChartPanel cPanel = new ChartPanel(pieChart); 
        panelCamembert.add(cPanel); 
        
        JPanel panelButtonBack = new JPanel();
        panelButtonBack.add(buttonBack);
        
        JPanel box = new JPanel();
        box.setLayout(new BoxLayout(box,BoxLayout.Y_AXIS));
        box.add(panelCamembert);
        box.add(panelButtonBack);
        
        add(box);
        setVisible(true);
    }
    
    public int getCheckCandidatesStatesUnique()
    {
        return checkCandidatesStatesUnique;
    }
    
    private class PlayButtonBack implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            setVisible(false);
            checkCandidatesStatesUnique = -1;
        }
    }
}
