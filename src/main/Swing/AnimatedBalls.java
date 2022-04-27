

import javax.swing.*;
import java.awt.*;

public class AnimatedBalls {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
//                try {
//                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//                } catch (Exception e) {
//                }
                JFrame frame = new JFrame();
                //frame.setLocationRelativeTo(null);
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