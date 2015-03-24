package se.liu.ida.andze132.tddd78.javaproject;

/**
 * Created by Andreas Zeijlon on 2015-03-20.
 */

public class BasicTower extends Towers
{
    private static int damage = 50;
    private static int bulletspeed = 50;
    private static int cost = 2;
    private static int sell = 50;


    public BasicTower() {
	super(damage, bulletspeed, cost, sell);
    }

}
