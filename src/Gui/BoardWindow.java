/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import monopolywithgui.Player;

/** Frame for board
 *
 * @author Susheel
 */
public class BoardWindow extends JFrame
{
    
    /** Constructor
     * 
     */
    Board board;
    public BoardWindow(Player[] players)
    {
        super("MONOPOLY GameBoard");

        setLayout(new FlowLayout());

        try 
        {
            board = new Board(players);
        } 
        catch (IOException ex) 
        {
            JOptionPane.showMessageDialog(null, "Unable to read 'board.png'", "ERROR", JOptionPane.ERROR);
        }
        
        add(board);
        add(new JButton("Refresh"));
        setSize(900, 900);
        
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    
    public void update(Player[] players)
    {
        board.players = players;
        board.repaint();
        board.revalidate();
        this.repaint();
        this.revalidate();
    }
}

/** Panel for the Board
 * 
 * @author Susheel
 */
/**class Board extends JPanel
{

    public Player players[];
    
    Board(Player[] players)
    {
        
        this.players = players;
        
    }
    
    @Override
    public void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        
        setPreferredSize(new Dimension(800, 800));
        
        ImageIcon boardPic = new ImageIcon("images/board.png");
        
        boardPic.paintIcon(this, g, 0, 0);
        
        setBackground(Color.WHITE);
        
        int x = 0;
        int y = 0;
        
        for(Player i: this.players) 
        {
            g.setColor(i.getColor());
            int position = i.getGamePosition();
            
            
            if(position == 0)
            {
                x = 25;
                y = 700;
            }
            else if (position > 0 && position < 9)
            {
                x = 25;
                y = ((10-position)*63)+31;
            }
            else if (position == 9)
            {
                x = 25;
                y = 25;
            }
            else if (position > 9 && position < 18)
            {
                y = 25;
                x = ((position-9)*63)+98;
            }
            else if(position == 18)
            {
                x = 750;
                y = 10;
            }
            else if(position > 18 && position < 27)
            {
                x = 745;
                y = ((position-18)*63)+95;
            }
            else if (position == 27)
            {
                x = 750;
                y = 660;
            }
            else if(position > 27)
            {
                x = ((20-position)*63)+1105; 
                y= 700;
            }


            g.fillRect(x, y, 40, 40);
        }
        
        setVisible(true);
        
    }
    
}**/

class Board extends JPanel {
    public Player players[];
    private BufferedImage boardPic; 

    Board(Player[] players) throws IOException 
    {
        this.players = players;
        setPreferredSize(new Dimension(800, 800));
        setBackground(Color.WHITE);


        boardPic = ImageIO.read(new File("images/board.png"));
    }

    @Override
    public void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        g.drawImage(boardPic, 0, 0, this);

        int x = 0;
        int y = 0;
        for(Player i: this.players) 
        {
            g.setColor(i.getColor());
            int position = i.getGamePosition();

            if(position == 0) 
            {
                x = 25;
                y = 700;
            }  
            else if (position > 0 && position < 9)  
            {
                x = 25;
                y = ((10-position)*63)+31;
            }  
            else if (position == 9)  
            {
                x = 25;
                y = 25;
            } 
            else if (position > 9 && position < 18) 
            {
                y = 25;
                x = ((position-9)*63)+98;
            } 
            else if(position == 18) {
                x = 750;
                y = 10;
            } 
            else if(position > 18 && position < 27) 
            {
                x = 745;
                y = ((position-18)*63)+95;
            } 
            else if (position == 27) 
            {
                x = 750;
                y = 660;
            } 
            else if(position > 27) 
            {
                x = ((20-position)*63)+1105; 
                y= 700;
            }
            g.fillRect(x, y, 40, 40);
        }
    }
}