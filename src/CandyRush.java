/**
 *
 * @author NAME
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;
import java.io.*;
import java.lang.*;

public class CandyRush extends JPanel implements ActionListener, KeyListener{

    javax.swing.Timer timer;
    Image background=new ImageIcon("background.gif").getImage();
    double difficulty;
    final double DSTEP=1;
    ArrayList<CandyPiece> candy=new ArrayList();
    Rectangle playArea;
    EnergyBar energy;
    Baby buddy;
    ToyBox box;
    int candyCount;
    int toyCount=0;
    public final int TOY_MAX=3500;
    Random r=new Random();
    public static void main(String[] args) throws Exception {
        File f=new File("candyRush.bat");
        f.createNewFile();
        JFrame frame=new JFrame("Insert Title Here");
        frame.setSize(516,538);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
        frame.add(new CandyRush());        
        frame.setVisible(true);        
    }
    public CandyRush(){
        //put constructor code here
        difficulty=0;
        toyCount=2500;
        candyCount=0;
        box=new ToyBox();
        box.makeInactive();
        energy=new EnergyBar();
        candy=new ArrayList();
        playArea=new Rectangle(0,180,500,500-180);
        buddy=new Baby(playArea);
        updateCandyList();
        timer=new javax.swing.Timer(10,this);
        timer.start();
        addKeyListener(this);
        setFocusable(true);
    }
    public void actionPerformed(ActionEvent e){
        //type what needs to be performed every time the timer ticks
        if(!box.active())
            toyCount++;
        System.out.println("Toy Count"+toyCount);
        if(toyCount>TOY_MAX)
            moveToyBox();
        buddy.act();
        if(buddy.intersects(box)&&box.active()){
            buddy.armyUp();
            box.makeInactive();
        }
        for(int i=0; i<candy.size();i++){
            if(buddy.intersects(candy.get(i))){
                candyCount++;
                candy.remove(i);
                i=0;
                buddy.eat();
                increaseDifficulty();
            }
        }
        updateCandyList();
        checkGameOver();
        repaint();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        //draw your graphics here
        g.drawImage(background, 0, 0, null);
        for(int i =0; i<candy.size(); i++)
            candy.get(i).draw(g);
        buddy.draw(g);
        box.draw(g);
        energy.draw(g,buddy);
    }
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode()==KeyEvent.VK_RIGHT)
            buddy.setRight();
        if(e.getKeyCode()==KeyEvent.VK_LEFT)
            buddy.setLeft();
        if(e.getKeyCode()==KeyEvent.VK_UP)
            buddy.setUp();
        if(e.getKeyCode()==KeyEvent.VK_DOWN)
            buddy.setDown();
    }
    public void keyTyped(KeyEvent e){
    }
    public void keyReleased(KeyEvent e){  
    }
    public void updateCandyList(){
        while(candy.size()<(int)((100.2-difficulty)/10+1)){
            CandyPiece c=new CandyPiece();
            if(!playArea.contains(c.getCenterX(),c.getCenterY()))
                continue;
            boolean cross=false;
            for(int i=0; i<candy.size(); i++)
                if(c.intersects(candy.get(i)))
                    cross=true;
            if(cross) continue;
            if(energy.intersects(c))
                continue;
            if(buddy.intersects(c))
                continue;
            if(box.intersects(c))
                continue;
            candy.add(c);
        }
    }
    public void increaseDifficulty(){
        difficulty+=DSTEP;
        if(difficulty>100.4)
            difficulty=100.1;
    }
    public void moveToyBox(){
        toyCount=0;
        box=new ToyBox();
        if(!playArea.contains(box))
            moveToyBox();
        if(buddy.intersects(box))
            moveToyBox();
        if(energy.intersects(box))
            moveToyBox();
        for(int i=0; i<candy.size(); i++)
            if(box.intersects(candy.get(i)))
                moveToyBox();
    }
    public void checkGameOver(){
        if(buddy.getEnergy()<-5){
            String s="After eating "+candyCount+" pieces of candy,\n"+
                    "the baby finally fell asleep....";
            JOptionPane.showMessageDialog(null, s);
            int choice=JOptionPane.showConfirmDialog(this, "Would you like to play again?");
            if(choice == 0){
                difficulty=0;
                toyCount=0;
                candyCount=0;
                candy.clear();
                buddy=new Baby(playArea);
                updateCandyList();
            }
            else{
                System.exit(0);
            }
        }
    }
}