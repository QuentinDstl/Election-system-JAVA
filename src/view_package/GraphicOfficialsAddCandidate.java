package view_package;

import javax.swing.*; // Needed for Swing classes
import java.awt.event.*;

public class GraphicOfficialsAddCandidate extends JFrame
{
    private int checkOfficialsAddCandidate;
    private String m_lastName;
    private String m_firstName;
    private String m_password;
    private String m_party;
    private final int WINDOW_WIDTH = 1500;
    private final int WINDOW_HEIGHT = 900;
    private final JLabel lastName;
    private final JTextField lastNameEnter;
    private final JLabel firstName;
    private final JTextField firstNameEnter;
    private final JLabel password;
    private final JTextField passwordEnter;
    private final JLabel party;
    private final JTextField partyEnter;
    private final JButton buttonEnter;
    private final JButton buttonCancel;
    
    public GraphicOfficialsAddCandidate()
    {
        checkOfficialsAddCandidate = 0;
        lastName = new JLabel("Enter a new lastname : ");
        firstName = new JLabel("Enter a new firstname : ");
        password = new JLabel("Enter a new password : ");
        party = new JLabel("Enter a party");
        lastNameEnter = new JTextField(10);
        firstNameEnter = new JTextField(10);
        passwordEnter = new JTextField(10);
        partyEnter = new JTextField(10);
        buttonEnter = new JButton("Add a candidate");
        buttonEnter.addActionListener(new PlayButtonAdd());
        buttonCancel = new JButton("Back to menu officials");
        buttonCancel.addActionListener(new PlayButtonCancel());
    }
    
    public void startOfficialsAddCandidate()
    {
        setTitle("Add a candidate");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setLocationRelativeTo(null);
        
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
        
        JPanel panelState = new JPanel();
        panelState.add(party);
        panelState.add(partyEnter);
        
        JPanel panelButtonEnter = new JPanel();
        panelButtonEnter.add(buttonEnter);
        
        JPanel panelButtonCancel = new JPanel();
        panelButtonCancel.add(buttonCancel);
        
        box.add(panelLastName);
        box.add(panelFirstName);
        box.add(panelMessagePassword);
        box.add(panelState);
        box.add(panelButtonEnter);
        box.add(panelButtonCancel);
        
        add(box);
        setVisible(true);
    }
    
    public int getCheckOfficialsAddCandidate()
    {
        return checkOfficialsAddCandidate;
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
    public String getParty()
    {
        return m_party;
    }
    
    private class PlayButtonAdd implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String captureLastName = lastNameEnter.getText();
            String captureFirstName = firstNameEnter.getText();
            String capturePassword = passwordEnter.getText();
            String captureParty = partyEnter.getText();
            
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
                m_party = captureParty;
                checkOfficialsAddCandidate = 1;
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
            checkOfficialsAddCandidate = -1;
        }
    }
}
