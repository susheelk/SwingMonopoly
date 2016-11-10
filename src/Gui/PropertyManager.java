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
import javax.swing.ImageIcon;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import monopolywithgui.Player;

/**
 *
 * @author Susheel
 */
public class PropertyManager extends JFrame
{
    Player player;
    JPanel propPanel;
    
    public PropertyManager(Player player) 
    {
        this.player = player;
        this.propPanel = new JPanel();
        setLayout(new FlowLayout());
        setSize(750, 600);
        
        
        
        add(new PropManageInfoPanel(player));
        add(new PropManageMain(player, this));
        add(propPanel);
        setVisible(true);
    }
    
    
}

class PropManageInfoPanel extends JPanel
{
    Player player;
    ImageIcon coin;

    public PropManageInfoPanel(Player player) 
    {
        this.player = player;
        setPreferredSize(new Dimension(750, 75));
        //setBackground(Color.WHITE);
        coin = new ImageIcon("images/coin.png");
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        coin.paintIcon(this, g, 15, 15);
        String balance = String.format("%.1f", player.getBankBalance());
        g.setFont(new Font(null, Font.BOLD, 25));
        g.drawString("$"+balance+" M", 55, 40);
    }
    
    
    
    
}

class PropManageMain extends JPanel
{
    Player player;
    PropertyManager mainMan;

    public PropManageMain(Player player, PropertyManager mainMan) 
    {
        this.player = player;
        this.mainMan = mainMan;
        
        
        String[] names = new String[player.getOwnedProperties().size()];
        
        for (int i = 0; i < names.length; i++) 
        {
            names[i] = player.getOwnedProperties().get(i).getName();
        }
        
        JList props = new JList(names);
        
        JScrollPane scroll = new JScrollPane(props);
        scroll.setPreferredSize(new Dimension(250, 350));
        props.addListSelectionListener(new ListSelectionListener() 
        {

            @Override
            public void valueChanged(ListSelectionEvent e) 
            {
                mainMan.remove(mainMan.propPanel);
                mainMan.propPanel = (player.getOwnedProperties().get(props.getSelectedIndex()).getInfoPanel());
                mainMan.add(mainMan.propPanel);
                mainMan.revalidate();
            }
        });
        
        add(scroll);
        
        
    }
    
    
}