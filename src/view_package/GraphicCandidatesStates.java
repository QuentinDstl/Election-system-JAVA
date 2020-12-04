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
        /* Copy to display states*/
        //m_candidate = m_user_candidate;
        
        /* Initialisation of the interface */
        setTitle("Score STATES");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        //METTRE TAILLE TAB TOTALE
        int heightTab = myElection.getStates().size();
        int heightFinal = heightTab/10 +1;
        setLayout(new GridLayout(10, heightFinal));
        
        for(int i = 0; i< heightTab; i++)
        {
            JPanel panelButton = new JPanel();
            JButton button = new JButton(myElection.getStates().get(i).getName() + " :" + i);
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
