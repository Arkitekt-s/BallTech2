

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Semaphore;

public class Ball extends JPanel implements Runnable {

    static BufferedImage img;

    static {
        try {
            img = ImageIO.read(new File("/Users/filippo/Downloads/dvd.png"));
        } catch (IOException e) {
            System.out.println("Failed to load the image");
            e.printStackTrace();
        }
    }

    public static int otherBallX;
    public static int otherBallY;
    public static Semaphore sem = new Semaphore(1);



    public static void setPos(int otherBallX, int otherBallY) {
        Ball.otherBallX = otherBallX;
        Ball.otherBallY = otherBallY;
    }
    

    

    Color color;
    static int diameter;
    long delay;
    private int vx;
    private int vy;

    public static boolean borderColl(int thisBallX, int thisBallY) {
        Ball.otherBallX = otherBallX;
        Ball.otherBallY = otherBallY;
        //DistanceSquared between the points
        int distSq = (otherBallX - thisBallX) * (otherBallX - thisBallX) + (otherBallY - thisBallY) * (otherBallY - thisBallY);
        //Squared sum of radiuses
        int radSumSq = (diameter) * (diameter);
        //
        if (distSq > radSumSq) {
            return false;
        } else
            //System.out.println("there was a collision");
        return true;
    }

    public Ball(String ballcolor, int xvelocity, int yvelocity) {
        if (ballcolor == "red") {
            color = Color.red;
        } else if (ballcolor == "blue") {
            color = Color.blue;
        } else if (ballcolor == "black") {
            color = Color.black;
        } else if (ballcolor == "cyan") {
            color = Color.cyan;
        } else if (ballcolor == "darkGray") {
            color = Color.darkGray;
        } else if (ballcolor == "gray") {
            color = Color.gray;
        } else if (ballcolor == "green") {
            color = Color.green;
        } else if (ballcolor == "yellow") {
            color = Color.yellow;
        } else if (ballcolor == "lightGray") {
            color = Color.lightGray;
        } else if (ballcolor == "magenta") {
            color = Color.magenta;
        } else if (ballcolor == "orange") {
            color = Color.orange;
        } else if (ballcolor == "pink") {
            color = Color.pink;
        } else if (ballcolor == "white") {
            color = Color.white;
        }
        diameter = 120;

        this.setOpaque(false);

        vx = xvelocity;
        vy = yvelocity;

        new Thread(this).start();

    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        int x = getX();
        int y = getY();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.setColor(color);
        g.fillOval(0, 0, diameter, diameter); //adds color to circle
        g.setColor(Color.black);
        g.drawOval(0, 0, diameter, diameter); //draws circle

        int imgx = diameter*17/150;
        int imgy = diameter*35/150;
        int imgWidth = diameter*120/150;
        int imgHeight = diameter*80/150;

        g.drawImage(img, imgx,imgy,imgWidth,imgHeight,color,this);
        g.dispose();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(diameter, diameter);
    }

    public void run() {
        try {
            // Randamize the location...
            SwingUtilities.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    //The location is smaller so the ball will pop up away from the corners
                    int x = (int) (Math.round(Math.random() * (getParent().getWidth()-diameter))) ;
                    int y = (int) (Math.round(Math.random() * (getParent().getHeight()-diameter)));
                    setLocation(x, y);
                }
            });
        } catch (Exception e){}

        while (isVisible()) {
            try {
                SwingUtilities.invokeAndWait(new Runnable() {
                    @Override
                    public void run() {
                        move();
                        repaint();
                    }
                });
            } catch (Exception e){}
        }
    }


    public void move() {
        try {
            sem.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //getsLocation
        int x = getX();
        //System.out.println("the ball is in xpos "+ x);
        int y = getY();

        //Check border collision
        if (borderColl(x, y)) {
            vx *= -1;
            vy *= -1;
        }

        //Check X Border
        if (x + vx < 0 || x + diameter + vx > getParent().getWidth()) {
            vx *= -1;
        }
        //Checks Y border
        if (y + vy < 0 || y + diameter + vy > getParent().getHeight()) {
            vy *= -1;
        }
        //Adds next move
        x += vx;
        y += vy;

        // Update the size and location...
        setSize(getPreferredSize());
        setLocation(x, y);
        setPos(x,y);
        //System.out.println("The "+this.color+" ball moved to: x" +x+ ", y"+y);
        //System.out.println(this.color+ " ball releases the permit.");
        sem.release();
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}