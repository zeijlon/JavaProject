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

    public void spawnBasicEnemy() {
        if (spawnTime >= spawnRate) {
            Enemy basic = new BasicEnemy();
            enemies.add(basic);
            for (int y = 0; y < grid.getSquares().length; y++) {
                for (int x = 0; x < grid.getSquares()[y].length; x++) {
                    if (grid.getSquares()[y][x] == 3) {
                        basic.yC = y;
                        basic.xC = x;
                        basic.Y = y * 40;
                        basic.X = x * 40;
                    }
                }
            }
            basic.enemySpawned = true;
            checkDirection(basic);
            spawnTime = 0;
        } else {
            spawnTime++;
        }
    }

    public void checkDirection(Enemy enemy){
        if(!collision(enemy.yC, enemy.xC + 1)){
            enemy.direction = Enemy.right;
        }
        else if(!collision(enemy.yC, enemy.xC - 1)){
            enemy.direction = Enemy.left;
        }
        if(!collision(enemy.yC + 1, enemy.xC)){
            enemy.direction = Enemy.down;
        }
        if(!collision(enemy.yC - 1, enemy.xC)){
            enemy.direction = Enemy.up;
        }
    }

    public void moveEnemy() {
        for (int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i).direction == Enemy.right) {
                enemies.get(i).X += enemies.get(i).getSpeed();
            } else if (enemies.get(i).direction == Enemy.left) {
                enemies.get(i).X -= enemies.get(i).getSpeed();
            } else if (enemies.get(i).direction == Enemy.down) {
                enemies.get(i).Y += enemies.get(i).getSpeed();
            } else if (enemies.get(i).direction == Enemy.up) {
                enemies.get(i).Y -= enemies.get(i).getSpeed();
            }

            enemies.get(i).enemyWalk += enemies.get(i).getSpeed();


            if (enemies.get(i).enemyWalk == GameComponent.TILE_SIZE) {

                if (enemies.get(i).direction == Enemy.right) {
                    enemies.get(i).xC += 1;
                    System.out.println(enemies.get(i).xC);
                    if (collision(enemies.get(i).yC, enemies.get(i).xC + 1)) {
                        enemies.get(i).hasRight = true;
                        changeDirection(enemies.get(i));
                    }
                } else if (enemies.get(i).direction == Enemy.left) {
                    enemies.get(i).xC -= 1;
                    if (collision(enemies.get(i).yC, enemies.get(i).xC - 1)) {
                        enemies.get(i).hasLeft = true;
                        changeDirection(enemies.get(i));
                    }
                } else if (enemies.get(i).direction == Enemy.down) {
                    enemies.get(i).yC += 1;
                    if (collision(enemies.get(i).yC + 1, enemies.get(i).xC)) {
                        enemies.get(i).hasDown = true;
                        changeDirection(enemies.get(i));
                    }
                } else if (enemies.get(i).direction == Enemy.up) {
                    enemies.get(i).yC -= 1;
                    if (collision(enemies.get(i).yC - 1, enemies.get(i).xC)) {
                        enemies.get(i).hasUp = true;
                        changeDirection(enemies.get(i));
                    }
                }
                enemies.get(i).hasDown = false;
                enemies.get(i).hasLeft = false;
                enemies.get(i).hasUp = false;
                enemies.get(i).hasRight = false;
                enemies.get(i).enemyWalk = 0;
            }
        }
    }

    public boolean collision(int y, int x) {
        try {
            if (grid.getSquares()[y][x] == GRID.PATH || grid.getSquares()[y][x] == GRID.FINISH) {
                return false;
            }
        } catch (Exception e) {
            return true;
        }
        return true;
    }


    public void changeDirection(Enemy enemy) {
        if (enemy.hasRight) {
            if (!collision(enemy.yC + 1, enemy.xC)) {
                enemy.direction = Enemy.down;
            } else {
                enemy.direction = Enemy.up;
            }
        } else if (enemy.hasDown) {
            if (!collision(enemy.yC, enemy.xC + 1)) {
                enemy.direction = Enemy.right;
            } else {
                enemy.direction = Enemy.left;
            }
        } else if (enemy.hasUp) {
            if (!collision(enemy.yC, enemy.xC + 1)) {
                enemy.direction = Enemy.right;
            } else {
                enemy.direction = Enemy.left;
            }
        } else if (enemy.hasLeft) {
            if (!collision(enemy.yC + 1, enemy.xC)) {
                enemy.direction = Enemy.down;
            } else {
                enemy.direction = Enemy.up;
            }
        }
    }

    public void checkEnemyFinished() {
        for (int i = 0; i < enemies.size(); i++) {
            int x = enemies.get(i).X;
            int y = enemies.get(i).Y;
            if (grid.getSquares()[y / 40][(x) / 40] == GRID.FINISH) {
                Shop.health -= enemies.get(i).getDamage();
                enemies.remove(i);
            }
        }
    }


    public void draw(Graphics g) {
        for (int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i).enemySpawned) {
                g.drawImage(basicEnemy, enemies.get(i).X, enemies.get(i).Y, null);
            }

        }
    }
}
