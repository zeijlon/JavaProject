package se.liu.ida.andze132.tddd78.javaproject;

/**
 * Created by Andreas Zeijlon on 2015-03-20.
 */

import java.awt.*;

public class Towers
{

    private int damage;
    private int bulletspeed;
    private int cost;
    private int sell;

    private Image image;

    private int x;
    private int y;

    private boolean targeted = false;

    public Towers(final int damage, final int bulletspeed, final int cost, final int sell, final Image image) {
	this.damage = damage;
	this.bulletspeed = bulletspeed;
	this.cost = cost;
	this.sell = sell;
	this.image = image;
    }

    public Image getImage() {
	return image;
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

    public int getX() {
	return x;
    }

    public int getY() {
	return y;
    }

    public boolean isTargeted() {
	return targeted;
    }

    public void setX(final int x) {
	this.x = x;
    }

    public void setY(final int y) {
	this.y = y;
    }

    public void setTargeted(final boolean targeted) {
	this.targeted = targeted;
    }
}

