/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FinalProject;

public enum Directions {
    LEFT, 
    RIGHT, 
    UP, 
    DOWN;
    
    public boolean compatibleWith(Directions newDirection) {
        if (this.equals(LEFT) || this.equals(RIGHT)) {
            return UP.equals(newDirection) || DOWN.equals(newDirection); 
        } else {
            return LEFT.equals(newDirection) || RIGHT.equals(newDirection);
        }
    }
}
