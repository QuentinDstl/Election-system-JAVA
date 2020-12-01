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
    private String m_lastName;
    private String m_firstName;
    private String m_password;
    private final int WINDOW_WIDTH = 1500;
    private final int WINDOW_HEIGHT = 900;
    private final JLabel lastName;
    private final JTextField lastNameEnter;
    private final JLabel firstName;
    private final JTextField firstNameEnter;
    private final JLabel password;
    private final JTextField passwordEnter;
    private final JButton buttonEnter;
    private final JButton buttonCancel;
    
    public GraphicOfficialsDelCandidate()
    {
        checkOfficialsDelCandidate = 0;
        lastName = new JLabel("Enter a lastname for delete : ");
        firstName = new JLabel("Enter a firstname for delete : ");
        password = new JLabel("Enter a password for delete : ");
        lastNameEnter = new JTextField(10);
        firstNameEnter = new JTextField(10);
        passwordEnter = new JTextField(10);
        buttonEnter = new JButton("Delete a candidate");
        buttonEnter.addActionListener(new PlayButtonDel());
        buttonCancel = new JButton("Back to menu officials");
        buttonCancel.addActionListener(new PlayButtonCancel());
    }
    
    public void startOfficialsDelCandidate()
    {
        /* Initialisation of the interface */
        setTitle("Delete a candiate");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel box = new JPanel();
        box.setLayout(new BoxLayout(box,BoxLayout.Y_AXIS));
        
        JPanel panelLastName = new JPanel();
        panelLastName.add(lastName);
        panelLastName.add(lastNameEnter);
        
        JPanel panelFirstName = new JPanel();
        panelFirstName.add(firstName);
        panelFirstName.add(firstNameEnter);
        
        JPanel panelMessagePassword = new JPanel();
        panelMessagePassword.add(password);
        panelMessagePassword.add(passwordEnter);
        
        JPanel panelButtonEnter = new JPanel();
        panelButtonEnter.add(buttonEnter);
        
        JPanel panelButtonCancel = new JPanel();
        panelButtonCancel.add(buttonCancel);
        
        box.add(panelLastName);
        box.add(panelFirstName);
        box.add(panelMessagePassword);
        box.add(panelButtonEnter);
        box.add(panelButtonCancel);
        
        add(box);
        
        setVisible(true);
    }
    
    public int getCheckOfficialsDelCandidate()
    {
        return checkOfficialsDelCandidate;
    }
    public String getLastName()
    {
        return m_lastName;
    }
    public String getFirstName()
    {
        return m_firstName;
    }
    
    private class PlayButtonDel implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String captureLastName = lastNameEnter.getText();
            String captureFirstName = firstNameEnter.getText();
            String capturePassword = passwordEnter.getText();
            if (captureLastName.equals(""))
            {
                System.out.println("You need to enter a lastname");
            }
            else if (captureFirstName.equals(""))
            {
                System.out.println("You need to enter a firstname");
            }
            else if (capturePassword.equals(""))
            {
                System.out.println("You need to enter a Password");
            }
            else
            {
                m_firstName = captureFirstName;
                m_lastName = captureLastName;
                m_password = capturePassword;
                checkOfficialsDelCandidate = -1;
                setVisible(false);
            }   
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
