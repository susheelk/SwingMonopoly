/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopolywithgui;

import Gui.BoardWindow;
import Gui.MainWindow;
import Gui.PlayerSetup;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/** Main Class
 *
 * @author Susheel
 */
public class MonopolyMain 
{

    private static Player[] players;
    private static int currentPlayerNo;
    public static Space[] board;
    
    public static boolean setupFinished = false;
    
    public static HashMap<String, Color> colorMap;
    
    public static boolean gameNotOver = true;
    
    public static BoardWindow boardWindow;
    
    public static MainWindow mainWin;
    
    
    /** Imports all of the space data from text file
     * 
     * @param path the path of t g
     * @throws FileNotFoundException 
     */
    public static void importSpaces(String path) throws FileNotFoundException
    {
        Scanner in = new Scanner(new File(path));
        
        int c = 0;
        
        String type, color, price, name;
        
        while(in.hasNext())
        {
            type = in.next();
            color = in.next();
            price = in.next();
            name = in.next();
            
            board[c] = new Space();
            
            
            switch (type)
            {
                case "GO":
                    board[c] = new Space();
                    //board[c] = new Go();
                    break;
                    
                case "wild":
                    board[c] = new Wild();
                    break;
                
                case "prop":
                    board[c] = new Property();
                    board[c].setColor(colorMap.get(color));
                    board[c].setPrice(Double.parseDouble(price));
                    board[c].setBaseRent(0.3);
                    board[c].setRentPerHouse(0.5);
                    board[c].setPurchasable(true);
                    break;
                
                case "util":
                    board[c] = new Utility();
                    board[c].setPrice(Double.parseDouble(price));
                    break;
                
                case "airport":
                    board[c] = new Airport();
                    board[c].setPrice(Double.parseDouble(price));
                    break;
                    
                case "tax":
                    board[c].setPrice(Double.parseDouble(price));
                    break;
                
            }
            board[c].setName(name);
            
            System.out.println(board[c].getName()+"\t"+board[c].isPurchasable());
            c++;            
        }
    }

    
    
    /** Main Method
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        colorMap = new HashMap<String, Color>();
        colorMap.put("pink", Color.PINK);
        colorMap.put("oran", Color.ORANGE);
        colorMap.put("red", Color.RED);
        colorMap.put("yell", Color.YELLOW);
        colorMap.put("green", Color.YELLOW);
        colorMap.put("Blue", Color.BLUE);
        colorMap.put("purp", new Color(192, 45, 230));
        colorMap.put("liBl", Color.CYAN);
        
        board = new Space[36];
        
        try 
        {
            importSpaces("spaces.txt");
        } 
        catch (FileNotFoundException ex) 
        {
            JOptionPane.showMessageDialog(null, "'spaces.txt' either missing or corrupted", "FILE NOT FOUND!", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }

        PlayerSetup.start();
        while(!setupFinished)
        {
            System.out.println("Waiting for setup to finish....");
        }
        
        players = PlayerSetup.getInitializedPlayers();
    
        /*
        while(gameNotOver)
        {
            for (Player current: players)
            {
                current.move();
                new MainWindow(current);
                
                
            }
        }*/
        
        
        players[0].move();
        mainWin = new MainWindow(players[0]);
        boardWindow = new BoardWindow(players);
        
        
        board[players[currentPlayerNo].getGamePosition()].onLanding(players[currentPlayerNo]);
        
    }
    
    public static void changePlayers()
    {
        if(currentPlayerNo == players.length-1)
        {
            currentPlayerNo = 0;
        }
        else
        {
            currentPlayerNo++;
        }
        
        
        players[currentPlayerNo].move();
        mainWin = new MainWindow(players[currentPlayerNo]);
        boardWindow.update(players);
        
        
        board[players[currentPlayerNo].getGamePosition()].onLanding(players[currentPlayerNo]);
        System.out.println(board[players[currentPlayerNo].getGamePosition()].getName());
    }
    
}
