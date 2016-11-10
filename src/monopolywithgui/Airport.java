/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopolywithgui;

import Gui.SpecialtyPanels.AirportPanel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Susheel
 */
public class Airport extends Space
{
    /** Pay rent or prompt to but
     * 
     * @param lander 
     */
    @Override
    public void onLanding(Player lander) 
    {
        if(this.getOwner() != null)
        {
            lander.payRent(this);
            JOptionPane.showMessageDialog(null,"You have landed on "+this.getName()+" which is owned by "+this.getOwner().getName()+". Rent of $"+this.getTotalRent()+"M has been subtracted from your balance!","RENT", JOptionPane.INFORMATION_MESSAGE);

        }
        else
        {
            int opt = JOptionPane.showConfirmDialog(null, "Would you like to purchase this vacant airport for $"+this.getPrice()+"M ? (You can view information about this airport on the right)", "You landed on: "+this.getName(), JOptionPane.YES_NO_OPTION);
            System.out.println(opt);
            
            if(opt == 0)
            {
                lander.buyProperties(this);
                JOptionPane.showMessageDialog(null, "You have bought: "+this.getName()+". $"+this.getPrice()+"M has been subtracted from your account. You can manage this property using the 'manage owned assets' menu", "Transaction Completed", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
    
    /** Property card
     * 
     * @return 
     */
    @Override
    public JPanel getInfoPanel() 
    {
        return new AirportPanel(this);
    }
    
}
