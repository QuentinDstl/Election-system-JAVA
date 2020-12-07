package view_package;

import java_project_desautel_pellen_perold.*;

import javax.swing.*; // Needed for Swing classes
import java.awt.*;    // Needed for GridLayout class
import java.awt.event.*;

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
        setTitle("Delete an elector");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        
        
        JPanel test = new JPanel();
        test.setPreferredSize(new Dimension(WINDOW_WIDTH,20000));
        
        JScrollPane scrollFrame = new JScrollPane(test);
        test.setAutoscrolls(true);
        scrollFrame.setPreferredSize(new Dimension(WINDOW_WIDTH,WINDOW_HEIGHT));
        
        int heightTab = myElection.getElectors().size();
        if (heightTab > 100)
        {
            int heightFinal = heightTab/2 +1;
            test.setLayout(new GridLayout(heightFinal, 2));
        }
        else
        {
            test.setLayout(new GridLayout(10, 1));
        }
        
        
        for(int i = 0; i< heightTab; i++)
        {
            JPanel panelButton = new JPanel();
            JButton button = new JButton(myElection.getElectors().get(i).getFirstName() + " "
                            + myElection.getElectors().get(i).getLastName() + " "
                            + myElection.getElectors().get(i).getState().getName()+ " :" + i);
            button.addActionListener(new PlayButtonElector());
            panelButton.add(button);
            test.add(panelButton);
        }
        
        JPanel panelButtonCancel = new JPanel();
        buttonCancel.setBackground(Color.RED);
        panelButtonCancel.add(buttonCancel);
        test.add(panelButtonCancel);
        
        add(scrollFrame);
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
            checkOfficialsDelElector = 1;
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
