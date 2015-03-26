package se.liu.ida.andze132.tddd78.javaproject;

import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.List;
import java.awt.*;

/**
 * Created by Administratör on 2015-03-24.
 */
public class TowerHandler {

    private int basicTower = 0, trashCan = 1, not = 2;
    private int buttonClicked;

    private List<Towers> towers = new ArrayList<>();

    private GRID grid;
    private Shop shop;
    private EnemySpawner spawner;

    private int bulletTravel;

    public TowerHandler(GRID grid, Shop shop, EnemySpawner spawner) {
        this.grid = grid;
        this.shop = shop;
        this.spawner = spawner;
        this.buttonClicked = not;
        this.bulletTravel = 0;
    }

    public void checkButtonClick() {
        if (shop.getShopButtons()[0][0].contains(GameFrame.clickPoint)) {
            buttonClicked = basicTower;
        } else if (shop.getShopButtons()[3][1].contains(GameFrame.clickPoint)) {
            buttonClicked = trashCan;
        }
        createTower();
    }

    public void createTower() {
        if (buttonClicked == basicTower) {
            shop.setHoldsItem(shop.getBasicTower());
        } else if (buttonClicked == trashCan) {
            shop.setHoldsItem(shop.getNothing());
        }
        buildTower();
    }

    public void buildTower() {
        if (shop.getHoldsItem() == shop.getBasicTower()) {
            for (int i = 0; i < grid.getRectangles().length; i++) {
                for (int j = 0; j < grid.getRectangles()[i].length; j++) {
                    if (grid.getRectangles()[i][j].contains(GameFrame.clickPoint)) {
                        if (grid.getSquares()[i][j] == GRID.GRASS) {
                            if (shop.getGold() >= BasicTower.cost) {
                                Towers tower = new BasicTower();
                                towers.add(tower);
                                grid.getSquares()[i][j] = GRID.TOWER;
                                tower.setX(j * GRID.getSquareWidth());
                                tower.setY(i * GRID.getSquareHeight());
                                tower.setRange(j * GRID.getSquareWidth(), i * GRID.getSquareHeight());
                                shop.setGold(tower.getCost());
                            }
                        }
                    }
                }

            }
        }
    }

    public void checkEnemyWithinReach() {
        for (Towers tower : towers) {
            for (Enemy enemy : spawner.getEnemies()) {
                if(tower.getRange().contains(enemy.getX()+GameComponent.TILE_SIZE/2, enemy.getY()+GameComponent.TILE_SIZE/2)){
                    System.out.println("Enemy within range");
                    shootEnemy(enemy, tower);
                }
            }
        }
    }

    public void shootEnemy(Enemy enemy, Towers tower){
        if(tower.getBulletspeed() <= bulletTravel){
        enemy.setHp(tower.getDamage());
        bulletTravel = 0;}
        else{
            bulletTravel ++;
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.black);
        for (Towers tower : towers) {
            g.drawImage(tower.getImage(), tower.getX(), tower.getY(), null);
           // if (tower.isTargeted()) {
                g.drawOval(tower.getX() - (tower.getRadius() / 2) + (GameComponent.TILE_SIZE / 2), tower.getY() - (tower.getRadius() / 2) + (GameComponent.TILE_SIZE / 2), tower.getRadius(), tower.getRadius());
           // }
        }
        Double dX = GameFrame.motionPoint.getX();
        Double dY = GameFrame.motionPoint.getY();
        int x = dX.intValue();
        int y = dY.intValue();
        if (shop.getHoldsItem() == shop.getBasicTower()) {
            g.drawImage(BasicTower.image, x - GameComponent.TILE_SIZE / 2, y - GameComponent.TILE_SIZE / 2, null);
            g.drawOval(x - (BasicTower.radius / 2), y - (BasicTower.radius / 2), BasicTower.radius, BasicTower.radius);

        }
    }
}

