package se.liu.ida.andze132.tddd78.javaproject;


import javax.swing.*;
/**
 * Created by Andreas Zeijlon on 2015-03-21.
 */
public class GameLoop {

    private Shop shop;
    private JFrame frame;
    private KeyHandler keyHandler;
    private EnemySpawner spawner;
    private TowerHandler towerHandler;
    private BulletHandler bulletHandler;
    private Menu menu;
    private int targetFPS;
    private long optimalTime;


    private int lastFpsTime, fps;


    public GameLoop(Shop shop, JFrame frame, EnemySpawner spawner, TowerHandler towerHandler, BulletHandler bulletHandler, Menu menu, KeyHandler keyHandler) {
        this.shop = shop;
        this.frame = frame;
        this.spawner = spawner;
        this.towerHandler = towerHandler;
        this.bulletHandler = bulletHandler;
        this.menu = menu;
        this.keyHandler = keyHandler;

        this.targetFPS = 60;
        this.optimalTime = 1000000000 / targetFPS;
        this.lastFpsTime = 0;
        this.fps = 0;

        gameLoop();
    }

    public void gameLoop() {
        long lastLoopTime = System.nanoTime();

        while (menu.isGameRunning()) {
            long now = System.nanoTime();
            long updateLength = now - lastLoopTime;
            lastLoopTime = now;
            double delta = updateLength / ((double) optimalTime);

            lastFpsTime += (int) updateLength;
            fps++;

            if (lastFpsTime >= 1000000000) {
                lastFpsTime = 0;
                fps = 0;
            }

            doGameUpdates();

            frame.repaint();

            try {
                Thread.sleep((lastLoopTime - System.nanoTime() + optimalTime) / 1000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (Exception ignored) {
            }
        }
    }

    private void doGameUpdates() {
        if (menu.isGameOn()) {
            if(spawner.isFastForward()){
                optimalTime = 1000000000 / (targetFPS*2);
            }
            else{
                optimalTime = 1000000000 / (targetFPS);
            }
            frame.validate();
            frame.pack();
            spawner.waveHandler();
            spawner.checkEnemyFinished();
            spawner.moveEnemy();

            towerHandler.checkButtonClick();
            towerHandler.towerPhysic();

            bulletHandler.updateBullets();

            towerHandler.checkTowerTargeted();

            if (shop.getHealth() <= 0) {
                menu.setGameOn(false);
                menu.setIfMenu(true);
                keyHandler.setClickPoint(null);
            }
        } else if (menu.isIfMenu()) {
            menu.ifMenuedit();
        }

    }

}

