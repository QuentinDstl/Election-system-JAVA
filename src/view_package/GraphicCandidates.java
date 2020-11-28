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
    private boolean check;
    private final int WINDOW_WIDTH = 1500;
    private final int WINDOW_HEIGHT = 750;
    
    public GraphicCandidates()
    {
        /* Initialisation of the interface */
        setTitle("Vote for YOUR FUTUR PRESIDENT");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        setVisible(true);
    }
    
    public void startCandidates()
    {
        
    }
}
