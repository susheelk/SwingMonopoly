/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopolywithgui;

import Gui.MainWindow;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

/** Player
 *
 * @author Susheel
 */
public class Player 
{
    //Details
    private String name;
    private Color color;
    
    //Money
    private double bankBalance;
    private double allAssets;
    private ArrayList<String> transactionHistory;
    
    //Ownerships
    private ArrayList<Space> ownedProperties;
    private ArrayList<Space> ownedAirports;
    private ArrayList<Space> ownedUtilities;
    
    //Misc
    private int gamePosition;

    
    public Player(String name, Color color) 
    {
        this.name = name;
        this.color = color;
        
        this.bankBalance = 15.0;
        this.allAssets = 0;
        this.transactionHistory = new ArrayList<>(5);
        
        this.ownedProperties = new ArrayList<>(5);
        this.ownedAirports = new ArrayList<>(5);
        this.ownedUtilities = new ArrayList<>(5);
        
        this.gamePosition = 0;

    }

    public String getName() 
    {
        return name;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public Color getColor() 
    {
        return color;
    }

    public void setColor(Color color) 
    {
        this.color = color;
    }

    public double getBankBalance() 
    {
        return bankBalance;
    }

    public void setBankBalance(double bankBalance) 
    {
        this.bankBalance = bankBalance;
    }

    public double getAllAssets() 
    {
        return allAssets;
    }

    public void setAllAssets(double allAssets)
    {
        this.allAssets = allAssets;
    }

    public ArrayList<String> getTransactionHistory() 
    {
        return transactionHistory;
    }

    public void setTransactionHistory(ArrayList<String> transactionHistory) 
    {
        this.transactionHistory = transactionHistory;
    }

    public ArrayList<Space> getOwnedProperties() 
    {
        return ownedProperties;
    }

    public void setOwnedProperties(ArrayList<Space> ownedProperties) 
    {
        this.ownedProperties = ownedProperties;
    }

    public ArrayList<Space> getOwnedAirports() 
    {
        return ownedAirports;
    }

    public void setOwnedAirports(ArrayList<Space> ownedAirports) 
    {
        this.ownedAirports = ownedAirports;
    }

    public ArrayList<Space> getOwnedUtilities() 
    {
        return ownedUtilities;
    }

    public void setOwnedUtilities(ArrayList<Space> ownedUtilities) 
    {
        this.ownedUtilities = ownedUtilities;
    }

    public int getGamePosition() 
    {
        return gamePosition;
    }

    public void setGamePosition(int gamePosition) 
    {
        this.gamePosition = gamePosition;
    }
    
    
    
    /** Buys a property
     * 
     * @param toBuy the space to buy
     */
    public void buyProperties(Space toBuy)
    {
        toBuy.setOwner(this);
        this.bankBalance -= toBuy.getPrice();
        this.allAssets += toBuy.getPrice();
        this.ownedProperties.add(toBuy);
        this.transactionHistory.add("(+$"+toBuy.getPrice()+" M) Bought property '"+toBuy.getName()+"'");
        
        MonopolyMain.mainWin.dispose();
        MonopolyMain.mainWin = new MainWindow(this);
    }
    
    public void buyAirports(Space toBuy)
    {
        toBuy.setOwner(this);
        this.bankBalance -= toBuy.getPrice();
        this.allAssets += toBuy.getPrice();
        this.ownedAirports.add(toBuy);
        this.transactionHistory.add("(+$"+toBuy.getPrice()+" M) Bought airport '"+toBuy.getName()+"'");
        
        MonopolyMain.mainWin.dispose();
        MonopolyMain.mainWin = new MainWindow(this);
    }
    
    public void addHistory(String toAdd)
    {
        this.transactionHistory.add(toAdd);
    }
    
    /** Pays rent to the owner of a space
     * 
     * @param toPay 
     */
    public void payRent(Space toPay)
    {
        this.bankBalance -= toPay.getTotalRent();
        toPay.getOwner().setBankBalance(toPay.getOwner().getBankBalance()+toPay.getTotalRent());
        this.transactionHistory.add("(-$"+toPay.getTotalRent()+"M ) Paid rent for: "+toPay.getName());
        toPay.getOwner().addHistory("(+$"+toPay.getTotalRent()+" M) Collected rent fot: "+toPay.getName());
        
        MonopolyMain.mainWin.dispose();
        MonopolyMain.mainWin = new MainWindow(this);
    }
    
    /** Moves the player based on a random diceroll
     * 
     */
    public void move()
    {
        Random dice = new Random();
        
        this.gamePosition += dice.nextInt(6)+1;
        
        if(this.gamePosition >= 36)
        {
            this.gamePosition -= 36;
        }
        
    }
    
    
}
