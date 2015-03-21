package se.liu.ida.andze132.tddd78.javaproject;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andreas Zeijlon on 2015-03-21.
 */
public class EnemySpawner {
    public GRID grid;

    public List<Enemy> enemies = new ArrayList<>();
    public int spawnRate = 300, spawnTime = 0;

    Image basicEnemy = Toolkit.getDefaultToolkit().getImage("images/basicEnemy.png");

    public EnemySpawner(GRID grid) {
        this.grid = grid;
    }

    public void spawnBasicEnemy( ){
        if(spawnTime >= spawnRate){
            Enemy basic = new BasicEnemy();
            enemies.add(basic);
            for (int y = 0; y < grid.getSquares().length; y++) {
                for (int x = 0; x < grid.getSquares()[y].length; x++) {
                    if(grid.getSquares()[y][x] == 3){
                        basic.Y = y*40;
                        basic.X = x*40;
                    }
                }
            }
            basic.enemySpawned = true;
            spawnTime = 0;
        }
        else {
            spawnTime ++;
        }
    }

    public void moveEnemy(){
        for (int i = 0; i < enemies.size(); i++) {
            int x = enemies.get(i).X;
            int y = enemies.get(i).Y;
            if(enemies.get(i).moveRight){
                if(grid.getSquares()[y/40][(x+40)/40] == GRID.PATH || grid.getSquares()[y/40][(x+40)/40] == GRID.FINISH){
                    enemies.get(i).X += enemies.get(i).getSpeed();
                }
                else{
                    enemies.get(i).moveRight = false;
                    changeDirection(enemies.get(i));
                }
            }
            else if (enemies.get(i).moveLeft){
                if(grid.getSquares()[y/40][(x-40)/40] == GRID.PATH || grid.getSquares()[y/40][(x-40)/40] == GRID.FINISH){
                    enemies.get(i).X -= enemies.get(i).getSpeed();
                }
                else{
                    enemies.get(i).moveLeft = false;
                    changeDirection(enemies.get(i));
                }
            }
            else if (enemies.get(i).moveUp){
                if(grid.getSquares()[(y-40)/40][x/40] == GRID.PATH || grid.getSquares()[(y-40)/40][(x)/40] == GRID.FINISH){
                    enemies.get(i).Y -= enemies.get(i).getSpeed();
                }
                else{
                    enemies.get(i).moveUp = false;
                    changeDirection(enemies.get(i));
                }
            }
            else if (enemies.get(i).moveDown){
                if(grid.getSquares()[(y+40)/40][x/40] == GRID.PATH || grid.getSquares()[(y+40)/40][x/40] == GRID.FINISH){
                    enemies.get(i).Y += enemies.get(i).getSpeed();
                }
                else{
                    enemies.get(i).moveDown = false;
                    changeDirection(enemies.get(i));
                }
            }
    }}

    public void changeDirection(Enemy enemy){
        int x = enemy.X;
        int y = enemy.Y;
        if(grid.getSquares()[y/40][(x+40)/40] == GRID.PATH || grid.getSquares()[y/40][(x+40)/40] == GRID.FINISH){
            enemy.moveRight = true;
        }

        else if(grid.getSquares()[(y-40)/40][x/40] == GRID.PATH || grid.getSquares()[(y-40)/40][x/40] == GRID.FINISH){
            enemy.moveUp = true;
        }
        else if(grid.getSquares()[(y+40)/40][x/40] == GRID.PATH || grid.getSquares()[(y+40)/40][x/40] == GRID.FINISH){
            enemy.moveDown = true;
        }
        else if(grid.getSquares()[y/40][(x-40)/40] == GRID.PATH || grid.getSquares()[y/40][(x-40)/40] == GRID.FINISH){
            enemy.moveLeft = true;
        }
    }

    public void checkEnemyFinished(){
        for (int i = 0; i < enemies.size(); i++) {
            int x = enemies.get(i).X;
            int y = enemies.get(i).Y;
            if(grid.getSquares()[y/40][(x)/40] == GRID.FINISH){
                Shop.health -= enemies.get(i).getDamage();
                enemies.remove(i);
            }}}


    public void draw(Graphics g){
        for (int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i).enemySpawned){
                g.drawImage(basicEnemy, enemies.get(i).X, enemies.get(i).Y, null);
                }

            }
        }
    }
