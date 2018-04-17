
package brickbreaker;

import static brickbreaker.Constants.*;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author elber
 */
public class Brick extends Rectangle2D {
    //Fields
    private int x;
    private int y;
    private int w;
    private int h;
    
    private boolean destroyed;
    
    //Constructors
    public Brick(int x, int y)
    {
        this.x = x;
        this.y = y;
        w = BRICK_WIDTH;
        h = BRICK_HEIGHT;
        
        destroyed = false;
    }
    
    //Functions   
    public void draw(Graphics2D g)
    {
        if (!destroyed)
        {
            g.setColor(Color.BLACK);
            g.fillRect(x, y, w, h);
        }
    }
    
    public void update()
    {
        
    }
    
    public void setDestroyed(boolean destroyed)
    {
        this.destroyed = destroyed;
    }
    
    public boolean getDestroyed()
    {
        return this.destroyed;
    }
    
    public boolean hitBottom(int ballX, int ballY)
    {
        if (ballY >= getY() + BRICK_HEIGHT && ballY <= getY() + BRICK_HEIGHT + BALL_SPEED && ballX >= getX() && ballX <= getX() + BRICK_WIDTH)
        {
            System.out.println("Bottom hit");
            return true;
        }
        return false;
    }
    public boolean hitTop(int ballX, int ballY)
    {
        if (ballY >= getY() - BALL_DIAMETER && ballY <= getY() && ballX >= getX() && ballX <= getX() + BRICK_WIDTH)
        {
            System.out.println("Top hit");
            return true;
        }
        return false;
    }
    
    public boolean hitLeft(int ballX, int ballY)
    {
        if (ballY <= getY() + BRICK_HEIGHT && ballY >= getY() && ballX >= getX() - BALL_DIAMETER && ballX <= getX())
        {
            System.out.println("Left hit");
            return true;
        }
        return false;
    }
    
    public boolean hitRight(int ballX, int ballY)
    {
        if (ballY <= getY() + BRICK_HEIGHT && ballY >= getY() && ballX >= getX() + BRICK_WIDTH && ballX <= getX() + BRICK_WIDTH - BALL_DIAMETER)
        {
            System.out.println("Right hit");
            return true;
        }
        return false;
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
