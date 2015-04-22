package se.liu.ida.andze132.tddd78.javaproject;

import java.awt.*;

/**
 * Created by Andreas Zeijlon on 2015-04-22.
 */
class TowerProperties {

    private static final int BASIC_TOWER_COST = 5;
    private static final int BASIC_TOWER_SELL = 5;
    private static final int BASIC_TOWER_RADIUS = 400;
    private static final int BASIC_TOWER_RELOADTIME = 20;

    private static final int AP_TOWER_COST = 10;
    private static final int AP_TOWER_SELL = 10;
    private static final int AP_TOWER_RADIUS = 400;
    private static final int AP_TOWER_RELOADTIME = 50;


    public void decideEnemy(Towers tower) {
        switch (tower.getType()) {
            case BASICTOWER:
                tower.setImage(Toolkit.getDefaultToolkit().getImage("images/basicTower60.png"));
                tower.setCost(BASIC_TOWER_COST);
                tower.setSell(BASIC_TOWER_SELL);
                tower.setRadius(BASIC_TOWER_RADIUS);
                tower.setReloadTime(BASIC_TOWER_RELOADTIME); //less is bettter
                tower.setReloadTick(tower.getReloadTime());
                break;
            case ARMORPIERCINGTOWER:
                tower.setImage(Toolkit.getDefaultToolkit().getImage("images/armorPiercingTower60.png"));
                tower.setCost(AP_TOWER_COST);
                tower.setSell(AP_TOWER_SELL);
                tower.setRadius(AP_TOWER_RADIUS);
                tower.setReloadTime(AP_TOWER_RELOADTIME); //less is bettter
                tower.setReloadTick(tower.getReloadTime());
                break;
        }
    }
}
