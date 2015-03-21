package se.liu.ida.andze132.tddd78.javaproject;

import javax.swing.*;
import java.awt.*;

public class GameComponent extends JComponent {
    private GRID grid;
    private Shop shop;
    private final static int SQUARE_WIDTH = 40;
    private final static int SQUARE_HEIGHT = 40;



    public GameComponent(GRID grid, Shop shop) {
        this.grid = grid;
        this.shop = shop;
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

        grid.draw(g2d);
        shop.draw(g2d);

    }





}