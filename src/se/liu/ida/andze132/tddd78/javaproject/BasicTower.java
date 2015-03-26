package se.liu.ida.andze132.tddd78.javaproject;

import java.awt.*;

/**
 * Created by Andreas Zeijlon on 2015-03-20.
 */


public class BasicTower extends Towers {
    public static Image image = (Toolkit.getDefaultToolkit().getImage("images/basicTower.png"));


    private static int damage = 10;
    private static int bulletspeed = 20;
    public static int cost = 5;
    private static int sell = 50;
    public static int radius = 150;


    public BasicTower() {
        super(damage, bulletspeed, cost, sell, image, radius);
    }

}
