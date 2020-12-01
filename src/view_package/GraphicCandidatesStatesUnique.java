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

public class GraphicCandidatesStatesUnique extends JFrame
{
    protected int checkCandidatesStatesUnique = 0;
    private final int WINDOW_WIDTH = 1500;
    private final int WINDOW_HEIGHT = 900;
    private final JButton buttonBack;
    
    public GraphicCandidatesStatesUnique()
    {
        checkCandidatesStatesUnique = 0;
        buttonBack = new JButton("Back to menu candidat");
        buttonBack.addActionListener(new PlayButtonBack());
    }
    
    public void startCandidatesStates(Candidate m_user_candidate)
    {   
        /* Initialisation of the interface */
        setTitle("Score STATES");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        add(buttonBack);
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
