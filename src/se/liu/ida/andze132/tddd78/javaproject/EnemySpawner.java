package se.liu.ida.andze132.tddd78.javaproject;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Andreas Zeijlon on 2015-03-21.
 */
public class EnemySpawner {
    public GRID grid;

    public java.util.List<Enemy> enemies = new ArrayList<>();
    public int spawnRate = 300, spawnTime = 0;
    public boolean enemySpawned = false;

    Image basicEnemy = Toolkit.getDefaultToolkit().getImage("images/basicEnemy.png");

    public EnemySpawner(GRID grid) {
        this.grid = grid;
    }

    public void spawnBasicEnemy( ){
        if(spawnTime >= spawnRate){
            Enemy basic = new BasicEnemy();
            enemies.add(basic);
            enemySpawned = true;
            spawnTime = 0;
        }
        else {
            spawnTime ++;
        }
    }


    public void deleteEnemy(){
    }

    public void draw(Graphics g){
        for (int y = 0; y < grid.getSquares().length; y++) {
            for (int x = 0; x < grid.getSquares()[y].length; x++) {
                if(grid.getSquares()[y][x] == SquareType.START){
                    if(enemySpawned)
                        g.drawImage(basicEnemy, x * 40, y * 40, null);
                }

            }

        }



    }}
