/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopolywithgui;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;



/**
 *
 * @author Susheel
 */
public class Space 
{
    //Basic Info
    private String name;
    
    //Purchase info
    private boolean Purchasable;
    private double price;
    private double baseRent;
    private double rentPerHouse;
    private int numHouses;
    
    private Color color;
    
    private Player owner;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPurchasable() {
        return Purchasable;
    }

    public void setPurchasable(boolean isPurchasable) {
        this.Purchasable = isPurchasable;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getBaseRent() {
        return baseRent;
    }

    public void setBaseRent(double baseRent) {
        this.baseRent = baseRent;
    }

    public double getRentPerHouse() {
        return rentPerHouse;
    }
    
    public double getTotalRent()
    {
        return this.baseRent +(this.numHouses*this.rentPerHouse);
    }
    
    public void setRentPerHouse(double rentPerHouse) {
        this.rentPerHouse = rentPerHouse;
    }

    public int getNumHouses() {
        return numHouses;
    }

    public void setNumHouses(int numHouses) {
        this.numHouses = numHouses;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    
    

    
    public void onLanding(Player lander)
    {
        
    }
    
    public JPanel getInfoPanel()
    {
        JPanel infoPanel = new JPanel();
        infoPanel.setPreferredSize(new Dimension(250, 350));
        infoPanel.setBackground(Color.WHITE);
        
        infoPanel.add(new JLabel(this.name));
        
        return infoPanel;
    }
}
