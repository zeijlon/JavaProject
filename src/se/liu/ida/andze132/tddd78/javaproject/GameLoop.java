package se.liu.ida.andze132.tddd78.javaproject;


import javax.swing.*;
/**
 * Created by Andreas Zeijlon on 2015-03-21.
 */
public class GameLoop {

    private static final int ONE_BILLION = 1000000000;
    private static final int ONE_MILLION = 1000000;
    private Shop shop;
    private JFrame frame;
    private KeyHandler keyHandler;
    private EnemySpawner spawner;
    private TowerHandler towerHandler;
    private BulletHandler bulletHandler;
    private Menu menu;
    private long targetFPS;
    private long optimalTime;
    private long lastLoopTime;

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
        this.optimalTime = ONE_BILLION / targetFPS;
        this.lastFpsTime = 0;
        this.fps = 0;

        loop();
    }

    private void loop() {

        while (menu.isGameRunning()) {
            long now = System.nanoTime();
            long updateLength = now - lastLoopTime;
            lastLoopTime = now;
            //double delta = updateLength / ((double) optimalTime);

            lastFpsTime += (int) updateLength;
            fps++;

            if (lastFpsTime >= ONE_BILLION) {
                lastFpsTime = 0;
                fps = 0;
            }

            doGameUpdates();

            frame.repaint();

            sleep();
        }
    }

    private void sleep(){
        try {
            Thread.sleep((lastLoopTime - System.nanoTime() + optimalTime) / ONE_MILLION);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        catch(IllegalArgumentException ignored){}
    }

    private void doGameUpdates() {
        if (menu.isGameOn()) {
            if(spawner.isFastForward()){
                optimalTime = ONE_BILLION / (targetFPS*2);
            }
            else{
                optimalTime = ONE_BILLION / (targetFPS);
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
                menu.setIfLost(true);
                keyHandler.setClickPoint();
            }
        } else if (menu.isIfMenu()) {
            menu.menuEdit();
        }

    }

}

