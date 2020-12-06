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

public class GraphicOfficialsWinner extends JFrame {
    
    private int checkOfficialsWinner = 0;
    private final int WINDOW_WIDTH = 1500;
    private final int WINDOW_HEIGHT = 900;
    private final JButton buttonBack;
    
    public GraphicOfficialsWinner()
    {
        buttonBack = new JButton("Back to menu officials");
        buttonBack.addActionListener(new PlayButtonBack());
    }
    
    public void startOfficialsWinner(Election myElection)
    {
        setTitle("See the potential winner");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 
        
        ArrayList<Integer> myTabScoreCandidates = new ArrayList<Integer>();
        
        for (int a=0; a<myElection.getCandidates().size(); a++)
        {
            myTabScoreCandidates.add(0);
        }
        
        JPanel test = new JPanel();
        test.setPreferredSize(new Dimension(WINDOW_WIDTH,2000));
        JScrollPane scrollFrame = new JScrollPane(test);
        test.setAutoscrolls(true);
        scrollFrame.setPreferredSize(new Dimension(WINDOW_WIDTH,WINDOW_HEIGHT));
        
        JPanel box = new JPanel();
        box.setLayout(new BoxLayout(box,BoxLayout.Y_AXIS));
        
        for (int i=0; i<myElection.getStates().size(); i++)
        {
            JLabel messageState = new JLabel(myElection.getStates().get(i).getName() + " : " + myElection.getStates().get(i).getNbrElector() 
                    + " is win by " );
            JPanel panelState = new JPanel();
            panelState.add(messageState);
            box.add(panelState);
        }
        
        JPanel panelButtonCancel = new JPanel();
        panelButtonCancel.add(buttonBack);
        box.add(buttonBack);
        
        test.add(box);
        add(scrollFrame);
        setVisible(true);
    }
         
    public int getCheckOfficialsWinner ()
    {
        return checkOfficialsWinner;
    }
    
    private class PlayButtonBack implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            setVisible(false);
            checkOfficialsWinner = -1;
        }
    }
}
