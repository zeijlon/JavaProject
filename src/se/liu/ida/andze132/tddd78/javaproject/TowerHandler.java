package se.liu.ida.andze132.tddd78.javaproject;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

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
    private BulletHandler bulletHandler;

    private int bulletTravel;

    public TowerHandler(GRID grid, Shop shop, EnemySpawner spawner, BulletHandler bulletHandler) {
        this.grid = grid;
        this.shop = shop;
        this.spawner = spawner;
        this.bulletHandler = bulletHandler;
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

    public void towerPhysic() {
        for (Towers tower : towers) {
            if (!tower.isShooting()) {
                checkEnemyWithinReach(tower);
            } else {
                    double x = (double) tower.getTargetEnemy().getX();
                    double y = (double) tower.getTargetEnemy().getY();
                    double w = (double) GameComponent.TILE_SIZE;
                    double h = (double) GameComponent.TILE_SIZE;
                    if (spawner.isBetweenRounds()) {
                        tower.setShooting(false);
                    } else if(tower.getTargetEnemy().getHp() <= 0){
                        tower.setShooting(false);
                    }
                    else if (!tower.getRange().intersects(x, y, w, h)) {
                        System.out.println("outta range");
                        tower.setShooting(false);
                    } else if (tower.getReloadTick() >= tower.getReloadTime()) {
                        System.out.println("fuvk");
                        bulletHandler.shootEnemy(tower.getTargetEnemy(), tower);
                        tower.setReloadTick(0);
                    } else {
                        tower.setReloadTick(tower.getReloadTick() + 1);
                    }
                }

            }

        }

    public void checkEnemyWithinReach(Towers tower) {
        for (int i = spawner.getEnemies().size()-1; i >= 0; i--) {
            double x = (double) spawner.getEnemies().get(i).getX();
            double y = (double) spawner.getEnemies().get(i).getY();
            double w = (double) GameComponent.TILE_SIZE;
            double h = (double) GameComponent.TILE_SIZE;
            if (tower.getRange().intersects(x, y, w, h)) {
                tower.setShooting(true);
                tower.setTargetEnemy(spawner.getEnemies().get(i));
            }
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.black);
        for (Towers tower : towers) {
            g.drawImage(tower.getImage(), tower.getX(), tower.getY(), null);
            g.drawOval(tower.getX() - (tower.getRadius() / 2) + (GameComponent.TILE_SIZE / 2), tower.getY() - (tower.getRadius() / 2) + (GameComponent.TILE_SIZE / 2), tower.getRadius(), tower.getRadius());
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

