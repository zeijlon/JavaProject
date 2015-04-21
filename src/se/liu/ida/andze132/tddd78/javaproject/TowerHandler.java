package se.liu.ida.andze132.tddd78.javaproject;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
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
    private KeyHandler keyHandler;

    private Towers buildTower = null;
    private Rectangle upgradeTower = null;

    public TowerHandler(GRID grid, Shop shop, EnemySpawner spawner, BulletHandler bulletHandler, KeyHandler keyHandler) {
        this.grid = grid;
        this.shop = shop;
        this.spawner = spawner;
        this.bulletHandler = bulletHandler;
        this.keyHandler = keyHandler;
    }

    public void checkButtonClick() {
        if (keyHandler.getClickPoint() != null) {
            if (shop.getShopButtons()[0][0].contains(keyHandler.getClickPoint())) {
                Towers tower = new BasicTower();
                if (shop.getGold() >= tower.getCost()) {
                    shop.setHoldsItem(tower);
                    buildTower = tower;
                } else {
                    shop.setHoldsItem(null);
                }
            } else if (shop.getShopButtons()[0][1].contains(keyHandler.getClickPoint())) {
                Towers tower = new ArmorpiercingTower();
                if (shop.getGold() >= tower.getCost()) {
                    shop.setHoldsItem(tower);
                    buildTower = tower;
                } else {
                    shop.setHoldsItem(null);
                }
            } else if (shop.getShopButtons()[3][1].contains(keyHandler.getClickPoint())) {
                shop.setHoldsItem(null);
            }

            if (buildTower != null) {
                buildTower(buildTower);
            }
        }
    }


    public void buildTower(Towers tower) {
        if (keyHandler.getClickPoint() != null) {
            for (int i = 0; i < grid.getRectangles().length; i++) {
                for (int j = 0; j < grid.getRectangles()[i].length; j++) {
                    if(keyHandler.getClickPoint() != null){
                    if (grid.getRectangles()[i][j].contains(keyHandler.getClickPoint())) {
                        if (grid.getSquares()[i][j] == GRID.GRASS) {
                            towers.add(tower);
                            grid.getSquares()[i][j] = GRID.TOWER;
                            tower.setX(j * GameComponent.TILE_SIZE);
                            tower.setY(i * GameComponent.TILE_SIZE);
                            tower.setRange();
                            tower.setRectangle(new Rectangle(tower.getX(), tower.getY(), tower.getImage().getWidth(null), tower.getImage().getHeight(null)));
                            shop.setGold(shop.getGold() - tower.getCost());
                            shop.setHoldsItem(null);
                            buildTower = null;
                        }}
                    }
                }
            }

        }
    }

    public void checkTowerTargeted() {
        for (Towers tower : towers) {
            if (keyHandler.getClickPoint() != null) {
                if (tower.getRectangle().contains(keyHandler.getClickPoint())) {
                    tower.setTargeted(true);
                    upgradeTower = new Rectangle(200, grid.getHeight() + 50, 100, 100);
                } else if (upgradeTower.contains(keyHandler.getClickPoint())) {
                    if (tower.isTargeted()) {
                        if (keyHandler.getMouseReleased()) {
                            upgradeTower(tower);
                        }
                    }
                } else {
                    tower.setTargeted(false);
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
            if (testIntersection(tower.getRange(), spawner.getEnemies().get(i).getEnemyEllipse())) {
                tower.setShooting(true);
                tower.setTargetEnemy(spawner.getEnemies().get(i));
            }
        }
    }

    public boolean testIntersection(Shape shapeA, Shape shapeB) {
        Area areaA = new Area(shapeA);
        areaA.intersect(new Area(shapeB));
        return !areaA.isEmpty();
    }

    public void upgradeTower(Towers tower) {
        if (shop.getGold() >= 5) {
            tower.setRadius(tower.getRadius() + 5);
            tower.setRange();
            shop.setGold(shop.getGold() - 5);
        }
        keyHandler.setMouseReleased(false);
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
            g.setTransform(old);
            if (tower.isTargeted()) {
                g.drawOval(tower.getX() - (tower.getRadius() / 2) + (GameComponent.TILE_SIZE / 2), tower.getY() - (tower.getRadius() / 2) + (GameComponent.TILE_SIZE / 2), tower.getRadius(), tower.getRadius());
                g.fillRect(200, grid.getHeight() + 50, 100, 100);
            }
        }
        Double dX = keyHandler.getMotionPoint().getX();
        Double dY = keyHandler.getMotionPoint().getY();
        int x = dX.intValue();
        int y = dY.intValue();
        if (shop.getHoldsItem() != null) {
            g.drawImage(shop.getHoldsItem().getImage(), x - GameComponent.TILE_SIZE / 2, y - GameComponent.TILE_SIZE / 2, null);
            g.drawOval(x - (shop.getHoldsItem().getRadius() / 2), y - (shop.getHoldsItem().getRadius() / 2),
                    shop.getHoldsItem().getRadius(), shop.getHoldsItem().getRadius());
        }

    }

    public void setTowers(Collection<Towers> towers) {
        this.towers = towers;
    }
}

