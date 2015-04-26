package se.liu.ida.andze132.tddd78.javaproject;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.util.*;
import java.util.List;


/**
 * Created by Administratör on 2015-03-24.
 */
public class TowerHandler {

    private List<Towers> towers = new ArrayList<>();
    public final static int UPGRADEWIDTHCOORD = 200;
    public final static int UPGRADEHEIGHTCORRECTIONCOORD = 30;
    private final static int maxUpgrades = 15;

    private GRID grid;
    private Shop shop;
    private EnemySpawner spawner;
    private BulletHandler bulletHandler;
    private KeyHandler keyHandler;

    private Towers buildTower = null;
    private Rectangle upgradeTowerRange = null;
    private Rectangle upgradeTowerDamage = null;
    private Rectangle upgradeTowerAS = null;


    private Rectangle sellTower = null;

    private Collection<Enemy> enemiesWithinRange = new ArrayList<>();

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
                Towers tower = new ArmorPiercingTower();
                if (shop.getGold() >= tower.getCost()) {
                    shop.setHoldsItem(tower);
                    buildTower = tower;
                } else {
                    shop.setHoldsItem(null);
                }
            } else if (shop.getShopButtons()[1][0].contains(keyHandler.getClickPoint())) {
                Towers tower = new ScoutTower();
                if (shop.getGold() >= tower.getCost()) {
                    shop.setHoldsItem(tower);
                    buildTower = tower;
                } else {
                    shop.setHoldsItem(null);
                }
            } else if (shop.getShopButtons()[1][1].contains(keyHandler.getClickPoint())) {
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
                    try {
                        if (grid.getRectangles()[i][j].contains(keyHandler.getClickPoint()))
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
                            }
                    } catch (RuntimeException ignored) {

                    }
                }
            }

        }
    }

    public void checkTowerTargeted() {
        for (int i = 0; i < towers.size(); i++) {
            if (keyHandler.getClickPoint() != null) {
                if (towers.get(i).getRectangle().contains(keyHandler.getClickPoint())) {
                    towers.get(i).setTargeted(true);
                    upgradeTowerAS = new Rectangle(0, grid.getHeight() + UPGRADEHEIGHTCORRECTIONCOORD, 100, 100);
                    upgradeTowerRange = new Rectangle(UPGRADEWIDTHCOORD, grid.getHeight() + UPGRADEHEIGHTCORRECTIONCOORD, 100, 100);
                    upgradeTowerDamage = new Rectangle(UPGRADEWIDTHCOORD * 2, grid.getHeight() + UPGRADEHEIGHTCORRECTIONCOORD, 100, 100);
                    sellTower = new Rectangle(UPGRADEWIDTHCOORD * 3, grid.getHeight() + UPGRADEHEIGHTCORRECTIONCOORD, 50, 50);
                } else if (towers.get(i).isTargeted()) {
                    if (upgradeTowerAS.contains(keyHandler.getClickPoint())) {
                        if (keyHandler.getMouseReleased()) {
                            upgradeAS(towers.get(i));
                        }
                    } else if (upgradeTowerRange.contains(keyHandler.getClickPoint())) {
                        if (keyHandler.getMouseReleased()) {
                            upgradeRange(towers.get(i));
                        }
                    } else if (upgradeTowerDamage.contains(keyHandler.getClickPoint())) {
                        if (keyHandler.getMouseReleased()) {
                            upgradeDamage(towers.get(i));
                        }

                    } else if (sellTower.contains(keyHandler.getClickPoint())) {
                        if (keyHandler.getMouseReleased()) {
                            sellTower(towers.get(i));
                        }

                    } else {
                        towers.get(i).setTargeted(false);
                    }
                }
            }
        }
    }


    public void towerPhysic() {
        for (Towers tower : towers) {
            if(spawner.isBetweenRounds()){
                tower.setReloadTick(tower.getReloadTime());
            }
            checkEnemyWithinReach(tower);
            if (!enemiesWithinRange.isEmpty()) {
                tower.setShooting(true);
                tower.setTargetEnemy(spawner.checkEnemyWalked(enemiesWithinRange));
                enemiesWithinRange = new ArrayList<>();
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
        for (int i = 0; i < spawner.getEnemies().size(); i++) {
            if (testIntersection(tower.getRange(), spawner.getEnemies().get(i).getEnemyEllipse())) {
                if (spawner.getEnemies().get(i).getType() == EnemyType.SPYENEMY) {
                    if (tower.getType() == TowerType.SCOUTTOWER) {
                        enemiesWithinRange.add(spawner.getEnemies().get(i));
                    }
                } else {
                    enemiesWithinRange.add(spawner.getEnemies().get(i));
                }
            }

        }
    }

    public boolean testIntersection(Shape shapeA, Shape shapeB) {
        Area areaA = new Area(shapeA);
        areaA.intersect(new Area(shapeB));
        return !areaA.isEmpty();
    }

    public void upgradeDamage(Towers tower) {
        if (shop.getGold() >= tower.getUpgradeDamageCost()) {
            if(tower.getUpgrades()<maxUpgrades){
            shop.setGold(shop.getGold() - tower.getUpgradeDamageCost());
            tower.setUpgradeDamageCost(tower.getUpgradeDamageCost() * 2);
            tower.setDamage(tower.getDamage() + 5);
            tower.setUpgrades(tower.getUpgrades()+1);
            }}
        keyHandler.setMouseReleased();
    }

    public void upgradeAS(Towers tower) {
        if (shop.getGold() >= tower.getUpgradeASCost()) {
            if(tower.getUpgrades()<maxUpgrades){
            shop.setGold(shop.getGold() - tower.getUpgradeASCost());
            tower.setUpgradeASCost(tower.getUpgradeASCost() * 2);
            tower.setReloadTime(tower.getReloadTime() - 5);
            tower.setBulletSpeed(tower.getBulletSpeed() + 1);
            tower.setUpgrades(tower.getUpgrades() + 1);

        }}
        keyHandler.setMouseReleased();
    }

    public void upgradeRange(Towers tower) {
        if (shop.getGold() >= tower.getUpgradeRangeCost()) {
            if(tower.getUpgrades()<maxUpgrades){
            shop.setGold(shop.getGold() - tower.getUpgradeRangeCost());
            tower.setUpgradeRangeCost(tower.getUpgradeRangeCost() * 2);
            tower.setRadius((int) (tower.getRadius() * 1.2));
            tower.setRange();
            tower.setUpgrades(tower.getUpgrades()+1);
        }}
        keyHandler.setMouseReleased();
    }

    public void sellTower(Towers tower) {
        shop.setGold(shop.getGold() + tower.getSell());
        towers.remove(tower);
        grid.getSquares()[tower.getY() / GameComponent.TILE_SIZE][tower.getX() / GameComponent.TILE_SIZE] = GRID.GRASS;
    }

    public void draw(Graphics2D g) {
        g.setColor(Color.black);
        for (Towers tower : towers) {
            AffineTransform at = new AffineTransform();
            AffineTransform old = g.getTransform();
            at.rotate(tower.getAngle(), tower.getX() + (float) tower.getImage().getWidth(null) / 2,
                    tower.getY() + (float) tower.getImage().getHeight(null) / 2);
            g.transform(at);
            g.drawImage(tower.getImage(), tower.getX(), tower.getY(), null);
            g.setTransform(old);
            if (tower.isTargeted()) {
                g.setColor(Color.black);
                g.setFont(new Font("courier new", Font.BOLD, 18));
                g.fillRect(0, grid.getHeight() + UPGRADEHEIGHTCORRECTIONCOORD, 100, 100);
                g.fillRect(UPGRADEWIDTHCOORD, grid.getHeight() + UPGRADEHEIGHTCORRECTIONCOORD, 100, 100);
                g.fillRect(UPGRADEWIDTHCOORD * 2, grid.getHeight() + UPGRADEHEIGHTCORRECTIONCOORD, 100, 100);
                g.fillRect(UPGRADEWIDTHCOORD * 3, grid.getHeight() + UPGRADEHEIGHTCORRECTIONCOORD, 50, 50);
                g.drawOval(tower.getX() - (tower.getRadius() / 2) + (GameComponent.TILE_SIZE / 2), tower.getY() - (tower.getRadius() / 2) + (GameComponent.TILE_SIZE / 2), tower.getRadius(), tower.getRadius());
                g.drawString("Attack Speed", 0, grid.getHeight() + UPGRADEHEIGHTCORRECTIONCOORD - 10);
                g.drawString("Range", UPGRADEWIDTHCOORD, grid.getHeight() + UPGRADEHEIGHTCORRECTIONCOORD - 10);
                g.drawString("Damage", UPGRADEWIDTHCOORD * 2, grid.getHeight() + UPGRADEHEIGHTCORRECTIONCOORD - 10);
                g.drawString("Sell", UPGRADEWIDTHCOORD * 3, grid.getHeight() + UPGRADEHEIGHTCORRECTIONCOORD - 10);
                g.drawString("Upgrades: "+tower.getUpgrades()+"/"+maxUpgrades, UPGRADEWIDTHCOORD * 3 + 100, grid.getHeight() + UPGRADEHEIGHTCORRECTIONCOORD + 20);

                g.setFont(new Font("courier new", Font.BOLD, 16));
                g.setColor(Color.yellow);

                g.drawString("$" + tower.getUpgradeASCost(), 0, grid.getHeight() + UPGRADEHEIGHTCORRECTIONCOORD + 10);

                g.drawString("$" + tower.getUpgradeRangeCost(), UPGRADEWIDTHCOORD, grid.getHeight() + UPGRADEHEIGHTCORRECTIONCOORD + 10);

                g.drawString("$" + tower.getUpgradeDamageCost(), UPGRADEWIDTHCOORD * 2, grid.getHeight() + UPGRADEHEIGHTCORRECTIONCOORD + 10);

                g.drawString("$" + tower.getSell(), UPGRADEWIDTHCOORD * 3, grid.getHeight() + UPGRADEHEIGHTCORRECTIONCOORD + 10);


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

    public void setTowers(List<Towers> towers) {
        this.towers = towers;
    }
}

