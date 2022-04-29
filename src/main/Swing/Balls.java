import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;

public class Balls extends JPanel {
    public Balls() {
        setLayout(null);
        //Randomize Speed and Direction
        Ball redBall = new Ball("red", 1 + (int) Math.round((Math.random() * 20)), 10 - (int) Math.round((Math.random() * 20)));
        Ball blueBall = new Ball("blue", 1 - (int) Math.round((Math.random() * 20)), 1 + (int) Math.round((Math.random() * 20)));
        //Adding the balls to Balls Jpanel
        add(blueBall);
        add(redBall);
        // Randomize location...
        redBall.setLocation((int) (Math.round(Math.random() * 100) + 150), (int) (Math.round(Math.random() * 100) + 150));
        blueBall.setLocation((int) (Math.round(Math.random() * 100) + 300), (int) (Math.round(Math.random() * 100) + 300));
        this.setBackground(Color.black);
    }
}