package brickbreaker;

import static brickbreaker.Constants.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 *
 * @author elber
 */
public class GamePanel extends JPanel implements Runnable, KeyListener {
    //Fields
    private Thread thread;
    private boolean running;
    
    private BufferedImage image;
    private Graphics2D g;
    
    private double hertz = GAME_HERTZ;
    private double averageFPS;
    
    private Brick[][] bricks;
    private Paddle player;
    private Ball ball;
    
    //Constructor
    public GamePanel()
    {
        super();
        setPreferredSize(new Dimension(PANE_WIDTH, PANE_HEIGHT));
        setFocusable(true);
        requestFocus();
    }
    
    //Functions
    public void addNotify()
    {
        super.addNotify();
        if (thread == null)
        {
             thread = new Thread(this);
             thread.start();
        }
        addKeyListener(this);
    }

    @Override
    public void run() {
        running = true;
        
        image = new BufferedImage(PANE_WIDTH, PANE_WIDTH, BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) image.getGraphics();
        
        bricks = fillBricks(g);
        player = new Paddle();
        ball = new Ball(player);
        
        
        long startTime;
        long URDTimeMillis;
        long waitTime;
        long totalTime = 0;
        
        int frameCount = 0;
        int maxFrameCount = 60;
        
        long targetTime = (long) (1000 / hertz); //16 ms per frame
        
        //Game Loop
        while (running)
        {
            startTime = System.nanoTime();
            
            gameUpdate();
            gameRender();
            gameDraw();
            
            URDTimeMillis = (System.nanoTime() - startTime) / 1000000; //Divide by million to get nano to milliseconds
            
            waitTime = targetTime - URDTimeMillis; //Amount of extra time needed to wait
            
            if (waitTime < 0) waitTime = 5;//In case the waitTime is negative
            
            try {
                Thread.sleep(waitTime); //Sleeps for that extra time until the next frame 
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            
            totalTime += System.nanoTime() - startTime;
            frameCount++;
            if (frameCount == maxFrameCount)
            {
                averageFPS = 1000.0 / (((double)totalTime / frameCount) / 1000000);
                frameCount = 0;
                totalTime = 0;
            }
        }
    }
    
    private void checkBricks()
    {
        for (int row = 0; row < bricks.length; row++)
        {
            for (int column = 0; column < bricks[row].length; column++)
            {
                if (bricks[row][column].hitBottom((int)ball.getX(), (int)ball.getY()))
                {
                    ball.horBounce();
                    bricks[row][column].setDestroyed(true);
                }
                else if (bricks[row][column].hitTop((int)ball.getX(), (int)ball.getY()))
                {
                    ball.horBounce();
                    bricks[row][column].setDestroyed(true);
                }
                else if (bricks[row][column].hitLeft((int)ball.getX(),(int)ball.getY()))
                {
                    ball.verBounce();
                    bricks[row][column].setDestroyed(true);
                }
                else if (bricks[row][column].hitRight((int)ball.getX(), (int)ball.getY()))
                {
                    ball.verBounce();
                    bricks[row][column].setDestroyed(true);
                }
            }
        }
    }
    
    private void gameUpdate()
    {
        //Collision
        checkBricks();
        //Updates the paddle's keys and movement
        player.update();
        //Updates the ball's movement
        ball.update();
        
    }
    
    private void gameRender()
    {
        //Sets the Borders
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, PANE_WIDTH, PANE_HEIGHT);
        //Sets the game room
        g.setColor(Color.WHITE);
        g.fillRect(BORDER_LENGTH, BORDER_LENGTH, BOARD_WIDTH, BOARD_HEIGHT);
        //FPS counter
        g.setColor(Color.BLACK);
        g.drawString("FPS: " + (int)averageFPS, 40, 40);
        
        bricks = fillBricks(g);
        player.draw(g);
        ball.draw(g);
        
        //Bricks draw
        for (int row = 0; row < bricks.length; row++)
        {
            for (int column = 0; column < bricks[row].length; column++)
            {
                bricks[row][column].draw(g);
            }
        }
    }
    
    private void gameDraw()
    {
        Graphics g2 = this.getGraphics();
        g2.drawImage(image, 0, 0, null);
        g2.dispose(); 
    }
    
    private Brick[][] fillBricks(Graphics2D g)
    {
        Brick[][] bricks = new Brick[2][7];
        for (int row = 0; row < bricks.length; row++)
        {
            for (int column = 0; column < bricks[row].length; column++)
            {
                bricks[row][column] = new Brick(90 + (PANE_WIDTH / 8) * column, 100 + (PANE_HEIGHT / 5) * row);
            }
        }
        
        return bricks;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode =  e.getKeyCode();
        if(keyCode == KeyEvent.VK_LEFT)
        {
            player.setLeft(true);
        }
        if(keyCode == KeyEvent.VK_RIGHT)
        {
            player.setRight(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode =  e.getKeyCode();
        if(keyCode == KeyEvent.VK_LEFT)
        {
            player.setLeft(false);
        }
        if(keyCode == KeyEvent.VK_RIGHT)
        {
            player.setRight(false);
        }
        
    }
    
}
