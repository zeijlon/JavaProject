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
    public int spawnRate = 100, spawnTime = 0;


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
        for (Enemy enemy : enemies) {
            if (enemy.direction == Enemy.right) {
                enemy.X += enemy.getSpeed();
            } else if (enemy.direction == Enemy.left) {
                enemy.X -= enemy.getSpeed();
            } else if (enemy.direction == Enemy.down) {
                enemy.Y += enemy.getSpeed();
            } else if (enemy.direction == Enemy.up) {
                enemy.Y -= enemy.getSpeed();
            }

            enemy.enemyWalk += enemy.getSpeed();


            if (enemy.enemyWalk == GameComponent.TILE_SIZE) {

                if (enemy.direction == Enemy.right) {
                    enemy.xC += 1;
                    if (collision(enemy.yC, enemy.xC + 1)) {
                        enemy.hasRight = true;
                        changeDirection(enemy);
                    }
                } else if (enemy.direction == Enemy.left) {
                    enemy.xC -= 1;
                    if (collision(enemy.yC, enemy.xC - 1)) {
                        enemy.hasLeft = true;
                        changeDirection(enemy);
                    }
                } else if (enemy.direction == Enemy.down) {
                    enemy.yC += 1;
                    if (collision(enemy.yC + 1, enemy.xC)) {
                        enemy.hasDown = true;
                        changeDirection(enemy);
                    }
                } else if (enemy.direction == Enemy.up) {
                    enemy.yC -= 1;
                    if (collision(enemy.yC - 1, enemy.xC)) {
                        enemy.hasUp = true;
                        changeDirection(enemy);
                    }
                }
                enemy.hasDown = false;
                enemy.hasLeft = false;
                enemy.hasUp = false;
                enemy.hasRight = false;
                enemy.enemyWalk = 0;
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
        for (Enemy enemy : enemies) {
            if (enemy.enemySpawned) {
                g.drawImage(basicEnemy, enemy.X, enemy.Y, null);
            }

        }
    }
}
