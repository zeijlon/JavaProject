package se.liu.ida.andze132.tddd78.javaproject;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andreas Zeijlon on 2015-03-21.
 */
public class EnemySpawner {
    private GRID grid;
    private Shop shop;

    private List<Enemy> enemies = new ArrayList<>();
    private int spawnRate = 50, spawnTime = 0;
    private int level = 0, enemiesSpawned;
    private int enemyCount = 1;


    private boolean betweenRounds;

    private Rectangle nextRoundButton;


    public EnemySpawner(GRID grid, Shop shop) {
        this.grid = grid;
        this.shop = shop;
        this.enemiesSpawned = 0;
        this.betweenRounds = true;

        this.nextRoundButton = new Rectangle(grid.gridWidth + Shop.SHOP_MARGIN, grid.gridHeight + Shop.SHOP_MARGIN, 50, 50);
    }


    public void waveHandler() {
        if (!betweenRounds) {
            if (spawnTime >= spawnRate) {
                if (enemiesSpawned < enemyCount) {
                    spawnBasicEnemy();
                    enemiesSpawned += 1;
                    spawnTime = 0;
                } else {
                    if (enemies.isEmpty()) {
                        betweenRounds = true;
                        enemiesSpawned = 0;
                        enemyCount += level * 2;
                    }
                }
            } else {
                spawnTime++;
            }

        }
        if (shop.getHoldsItem() == shop.getNothing()) {
            if (nextRoundButton.contains(GameFrame.clickPoint)) {
                if (betweenRounds) {
                    level++;
                    betweenRounds = false;
                }
                GameFrame.clickPoint = new Point();
            }
        }
    }

    public void spawnBasicEnemy() {
        Enemy basic = new BasicEnemy();
        enemies.add(basic);
        for (int y = 0; y < grid.getSquares().length; y++) {
            for (int x = 0; x < grid.getSquares()[y].length; x++) {
                if (grid.getSquares()[y][x] == GRID.START) {
                    basic.setyC(y);
                    basic.setxC(x);
                    basic.setY(y * GameComponent.TILE_SIZE);
                    basic.setX(x * GameComponent.TILE_SIZE);
                }
            }
        }
        defineHasWalked(basic);
        decideDirection(basic);
        defineEnemyRect(basic);
    }

    public void defineEnemyRect(Enemy enemy) {
        enemy.setEnemyRect(new Rectangle(enemy.getX(), enemy.getY(), GameComponent.TILE_SIZE, GameComponent.TILE_SIZE));
    }

    public void defineHasWalked(Enemy enemy) {
        int height = grid.getSquares().length;
        int width = GRID.checkLargestRow(grid);
        enemy.setHasWalked(new int[height][width]);
    }

    public void decideDirection(Enemy enemy) {
        if (!collision(enemy, enemy.getyC(), enemy.getxC() + 1)) {
            enemy.setDirection(Enemy.getRight());
        } else if (!collision(enemy, enemy.getyC(), enemy.getxC() - 1)) {
            enemy.setDirection(Enemy.getLeft());
        }
        if (!collision(enemy, enemy.getyC() + 1, enemy.getxC())) {
            enemy.setDirection(Enemy.getDown());
        }
        if (!collision(enemy, enemy.getyC() - 1, enemy.getxC())) {
            enemy.setDirection(Enemy.getUp());
        }
    }

