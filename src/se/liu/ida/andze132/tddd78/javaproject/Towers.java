package se.liu.ida.andze132.tddd78.javaproject;

/**
 * Created by Andreas Zeijlon on 2015-03-20.
 */
public class Towers
{

    private int damage;
    private int bulletspeed;
    private int cost;
    private int sell;


    public int X;
    public int Y;

    public boolean targeted = false;

    public Towers(final int damage, final int bulletspeed, final int cost, final int sell) {
	this.damage = damage;
	this.bulletspeed = bulletspeed;
	this.cost = cost;
	this.sell = sell;
	}


    public int getDamage() {
        return damage;
    }

    public int getBulletspeed() {
        return bulletspeed;
    }

    public int getCost() {
        return cost;
    }

    public int getSell() {
        return sell;
    }
}

