/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import monopolywithgui.*;


/**
 *
 * @author Susheel
 */
public class MainWindow extends JFrame
{
    private Player currentPlayer;
    
    public MainWindow(Player currentPlayer)
    {
       super("Main Window");
       
       this.currentPlayer = currentPlayer;
       
       setLayout(new FlowLayout());
       
       add(new InfoPanel(currentPlayer));
       
       add(new Body(this, this.currentPlayer));

    
       setSize(1000, 500);
       setVisible(true);
       
       Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
       this.setLocation(dim.width/2-50, 25);
       
       setResizable(false);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       

    }
    
    
    
}

class InfoPanel extends JPanel
{
    Font nameFont;
    Font smallFont;
    
    private Player currentPlayer;
    
    public InfoPanel(Player currentPlayer)
    {
        this.currentPlayer = currentPlayer;
        setPreferredSize(new Dimension(1000, 75));
        smallFont = new Font(null, 0, 15);
        nameFont = new Font(null, Font.BOLD, 25);
    }
    
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        //this.setBackground(Color.WHITE);
        
        
        
        g.setFont(smallFont);
        g.drawString("Current Player:", 58, 21);
        
        g.setFont(nameFont);
        g.drawString(this.currentPlayer.getName(), 56, 45);
        
        ImageIcon coin = new ImageIcon("images/coin.png");
        coin.paintIcon(this, g, 800, 15);
        
        //double balance = currentPlayer.getBankBalance();
        String balance = String.format("%.1f", currentPlayer.getBankBalance());
        g.drawString("$"+balance+" M", 840, 40);
        
        g.setColor(currentPlayer.getColor());
        g.fillRect(15, 9, 35, 38);
        
    }
}

class Body extends JPanel
{
    MainWindow mainWin;
    Player player;
    
    public Body(MainWindow mainW, Player player)
    {
        this.mainWin = mainW;
        this.player = player;
        //setLayout(new GridLayout(1, 2));
        //setComponentOrientation(LEFT_TO_RIGHT);
        
        setPreferredSize(new Dimension(960, 360));
        setLayout(new FlowLayout());
        //add(new ControlPane());

        //setBackground(Color.WHITE);
        add(new ControlPane(mainWin, this.player));
        //add(new ProPane());
        add(MonopolyMain.board[player.getGamePosition()].getInfoPanel());
    }
}

class ControlPane extends JPanel
{
    MainWindow mainWin;
    Player player;
    ControlPane(MainWindow mainW, Player player)
    {
        this.mainWin = mainW;
        this.player = player;
        setPreferredSize(new Dimension(700, 350));
        
        setLayout(new FlowLayout());
        
        ButtonHandler handler = new ButtonHandler();
        handler.player = player;
        
        JButton manageBank = new JButton("Manage Bank Account");
        manageBank.setPreferredSize(new Dimension(300, 75));
        add(manageBank);
        manageBank.addActionListener(handler);
        
        JButton manageProps = new JButton("Manage Owned Assets");
        manageProps.setPreferredSize(new Dimension(300, 75));
        add(manageProps);
        manageProps.addActionListener(handler);
        
        JButton viewPlayers = new JButton("View Players");
        viewPlayers.setPreferredSize(new Dimension(300, 75));
        add(viewPlayers);
        viewPlayers.addActionListener(handler);
        
        JButton endTurnButton = new JButton("End Turn");
        endTurnButton.setBackground(Color.RED);
        endTurnButton.setForeground(Color.WHITE);
        endTurnButton.setPreferredSize(new Dimension(300, 75));
        add(endTurnButton);
        endTurnButton.addActionListener(handler);
        
        
    }
    
    class ButtonHandler implements ActionListener
    {
        Player player;
        
        
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            if(e.getActionCommand().matches("End Turn"))
            {
                MonopolyMain.changePlayers();
                mainWin.dispose();
            }
            else if (e.getActionCommand().matches("Manage Bank Account"))
            {
                new BankManager(player);
            }
            else if (e.getActionCommand().matches("Manage Owned Assets"))
            {
                new PropertyManager(player);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Coming Soon!", "Action not supported yet", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        
    }
    
}

//class ProPane extends JPanel
//{
//    ProPane()
//    {
//        setPreferredSize(new Dimension(250, 350));
//        setBackground(Color.CYAN);
//    }
//}
