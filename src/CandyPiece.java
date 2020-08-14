/**
 *
 * @author J. Barrett
 */

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;
import javax.swing.ImageIcon;

public class CandyPiece extends Rectangle{
    Image candyPic;
    Random r=new Random();
    
    public CandyPiece() {
        super(0, 0, 20, 10);
        x=r.nextInt(500-width);
        y=r.nextInt(500-height);
        int num=r.nextInt(8);
        candyPic=new ImageIcon("candy0"+num+".png").getImage();
    }
    
    public void draw(Graphics g){
        g.drawImage(candyPic, x, y,width, height, null);
    }

}