/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brickbreaker;

import static brickbreaker.Constants.BOARD_HEIGHT;
import static brickbreaker.Constants.BOARD_WIDTH;
import static brickbreaker.Constants.*;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author elber
 */
public class Ball extends Ellipse2D {
    //Fields
    private int x;
    private int y;
    private int d;
    
    private double vx;
    private double vy;
    
    private double direction;
    
    private Color ballColor;
    
    private Paddle paddle;
    
    //Constructors
    public Ball(Paddle paddle)
    {
        x = (int) INITIAL_BALL_X;
        y = INITIAL_BALL_Y;
        d = BALL_DIAMETER;
        
        direction = INITIAL_DIRECTION;
        ballColor = BALL_COLOR;
        this.paddle = paddle;
        
        vx = BALL_SPEED;
        vy = -BALL_SPEED;
    }
    
    
    //Functions
    public void update()
    {
        if (collide())
        {
            if (getY() < paddle.getY())
            {
                System.out.println("Ball: " + getY() + " Paddle: " + paddle.getY());
                horBounce();
                y = (int)(paddle.getY() - d);
            }
            else if (getY() > paddle.getY() - PADDLE_HEIGHT)
            {
                System.out.println("Ball: " + getY() + " Paddle: " + paddle.getY());
                horBounce();
                y = (int)(paddle.getY() + PADDLE_HEIGHT);
            }
        }
        else if (y < BORDER_LENGTH) //Top
        {
            horBounce();
        }
        else if (y > BOARD_HEIGHT) //Bottom
        {
            System.out.println(y);
            horBounce();
        }
        else if (x < BORDER_LENGTH) //Left
        {
            verBounce();
        }
        else if (x > BOARD_WIDTH) //Right
        {
            verBounce();
        }  
        x += (int)vx;
        y -= (int)vy;
    }
    
    public void verBounce()
    {
        vx = -vx;
    }
    
    public void horBounce()
    {
        vy = -vy;
    }
    
    public void setDirection(double direction)
    {
        this.direction = direction;
    }
    
    public double getDirection()
    {
        return this.direction;
    } 
    
    public void draw(Graphics2D g)
    {
        g.setColor(ballColor);
        g.fillOval((int) x, (int) y, d, d);
    }
    
    public boolean collide()
    {
        return intersects(paddle);
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
        return d;
    }

    @Override
    public double getHeight() {
        return d;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void setFrame(double x, double y, double w, double h) {
        
    }

    @Override
    public Rectangle2D getBounds2D() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
