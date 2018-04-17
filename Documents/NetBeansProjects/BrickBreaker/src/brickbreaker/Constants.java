/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brickbreaker;

import java.awt.Color;

/**
 *
 * @author elber
 */
public class Constants {
    //GamePanel Constants
    public static final int PANE_WIDTH = 1280;
    public static final int PANE_HEIGHT = 720;
    public static final double GAME_HERTZ = 60.0;
    
    //Board Constants
    public static final int BOARD_WIDTH = 1240;
    public static final int BOARD_HEIGHT = 680; 
    
    public static final int BORDER_LENGTH = (PANE_HEIGHT - BOARD_HEIGHT) / 2; //calculation for border
    
    //Paddle Constants
    public static final int PADDLE_WIDTH = 200;
    public static final int PADDLE_HEIGHT = 10;
    public static final int PADDLE_SPEED = 8;
    public static final Color PADDLE_COLOR = Color.GRAY;
   
    //Ball Constants
    public static final double INITIAL_BALL_X = 627.5;
    public static final int INITIAL_BALL_Y = 500;
    public static final double INITIAL_DIRECTION = 45;
    public static final int BALL_DIAMETER = 20;
    public static final double BALL_SPEED = 5;
    public static final Color BALL_COLOR = Color.BLUE;
    
    //Brick Constants
    public static final int BRICK_WIDTH = 150;
    public static final int BRICK_HEIGHT = 75;

}
