package se.liu.ida.andze132.tddd78.javaproject;

/**
 * Created by Administratör on 2015-03-23.
 */

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 */
public class KeyHandler extends MouseAdapter {
    private static final int MENU_HEIGHT = 30;
    protected static Point motionPoint = null;

    private GRID grid;
    private Shop shop;
    private EnemySpawner spawner;
    private TowerHandler towerHandler;
    private Menu menu;
    private BulletHandler bulletHandler;


    public KeyHandler(final GRID grid, final Shop shop, final EnemySpawner spawner, final TowerHandler towerHandler,
                      final Menu menu, final BulletHandler bulletHandler)
    {
        this.grid = grid;
        this.shop = shop;
        this.spawner = spawner;
        this.towerHandler = towerHandler;
        this.menu = menu;
        this.bulletHandler = bulletHandler;
    }


    @Override
    public void mousePressed(final MouseEvent e) {
        super.mousePressed(e);
        Point p = new Point(e.getX() - 8, e.getY() - MENU_HEIGHT);
        if (menu.isIfMenu()) {
            menu.menuEdit(p);
            System.out.println("hej");
        }
        else if(menu.isGameOn()) {
            spawner.nextWave(p);
            towerHandler.checkButtonClick(p);
            towerHandler.checkTowerTargeted(p);
        }
    }

    @Override
    public void mouseMoved(final MouseEvent e) {
        super.mouseMoved(e);
        motionPoint = new Point(e.getX() - 8, e.getY() - MENU_HEIGHT);
    }
}
