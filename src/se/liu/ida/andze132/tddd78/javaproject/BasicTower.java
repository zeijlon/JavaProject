package se.liu.ida.andze132.tddd78.javaproject;

/**
 * Created by Andreas Zeijlon on 2015-03-20.
 */
public class BasicTower extends Towers
{
    private int damage = 50;
    private int speed = 50;
    private int cost = 100;
    private int sell = 50;


    public BasicTower(final int damage, final int speed, final int cost, final int sell) {
	super(damage, speed, cost, sell);
    }
}
