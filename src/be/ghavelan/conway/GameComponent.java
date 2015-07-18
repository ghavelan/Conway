package be.ghavelan.conway;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.Arrays;

/**
 * Created by Gilles Havelange on 12/07/15.
 */
public class GameComponent extends JPanel {

    private static final int DEFAULT_WIDTH = 1000;
    private static final int DEFAULT_HEIGHT = 600;
    private static final int LINE_SPACING = 10;
    private static final int STROKE = 1;

    private int row;
    private int column;

    private int[][] grid;

    public GameComponent() {

        row = (DEFAULT_HEIGHT - 2 * LINE_SPACING) / LINE_SPACING;
        column = (DEFAULT_WIDTH - 2 * LINE_SPACING) / LINE_SPACING;

        grid = new int[row][column];

        for (int j = 0; j < row; j++) {

            Arrays.fill(grid[j], 0);

        }

        addMouseListener(new MouseHandler());
        addMouseMotionListener(new MouseMotionHandler());

    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(STROKE));

        g2.setPaint(Color.BLUE);

        for (int i = 0; i < row; i++) {

            for (int j = 0; j < column; j++) {

                if (grid[i][j] != 0) {

                    int baseY = (i + 1) * LINE_SPACING;
                    int baseX = (j + 1) * LINE_SPACING;

                    g2.fill(new Rectangle2D.Double(baseX, baseY, LINE_SPACING + STROKE, LINE_SPACING + STROKE));

                }
            }
        }

        g2.setPaint(Color.LIGHT_GRAY);

        for (int j = LINE_SPACING; j <= DEFAULT_WIDTH - LINE_SPACING; j += LINE_SPACING) {
            Line2D line = new Line2D.Double(j, LINE_SPACING, j, DEFAULT_HEIGHT - LINE_SPACING);
            g2.draw(line);
        }

        for (int j = LINE_SPACING; j <= DEFAULT_HEIGHT - LINE_SPACING; j += LINE_SPACING) {
            Line2D line = new Line2D.Double(LINE_SPACING, j, DEFAULT_WIDTH - LINE_SPACING, j);
            g2.draw(line);
        }

    }

    public Dimension getPreferredSize() {

        return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    public void clearCells() {

        for (int j = 0; j < row; j++) {

            Arrays.fill(grid[j], 0);

        }

        repaint();
    }

    public void update() {

        int neighbors;
        int[][] copy = new int[grid.length][];

        for (int j = 0; j < row; j++) {

            copy[j] = grid[j].clone();
        }

        for (int i = 0; i < row; i++) {

            for (int j = 0; j < column; j++) {

                if (i == 0) {

                    if (j == 0) {

                        neighbors = grid[i][j + 1] + grid[i + 1][j + 1] + grid[i + 1][j];
                    } else if (j == column - 1) {

                        neighbors = grid[i][j - 1] + grid[i + 1][j - 1] + grid[i + 1][j];
                    } else {

                        neighbors = grid[i][j - 1] + grid[i + 1][j - 1] + grid[i + 1][j] + grid[i + 1][j + 1] + grid[i][j + 1];
                    }

                } else if (i == row - 1) {

                    if (j == 0) {

                        neighbors = grid[i - 1][j] + grid[i - 1][j + 1] + grid[i][j + 1];
                    } else if (j == column - 1) {

                        neighbors = grid[i - 1][j] + grid[i - 1][j - 1] + grid[i][j - 1];
                    } else {

                        neighbors = grid[i][j - 1] + grid[i - 1][j - 1] + grid[i - 1][j] + grid[i - 1][j + 1] + grid[i][j + 1];
                    }
                } else if (j == 0) {

                    if (i == row - 1) {

                        neighbors = grid[i - 1][j] + grid[i - 1][j + 1] + grid[i][j + 1];

                    } else {

                        neighbors = grid[i - 1][j] + grid[i - 1][j + 1] + grid[i][j + 1] + grid[i + 1][j + 1] + grid[i + 1][j];

                    }
                } else if (j == column - 1) {

                    if (i == row - 1) {

                        neighbors = grid[i - 1][j] + grid[i - 1][j - 1] + grid[i][j - 1];
                    } else {

                        neighbors = grid[i - 1][j] + grid[i - 1][j - 1] + grid[i][j - 1] + grid[i + 1][j - 1] + grid[i + 1][j];

                    }
                } else {

                    neighbors = grid[i - 1][j - 1] + grid[i - 1][j] + grid[i - 1][j + 1] + grid[i][j + 1] + grid[i + 1][j + 1] +
                            grid[i + 1][j] + grid[i + 1][j - 1] + grid[i][j - 1];

                }

                if (grid[i][j] == 0) {

                    if (neighbors == 3) {

                        copy[i][j] = 1;
                    }
                } else {

                    if (neighbors != 3 && neighbors != 2) {

                        copy[i][j] = 0;

                    } else {

                        copy[i][j] = 1;
                    }

                }

            }
        }

        grid = copy;
        repaint();

    }

    private class MouseHandler extends MouseAdapter {

        public void mousePressed(MouseEvent event) {

            int x = event.getPoint().x;
            int y = event.getPoint().y;

            if (x > LINE_SPACING && y > LINE_SPACING && x < (DEFAULT_WIDTH - LINE_SPACING)
                    && y < (DEFAULT_HEIGHT - LINE_SPACING)) {

                int index_x = x / LINE_SPACING;
                int index_y = y / LINE_SPACING;

                if (grid[index_y - 1][index_x - 1] == 0) {

                    grid[index_y - 1][index_x - 1] = 1;
                    repaint();

                } else {

                    grid[index_y - 1][index_x - 1] = 0;
                    repaint();

                }

            }

        }

    }

    private class MouseMotionHandler implements MouseMotionListener {

        public void mouseDragged(MouseEvent event) {

            int x = event.getPoint().x;
            int y = event.getPoint().y;

            if (x > LINE_SPACING && y > LINE_SPACING && x < (DEFAULT_WIDTH - LINE_SPACING)
                    && y < (DEFAULT_HEIGHT - LINE_SPACING)) {

                int index_x = x / LINE_SPACING;
                int index_y = y / LINE_SPACING;

                if (grid[index_y - 1][index_x - 1] == 0) {

                    grid[index_y - 1][index_x - 1] = 1;
                    repaint();

                }

            }

        }

        @Override
        public void mouseMoved(MouseEvent e) {

        }

    }

}
