package se.liu.ida.andze132.tddd78.javaproject;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andreas Zeijlon on 2015-03-21.
 */
public class EnemySpawner {
    private GRID grid;
    private Shop shop;

    private List<Enemy> enemies = new ArrayList<>();
    private int spawnTime = 0;
    private int level = 0, enemiesSpawned;
    private int enemyCount = 1;
    private int basicEnemyCount = 1;
    private int armoredEnemyCount = 1;
   // private int enemyCount = basicEnemyCount+=level*2+armoredEnemyCount;



    private boolean betweenRounds;

    private Rectangle nextRoundButton;


    public EnemySpawner(GRID grid, Shop shop) {
        this.grid = grid;
        this.shop = shop;
        this.enemiesSpawned = 0;
        this.betweenRounds = true;

        this.nextRoundButton = new Rectangle(grid.getWidth() + Shop.SHOP_MARGIN, grid.getHeight() + Shop.SHOP_MARGIN, 50, 50);
    }


    public void waveHandler() {
        if (!betweenRounds) {
            final int spawnRate = 75;
            if (spawnTime >= spawnRate) {
                if (enemiesSpawned < enemyCount) {
                    spawnBasicEnemy();
                    if(level == 5 ) {
                        spawnArmoredEnemy();
                        armoredEnemyCount +=1;
                                                 }
                    else if (level > 9) {
                        spawnArmoredEnemy();}

                    enemiesSpawned += 1;
                    spawnTime = 0;
                } else {
                    if (enemies.isEmpty()) {
                        if ( level > 9) {
                            armoredEnemyCount += level * 2;
                        }
                            betweenRounds = true;
                            enemiesSpawned = 0;
                            enemyCount += level * 2;
                        }
                }
            } else {
                spawnTime++;
            }

        }
        if (shop.getHoldsItem() == null) {
            if (nextRoundButton.contains(GameFrame.clickPoint)) {
                if (betweenRounds) {
                    level++;
                    betweenRounds = false;
                }
                GameFrame.clickPoint = new Point();
            }
        }
    }
    public void addAllEnemiesForSpawn(){
        enemyCount = basicEnemyCount+armoredEnemyCount;
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
    public void spawnArmoredEnemy() {
        Enemy armored = new ArmoredEnemy();
        enemies.add(armored);
        for (int y = 0; y < grid.getSquares().length; y++) {
            for (int x = 0; x < grid.getSquares()[y].length; x++) {
                if (grid.getSquares()[y][x] == GRID.START) {
                    armored.setyC(y);
                    armored.setxC(x);
                    armored.setY(y * GameComponent.TILE_SIZE);
                    armored.setX(x * GameComponent.TILE_SIZE);
                }
            }
        }
        defineHasWalked(armored);
        decideDirection(armored);
        defineEnemyRect(armored);
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
            enemy.setDirection(Direction.RIGHT);
        } else if (!collision(enemy, enemy.getyC(), enemy.getxC() - 1)) {
            enemy.setDirection(Direction.LEFT);
        }
        if (!collision(enemy, enemy.getyC() + 1, enemy.getxC())) {
            enemy.setDirection(Direction.DOWN);
        }
        if (!collision(enemy, enemy.getyC() - 1, enemy.getxC())) {
            enemy.setDirection(Direction.UP
            );
        }
    }

