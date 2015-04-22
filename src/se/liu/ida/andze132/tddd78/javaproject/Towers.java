package se.liu.ida.andze132.tddd78.javaproject;

/**
 * Created by Andreas Zeijlon on 2015-03-20.
 */

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Towers extends TowerProperties{

    private int cost;
    private int radius;
    private int reloadTime;
    private int reloadTick;
    private int sell;
    private boolean shooting;
    private TowerType type;

    private Enemy targetEnemy = null;

    private Image image = null;

    private Ellipse2D range = null;

    private double angle;

    private boolean targeted;

    private Rectangle rectangle = null;


    private int x;
    private int y;

    Towers(TowerType type) {
        this.type = type;
        this.targeted = true;
        decideEnemy(this);

    }

    public void setRange() {
        this.range = new Ellipse2D.Float(x - (float)(radius / 2) + ((float)GameComponent.TILE_SIZE / 2), y - (float)(radius / 2) + ((float)GameComponent.TILE_SIZE / 2), radius, radius);

    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void setReloadTime(int reloadTime) {
        this.reloadTime = reloadTime;
    }

    public Image getImage() {
        return image;
    }

    public int getCost() {
        return cost;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(final int x) {
        this.x = x;
    }

    public void setY(final int y) {
        this.y = y;
    }

    public int getRadius() {
        return radius;
    }

    public int getReloadTime() {
        return reloadTime;
    }

    public Shape getRange() {
        return range;
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

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public double getAngle() {
        return angle;
    }

    public TowerType getType() {
        return type;
    }

    public boolean isTargeted() {
        return targeted;
    }

    public void setTargeted(boolean targeted) {
        this.targeted = targeted;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setSell(int sell) {
        this.sell = sell;
    }
}

