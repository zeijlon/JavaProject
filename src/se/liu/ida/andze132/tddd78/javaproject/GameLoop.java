package se.liu.ida.andze132.tddd78.javaproject;


import javax.swing.*;

/**
 * Created by Andreas Zeijlon on 2015-03-21.
 */
public class GameLoop {

    public GRID grid;
    public Shop shop;
    public JFrame frame;
    public EnemySpawner spawner;
    private boolean gameRunning;
    public boolean gamePaused;
    public static boolean gameIsPaused = false;
    private int lastFpsTime, fps;
    protected static int currentFPS;



    public GameLoop(GRID grid, Shop shop, JFrame frame, EnemySpawner spawner) {
        this.grid = grid;
        this.shop = shop;
        this.frame = frame;
        this.lastFpsTime = 0;
        this.fps = 0;
        this.gameRunning = true;
        this.gamePaused = false;
        this.spawner = spawner;

        gameLoop();
    }


    public void gameLoop()
    {
        long lastLoopTime = System.nanoTime();
        final int TARGET_FPS = 60;
        final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;

        // keep looping round til the game ends
        while (gameRunning)
        {
            // work out how long its been since the last update, this
            // will be used to calculate how far the entities should
            // move this loop
            long now = System.nanoTime();
            long updateLength = now - lastLoopTime;
            lastLoopTime = now;
            double delta = updateLength / ((double)OPTIMAL_TIME);

            // update the frame counter
            lastFpsTime += updateLength;
            fps++;

            // update our FPS counter if a second has passed since
            // we last recorded
            if (lastFpsTime >= 1000000000)
            {
                System.out.println("(FPS: "+fps+")");
                currentFPS = fps;
                lastFpsTime = 0;
                fps = 0;
            }

            // update the game logic
            if (!gameIsPaused) {
                doGameUpdates(delta);
            }

            // draw everything
            frame.repaint();

            // we want each frame to take 10 milliseconds, to do this
            // we've recorded when we started the frame. We add 10 milliseconds
            // to this and then factor in the current time to give
            // us our final value to wait for
            // remember this is in ms, whereas our lastLoopTime etc. vars are in ns.
            try {
                Thread.sleep((lastLoopTime-System.nanoTime() + OPTIMAL_TIME)/1000000 );
            } catch (Exception e) {
            }
        }
    }

    private void doGameUpdates(double delta) {
        spawner.spawnBasicEnemy();
        spawner.moveEnemy();
        spawner.checkEnemyFinished();
        if (Shop.health <= 0)
        {gameRunning = false;
    }

}}
