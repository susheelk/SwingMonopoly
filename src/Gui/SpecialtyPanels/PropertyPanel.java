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
import javax.swing.JPanel;
import monopolywithgui.Space;

/** Panel for a property
 * 
 * @author Susheel
 */
public class PropertyPanel extends JPanel
{
    protected Space property;
    
    /** Constructor
     * 
     * @param prop the property
     */
    public PropertyPanel(Space prop)
    {
        this.property = prop;
        this.setBackground(Color.WHITE);
        setPreferredSize(new Dimension(250, 350));   
    }
    
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        g.setColor(property.getColor());
        g.fillRect(0, 0, 250, 50);
        
        
        if(property.getColor() == Color.CYAN || property.getColor() == Color.YELLOW || property.getColor() == Color.PINK)
        {
            g.setColor(Color.BLACK);
        }
        else
        {
            g.setColor(Color.WHITE);
        }
        
        g.setFont(new Font(null, Font.BOLD, 20));
        
        //int x = 175-(property.getName().length()*9);
        g.drawString(property.getName(), 25, 25);
        
        g.setFont(new Font(null, Font.BOLD, 13));
        g.drawString("Price: $"+property.getPrice()+" M", 30, 43);
        
        if(property.getOwner() != null)
        {
            g.setColor(property.getOwner().getColor());
            g.fillRect(0, 50, 250, 30);
            
            if(property.getOwner().getColor() == Color.CYAN || property.getOwner().getColor() == Color.YELLOW || property.getOwner().getColor() == Color.PINK)
            {
                g.setColor(Color.BLACK);
            }
            else
            {
                g.setColor(Color.WHITE);
            }
            
            g.drawString("Owner: "+property.getOwner().getName()+", with "+property.getNumHouses()+" houses ", 25, 68);
            
            g.setColor(Color.BLACK);
            g.setFont(new Font(null, Font.BOLD, 18));
            g.drawLine(15, 157, 235, 157);
            g.drawString("Total Rent: $"+property.getTotalRent()+"M", 15, 177);
 
        }
        else
        {
            g.setColor(Color.BLACK);
            g.drawString("Not owned by Anyone!", 25, 78);
        }
        
        
        g.setColor(Color.BLACK);
        g.setFont(new Font(null, Font.PLAIN, 18));
        g.drawString(" Base Rent: $"+property.getBaseRent()+"M", 13, 125);
        g.drawString("                + $"+property.getRentPerHouse()+"M per house", 15, 145);
        
        g.setColor(Color.GRAY);
        g.setFont(new Font(null, Font.PLAIN, 13));
        g.drawString("Rent doubles when owner owns ", 30, 300);
        g.drawString("ALL lots in one color group", 39, 315);
        
        
        //g.fillRect(0, 50, 250, 30);
        
        //g.drawString("Rent is $"+property.getBaseRent()+"M", WIDTH, WIDTH);
    }
    
    
}

