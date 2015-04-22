package se.liu.ida.andze132.tddd78.javaproject;

import java.awt.*;

/**
 * Created by Administrat�r on 2015-03-30.
 */
class Bullet extends BulletProperties{
    private double angle;
    private double x;
    private double y;
    private double bulletSpeed;
    private int damage;
    private BulletType type;

    private Image image = null;

    private Rectangle bulletRect = null;

    Bullet(int x, int y, BulletType type) {
        this.x = x;
        this.y = y;
        this.type = type;
        decideBullet(this);
    }

    public BulletType getType() {
        return type;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setBulletSpeed(double bulletSpeed) {
        this.bulletSpeed = bulletSpeed;
    }

    public void setDamage(int damage) {
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
