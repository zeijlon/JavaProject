package se.liu.ida.andze132.tddd78.javaproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


public class GameComponent extends JComponent {
    private GRID grid;
    private Shop shop;
    private EnemySpawner spawner;
    private TowerHandler towerHandler;
    private BulletHandler bulletHandler;
    private Menu menu;


    public final static int TILE_SIZE = 60;
    public final static int SHOP_SIZE_X = 175;
    public final static int INFO_BOX = 175;


    public GameComponent(GRID grid, Shop shop, EnemySpawner spawner, TowerHandler towerHandler, BulletHandler bulletHandler, Menu menu) {
        this.grid = grid;
        this.shop = shop;
        this.spawner = spawner;
        this.towerHandler = towerHandler;
        this.bulletHandler = bulletHandler;
        this.menu = menu;
        this.addKeyBindings();
    }

    @Override
    public Dimension getPreferredSize() {
        super.getPreferredSize();
        int row = GRID.checkLargestRow(grid);
        if (menu.isIfMenu()) {
            return new Dimension(819, 460);
        } else {
            return new Dimension(row * TILE_SIZE + SHOP_SIZE_X, grid.getSquares().length * TILE_SIZE + INFO_BOX);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        final Graphics2D g2d = (Graphics2D) g;
        if (menu.isIfMenu()) {
            menu.draw(g2d);
        } else {


            grid.draw(g2d);
            shop.draw(g2d);
            spawner.draw(g2d);
            towerHandler.draw(g2d);
            bulletHandler.draw(g2d);
        }
    }


    class EscapeAction extends AbstractAction {
        public void actionPerformed(ActionEvent e) {
            menu.escape();
        }
    }

    private void addKeyBindings() {
        InputMap inputMap = getInputMap(JComponent.WHEN_FOCUSED);

        Action exitGame = new EscapeAction();
        getActionMap().put("exitGame", exitGame);
        inputMap.put(KeyStroke.getKeyStroke("ESCAPE"), "exitGame");
    }


}