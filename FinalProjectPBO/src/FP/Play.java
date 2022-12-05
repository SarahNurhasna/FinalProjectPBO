/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package FP;

import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 *
 * @author Asus
 */
public class Play extends JFrame {
    public Play() {
        initUI();
    }
    
    private void initUI() {
        add(new Board());
        add(new Blocks());
        
        setResizable(false);
        pack();
        
        setTitle("Collect The Blue Block!");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public static void main(String[] args) {
        
        EventQueue.invokeLater(() -> {
            JFrame ex = new Play();
            ex.setVisible(true);
        });
    } 
}
