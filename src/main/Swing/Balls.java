import javax.swing.*;
import java.awt.*;

public class Balls extends JPanel {
    public Balls() {
        setLayout(null);
        // Randomize the speed and direction...
        add(new Ball("red", 1 + (int) Math.round((Math.random() * 20)), 10 - (int) Math.round((Math.random() * 20))));
        add(new Ball("blue", 1 - (int) Math.round((Math.random() * 20)), 1 + (int) Math.round((Math.random() * 20))));
        this.setBackground(Color.black);
    }
}