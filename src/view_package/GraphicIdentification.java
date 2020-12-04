/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view_package;

import dao_package.*;

import javax.swing.*; // Needed for Swing classes
import java.awt.*;    // Needed for GridLayout class
import java.awt.event.*;
import java.util.ArrayList;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

public class GraphicIdentification extends JFrame
{
    private int checkIdentification;
    private String m_lastName;
    private String m_firstName;
    private String m_password;
    private final int WINDOW_WIDTH = 1500;
    private final int WINDOW_HEIGHT = 900;
    private final String file_name = "pictures\\" + "\\intro2.jpg";
    private final JLabel photoIntroduction;
    private final JLabel messageIntroduction;
    private final JLabel lastName;
    private final JTextField lastNameEnter;
    private final JLabel firstName;
    private final JTextField firstNameEnter;
    private final JLabel password;
    private final JTextField passwordEnter;
    private final JButton enter;

    public GraphicIdentification()
    {
        checkIdentification = 0;
        photoIntroduction = new JLabel(new ImageIcon(file_name));
        messageIntroduction = new JLabel("Welcome to our voting system, "
                + "please enter your lastname, firstname and Password");
        lastName = new JLabel("My lastname : ");
        firstName = new JLabel("My firstname : ");
        password = new JLabel("My Password : ");
        lastNameEnter = new JTextField(10);
        firstNameEnter = new JTextField(10);
        passwordEnter = new JTextField(10);
        enter = new JButton("identify me");
        enter.addActionListener(new PlayButtonIdentifyMe());
    }
    
    public void startIdentification()
    {
        /* Initialisation of the interface */
        setTitle("Voting system by 'Go Percer.'");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel box = new JPanel();
        box.setLayout(new BoxLayout(box,BoxLayout.Y_AXIS));
        
        JPanel panelMessageIntroduction = new JPanel();
        panelMessageIntroduction.add(messageIntroduction);
        
        JPanel panelLastName = new JPanel();
        panelLastName.add(lastName);
        panelLastName.add(lastNameEnter);
        
        JPanel panelFirstName = new JPanel();
        panelFirstName.add(firstName);
        panelFirstName.add(firstNameEnter);
        
        JPanel panelMessagePassword = new JPanel();
        panelMessagePassword.add(password);
        panelMessagePassword.add(passwordEnter);
        
        JPanel panelButton = new JPanel();
        panelButton.add(enter);
        
        box.add(photoIntroduction);
        box.add(panelMessageIntroduction);
        box.add(panelLastName);
        box.add(panelFirstName);
        box.add(panelMessagePassword);
        box.add(panelButton);
        
        add(box);
        
        setVisible(true);
    }
    
    public int getCheckIdentification()
    {
        return checkIdentification;
    }
    public String getLastName()
    {
        return m_lastName;
    }
    public String getFirstName()
    {
        return m_firstName;
    }
    public String getPassword()
    {
        return m_password;
    }
    
    private class PlayButtonIdentifyMe implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String captureLastName = lastNameEnter.getText();
            String captureFirstName = firstNameEnter.getText();
            String capturePassword = passwordEnter.getText();
            if (captureLastName.equals(""))
            {
                JOptionPane.showMessageDialog(null,"You need to enter a lastname");
            }
            else if (captureFirstName.equals(""))
            {
                JOptionPane.showMessageDialog(null,"You need to enter a firstname");
            }
            else if (capturePassword.equals(""))
            {
                JOptionPane.showMessageDialog(null,"You need to enter a password");
            }
            else
            {
                System.out.print(captureLastName + " " + captureFirstName + " "+ capturePassword);
                m_firstName = captureFirstName;
                m_lastName = captureLastName;
                m_password = capturePassword;
                checkIdentification = -1;
                setVisible(false);
            }   
        }
    }
}
