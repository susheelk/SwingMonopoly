/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.SpecialtyPanels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import monopolywithgui.Space;

/** Airport property card
 *
 * @author Susheel
 */
public class AirportPanel extends JPanel
{
    Space airport;
    ImageIcon pic;

    public AirportPanel(Space airport) 
    {
        this.airport = airport;
        this.setBackground(Color.WHITE);
        setPreferredSize(new Dimension(250, 350));
        
        pic = new ImageIcon("images/card/airport.png");
    }
    
    /** Draws the card
     * 
     * @param g graphics
     */
    @Override
    protected void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        
        g.setFont(new Font(null, Font.BOLD, 20));
        g.drawString(airport.getName(), 35, 30);
        
        pic.paintIcon(this, g, 50, 50);
        
        g.setFont(new Font(null, Font.BOLD, 15));
        g.drawString("Price: $"+airport.getPrice()+" M", 65, 235);
        
        if(airport.getOwner() != null)
        {
            g.setColor(airport.getOwner().getColor());
            g.fillRect(0, 305, 250, 45);
            
            if(airport.getOwner().getColor() == Color.CYAN || airport.getOwner().getColor() == Color.YELLOW || airport.getOwner().getColor() == Color.PINK)
            {
                g.setColor(Color.BLACK);
            }
            else
            {
                g.setColor(Color.WHITE);
            }
            
            g.drawString("Owner: "+airport.getOwner().getName(), 25, 335);
            
            g.setColor(Color.BLACK);
            g.setFont(new Font(null, Font.BOLD, 18));
            g.drawString("Total Rent: $"+airport.getTotalRent()+"M", 65, 260);
 
        }
        else
        {
            g.setColor(Color.BLACK);
            g.drawString("Not owned by Anyone!", 25, 335);
        }
    }
    
    
    
    
}
