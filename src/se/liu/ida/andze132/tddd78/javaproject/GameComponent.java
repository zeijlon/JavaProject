package se.liu.ida.andze132.tddd78.javaproject;

import javax.swing.*;
import java.awt.*;

public class GameComponent extends JComponent {
    private GRID grid;
    private final static int SQUARE_WIDTH = 40;
    private final static int SQUARE_HEIGHT = 40;

    public GameComponent(final GRID grid) {
        this.grid = grid;
    }

    @Override
    public Dimension getPreferredSize() {
        super.getPreferredSize();
        return new Dimension(grid.getSquares().length * SQUARE_WIDTH + 200, grid.getSquares().length * SQUARE_HEIGHT);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        final Graphics2D g2d = (Graphics2D) g;

        for (int i = 0; i < grid.getSquares().length; i++) {
            for (int j = 0; j < grid.getSquares()[i].length; j++) {
                g2d.setColor(squareTypeColor(grid.getSquares()[i][j]));
                g2d.fill(new Rectangle(j * SQUARE_WIDTH, i * SQUARE_WIDTH, SQUARE_WIDTH - 5, SQUARE_HEIGHT - 5));
            }
        }
        g2d.setColor(Color.BLACK);
        g2d.draw(new Rectangle(grid.getSquares()[1].length * SQUARE_WIDTH + SQUARE_HEIGHT, SQUARE_HEIGHT, SQUARE_WIDTH, SQUARE_HEIGHT));
    }



    public Color squareTypeColor(SquareType squaretype) {
        switch (squaretype) {
            case GRASS:
                return Color.GREEN;
            case PATH:
                return Color.GRAY;
            case TOWER:
                return Color.BLACK;
            case START:
                return Color.YELLOW;
            case FINISH:
                return Color.MAGENTA;
            default:
                throw new IllegalArgumentException("Invalid input squaretype");
        }

    }

}