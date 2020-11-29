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

public class GraphicCandidates extends GraphicIdentification
{
    private final int WINDOW_WIDTH = 1500;
    private final int WINDOW_HEIGHT = 900;
    private final JLabel messageEnter;
    private final JButton buttonNational;
    private final JButton buttonState;
    private final JButton buttonCancel;
    
    public GraphicCandidates()
    {
        /* Initialisation of the interface */
        setTitle("See your futur victory");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        messageEnter = new JLabel("You are the candidate DONALD TRUMP");
        buttonNational = new  JButton("See my national score");
        buttonNational.addActionListener(new PlayButtonShowNational());
        buttonState = new  JButton("See my states score");
        buttonState.addActionListener(new PlayButtonShowState());
        buttonCancel = new JButton("Cancel my authentification, and back to identification");
        buttonCancel.addActionListener(new PlayButtonCancel());
    }
    
    public void startCandidates()
    {
        setLayout(new GridLayout(4,1));
        
        JPanel panelMessageEnter = new JPanel();
        panelMessageEnter.add(messageEnter);
        
        add(panelMessageEnter);
        add(buttonNational);
        add(buttonState);
        add(buttonCancel);
        
        setVisible(true);
    }
    
    private class PlayButtonShowNational implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            setVisible(false);
            GraphicCandidatesNational test4 = new GraphicCandidatesNational();
            test4.startCandidatesNational();
            boolean checkCandidatesNationalOut = false;
           
            
            while (checkCandidatesNationalOut == false) {  
                System.out.print("");
                checkCandidatesNationalOut = test4.getCheckCandidatesNational();
            }
            
            System.out.println("HEYYYYYY");
        }
    }
    
    
    private class PlayButtonShowState implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            System.out.println("JE REGARDE STATE");
        }
    }
    private class PlayButtonCancel implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            setVisible(false);
            checkCandidates = true;
        }
    }
}
