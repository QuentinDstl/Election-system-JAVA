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

public class GraphicElectors extends GraphicIdentification
{
    private boolean check;
    private final int WINDOW_WIDTH = 1500;
    private final int WINDOW_HEIGHT = 750;
    private final String imageBiden = "pictures\\" + "\\biden.jpg";
    private final String imageTrump = "pictures\\" + "\\trump.jpg";
    private final JLabel messageBiden;
    private final JLabel messageTrump;
    private final JButton buttonBiden;
    private final JButton buttonTrump;
    private final JButton buttonCancel;
    
    public GraphicElectors()
    {
        /* Initialisation of the interface */
        setTitle("Vote for YOUR FUTUR PRESIDENT");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        check = false;
        buttonBiden = new JButton(new ImageIcon(imageBiden));
        messageBiden = new JLabel("Joe Biden, Democratic Party");
        buttonBiden.addActionListener(new PlayButtonBiden());
        buttonTrump = new JButton(new ImageIcon(imageTrump));
        messageTrump = new JLabel("Donald Trump, Republican Party");
        buttonTrump.addActionListener(new PlayButtonTrump());
        buttonCancel = new JButton("Cancel my vote");
        buttonCancel.addActionListener(new PlayButtonCancel());
        
        setVisible(true);
    }
    
    public void startElectors()
    {
        setLayout(new GridLayout(1, 3));
        
        JPanel panelCancel = new JPanel();
        panelCancel.add(buttonCancel);
        JPanel panelBiden= new JPanel();
        panelBiden.add(buttonBiden);
        panelBiden.add(messageBiden);
        JPanel panelTrump = new JPanel();
        panelTrump.add(buttonTrump);
        panelTrump.add(messageTrump);
        
        add(panelBiden);
        add(panelCancel);
        add(panelTrump);
        
        setVisible(true);
    }
    
    public boolean getCheck()
    {
        return check;
    }
    
    private class PlayButtonTrump implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            System.out.println("JE VOTE POUR LE FOU");
        }
    }
    private class PlayButtonBiden implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            System.out.println("JE VOTE POUR L'AVENIR");
        }
    }
    private class PlayButtonCancel implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            System.out.println("JE SUIS UNE MERDE ET JE NE VEUX PLUS VOTER");
        }
    }
}
