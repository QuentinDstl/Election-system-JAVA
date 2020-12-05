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
import java.lang.Object;
import java.awt.event.*;  
import org.jfree.chart.*; 
import org.jfree.chart.plot.*; 
import org.jfree.data.general.*;

public class GraphicOfficialsStatesUnique extends JFrame
{
    protected int checkOfficialsStatesUnique = 0;
    private final int WINDOW_WIDTH = 1500;
    private final int WINDOW_HEIGHT = 900;
    private final JPanel panelCamembert;
    private final JButton buttonBack;
    
    public GraphicOfficialsStatesUnique()
    {
        checkOfficialsStatesUnique = 0;
        panelCamembert = new JPanel(new BorderLayout());
        buttonBack = new JButton("Back to menu states");
        buttonBack.addActionListener(new PlayButtonBack());
    }
    
    public void startOfficialsStatesUnique(Election myElection, int IntState)
    {   
        setTitle("Score STATES");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 
        
        DefaultPieDataset pieDataset = new DefaultPieDataset(); 
        
        for (int i = 0; i < myElection.getCandidates().size(); i++)
        {
            pieDataset.setValue(myElection.getCandidates().get(i).getLastName() + " " + myElection.getCandidates().get(i).getFirstName()
                    , new Integer(myElection.getStates().get(IntState).getNbVotesCandidateInState(      myElection.getCandidates().get(i).getLastName()))    ); 
        }

        JFreeChart pieChart = ChartFactory.createPieChart("See scores beetween other candidates", pieDataset, true, true, true); 
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
    
    public int getCheckOfficialsStatesUnique()
    {
        return checkOfficialsStatesUnique;
    }
    
    private class PlayButtonBack implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            setVisible(false);
            checkOfficialsStatesUnique = -1;
        }
    }
}
