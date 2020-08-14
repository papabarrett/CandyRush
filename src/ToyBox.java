/**
 *
 * @author J. Barrett
 */

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;
import javax.swing.ImageIcon;

public class ToyBox extends Rectangle{
    boolean active;
    Image toybox=new ImageIcon("toybox.png").getImage();
    
    public ToyBox(){
        super(0, 0, 50, 35);
        Random r=new Random();
        x=r.nextInt(500);
        y=r.nextInt(500);
        active=true;
    }
    
    public void makeInactive(){
        active=false;
    }
    
    public boolean active(){
        return active;
    }
    
    public void draw(Graphics g) {
        if(active)
            g.drawImage(toybox,x,y,null);
    }
    
}