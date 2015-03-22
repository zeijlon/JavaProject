package se.liu.ida.andze132.tddd78.javaproject;

import javax.swing.*;
import java.awt.*;

public class GameComponent extends JComponent {
    private GRID grid;
    private Shop shop;

    private EnemySpawner spawner;


    public final static int TILE_SIZE = 40;
    public final static int SHOP_SIZE_X = 175;
    public final static int INFO_BOX = 175;


    public GameComponent(GRID grid, Shop shop, EnemySpawner spawner) {
        this.grid = grid;
        this.shop = shop;
        this.spawner = spawner;
    }

    @Override
    public Dimension getPreferredSize() {
        super.getPreferredSize();
        return new Dimension(grid.getSquares()[0].length * TILE_SIZE + SHOP_SIZE_X, grid.getSquares().length * TILE_SIZE + INFO_BOX);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        final Graphics2D g2d = (Graphics2D) g;


        grid.draw(g2d);
        shop.draw(g2d);
        spawner.draw(g2d);
        }

    }

//grid.getSquares().length * TILE_SIZE + SHOP_SIZE_X, grid.getSquares().length * TILE_SIZE + INFO_BOX