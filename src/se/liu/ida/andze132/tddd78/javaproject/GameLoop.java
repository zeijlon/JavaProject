package se.liu.ida.andze132.tddd78.javaproject;


import javax.swing.*;

/**
 * Created by Andreas Zeijlon on 2015-03-21.
 */
public class GameLoop {

    private Shop shop;
    private JFrame frame;
    private EnemySpawner spawner;
    private TowerHandler towerHandler;
    private BulletHandler bulletHandler;

    private boolean gameRunning;
    private boolean gameOn;
    private boolean gamePaused;
    private boolean menu;
    private int lastFpsTime, fps;


    public GameLoop(Shop shop, JFrame frame, EnemySpawner spawner, TowerHandler towerHandler, BulletHandler bulletHandler) {
        this.shop = shop;
        this.frame = frame;
        this.spawner = spawner;
        this.towerHandler = towerHandler;
        this.bulletHandler = bulletHandler;

        this.lastFpsTime = 0;
        this.fps = 0;
        this.gameRunning = true;
        this.gameOn = true;
        this.gamePaused = false;

        gameLoop();
    }

    public void gameLoop() {
        long lastLoopTime = System.nanoTime();
        final int targetFps = 60;
        final long optimalTime = 1000000000 / targetFps;

        while (gameRunning) {
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
            }  catch (InterruptedException e) {
                e.printStackTrace();
            } catch (Exception ignored) {
            }
        }
    }

    private void doGameUpdates() {
        if (gameOn) {
            spawner.waveHandler();
            spawner.checkEnemyFinished();
            spawner.moveEnemy();

            towerHandler.checkButtonClick();
            towerHandler.towerPhysic();

            bulletHandler.updateBullets();
            if (shop.getHealth() <= 0) {
                gameOn = false;
                menu = true;
            }
        }
        //if (gamePaused) {

        }
       // if (menu) {

        }

   // }
//}
