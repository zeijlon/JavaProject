package se.liu.ida.andze132.tddd78.javaproject;

import java.awt.*;

/**
 * Created by Administrat�r on 2015-03-30.
 */
public class Bullet {
    private double angle;
    private double x;
    private double y;
    private double bulletSpeed;
    private int damage;

    private Image image;

    private Rectangle bulletRect = null;

    public Bullet(int x, int y, Image image, int bulletSpeed, int damage) {
        this.x = x;
        this.y = y;
        this.image = image;
        this.bulletSpeed = bulletSpeed;
        this.damage = damage;
    }

    public double getAngle() {
        return angle;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }


    public void setAngle(double angle) {
        this.angle = angle;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getBulletSpeed() {
        return bulletSpeed;
    }

    public Rectangle getBulletRect() {
        return bulletRect;
    }

    public void setBulletRect(Rectangle bulletRect) {
        this.bulletRect = bulletRect;
    }

    public Image getImage() {
        return image;
    }

    public int getDamage() {
        return damage;
    }
}
