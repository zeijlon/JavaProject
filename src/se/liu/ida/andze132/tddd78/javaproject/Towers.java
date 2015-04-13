package se.liu.ida.andze132.tddd78.javaproject;

/**
 * Created by Andreas Zeijlon on 2015-03-20.
 */

import javafx.scene.shape.Circle;

import java.awt.*;

public class Towers {

    private int cost;
    private int sell;
    private int radius;
    private int reloadTime;
    private int reloadTick;
    private boolean firstShot;
    private boolean shooting;


    private Enemy targetEnemy;

    private Image image;

    private Circle range;


    private int x;
    private int y;

    private boolean targeted;

    public Towers(final int cost, final int sell, final Image image, final int radius, final int reloadTime) {
        this.cost = cost;
        this.sell = sell;
        this.image = image;
        this.radius = radius;
        this.reloadTime = reloadTime;
        this.targeted = false;
        this.firstShot = true;
        this.shooting = false;
        this.reloadTick = reloadTime;
    }

    public void setRange(int x, int y) {
        this.range = new Circle(x + GameComponent.TILE_SIZE / 2, y + GameComponent.TILE_SIZE / 2, radius / 2);
    }

    public Image getImage() {
        return image;
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

    public int getReloadTime() {
        return reloadTime;
    }

    public void setReloadTime(int reloadTime) {
        this.reloadTime = reloadTime;
    }

    public Circle getRange() {
        return range;
    }

    public boolean isFirstShot() {
        return firstShot;
    }

    public void setFirstShot(boolean firstShot) {
        this.firstShot = firstShot;
    }

    public boolean isShooting() {
        return shooting;
    }

    public void setShooting(boolean shooting) {
        this.shooting = shooting;
    }

    public Enemy getTargetEnemy() {
        return targetEnemy;
    }

    public void setTargetEnemy(Enemy targetEnemy) {
        this.targetEnemy = targetEnemy;
    }

    public int getReloadTick() {
        return reloadTick;
    }

    public void setReloadTick(int reloadTick) {
        this.reloadTick = reloadTick;
    }
}

