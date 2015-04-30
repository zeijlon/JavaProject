package se.liu.ida.andze132.tddd78.javaproject;

import java.awt.*;

/**
 * Created by Andreas Zeijlon on 2015-04-22.
 */
public class TowerProperties {

    private static final Image BASIC_TOWER_IMAGE = Toolkit.getDefaultToolkit().getImage("images/basicTower60.png");
    private static final int BASIC_TOWER_DAMAGE = 10;
    private static final int BASIC_TOWER_BULLETSPEED = 6;
    private static final int BASIC_TOWER_COST = 10;
    private static final int BASIC_TOWER_SELL = 5;
    private static final int BASIC_TOWER_RADIUS = 200;
    private static final int BASIC_TOWER_RELOADTIME = 50;

    private static final Image AP_TOWER_IMAGE = Toolkit.getDefaultToolkit().getImage("images/armorPiercingTower60.png");
    private static final int AP_TOWER_DAMAGE = 10;
    private static final int AP_TOWER_BULLETSPEED = 10;
    private static final int AP_TOWER_COST = 20;
    private static final int AP_TOWER_SELL = 10;
    private static final int AP_TOWER_RADIUS = 300;
    private static final int AP_TOWER_RELOADTIME = 60;

    private static final Image SCOUT_TOWER_IMAGE = Toolkit.getDefaultToolkit().getImage("images/armorPiercingTower60.png");
    private static final int SCOUT_TOWER_DAMAGE = 10;
    private static final int SCOUT_TOWER_BULLETSPEED = 10;
    private static final int SCOUT_TOWER_COST = 40;
    private static final int SCOUT_TOWER_SELL = 20;
    private static final int SCOUT_TOWER_RADIUS = 500;
    private static final int SCOUT_TOWER_RELOADTIME = 40;

    public void decideEnemy(Towers tower) {
        switch (tower.getType()) {
            case BASICTOWER:
                tower.setImage(BASIC_TOWER_IMAGE);
                tower.setDamage(BASIC_TOWER_DAMAGE);
                tower.setBulletSpeed(BASIC_TOWER_BULLETSPEED);
                tower.setCost(BASIC_TOWER_COST);
                tower.setSell(BASIC_TOWER_SELL);
                tower.setRadius(BASIC_TOWER_RADIUS);
                tower.setReloadTime(BASIC_TOWER_RELOADTIME); //less is bettter
                tower.setReloadTick(tower.getReloadTime());
                break;
            case ARMORPIERCINGTOWER:
                tower.setImage(AP_TOWER_IMAGE);
                tower.setDamage(AP_TOWER_DAMAGE);
                tower.setBulletSpeed(AP_TOWER_BULLETSPEED);
                tower.setCost(AP_TOWER_COST);
                tower.setSell(AP_TOWER_SELL);
                tower.setRadius(AP_TOWER_RADIUS);
                tower.setReloadTime(AP_TOWER_RELOADTIME); //less is bettter
                tower.setReloadTick(tower.getReloadTime());
                break;
            case SCOUTTOWER:
                tower.setImage(SCOUT_TOWER_IMAGE);
                tower.setDamage(SCOUT_TOWER_DAMAGE);
                tower.setBulletSpeed(SCOUT_TOWER_BULLETSPEED);
                tower.setCost(SCOUT_TOWER_COST);
                tower.setSell(SCOUT_TOWER_SELL);
                tower.setRadius(SCOUT_TOWER_RADIUS);
                tower.setReloadTime(SCOUT_TOWER_RELOADTIME); //less is bettter
                tower.setReloadTick(tower.getReloadTime());
                break;
        }
    }
}
