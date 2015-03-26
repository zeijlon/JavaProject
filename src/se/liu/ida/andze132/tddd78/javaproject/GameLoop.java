package se.liu.ida.andze132.tddd78.javaproject;


import javax.swing.*;

/**
 * Created by Andreas Zeijlon on 2015-03-21.
 */
public class GameLoop {

    private GRID grid;
    private Shop shop;
    private JFrame frame;
    private EnemySpawner spawner;
    private TowerHandler towerHandler;

    private boolean gameRunning;
    private boolean gameOn;
    private boolean gamePaused;
    private boolean menu;
    private int lastFpsTime, fps;


    public GameLoop(GRID grid, Shop shop, JFrame frame, EnemySpawner spawner, TowerHandler towerHandler) {
        this.grid = grid;
        this.shop = shop;
        this.frame = frame;
        this.spawner = spawner;
        this.towerHandler = towerHandler;

        this.lastFpsTime = 0;
        this.fps = 0;
        this.gameRunning = true;
        this.gameOn = true;
        this.gamePaused = false;

        gameLoop();
    }

    public void gameLoop() {
        long lastLoopTime = System.nanoTime();
        final int TARGET_FPS = 60;
        final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;

        // keep looping round til the game ends
        while (gameRunning) {
            // work out how long its been since the last update, this
            // will be used to calculate how far the entities should
            // move this loop
            long now = System.nanoTime();
            long updateLength = now - lastLoopTime;
            lastLoopTime = now;
            double delta = updateLength / ((double) OPTIMAL_TIME);

            // update the frame counter
            lastFpsTime += updateLength;
            fps++;

            // update our FPS counter if a second has passed since
            // we last recorded
            if (lastFpsTime >= 1000000000) {
                //System.out.println("(FPS: " + fps + ")");
                lastFpsTime = 0;
                fps = 0;
            }

            // update the game logic
            doGameUpdates(delta);

            // draw everything
            frame.repaint();

            // we want each frame to take 10 milliseconds, to do this
            // we've recorded when we started the frame. We add 10 milliseconds
            // to this and then factor in the current time to give
            // us our final value to wait for
            // remember this is in ms, whereas our lastLoopTime etc. vars are in ns.
            try {
                Thread.sleep((lastLoopTime - System.nanoTime() + OPTIMAL_TIME) / 1000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (Exception ignored) {
            }
        }
    }

    private void doGameUpdates(double delta) {
        if (gameOn) {
            spawner.waveHandler();
            spawner.checkEnemyFinished();

            spawner.moveEnemy();

            towerHandler.checkButtonClick();
            towerHandler.checkEnemyWithinReach();
            if (shop.getHealth() <= 0) {
                gameOn = false;
                menu = true;
            }
        }
        if (gamePaused) {

        }
        if (menu) {

        }

    }
}
