package be.ghavelan.conway;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Gilles Havelange on 12/07/15.
 */
public class ConwayFrame extends JFrame {

    private GameComponent gameComponent;
    private GameRunnable gameRunnable;

    private JButton run;
    private JButton pause;
    private JButton next;
    private JButton clear;
    private JButton close;

    public ConwayFrame() {

        gameComponent = new GameComponent();
        add(gameComponent, BorderLayout.CENTER);

        run = new JButton("Run");
        pause = new JButton("Pause");
        next = new JButton("Next");
        clear = new JButton("Clear");
        close = new JButton("Close");

        JPanel buttonPanel = new JPanel();

        addButton(buttonPanel, run, new ActionListener() {

            public void actionPerformed(ActionEvent event) {

                startGame();
                next.setEnabled(false);
                clear.setEnabled(false);

            }

        });

        addButton(buttonPanel, pause, new ActionListener() {

            public void actionPerformed(ActionEvent event) {

                stopGame();
                next.setEnabled(true);
                clear.setEnabled(true);

            }

        });

        addButton(buttonPanel, next, new ActionListener() {

            public void actionPerformed(ActionEvent event) {

                gameComponent.update();
            }

        });

        addButton(buttonPanel, clear, new ActionListener() {

            public void actionPerformed(ActionEvent event) {

                gameComponent.clearCells();

            }

        });

        addButton(buttonPanel, close, new ActionListener() {

            public void actionPerformed(ActionEvent event) {

                System.exit(0);

            }

        });

        add(buttonPanel, BorderLayout.SOUTH);
        pack();

    }

    public void addButton(Container c, JButton button, ActionListener listener) {

        c.add(button);
        button.addActionListener(listener);
    }

    public void startGame() {

        gameRunnable = new GameRunnable(gameComponent);
        gameRunnable.start();
    }

    public void stopGame() {

        gameRunnable.suspend();
    }

}
