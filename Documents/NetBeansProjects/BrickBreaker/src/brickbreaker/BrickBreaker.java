    
package brickbreaker;

import javax.swing.JFrame;



/**
 *
 * @author elber
 */
public class BrickBreaker {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame window = new JFrame("Brick Breaker");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        window.setContentPane(new GamePanel());
        
        window.pack();
        window.setVisible(true);
    }
    
}
