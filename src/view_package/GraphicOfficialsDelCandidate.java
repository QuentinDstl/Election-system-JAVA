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
        
        //METTRE TAILLE TAB TOTALE
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
