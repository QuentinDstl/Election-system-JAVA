package view_package;

import java_project_desautel_pellen_perold.*;

import javax.swing.*; // Needed for Swing classes
import java.awt.*;    // Needed for GridLayout class
import java.awt.event.*;

public class GraphicOfficialsAddElector extends JFrame
{
    private int checkOfficialsAddElector;
    private int m_intState;
    private String m_lastName;
    private String m_firstName;
    private String m_password;
    private final int WINDOW_WIDTH = 2000;
    private final int WINDOW_HEIGHT = 1500;
    private final JLabel lastName;
    private final JTextField lastNameEnter;
    private final JLabel firstName;
    private final JTextField firstNameEnter;
    private final JLabel password;
    private final JTextField passwordEnter;
    private final JButton buttonEnter;
    private final JButton buttonCancel;
    
    public GraphicOfficialsAddElector()
    {
        checkOfficialsAddElector = 0;
        lastName = new JLabel("Enter a new lastname : ");
        firstName = new JLabel("Enter a new firstname : ");
        password = new JLabel("Enter a new password : ");
        lastNameEnter = new JTextField(10);
        firstNameEnter = new JTextField(10);
        passwordEnter = new JTextField(10);
        buttonEnter = new JButton("Add an elector");
        buttonEnter.addActionListener(new PlayButtonAdd());
        buttonCancel = new JButton("Back to menu officials");
        buttonCancel.addActionListener(new PlayButtonCancel());
    }
    
    public void startOfficialsAddElector(Election myElection)
    {
        /* Initialisation of the interface */
        setTitle("Add a elector");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setLocationRelativeTo(null);
        
        int heightTab = myElection.getStates().size();
        if (heightTab > 10)
        {
            int heightFinal = heightTab/10 +1;
            setLayout(new GridLayout(10, heightFinal));
            System.out.print(heightTab + " " + heightFinal);
        }
        else
        {
            setLayout(new GridLayout(10, 1));
        }
        
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
        
        JPanel panelButtonCancel = new JPanel();
        panelButtonCancel.add(buttonCancel);
        
        box.add(panelLastName);
        box.add(panelFirstName);
        box.add(panelMessagePassword);
        box.add(panelButtonCancel);
        
        add(box);
        
        for(int i = 0; i< myElection.getStates().size() ; i++)
        {
            JPanel panelButton = new JPanel();
            JButton button = new JButton(myElection.getStates().get(i).getName() + " :" + i);
            button.addActionListener(new PlayButtonAdd());
            panelButton.add(button);
            add(panelButton);
        }
        
        setVisible(true);
    }
    
    public int getCheckOfficialsAddElector()
    {
        return checkOfficialsAddElector;
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
    public int getIntState()
    {
        return m_intState;
    }
    
    private class PlayButtonAdd implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String captureLastName = lastNameEnter.getText();
            String captureFirstName = firstNameEnter.getText();
            String capturePassword = passwordEnter.getText();
            String source = e.getActionCommand();
            String[] parts = source.split(":");
            
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
                checkOfficialsAddElector = 1;
                m_intState = Integer.parseInt(parts[1]);
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
            checkOfficialsAddElector = -1;
        }
    }
}