    public void moveEnemy() {
        for (Enemy enemy : enemies) {
            if (enemy.getDirection() == Direction.RIGHT) {
                enemy.setAngle(Math.PI/2);
                enemy.setX(enemy.getX() + enemy.getSpeed());
            } else if (enemy.getDirection() == Direction.LEFT) {
                enemy.setAngle(3*Math.PI/2);
                enemy.setX(enemy.getX() - enemy.getSpeed());
            } else if (enemy.getDirection() == Direction.DOWN) {
                enemy.setAngle(Math.PI);
                enemy.setY(enemy.getY() + enemy.getSpeed());
            } else if (enemy.getDirection() == Direction.UP) {
                enemy.setAngle(0);
                enemy.setY(enemy.getY() - enemy.getSpeed());
            }
            defineEnemyRect(enemy);
            enemy.setEnemyWalk(enemy.getEnemyWalk() + enemy.getSpeed());


            if (enemy.getEnemyWalk() >= GameComponent.TILE_SIZE) {

                if (enemy.getDirection() == Direction.RIGHT) {
                    enemy.getHasWalked()[enemy.getyC()][enemy.getxC()] = grid.getSquares()[enemy.getyC()][enemy.getxC()];
                    enemy.setxC(enemy.getxC() + 1);
                    if (collision(enemy, enemy.getyC(), enemy.getxC() + 1)) {
                        enemy.setHasRight(true);
                        changeDirection(enemy);
                    }
                } else if (enemy.getDirection() == Direction.LEFT) {
                    enemy.getHasWalked()[enemy.getyC()][enemy.getxC()] = grid.getSquares()[enemy.getyC()][enemy.getxC()];
                    enemy.setxC(enemy.getxC() - 1);
                    if (collision(enemy, enemy.getyC(), enemy.getxC() - 1)) {
                        enemy.setHasLeft(true);
                        changeDirection(enemy);
                    }
                } else if (enemy.getDirection() == Direction.DOWN) {
                    enemy.getHasWalked()[enemy.getyC()][enemy.getxC()] = grid.getSquares()[enemy.getyC()][enemy.getxC()];
                    enemy.setyC(enemy.getyC() + 1);
                    if (collision(enemy, enemy.getyC() + 1, enemy.getxC())) {
                        enemy.setHasDown(true);
                        changeDirection(enemy);
                    }
                } else if (enemy.getDirection() == Direction.UP) {
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
                enemy.setDirection(Direction.DOWN);
            } else if (!collision(enemy, enemy.getyC() - 1, enemy.getxC())) {
                enemy.setDirection(Direction.UP);
            } else {
                enemy.setDirection(Direction.STILL);
            }
        } else if (enemy.isHasDown() || enemy.isHasUp()) {
            if (!collision(enemy, enemy.getyC(), enemy.getxC() + 1)) {
                enemy.setDirection(Direction.RIGHT);
            } else if (!collision(enemy, enemy.getyC(), enemy.getxC() - 1)) {
                enemy.setDirection(Direction.LEFT);
            } else {
                enemy.setDirection(Direction.STILL);
            }
        }
    }

    public void checkEnemyFinished() {
        for (int i = 0; i < enemies.size(); i++) {
            int x = enemies.get(i).getxC();
            int y = enemies.get(i).getyC();
            if (enemies.get(i).getHp() <= 0) {
                    shop.withdrawGold(enemies.get(i).getGoldgain());
                enemies.remove(enemies.get(i));
                }
                else if (grid.getSquares()[y][x] == GRID.FINISH) {
                    shop.setHealth(enemies.get(i).getDamage());
                    enemies.remove(enemies.get(i));
                }
            }


        }


    public void draw(Graphics2D g) {
        for (Enemy enemy : enemies) {
            AffineTransform at = new AffineTransform();
            AffineTransform old = g.getTransform();
            at.rotate(enemy.getAngle(), enemy.getX() + enemy.getImage().getWidth(null) / 2,
                      enemy.getY() + enemy.getImage().getHeight(null) / 2);
            g.transform(at);
            g.drawImage(enemy.getImage(), enemy.getX(), enemy.getY(), null);
            g.setTransform(old);
            g.setFont(new Font("courier new", Font.BOLD, 14));
            g.drawString(String.valueOf(enemy.getHp()), enemy.getX(), enemy.getY());
        }
        g.setColor(Color.red);
        g.setFont(new Font("courier new", Font.BOLD, 20));
        if (level >= 1) {
            g.drawString("ROUND: " + level, grid.getWidth() + Shop.SHOP_MARGIN, grid.getHeight());
        }

        //Draw next round button
        if (betweenRounds) {
            g.setColor(Color.black);
            g.fillRect(grid.getWidth() + Shop.SHOP_MARGIN, grid.getHeight() + Shop.SHOP_MARGIN, 50, 50);
        } else {
            g.setColor(Color.green);
            g.fillRect(grid.getWidth() + Shop.SHOP_MARGIN, grid.getHeight() + Shop.SHOP_MARGIN, 50, 50);
        }
    }

    public boolean isBetweenRounds() {
        return betweenRounds;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

}
