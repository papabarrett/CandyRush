/**
 *
 * @author J. Barrett
 */

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Baby extends Rectangle{
    double energy;
    Image baby1=new ImageIcon("babyLeft.png").getImage();
    Image baby2=new ImageIcon("babyRight.png").getImage();
    Image babyAlarm1=new ImageIcon("babyAlarmLeft.png").getImage();
    Image babyAlarm2=new ImageIcon("babyAlarmRight.png").getImage();
    Image armyBaby1=new ImageIcon("armyBabyLeft.png").getImage();
    Image armyBaby2=new ImageIcon("armyBabyRight.png").getImage();
    Image armyBabyAlarm1=new ImageIcon("armyBabyAlarmLeft.png").getImage();
    Image armyBabyAlarm2=new ImageIcon("armyBabyAlarmRight.png").getImage();
    Image armyBabySleep=new ImageIcon("armyBabySleep.png").getImage();
    Image babySleep=new ImageIcon("babySleep.png").getImage();
    int count;
    public final int COUNT_MAX=50;
    boolean altPic;
    int countMid;
    Rectangle playArea;
    public final int ARMY_MAX=600;
    boolean left, right, up, down;
    int army=0;
    
    public Baby(Rectangle pA) {
        super(0, 200, 35, 50);
        energy=100;
        count=0;
        countMid=COUNT_MAX/2;
        playArea=pA;
        army=0;
    }

    public double getEnergy() {
        return energy;
    }

    public void draw(Graphics g){
        if(army>0&&energy>30){
            if(altPic)
                g.drawImage(armyBaby1,x,y,null);
            else
                g.drawImage(armyBaby2, x, y, null);
        }
        else if(army>0&&energy<=30&&energy>0){
        
            if(altPic)
                g.drawImage(armyBabyAlarm1,x,y,null);
            else
                g.drawImage(armyBabyAlarm2, x, y, null);
        }
        else if(army>0&&energy<=0){
            g.drawImage(armyBabySleep, x, y, null);
        }
        else if(energy>30){
            if(altPic)
                g.drawImage(baby1,x,y,null);
            else
                g.drawImage(baby2, x, y, null);
        }
        else if(energy<=30&&energy>0){
        
            if(altPic)
                g.drawImage(babyAlarm1,x,y,null);
            else
                g.drawImage(babyAlarm2, x, y, null);
        }
        else if(energy<=0){
            g.drawImage(babySleep, x, y, null);
        }
        else{
        if(altPic)
                g.drawImage(baby1,x,y,null);
            else
                g.drawImage(baby2, x, y, null);
        }
    }
    
    public void eat(){
        energy+=5;
        if(energy>100){
            energy=100;
        }
    }
    
    public void act(){
        energy-=.05;
        count++;
        army--;
        System.out.println("Army"+army);
        if(army<-10)
            army=-10;
        if(count==COUNT_MAX)
            count=0;
        altPic=count<countMid;
        if(energy>0){
            move();
            if(army>0)
                for(int i=0; i<2; i++)
                    move();
        }
    }

    public int getArmy() {
        if(army>0)
        return army;
        return 0;
    }
    
    public void armyUp(){
        army=ARMY_MAX;
    }
    
    public void move(){
        if(right){
            moveRight();
            while(!playArea.contains(this)){
                moveLeft();
                setStop();
            }
        }
        if(left){
            moveLeft();
            while(getMinX()<playArea.getMinX()){
                moveRight();
                setStop();
            }
        }
        if(up){
            moveUp();
            while(getMinY()<playArea.getMinY()){
                moveDown();
                setStop();
            }
        }
        if(down){
            moveDown();
            while(getMaxY()>playArea.getMaxY()){
                moveUp();
                setStop();
            }
        }
        
    }

    public void setLeft() {
        setStop();
        this.left = true;
    }

    public void setRight() {
        setStop();
        this.right = true;
    }

    public void setUp() {
        setStop();
        this.up = true;
    }

    public void setDown() {
        setStop();
        this.down = true;
    }
    
    public void setStop(){        
        left=right=up=down=false;
    }
    
    public void moveRight(){
        x++;
    }
    
    public void moveLeft(){
        x--;
    }
    
    public void moveUp(){
        y--;
    }
    
    public void moveDown(){
        y++;
    }
    
}