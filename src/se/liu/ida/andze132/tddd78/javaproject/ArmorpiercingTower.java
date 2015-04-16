package se.liu.ida.andze132.tddd78.javaproject;

import java.awt.*;

/**
 * Created by Administratör on 2015-04-12.
 */
public class ArmorpiercingTower extends Towers {
    public static Image image = (Toolkit.getDefaultToolkit().getImage("images/basicTower.png"));


    public static int cost = 10;
    private static int sell = 100;
    public static int radius = 100;
    public static int reloadTime = 50; //less is bettter


    public ArmorpiercingTower() {
        super(TowerType.ARMORPIERCINGTOWER);
    }

}

