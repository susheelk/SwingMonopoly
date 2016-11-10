/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import monopolywithgui.MonopolyMain;
import monopolywithgui.Player;

/** Initializes the player array
 *
 * @author Susheel
 */
public class PlayerSetup
{
    private static int numPlayers;
    private static Player[] playersToInit;
    
    /**
     * 
     */
    public static void start()
    {
        
        JFrame playerNums = new JFrame("Setup Game");
        playerNums.setLayout(new FlowLayout());
        playerNums.setSize(new Dimension(249, 259));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        playerNums.setLocation(dim.width/2-playerNums.getSize().width/2, dim.height/2-playerNums.getSize().height/2);
        playerNums.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        playerNums.add(new JLabel("Select number of players"));
       
        String[] numbers = new String[99];
        for (int i = 2; i < 100; i++) 
        {
            numbers[i-1] = Integer.toString(i);

        }
        
        JList numsList = new JList(numbers);
        numsList.setSelectedIndex(1);
        numsList.setVisibleRowCount(5);
        
        JScrollPane scrollPane = new JScrollPane(numsList);
        scrollPane.setPreferredSize(new Dimension(150, 150));
        playerNums.add(scrollPane);
        
        JButton nextButton = new JButton("NEXT >");
        playerNums.add(nextButton);
        
        nextButton.addActionListener(new ActionListener() 
        {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                numPlayers = numsList.getSelectedIndex()+1;
                //playerNums.dispatchEvent(new WindowEvent(playerNums, WindowEvent.WINDOW_CLOSING));
                playerNums.dispose();
                createPlayers();
                
            }
        });
        
        playerNums.setVisible(true);
    }
    
    /** Gets each player's info
     * 
     */
    public static void createPlayers()
    {
        Color[] colors = {Color.BLACK, Color.BLUE, Color.GRAY, Color.GREEN, Color.ORANGE, Color.PINK, Color.RED, Color.YELLOW};
        String[] colorNames = {"Black", "Blue", "Grey", "Green", "Orange", "Pink", "Red", "Yellow"};
        
        playersToInit = new Player[numPlayers];
        
        for (int j = numPlayers; j > 0; j--) 
        {
            JFrame playerSetup = new JFrame("Player "+j);
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            playerSetup.setLocation(dim.width/2-playerSetup.getSize().width/2-120, dim.height/2-playerSetup.getSize().height/2-120);
            playerSetup.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            playerSetup.setLayout(new FlowLayout());
            playerSetup.setSize(new Dimension(240, 300));
            
            playerSetup.add(new JLabel("Enter Name: "));
            JTextField nameArea = new JTextField(15);
            playerSetup.add(nameArea);
            
            playerSetup.add(new JLabel("Select Color:"));
            JList colorList = new JList(colorNames);
            colorList.setSelectedIndex(0);
            
            JScrollPane scrollPane = new JScrollPane(colorList);
            scrollPane.setPreferredSize(new Dimension(200, 150));
            playerSetup.add(scrollPane);
            
            JButton nextButton = new JButton("SETUP NEXT PLAYER >");
            nextButton.addActionListener(new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent e) 
                {
                    if(nameArea.getText().matches(""))
                    {
                        JOptionPane.showMessageDialog(null, "Enter a name!", "Name not provided", JOptionPane.ERROR_MESSAGE, null);
                    }
                    else
                    {
                        String title = playerSetup.getTitle();
                        int currentPlayerNo = Integer.parseInt(title.substring(7, title.length()))-1;
                        initializePlayer(currentPlayerNo, nameArea.getText(), colors[colorList.getSelectedIndex()]);
                        playerSetup.dispose();

                        if(currentPlayerNo == numPlayers-1) MonopolyMain.setupFinished = true;
                    }
                    
                }
            });
            
            playerSetup.add(nextButton);
            
            playerSetup.setVisible(true);
        }
        
        
    }
    
    /** Initializes a specific player
     * 
     * @param playerNo the index of the player to initialize
     * @param name the name of the player to initialize
     * @param color the player's preferred color
     */
    public static void initializePlayer(int playerNo, String name, Color color)
    {
        playersToInit[playerNo] = new Player(name, color);
    }
    
    public static Player[] getInitializedPlayers()
    {
        return playersToInit;
    }
}
