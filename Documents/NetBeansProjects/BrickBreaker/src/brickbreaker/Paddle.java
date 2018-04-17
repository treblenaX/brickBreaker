
package brickbreaker;

import static brickbreaker.Constants.*;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author elber
 */
public class Paddle extends Rectangle2D{
    //Fields
    private int x;
    private int y;
    private int w;
    private int h;
    
    private int dx;
    
    private int speed;
    
    private boolean left;
    private boolean right;
    
    private Color paddleColor;
    
    //Constructor
    public Paddle()
    {
        x = (PANE_WIDTH / 2) - (PADDLE_WIDTH / 2);
        y = PANE_HEIGHT - (PANE_HEIGHT / 12);
        w = PADDLE_WIDTH;
        h = PADDLE_HEIGHT;
        
        speed = PADDLE_SPEED;
        
        paddleColor = PADDLE_COLOR;
    }
    
    //Functions
    public void setLeft(boolean b)
    {
        left = b;
    }
    
    public void setRight(boolean b)
    {
        right = b;
    }
    
    public void update()
    {
        if(left)
        {
            dx = -speed;
        }
        if(right)
        {
            dx = speed;
        }
        
        x += dx;
        
        //Boundaries for the paddle
        if(x < (PANE_WIDTH - BOARD_WIDTH) / 2) x = (PANE_WIDTH - BOARD_WIDTH) / 2; //Left side
        if(x > PANE_WIDTH - (w + (PANE_WIDTH - BOARD_WIDTH) / 2))  x = PANE_WIDTH - (w + (PANE_WIDTH - BOARD_WIDTH) / 2); //Right side
        
        dx = 0;
    }
    
    public void draw(Graphics2D g)
    {
        g.setColor(paddleColor);
        g.fillRect(x, y, w, h);
        
        g.setStroke(new BasicStroke(2));
        g.setColor(paddleColor.brighter());
        g.drawRect(x, y, w, h);
    }

    @Override
    public void setRect(double x, double y, double w, double h) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int outcode(double x, double y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Rectangle2D createIntersection(Rectangle2D r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Rectangle2D createUnion(Rectangle2D r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public double getWidth() {
        return w;
    }

    @Override
    public double getHeight() {
        return h;
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
