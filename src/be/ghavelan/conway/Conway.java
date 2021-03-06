package be.ghavelan.conway;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Gilles Havelange on 12/07/15.
 */
public class Conway {

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {

            public void run() {

                JFrame frame = new ConwayFrame();
                frame.setTitle("Conway's game of life");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);

            }
        });
    }

}
