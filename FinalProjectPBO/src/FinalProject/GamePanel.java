/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FinalProject;

/**
 *
 * @author Asus
 */
import javax.swing.*;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.nio.*;
import java.io.*;
import java.util.ArrayList;
//import javax.swing.JPanel;
//import java.awt.event.ActionListener;
//import java.awt.event.ActionEvent;
//import java.awt.Graphics;
//import java.awt.event.KeyAdapter;
//import java.awt.event.KeyEvent;

//polymorphism dari JPanel
public class GamePanel extends JPanel implements Runnable, ActionListener{
    
    private static final int SCREEN_WIDTH = 600;
    private static final int SCREEN_HEIGHT = 600;
    private static final int UNIT_SIZE = 25;
    private static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;
    private static final int DELAY = 120;
    
    private final int x[] = new int[GAME_UNITS];
    private final int y[] = new int[GAME_UNITS];
    private int block = 1;
    private int score = 0;
    private int block_x;
    private int block_y;
    
    private int redBlock_x;
    private int redBlock_y;
    
    private int greenBlock_x;
    private int greenBlock_y;
    
    
    
    boolean running = false;
    private Directions blockDirection = Directions.RIGHT; 
    private boolean isDirectionChanged = false;
    Timer timer;
    Random random;
    
    
    private int highScore = 0;
    public String highScore(String name){
        return name;
    }
    
    public int highScore(int total){
        return total;
    }
    
    public GamePanel(int block_x, int block_y) {
        this.block_x = block_x;
        this.block_y = block_y;
    }
  
    public GamePanel(){
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
    }
    
    public void startGame(){
        newBlock();
        newRedBlock();
        newGreenBlock();
        
        //redBlocks.newBlock();
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();
    }
    
