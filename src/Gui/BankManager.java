/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;



import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import monopolywithgui.Player;

/**
 *
 * @author Susheel
 */
public class BankManager extends JFrame
{
    Player client;
    public BankManager(Player client)
    {
        super("Bank Manager");
        this.client = client;
        
        setLayout(new FlowLayout());
        setSize(new Dimension(700, 300));
        setResizable(false);
        
        add(new BankInfoPane(client));
        add(new BankControlPane(client));
        setVisible(true);
    }
}

/** The info panel
 * 
 * @author Susheel
 */
class BankInfoPane extends JPanel
{
    Player player;
    
    /** 
     * 
     * @param player 
     */
    BankInfoPane(Player player)
    {
        this.player = player;
        
        setPreferredSize(new Dimension(690, 50));
        setBackground(Color.WHITE);
        
    }
    
    /** Draws the info pane
     * 
     * @param g 
     */
    @Override
    public void paintComponent(Graphics g)
    {
        g.setFont(new Font(null, Font.BOLD, 25));
        g.drawString("$"+player.getBankBalance()+" M", 50, 35);
        
        ImageIcon coin = new ImageIcon("images/coin.png");
        coin.paintIcon(this, g, 10, 10);
    }
}



class BankControlPane extends JPanel
{
    Player player;

    BankControlPane(Player player) 
    {
        this.player = player;
        setPreferredSize(new Dimension(690, 230));
        
        ButtonHandler handler = new ButtonHandler();
        handler.player = player;
        
        JButton loan = new JButton("Loans");
        loan.addActionListener(handler);
        add(loan);
        
        JButton history = new JButton("View Transaction History");
        history.addActionListener(handler);
        add(history);
        
        JButton transfer = new JButton("Transfer money");
        transfer.addActionListener(handler);
        add(transfer);
        
        
        
    }
    
    class ButtonHandler implements ActionListener
    {
        Player player;
        
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            if(e.getActionCommand().matches("View Transaction History"))
            {
                new TransactionHistoryViewer(player);
            }
        }
        
    }

}

class TransactionHistoryViewer extends JFrame
{
    Player player;

    public TransactionHistoryViewer(Player player) 
    {
        super("Transaction History");
        
        this.player = player;
        setLayout(new FlowLayout());
        
        setSize(400, 150);
        
        String[] arr = new String[player.getTransactionHistory().size()];
        //arr = player.getTransactionHistory().toArray(arr);
        
        for (int i = 0; i<player.getTransactionHistory().size(); i++)
        {
            arr[i] = player.getTransactionHistory().get(i);
        }
        
        JList history = new JList(arr);
        history.setSelectedIndex(0);
        history.setSize(400, 145);
        JScrollPane scroll = new JScrollPane(history);
        
        add(history);
        
        setVisible(true);
        
        
        
    }
    
}