    public void moveEnemy() {
        for (Enemy enemy : enemies) {
            if (enemy.getDirection() == Enemy.getRight()) {
                enemy.setX(enemy.getX() + enemy.getSpeed());
            } else if (enemy.getDirection() == Enemy.getLeft()) {
                enemy.setX(enemy.getX() - enemy.getSpeed());
            } else if (enemy.getDirection() == Enemy.getDown()) {
                enemy.setY(enemy.getY() + enemy.getSpeed());
            } else if (enemy.getDirection() == Enemy.getUp()) {
                enemy.setY(enemy.getY() - enemy.getSpeed());
            }
            defineEnemyRect(enemy);
            enemy.setEnemyWalk(enemy.getEnemyWalk() + enemy.getSpeed());


            if (enemy.getEnemyWalk() == GameComponent.TILE_SIZE) {

                if (enemy.getDirection() == Enemy.getRight()) {
                    enemy.getHasWalked()[enemy.getyC()][enemy.getxC()] = grid.getSquares()[enemy.getyC()][enemy.getxC()];
                    enemy.setxC(enemy.getxC() + 1);
                    if (collision(enemy, enemy.getyC(), enemy.getxC() + 1)) {
                        enemy.setHasRight(true);
                        changeDirection(enemy);
                    }
                } else if (enemy.getDirection() == Enemy.getLeft()) {
                    enemy.getHasWalked()[enemy.getyC()][enemy.getxC()] = grid.getSquares()[enemy.getyC()][enemy.getxC()];
                    enemy.setxC(enemy.getxC() - 1);
                    if (collision(enemy, enemy.getyC(), enemy.getxC() - 1)) {
                        enemy.setHasLeft(true);
                        changeDirection(enemy);
                    }
                } else if (enemy.getDirection() == Enemy.getDown()) {
                    enemy.getHasWalked()[enemy.getyC()][enemy.getxC()] = grid.getSquares()[enemy.getyC()][enemy.getxC()];
                    enemy.setyC(enemy.getyC() + 1);
                    if (collision(enemy, enemy.getyC() + 1, enemy.getxC())) {
                        enemy.setHasDown(true);
                        changeDirection(enemy);
                    }
                } else if (enemy.getDirection() == Enemy.getUp()) {
                    enemy.getHasWalked()[enemy.getyC()][enemy.getxC()] = grid.getSquares()[enemy.getyC()][enemy.getxC()];
                    enemy.setyC(enemy.getyC() - 1);
                    if (collision(enemy, enemy.getyC() - 1, enemy.getxC())) {
                        enemy.setHasUp(true);
                        changeDirection(enemy);
                    }
                }
                enemy.setHasDown(false);
                enemy.setHasLeft(false);
                enemy.setHasUp(false);
                enemy.setHasRight(false);
                enemy.setEnemyWalk(0);
            }
        }
    }

    public boolean collision(Enemy enemy, int y, int x) {
        try {
            if (enemy.getHasWalked()[y][x] != GRID.PATH) {
                if (grid.getSquares()[y][x] == GRID.PATH || grid.getSquares()[y][x] == GRID.CROSSROAD ||
                        grid.getSquares()[y][x] == GRID.FINISH) {
                    return false;
                }
            }
        } catch (RuntimeException ignored) {
            return true;
        }
        return true;
    }


    public void changeDirection(Enemy enemy) {
        if (enemy.isHasRight() || enemy.isHasLeft()) {
            if (!collision(enemy, enemy.getyC() + 1, enemy.getxC())) {
                enemy.setDirection(Enemy.getDown());
            } else if (!collision(enemy, enemy.getyC() - 1, enemy.getxC())) {
                enemy.setDirection(Enemy.getUp());
            } else {
                enemy.setDirection(Enemy.getStill());
            }
        } else if (enemy.isHasDown() || enemy.isHasUp()) {
            if (!collision(enemy, enemy.getyC(), enemy.getxC() + 1)) {
                enemy.setDirection(Enemy.getRight());
            } else if (!collision(enemy, enemy.getyC(), enemy.getxC() - 1)) {
                enemy.setDirection(Enemy.getLeft());
            } else {
                enemy.setDirection(Enemy.getStill());
            }
        }
    }

    public void checkEnemyFinished() {
        try {
            for (Enemy enemy : enemies) {
                if (enemy.getHp() <= 0) {
                    enemies.remove(enemy);
                    shop.withdrawGold(enemy.getGoldgain());
                }

                int x = enemy.getxC();
                int y = enemy.getyC();
                if (grid.getSquares()[y][x] == GRID.FINISH) {
                    shop.setHealth(enemy.getDamage());
                    enemies.remove(enemy);
                }
            }


        } catch (Exception ignored) {
        }
    }


    public void draw(Graphics g) {
        for (Enemy enemy : enemies) {
            g.drawImage(enemy.getImage(), enemy.getX(), enemy.getY(), null);
            g.setFont(new Font("courier new", Font.BOLD, 14));
            g.drawString("" + enemy.getHp(), enemy.getX(), enemy.getY());
        }
        g.setColor(Color.red);
        g.setFont(new Font("courier new", Font.BOLD, 20));
        if (level >= 1) {
            g.drawString("ROUND: " + level, grid.gridWidth + Shop.SHOP_MARGIN, grid.gridHeight);
        }

        //Draw next round button
        if (betweenRounds) {
            g.setColor(Color.black);
            g.fillRect(grid.gridWidth + Shop.SHOP_MARGIN, grid.gridHeight + Shop.SHOP_MARGIN, 50, 50);
        } else {
            g.setColor(Color.green);
            g.fillRect(grid.gridWidth + Shop.SHOP_MARGIN, grid.gridHeight + Shop.SHOP_MARGIN, 50, 50);
        }
    }

    public boolean isBetweenRounds() {
        return betweenRounds;
    }

    public void setBetweenRounds(final boolean betweenRounds) {
        this.betweenRounds = betweenRounds;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public void removeEnemy(Enemy enemy) {
        this.enemies.remove(enemy);
    }
}