    @Override //polimorphism because super?
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }
    
    public void draw(Graphics g){
        
        if(running){
            try {
                Thread.sleep(25);  
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //grid
            /*for(int i =0; i<SCREEN_HEIGHT/UNIT_SIZE; i++){
                g.drawLine(0, i*UNIT_SIZE, SCREEN_WIDTH, i*UNIT_SIZE);
            }*/
            isDirectionChanged = false;
            //The blue block
            g.setColor(new Color(0,191,255));
            g.fillRect(block_x, block_y, UNIT_SIZE, UNIT_SIZE);
            
            //Obstacle
            g.setColor(new Color(210, 4, 45)); //merah
            g.fillRect(redBlock_x, redBlock_y, UNIT_SIZE, UNIT_SIZE);
            g.setColor(new Color(50, 205, 50)); //hijau
            g.fillRect(greenBlock_x, greenBlock_y, UNIT_SIZE, UNIT_SIZE);
            
            //Blocks
            for(int i=0; i<block; i++){
                //first block
                if(i == 0){
                    g.setColor(new Color(30,144,255));
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }
                else{
                    g.setColor(new Color(0,191,255));
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }
            }
            //points
            g.setColor(Color.white);
            g.setFont(new Font("Poppins", Font.BOLD, 15));
            FontMetrics metr = getFontMetrics(g.getFont());
            g.drawString("Score: "+score, 
                    (SCREEN_WIDTH - metr.stringWidth("Score: "+score))/2, g.getFont().getSize());
            //highscore
            highScore = this.GetHighScore();
            checkScore();
            g.setFont(new Font("Poppins", Font.BOLD, 15));
            FontMetrics metr3 = getFontMetrics(g.getFont());
            g.drawString("High Score: "+highScore, 10,15);
        }
        else{
            gameOver(g);
        }
    }
    
    public void newBlock(){ //generate coordinate of the new blue block
        block_x = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE; //casting
        block_y = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;//casting
    }
    
    public void newRedBlock(){ //generate coordinate of the new blue block
        redBlock_x = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE; //casting
        redBlock_y = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;//casting
    }
    
    public void newGreenBlock(){ //generate coordinate of the new blue block
        greenBlock_x = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE; //casting
        greenBlock_y = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;//casting
    }
    
    public void move(Directions direction){
        for(int i = block; i>0; i--){
            x[i] = x [i-1];
            y[i] = y [i-1];
        }
        switch(direction){ //bisa pake enum
            case UP:
                y[0] = y[0] - UNIT_SIZE;
                break;
            case DOWN:
                y[0] = y[0] + UNIT_SIZE;
                break; 
            case LEFT:
                x[0] = x[0] - UNIT_SIZE;
                break;
            case RIGHT:
                x[0] = x[0] + UNIT_SIZE;
                break;
        } 
    }
    
    public void checkBlocks(){
        if( (x[0]==block_x) && (y[0]==block_y) ){
            block++;
            score++;
            newBlock();
            newRedBlock();
            newGreenBlock();
            checkScore();
        }
    }
    
    public void checkCollisions(){
        //kalo kepala kena badan
        for(int i = block; i>0; i--){
            if(  (x[0] == x[i]) && (y[0] == y[i])   ){
                running = false;
            }
        }
        //If Blocks hit the left border
        if(x[0]<0){
            running = false;
        }
        //If Blocks hit the right border
        if(x[0] > SCREEN_WIDTH){
            running = false;
        }
        //If Blocks hit the top border
        if(y[0]<0){
            running = false;
        }
        //If Blocks hit the bottom border
        if(y[0] > SCREEN_WIDTH){
            running = false;
        }
        //If Blocks hit red obstackle
        if( (redBlock_x == x[0]) && (redBlock_y == y[0]) ){
            running = false;
        }
        //If Blocks hit green obstackle
        if( (greenBlock_x == x[0]) && (greenBlock_y == y[0]) ){
            running = false;
        }
        if(!running){
            timer.stop();
        }
    }
    
    public void gameOver(Graphics g){
        //score
        g.setColor(Color.white);
        g.setFont(new Font("Poppins", Font.BOLD, 20));
        FontMetrics metr1 = getFontMetrics(g.getFont());
        g.drawString("Score: "+score, 
           (SCREEN_WIDTH - metr1.stringWidth("Score: "+score))/2, (int)(SCREEN_HEIGHT/1.6)); //casting
        
        //game over text
        g.setColor(Color.white);
        g.setFont(new Font("Poppins", Font.BOLD, 50));
        FontMetrics metr2 = getFontMetrics(g.getFont());
        g.drawString("Game Over", (SCREEN_WIDTH - metr2.stringWidth("Game Over"))/2, SCREEN_HEIGHT/2);
        
        //highscore
        highScore = this.GetHighScore();
        g.setFont(new Font("Poppins", Font.BOLD, 20));
        FontMetrics metr3 = getFontMetrics(g.getFont());
        g.drawString("High Score: "+highScore, 
                (SCREEN_WIDTH - metr3.stringWidth("High Score: "+highScore))/2, (int)(SCREEN_HEIGHT/1.75));
           
    }
   
   public void checkScore(){//casting
       //System.out.println(highScore);
        if(score > highScore){
            //user has set a new record
            highScore = score;
            File scoreFile = new File("highscore.dat"); //file yang sudah dibuat sebelumnya
            if(!scoreFile.exists()){
                try{
                    scoreFile.createNewFile();
                }
                catch(IOException e){
                    e.printStackTrace();
                }
            }
            FileWriter writeFile = null; //store the file to write to
            BufferedWriter writer = null; 
            try{
                writeFile = new FileWriter(scoreFile);
                writer = new BufferedWriter(writeFile);
                writer.write(this.highScore);
            }
            catch(Exception e){
                
            }
            finally{
                try{
                    if(writer != null) 
                        writer.close();
                }
                catch (Exception e){}
            }
        }else{
            
        }   
    }
        
    public int GetHighScore(){
        FileReader readFile = null;
        BufferedReader reader = null;
        try{
            readFile = new FileReader("highscore.dat");
            reader = new BufferedReader(readFile);
            return reader.read();
        }
        catch(Exception e){
            return 0;
        }
        finally{
            try{
                if(reader != null){
                   reader.close(); 
                }
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
    }
    
    @Override
    public void run(){
        while(true){
            try{
                Thread.sleep(50);
            }
            catch(Exception e){
                e.printStackTrace();
                break;
            }
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(running){
            move(blockDirection); //move();
            checkBlocks();
            checkCollisions();
            
        }
        //checkScore();
        repaint();
    }
    
    public void changeDirection(Directions newDirection) {
        if (blockDirection.compatibleWith(newDirection)) {
            blockDirection = newDirection;
            isDirectionChanged = true;
        }
    }
    
    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            if (isDirectionChanged == false) {
                switch (keyCode) {
                    case KeyEvent.VK_UP :
                        changeDirection(Directions.UP);
                        break;
                    case KeyEvent.VK_RIGHT :
                        changeDirection(Directions.RIGHT);
                        break;
                    case KeyEvent.VK_DOWN :
                        changeDirection(Directions.DOWN);
                        break;
                    case KeyEvent.VK_LEFT :
                        changeDirection(Directions.LEFT);
                        break;
                }
           }
        }
    }
    
}

//    public void move(){
//        for(int i = block; i>0; i--){
//            x[i] = x [i-1];
//            y[i] = y [i-1];
//        }
//        switch(direction){ //bisa pake enum
//            case 'U':
//                y[0] = y[0] - UNIT_SIZE;
//                break;
//            case 'D':
//                y[0] = y[0] + UNIT_SIZE;
//                break; 
//            case 'L':
//                x[0] = x[0] - UNIT_SIZE;
//                break;
//            case 'R':
//                x[0] = x[0] + UNIT_SIZE;
//                break;
//        } 
//    }

//  public class MyKeyAdapter extends KeyAdapter {
//        @Override
//        public void keyPressed(KeyEvent e){
//            switch(e.getKeyCode()){
//                case KeyEvent.VK_LEFT:
//                    if(direction != 'R'){
//                        direction = 'L';
//                    }
//                    break;
//                case KeyEvent.VK_RIGHT:
//                    if(direction != 'L'){
//                        direction = 'R';
//                    }
//                    break;
//                case KeyEvent.VK_UP:
//                    if(direction != 'D'){
//                        direction = 'U';
//                    }
//                    break;
//                case KeyEvent.VK_DOWN:
//                    if(direction != 'U'){
//                        direction = 'D';
//                    }
//                    break;
//            }
//        }
//    }