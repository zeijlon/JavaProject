package se.liu.ida.andze132.tddd78.javaproject;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Collection;


/**
 * Created by Administratör on 2015-03-24.
 */
public class TowerHandler {

    private Collection<Towers> towers = new ArrayList<>();

    private GRID grid;
    private Shop shop;
    private EnemySpawner spawner;
    private BulletHandler bulletHandler;

    private Towers buildTower = null;

    public TowerHandler(GRID grid, Shop shop, EnemySpawner spawner, BulletHandler bulletHandler) {
        this.grid = grid;
        this.shop = shop;
        this.spawner = spawner;
        this.bulletHandler = bulletHandler;
    }

    public void checkButtonClick() {
        if (shop.getShopButtons()[0][0].contains(GameFrame.clickPoint) && shop.getShopButtons()[0][0].contains(GameFrame.motionPoint)) {
            Towers tower = new BasicTower();
            if (shop.getGold() >= tower.getCost()) {
                shop.setHoldsItem(tower);
                buildTower = tower;
            } else {
                shop.setHoldsItem(null);
            }
        } else if (shop.getShopButtons()[3][1].contains(GameFrame.clickPoint) && shop.getShopButtons()[3][1].contains(GameFrame.motionPoint)) {
            shop.setHoldsItem(null);
        }

        if(buildTower!=null){
        buildTower(buildTower);}
    }


    public void buildTower(Towers tower) {
            for (int i = 0; i < grid.getRectangles().length; i++) {
                for (int j = 0; j < grid.getRectangles()[i].length; j++) {
                    if (grid.getRectangles()[i][j].contains(GameFrame.clickPoint)) {
                        if (grid.getSquares()[i][j] == GRID.GRASS) {
                                towers.add(tower);
                                grid.getSquares()[i][j] = GRID.TOWER;
                                tower.setX(j * GameComponent.TILE_SIZE);
                                tower.setY(i * GameComponent.TILE_SIZE);
                                tower.setRange(j * GameComponent.TILE_SIZE, i * GameComponent.TILE_SIZE);
                                shop.setGold(tower.getCost());
                                shop.setHoldsItem(null);
                                buildTower = null;
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
                tower.setAngle(Math.atan2(tower.getTargetEnemy().getY() - tower.getY(),
                                          tower.getTargetEnemy().getX() - tower.getX()));
                double x = tower.getTargetEnemy().getX();
                double y = tower.getTargetEnemy().getY();
                double w = GameComponent.TILE_SIZE;
                double h = GameComponent.TILE_SIZE;
                if (spawner.isBetweenRounds() || tower.getTargetEnemy().getHp() <= 0 ||
                    !tower.getRange().intersects(x, y, w, h)) {
                    tower.setShooting(false);
                } else if (tower.getReloadTick() >= tower.getReloadTime()) {
                    bulletHandler.shootEnemy(tower.getTargetEnemy(), tower);
                    tower.setReloadTick(0);
                } else {
                    tower.setReloadTick(tower.getReloadTick() + 1);
                }
            }

        }

    }

    public void checkEnemyWithinReach(Towers tower) {
        for (int i = spawner.getEnemies().size() - 1; i >= 0; i--) {
            double x = spawner.getEnemies().get(i).getX();
            double y = spawner.getEnemies().get(i).getY();
            double w = GameComponent.TILE_SIZE;
            double h = GameComponent.TILE_SIZE;
            if (tower.getRange().intersects(x, y, w, h)) {
                tower.setShooting(true);
                tower.setTargetEnemy(spawner.getEnemies().get(i));
            }
        }
    }

    public void draw(Graphics2D g) {
        g.setColor(Color.black);
        for (Towers tower : towers) {
            AffineTransform at = new AffineTransform();
            AffineTransform old = g.getTransform();
            at.rotate(tower.getAngle(), tower.getX() + tower.getImage().getWidth(null) / 2,
                      tower.getY() + tower.getImage().getHeight(null) / 2);
            g.transform(at);
            g.drawImage(tower.getImage(), tower.getX(), tower.getY(), null);
            g.drawOval(tower.getX() - (tower.getRadius() / 2) + (GameComponent.TILE_SIZE / 2), tower.getY() - (tower.getRadius() / 2) + (GameComponent.TILE_SIZE / 2), tower.getRadius(), tower.getRadius());
            g.setTransform(old);
        }
        Double dX = GameFrame.motionPoint.getX();
        Double dY = GameFrame.motionPoint.getY();
        int x = dX.intValue();
        int y = dY.intValue();
        if(shop.getHoldsItem() != null) {
            g.drawImage(shop.getHoldsItem().getImage(), x - GameComponent.TILE_SIZE / 2, y - GameComponent.TILE_SIZE / 2, null);
            g.drawOval(x - (shop.getHoldsItem().getRadius() / 2), y - (shop.getHoldsItem().getRadius() / 2),
                       shop.getHoldsItem().getRadius(), shop.getHoldsItem().getRadius());
        }

    }
}

