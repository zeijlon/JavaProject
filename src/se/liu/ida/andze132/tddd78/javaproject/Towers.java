package se.liu.ida.andze132.tddd78.javaproject;

/**
 * Created by Andreas Zeijlon on 2015-03-20.
 */

import javafx.scene.shape.Circle;

import java.awt.*;

public class Towers {

    private int damage;
    private int bulletspeed;
    private int cost;
    private int sell;
    private int radius;

    private Image image;

    private Circle range;

    private int x;
    private int y;

    private boolean targeted;

    public Towers(final int damage, final int bulletspeed, final int cost, final int sell, final Image image, final int radius) {
        this.damage = damage;
        this.bulletspeed = bulletspeed;
        this.cost = cost;
        this.sell = sell;
        this.image = image;
        this.radius = radius;
        this.targeted = false;
    }

    public void setRange(int x, int y) {
        this.range = new Circle(x + GameComponent.TILE_SIZE / 2, y + GameComponent.TILE_SIZE / 2, radius / 2);
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

    public int getRadius() {
        return radius;
    }

    public Circle getRange() {
        return range;
    }
}

