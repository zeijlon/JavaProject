package se.liu.ida.andze132.tddd78.javaproject;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.*;
import java.util.List;

/**
 * Created by Andreas Zeijlon on 2015-03-21.
 */
public class EnemySpawner {
    private static final int WAVE_BUTTON_SIZE = 150;
    public static final int SPACE_WAVE_BUTTON_Y = 60;
    public static final int SPACE_WAVE_BUTTON_X = 20;

    private GRID grid;
    private Shop shop;
    private KeyHandler keyHandler;

    private List<Enemy> enemies = new ArrayList<>();
    private List<Start> starts = new ArrayList<>();
    private Map<String, Sound> sfx;
    private int spawnTime = 0;
    private int level = 0, enemiesSpawned;
    private int enemyCount = 1;
    private int armoredEnemyCount = 1;


    private Image nextWave = Toolkit.getDefaultToolkit().getImage("images/nextWave.png").getScaledInstance(WAVE_BUTTON_SIZE, WAVE_BUTTON_SIZE, Image.SCALE_DEFAULT);
    private Image slowMode = Toolkit.getDefaultToolkit().getImage("images/slowMode.png").getScaledInstance(WAVE_BUTTON_SIZE, WAVE_BUTTON_SIZE, Image.SCALE_DEFAULT);
    private Image fastMode = Toolkit.getDefaultToolkit().getImage("images/fastMode.png").getScaledInstance(WAVE_BUTTON_SIZE, WAVE_BUTTON_SIZE, Image.SCALE_DEFAULT);



    private boolean betweenRounds;
    private boolean fastForward;

    private Rectangle nextRoundButton;


    public EnemySpawner(GRID grid, Shop shop, KeyHandler keyHandler) {
        this.grid = grid;
        this.shop = shop;
        this.enemiesSpawned = 0;
        this.betweenRounds = true;
        this.fastForward = false;
        this.keyHandler = keyHandler;
	sfx = new HashMap<>();
	sfx.put("dies", new Sound("sounds/beardeath.wav"));
        this.nextRoundButton = new Rectangle(grid.getWidth()+ 20, grid.getHeight() - WAVE_BUTTON_SIZE, WAVE_BUTTON_SIZE, WAVE_BUTTON_SIZE);
    }



    public void waveHandler() {
        if (!betweenRounds) {
            final int spawnRate = 75;
            if (spawnTime >= spawnRate) {
                if (enemiesSpawned < enemyCount) {
                    spawnEnemy(new BasicEnemy());
                    if (level == 5) {
                        spawnEnemy(new SpyEnemy());
                        armoredEnemyCount += 1;
                        enemyCount = 0;
                    } else if (level > 9) {
                        spawnEnemy(new ArmoredEnemy());
                    }

                    enemiesSpawned += 1;
                    spawnTime = 0;
                } else {
                    if (enemies.isEmpty()) {
                        if (level > 9) {
                            armoredEnemyCount += level * 2;
                        }
                        betweenRounds = true;
                        fastForward = false;
                        enemiesSpawned = 0;
                        enemyCount += level * 2;
                    }
                }
            } else {
                spawnTime++;
            }

        }
        if (shop.getHoldsItem() == null) {
            if (keyHandler.getClickPoint() != null) {
                if (nextRoundButton.contains(keyHandler.getClickPoint())) {
                    if (betweenRounds) {
                        level++;
                        betweenRounds = false;
                    } else {
                        fastForward = !fastForward;
                    }
                    keyHandler.setClickPoint();

                }
            }
        }
    }

    private void spawnEnemy(Enemy enemy) {
        enemies.add(enemy);
        for (int y = 0; y < grid.getSquares().length; y++) {
            for (int x = 0; x < grid.getSquares()[y].length; x++) {
                if (grid.getSquares()[y][x] == GRID.START) {
                    Start start = new Start(x, y);
                    starts.add(start);
                }
            }
        }
        Random random = new Random();
        int n = starts.size();
        int rnd = random.nextInt(n);
        enemy.setyC(starts.get(rnd).getY());
        enemy.setxC(starts.get(rnd).getX());
        enemy.setY(starts.get(rnd).getY() * GameComponent.TILE_SIZE);
        enemy.setX(starts.get(rnd).getX() * GameComponent.TILE_SIZE);


        defineHasWalked(enemy);
        decideDirection(enemy);
        enemy.setEnemyEllipse();
    }

    /*private void spawnArmoredEnemy() {
        Enemy armored = new ArmoredEnemy();
        enemies.add(armored);
        for (int y = 0; y < grid.getSquares().length; y++) {
            for (int x = 0; x < grid.getSquares()[y].length; x++) {
                if (grid.getSquares()[y][x] == GRID.START) {
                    Start start = new Start(x, y);
                    starts.add(start);
                }
            }
        }
        Random random = new Random();
        int n = starts.size();
        int rnd = random.nextInt(n);
        armored.setyC(starts.get(rnd).getY());
        armored.setxC(starts.get(rnd).getX());
        armored.setY(starts.get(rnd).getY() * GameComponent.TILE_SIZE);
        armored.setX(starts.get(rnd).getX() * GameComponent.TILE_SIZE);
        defineHasWalked(armored);
        decideDirection(armored);
        armored.setEnemyEllipse();
    }*/


    private void defineHasWalked(Enemy enemy) {
        int height = grid.getSquares().length;
        int width = GRID.checkLargestRow(grid);
        enemy.setHasWalked(new int[height][width]);
    }

