/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FP;

/**
 *
 * @author Asus
 */
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Blocks extends JPanel implements ActionListener{
    private final int BLOCK_SIZE = 10; //block
    private final int ALL_BLOCK = 180; //jumlah maksimal block yg ada pada board
    private final int RAND_POS = 29; //untuk menghitung random position
    private final int DELAY = 140;
    
    private final int x[] = new int[ALL_BLOCK];
    private final int y[] = new int[ALL_BLOCK];
    
    private int block;
    private int blueBlock_x;
    private int blueBlock_y;
    
    private int redBlock_x;
    private int redBlock_y;
    
    private int greenBlock_x;
    private int greenBlock_y;

    private int purpleBlock_x;
    private int purpleBlock_y;
    
    private Timer timer;
    private Image head;
    private Image redBlock;
    private Image blueBlock;
    private Image greenBlock;
    private Image purpleBlock;
    
    private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;
    private boolean inGame = true;
    Board bd = new Board();
    
    public void loadImages() { //Block
        ImageIcon iih = new ImageIcon(getClass().getResource("blue block.png"));
        head = iih.getImage();

        ImageIcon iirb = new ImageIcon(getClass().getResource("red block.png"));
        redBlock = iirb.getImage();

        ImageIcon iibb = new ImageIcon(getClass().getResource("blue block.png"));
        blueBlock = iibb.getImage();
        
        ImageIcon iigb = new ImageIcon(getClass().getResource("green block.png"));
        greenBlock = iigb.getImage();
        
        ImageIcon iipb = new ImageIcon(getClass().getResource("purple block.png"));
        purpleBlock = iipb.getImage();
    }
    
    public void locateBlueBlock() { //block

        int r = (int) (Math.random() * RAND_POS);
        blueBlock_x = ((r * BLOCK_SIZE));

        r = (int) (Math.random() * RAND_POS);
        blueBlock_y = ((r * BLOCK_SIZE));
    }
    
    public void locateRedBlock() {
        int r = (int) (Math.random() * RAND_POS);
        redBlock_x = ((r * BLOCK_SIZE));

        r = (int) (Math.random() * RAND_POS);
        redBlock_y = ((r * BLOCK_SIZE));
    }    
    
    public void locateGreenBlock(){
        int r = (int) (Math.random() * RAND_POS);
        greenBlock_x = ((r * BLOCK_SIZE));
        
        r = (int) (Math.random() * RAND_POS);
        greenBlock_y = (r * BLOCK_SIZE);
    }
    
    public void locatePurpleBlock(){
        int r = (int) (Math.random() * RAND_POS);
        purpleBlock_x = (r * BLOCK_SIZE);
        
        r = (int) (Math.random() * RAND_POS);
        purpleBlock_y = (r * BLOCK_SIZE);
    }
    
    public void move() { 
        for (int z = block; z > 0; z--) {
            x[z] = x[(z - 1)];
            y[z] = y[(z - 1)];
        }
        if (leftDirection) {
            x[0] -= BLOCK_SIZE;
        }
        if (rightDirection) {
            x[0] += BLOCK_SIZE;
        }
        if (upDirection) {
            y[0] -= BLOCK_SIZE;
        }
        if (downDirection) {
            y[0] += BLOCK_SIZE;
        }
    }
    
    public void initGame() {
        block = 1;
        x[0] = 150;
        y[0] = 150;
        
        locateBlueBlock();
        locateRedBlock();
        locateGreenBlock();
        locatePurpleBlock();
        
        timer = new Timer(DELAY, this);
        timer.start();
    }
    
    private void checkCollision() {
        for (int z = block; z > 0; z--) {

            if ((z > 4) && (x[0] == x[z]) && (y[0] == y[z])) {
                inGame = false;
            }
        }
        if (y[0] >= bd.getHeight()) {
            inGame = false;
        }
        if (y[0] < 0) {
            inGame = false;
        }
        if (x[0] >= bd.getWidth()) {
            inGame = false;
        }
        if (x[0] < 0) {
            inGame = false;
        }
        if((x[0] == redBlock_x) && (y[0] == redBlock_y)){
            inGame = false;
        }
        if((x[0] == greenBlock_x) && (y[0] == greenBlock_y)){
            inGame = false;
        }
        if((x[0] == purpleBlock_x) && (y[0] == purpleBlock_y)){
            inGame = false;
        }
        if (!inGame) {
            timer.stop();
        }
    }
    
    public void checkBlocks() {
        if ((x[0] == blueBlock_x) && (y[0] == blueBlock_y)) {
            block++;
            locateBlueBlock();
            locateRedBlock();
            locateGreenBlock();
            locatePurpleBlock();
        }//tambah poin
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame) {
            checkBlocks();
            checkCollision();
            move();
        }
        repaint();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }
    
    private void doDrawing(Graphics g) {
        
        if (inGame) {

            g.drawImage(blueBlock, blueBlock_x, blueBlock_y, this);
            g.drawImage(redBlock , redBlock_x, redBlock_y, this);
            g.drawImage(greenBlock, greenBlock_x, greenBlock_y, this);
            g.drawImage(purpleBlock , purpleBlock_x, purpleBlock_y, this);            
            
            for (int z = 0; z < block; z++) {
                if (z == 0) {
                    g.drawImage(head, x[z], y[z], this);
                } else {
                    g.drawImage(blueBlock, x[z], y[z], this);
                }
            }

            Toolkit.getDefaultToolkit().sync();
            
        } else {
            bd.gameOver(g);
        }        
    }  
}
