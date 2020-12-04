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

public class GraphicOfficialsStartPause extends JFrame
{
    protected int checkOfficialsStartPause = 0;
    private final int WINDOW_WIDTH = 1500;
    private final int WINDOW_HEIGHT = 900;
    private final JButton buttonStart;
    private final JButton buttonPause;
    private final JButton buttonEnd;
    private final JButton buttonBack;
    
    public GraphicOfficialsStartPause()
    {
        checkOfficialsStartPause = 0;
        buttonBack = new JButton("Back to menu official");
        buttonBack.addActionListener(new PlayButtonBack());
        buttonStart = new JButton("Start vote");
        buttonStart.addActionListener(new PlayButtonStart());
        buttonPause = new JButton("Pause vote");
        buttonPause.addActionListener(new PlayButtonPause());
        buttonEnd = new JButton("End vote");
        buttonEnd.addActionListener(new PlayButtonEnd());
    }
    
    public void startOfficialsStartPause()
    {
        /* Initialisation of the interface */
        setTitle("Vote menu");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(2, 3));
        
        JLabel textVoid1 = new JLabel("");
        JPanel panelVoid1 = new JPanel();
        panelVoid1.add(textVoid1);
        
        JLabel messageStatus = new JLabel("Le vote est actuellment en pause");
        JPanel panelMessageStatus = new JPanel();
        panelMessageStatus.add(messageStatus);
        
        JPanel panelButtonStart = new JPanel();
        panelButtonStart.add(buttonStart);
        JPanel panelButtonPause = new JPanel();
        panelButtonPause.add(buttonPause);
        JPanel panelButtonEnd = new JPanel();
        panelButtonEnd.add(buttonEnd);
        JPanel panelButtonBack = new JPanel();
        panelButtonBack.add(buttonBack);
        
        add(panelVoid1);
        add(panelMessageStatus);
        add(panelButtonBack);
        add(panelButtonStart);
        add(panelButtonPause);
        add(panelButtonEnd);
        
        setVisible(true);
    }
    
    
    public int getCheckOfficialsStartPause()
    {
        return checkOfficialsStartPause;
    }
    
    private class PlayButtonStart implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            setVisible(false);

            
            System.out.println("Je commence le vote");
        }
    }
    private class PlayButtonPause implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            setVisible(false);
            
            System.out.println("Je mets le vote en pause");
        }
    }
    private class PlayButtonEnd implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            setVisible(false);
            
            System.out.println("Je stop le vote");
        }
    }
    private class PlayButtonBack implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            setVisible(false);
            checkOfficialsStartPause = -1;
        }
    }
}