    private void decideDirection(Enemy enemy) {
        if (!collision(enemy, enemy.getyC(), enemy.getxC() + 1)) {
            enemy.setDirection(Direction.RIGHT);
        } else if (!collision(enemy, enemy.getyC(), enemy.getxC() - 1)) {
            enemy.setDirection(Direction.LEFT);
        }
        if (!collision(enemy, enemy.getyC() + 1, enemy.getxC())) {
            enemy.setDirection(Direction.DOWN);
        }
        if (!collision(enemy, enemy.getyC() - 1, enemy.getxC())) {
            enemy.setDirection(Direction.UPWARD
            );
        }
    }

    public void moveEnemy() {
        for (Enemy enemy : enemies) {
            if (enemy.getDirection() == Direction.RIGHT) {
                enemy.setAngle(Math.PI / 2);
                enemy.setX(enemy.getX() + enemy.getSpeed());
            } else if (enemy.getDirection() == Direction.LEFT) {
                enemy.setAngle(3 * Math.PI / 2);
                enemy.setX(enemy.getX() - enemy.getSpeed());
            } else if (enemy.getDirection() == Direction.DOWN) {
                enemy.setAngle(Math.PI);
                enemy.setY(enemy.getY() + enemy.getSpeed());
            } else if (enemy.getDirection() == Direction.UPWARD) {
                enemy.setAngle(0);
                enemy.setY(enemy.getY() - enemy.getSpeed());
            }
            enemy.setEnemyEllipse();
            enemy.setEnemyWalk(enemy.getEnemyWalk() + enemy.getSpeed());
            enemy.setPixelsWalked(enemy.getPixelsWalked() + enemy.getSpeed());

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
                } else if (enemy.getDirection() == Direction.UPWARD) {
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

    private boolean collision(Enemy enemy, int y, int x) {
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


    private void changeDirection(Enemy enemy) {
        if (enemy.isHasRight() || enemy.isHasLeft()) {
            if (!collision(enemy, enemy.getyC() + 1, enemy.getxC())) {
                enemy.setDirection(Direction.DOWN);
            } else if (!collision(enemy, enemy.getyC() - 1, enemy.getxC())) {
                enemy.setDirection(Direction.UPWARD);
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
                shop.setGold(shop.getGold() + enemies.get(i).getGoldgain());
                enemies.remove(enemies.get(i));
		if(!Sound.getNoGameAudio()){
		sfx.get("dies").play();}
            } else if (grid.getSquares()[y][x] == GRID.FINISH) {
                shop.setHealth(shop.getHealth() - enemies.get(i).getDamage());
                enemies.remove(enemies.get(i));
            }
        }


    }

    public Enemy checkEnemyWalked(Iterable<Enemy> list) {
        int walked = 0;
        Enemy enemy = null;
        for (Enemy aList : list) {
            if (aList.getPixelsWalked() > walked) {
                walked = aList.getPixelsWalked();
                enemy = aList;
            }
        }
        return enemy;
    }

    public void draw(Graphics2D g) {
        for (int i = enemies.size() - 1; i >= 0; i--) {
            //g.drawOval((int) enemies.get(i).getEnemyEllipse().getX(), (int) enemies.get(i).getEnemyEllipse().getY(), (int) enemies.get(i).getEnemyEllipse().getWidth(), (int) enemies.get(i).getEnemyEllipse().getHeight());
            AffineTransform at = new AffineTransform();
            AffineTransform old = g.getTransform();
            at.rotate(enemies.get(i).getAngle(), enemies.get(i).getX() + (float)enemies.get(i).getImage().getWidth(null) / 2,
                    enemies.get(i).getY() + (float)enemies.get(i).getImage().getHeight(null) / 2);
            g.transform(at);
            g.drawImage(enemies.get(i).getImage(), enemies.get(i).getX(), enemies.get(i).getY(), null);
            g.setTransform(old);

            g.setColor(Color.red);
            int healthSpace = 3;
            int healthHeight = 6;
            g.fillRect(enemies.get(i).getX(), enemies.get(i).getY() - (healthSpace + healthHeight), Enemy.HP_BAR_LENGTH, healthHeight);

            g.setColor(Color.green);
            g.fillRect(enemies.get(i).getX(), enemies.get(i).getY() - (healthSpace + healthHeight), (int) enemies.get(i).getHealthBarHp(), healthHeight);

            g.setColor(Color.black);
            g.drawRect(enemies.get(i).getX(), enemies.get(i).getY() - (healthSpace + healthHeight), Enemy.HP_BAR_LENGTH - 1, healthHeight - 1);
        }
        g.setColor(Color.red);
        g.setFont(new Font("courier new", Font.BOLD, 32));
        if (level >= 1) {
            g.drawString("ROUND " + level, grid.getWidth() + 20, grid.getHeight()+30);
        }

        //Draw next round button
        if (betweenRounds) {
            g.drawImage(nextWave, grid.getWidth()+20, grid.getHeight() - WAVE_BUTTON_SIZE, null);
        } else if (fastForward) {
            g.drawImage(fastMode, grid.getWidth()+20, grid.getHeight() - WAVE_BUTTON_SIZE, null);
        } else {
            g.drawImage(slowMode, grid.getWidth()+20, grid.getHeight() - WAVE_BUTTON_SIZE, null);
        }
    }

    public boolean isBetweenRounds() {
        return betweenRounds;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public void setLevel() {
        this.level = 0;
    }

    public void setArmoredEnemyCount(int armoredEnemyCount) {
        this.armoredEnemyCount = armoredEnemyCount;
    }

    public void setEnemies(List<Enemy> enemies) {
        this.enemies = enemies;
    }

    public void setEnemyCount(int enemyCount) {
        this.enemyCount = enemyCount;
    }

    public boolean isFastForward() {
        return fastForward;
    }
}
