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

public class GraphicOfficialsDelElector extends JFrame
{
    private int checkOfficialsDelElector;
    private int m_intElector;
    private final int WINDOW_WIDTH = 1500;
    private final int WINDOW_HEIGHT = 900;

    private final JButton buttonCancel;
    public GraphicOfficialsDelElector()
    {
        checkOfficialsDelElector = 0;

        buttonCancel = new JButton("Back to menu officials");
        buttonCancel.addActionListener(new PlayButtonCancel());
    }
    
    public void startOfficialsDelElector(Election myElection)
    {
        /* Initialisation of the interface */
        setTitle("Delete a candiate");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        //METTRE TAILLE TAB TOTALE
        int heightTab = myElection.getElectors().size();
        int heightFinal = heightTab/10 +1;
        setLayout(new GridLayout(10, heightFinal));
        
        for(int i = 0; i< heightTab; i++)
        {
            JPanel panelButton = new JPanel();
            JButton button = new JButton(myElection.getElectors().get(i).getFirstName() + " "
                            + myElection.getElectors().get(i).getLastName() + " "
                            + myElection.getElectors().get(i).getState().getName()+ " :" + i);
            button.addActionListener(new PlayButtonElector());
            panelButton.add(button);
            add(panelButton);
        }
        
        JPanel panelButtonCancel = new JPanel();
        panelButtonCancel.add(buttonCancel);
        
        setVisible(true);
    }
    
    public int getCheckOfficialsDelElector()
    {
        return checkOfficialsDelElector;
    }
    public int getIntElector()
    {
        return m_intElector;
    }
   
    private class PlayButtonElector implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String source = e.getActionCommand();
            String[] parts = source.split(":");
            m_intElector = Integer.parseInt(parts[1]);          
            checkOfficialsDelElector = -1;
            setVisible(false);   
        }
    }
    private class PlayButtonCancel implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            setVisible(false);
            checkOfficialsDelElector = -1;
        }
    }
}
