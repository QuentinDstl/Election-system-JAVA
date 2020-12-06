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
import java.sql.SQLException;
import org.jfree.chart.*; 
import org.jfree.chart.plot.*; 
import org.jfree.data.general.*;

public class GraphicOfficialsNational extends JFrame
{
    protected int checkOfficialsNational = 0;
    private final int WINDOW_WIDTH = 1500;
    private final int WINDOW_HEIGHT = 900;
    private final JPanel panelCamembert;
    private final JButton buttonBack;
    
    public GraphicOfficialsNational()
    {    
        checkOfficialsNational = 0;
        panelCamembert = new JPanel(new BorderLayout());
        buttonBack = new JButton("Back to menu officials");
        buttonBack.addActionListener(new PlayButtonBack());
    }
    
    public void startOfficialsNational(Election myElection) throws SQLException
    {
        setTitle("Score NATIONAL");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 
        
        int nbrVoted = 0;
        int nbrNoVoted = 0;
        
        DefaultPieDataset pieDataset = new DefaultPieDataset(); 
        for (int i = 0; i < myElection.getCandidates().size(); i++)
        {
            pieDataset.setValue(myElection.getCandidates().get(i).getLastName() + " " + myElection.getCandidates().get(i).getFirstName()
                    , myElection.getCandidates().get(i).getNbVotesTotal()); 
            nbrVoted = nbrVoted + myElection.getCandidates().get(i).getNbVotesTotal();
        }
        nbrNoVoted = new Integer(myElection.getElectors().size()) 
                - nbrVoted;
        pieDataset.setValue(" No vote", new Integer(nbrNoVoted));
                
        JFreeChart pieChart = ChartFactory.createPieChart("Score National : " + myElection.elector_from_db.getNumberOfElectorsIntoTable() + " electors , " + nbrVoted + " voted", pieDataset, true, true, true); 
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
    
     public int getCheckOfficialsNational ()
    {
        return checkOfficialsNational;
    }
    
    private class PlayButtonBack implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            setVisible(false);
            checkOfficialsNational = -1;
        }
    }
}
