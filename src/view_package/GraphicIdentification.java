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

public class GraphicIdentification extends JFrame
{
    private final int WINDOW_WIDTH = 1400;   // Window width
    private final int WINDOW_HEIGHT = 800;  // Window height
    private final String file_name = "pictures\\" + "\\intro2.jpg";
    private final JLabel photoIntroduction;
    private final JLabel messageIntroduction;
    private final JLabel id;
    private final JTextField idEnter;
    private final JLabel password;
    private final JTextField passwordEnter;
    private final JButton enter;
    private boolean check;
    
    public GraphicIdentification()
    {
        /* Initialisation of the interface */
        setTitle("Voting system by 'Go Percer.'");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        check = false;
        photoIntroduction = new JLabel(new ImageIcon(file_name));
        messageIntroduction = new JLabel("Welcome to our voting system, "
                + "please enter your ID and Password");
        id = new JLabel("My ID : ");
        password = new JLabel("My Password : ");
        idEnter = new JTextField(10);
        passwordEnter = new JTextField(10);
        enter = new JButton("identify me");
        enter.addActionListener(new PlayButtonIdentifyMe());
       
        setVisible(true);
    }
    
    public void startIdentification()
    {
        JPanel box = new JPanel();
        box.setLayout(new BoxLayout(box,BoxLayout.Y_AXIS));
        
        JPanel panelMessageIntroduction = new JPanel();
        panelMessageIntroduction.add(messageIntroduction);
        
        JPanel panelMessageID = new JPanel();
        panelMessageID.add(id);
        panelMessageID.add(idEnter);
        
        JPanel panelMessagePassword = new JPanel();
        panelMessagePassword.add(password);
        panelMessagePassword.add(passwordEnter);
        
        JPanel panelButton = new JPanel();
        panelButton.add(enter);
        
        box.add(photoIntroduction);
        box.add(panelMessageIntroduction);
        box.add(panelMessageID);
        box.add(panelMessagePassword);
        box.add(panelButton);
        
        add(box);
        
        setVisible(true);
    }
    
    public boolean getCheck()
    {
        return check;
    }
    
    private class PlayButtonIdentifyMe implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String captureIdString = idEnter.getText();
            String capturePassword = passwordEnter.getText();
            if (captureIdString.equals(""))
            {
                System.out.println("You need to enter an ID");
            }
            else if (capturePassword.equals(""))
            {
                System.out.println("You need to enter a Password");
            }
            else
            {
                int captureId = Integer.parseInt(captureIdString);
                /* DELETE */ System.out.print(captureId + " " + capturePassword);
                check = true;
                setVisible(false);
            }   
        }
    }
}
