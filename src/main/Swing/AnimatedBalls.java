import javax.swing.*;
import java.awt.*;

public class AnimatedBalls {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                frame.add(new Balls());
                frame.setSize(800, 600);
                frame.getContentPane().setBackground(Color.black);
                frame.setVisible(true);
            }
        });
    }
}