/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view_package;

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

public class GraphicCandidatesNational extends GraphicCandidates
{
    private boolean checkCandidatesNational;
    private final int WINDOW_WIDTH = 1500;
    private final int WINDOW_HEIGHT = 900;
    private final JPanel panelCamembert;
    private final JLabel messageEnter;
    private final JButton buttonBack;
    
    public GraphicCandidatesNational()
    {
        /* Initialisation of the interface */
        setTitle("Score NATIONAL");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);     
        
        panelCamembert = new JPanel(new BorderLayout());
        messageEnter = new JLabel("You are the candidate DONALD TRUMP");
        buttonBack = new JButton("Back to menu candidat");
        buttonBack.addActionListener(new PlayButtonBack());
        
        setVisible(true);
    }
    
    public void startCandidatesNational()
    {
        DefaultPieDataset pieDataset = new DefaultPieDataset(); 
        pieDataset.setValue("You ", new Integer(27)); 
        pieDataset.setValue("Valeur2", new Integer(10)); 
        pieDataset.setValue("Valeur3", new Integer(50)); 
        pieDataset.setValue("Valeur4", new Integer(5)); 

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
    
    public boolean getCheckCandidatesNational ()
    {
        return checkCandidatesNational;
    }
    
    private class PlayButtonBack implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            setVisible(false);
            checkCandidatesNational = true;
        }
    }
}
