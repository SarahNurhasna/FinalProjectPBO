/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FinalProject;

/**
 *
 * @author Asus
 */


import javax.swing.JFrame;
public class GameFrame extends JFrame{
    
    public GameFrame (){
//      GamePanel panel = new GamePanel();
//      this.add(panel);
        this.add(new GamePanel()); 
        this.setTitle("Collect The Blue Block!");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }  
}
