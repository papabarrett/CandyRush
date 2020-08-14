/**
 *
 * @author J. Barrett
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class EnergyBar extends Rectangle{

    Image ePic=new ImageIcon("energyBar.png").getImage();
    public EnergyBar() {
        super(50, 450, 400, 50);
    }
    public void draw(Graphics g, Baby buddy){
        g.drawImage(ePic,x,y,null);
        int green=255-(int)((100.0*buddy.getArmy())/buddy.ARMY_MAX);
        System.out.println(green);
        g.setColor(new Color(255,green,0));
        int bar=(int)((buddy.getEnergy()/100)*275);
        g.fillRect(x+118, y+14, bar, 20);
    }

}