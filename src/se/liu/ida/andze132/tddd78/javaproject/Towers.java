package se.liu.ida.andze132.tddd78.javaproject;

/**
 * Created by Andreas Zeijlon on 2015-03-20.
 */

import javafx.scene.shape.Circle;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Towers {

    private int cost;
    private int sell;
    private int radius;
    private int reloadTime;
    private int reloadTick;
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

    public Towers(TowerType type) {
        this.type = type;
        this.targeted = true;
        decideEnemy(type);

    }

    public void decideEnemy(TowerType tower) {
        switch (tower) {
            case BASICTOWER:
                image = (Toolkit.getDefaultToolkit().getImage("images/basicTower60.png"));
                cost = 5;
                sell = 5;
                radius = 400;
                reloadTime = 20; //less is bettter
                reloadTick = reloadTime;
                break;
            case ARMORPIERCINGTOWER:
                image = (Toolkit.getDefaultToolkit().getImage("images/armorPiercingTower60.png"));
                cost = 10;
                sell = 5;
                radius = 400;
                reloadTime = 100; //less is bettter
                reloadTick = reloadTime;
                break;
        }
    }

    public void setRange() {
        this.range = new Ellipse2D.Float(x - (this.getRadius() / 2) + (GameComponent.TILE_SIZE / 2), y - (this.getRadius() / 2) + (GameComponent.TILE_SIZE / 2), this.getRadius(), this.getRadius());

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

    public Ellipse2D getRange() {
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
}

