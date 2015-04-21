package se.liu.ida.andze132.tddd78.javaproject;

import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Created by Andreas Zeijlon on 2015-03-21.
 */
public class Enemy {

    private double originalHp;
    private int hp;
    private double healthBarHp = 60;
    private int speed;
    private int goldgain;
    private int damage;
    private double angle;
    private int x;
    private int y;

    private Direction direction = null;
    private int enemyWalk = 0;
    private int yC = 0;
    private int xC = 0;

    private boolean hasUp = false;
    private boolean hasDown = false;
    private boolean hasLeft = false;
    private boolean hasRight = false;

    private Image image = null;

    private int[][] hasWalked = null;

    private Ellipse2D enemyEllipse = null;


    public Enemy(final EnemyType type) {
        decideEnemy(type);
    }

    public Ellipse2D getEnemyEllipse() {
        return enemyEllipse;
    }

    public void setEnemyEllipse() {
        switch (this.direction) {
            case RIGHT:
            case LEFT:
                this.enemyEllipse = new Ellipse2D.Float(x, y + 10, GameComponent.TILE_SIZE, GameComponent.TILE_SIZE - 20);
                break;
            case STILL:
            case DOWN:
            case UP:
                this.enemyEllipse = new Ellipse2D.Float(x + 10, y, GameComponent.TILE_SIZE-20, GameComponent.TILE_SIZE);
                break;
        }
    }

    public void decideEnemy(EnemyType enemy) {
        switch (enemy) {
            case BASICENEMY:
                originalHp = 100;
                hp = (int)originalHp;
                speed = 1;
                goldgain = 1;
                damage = 2;
                image = (Toolkit.getDefaultToolkit().getImage("images/PolarBearNormal.gif"));
                //image = image.getScaledInstance(60, 60, Image.SCALE_DEFAULT);
                break;
            case ARMOREDENEMY:
                originalHp = 200;
                hp = (int)originalHp;
                speed = 2;
                goldgain = 2;
                damage = 5;
                image = (Toolkit.getDefaultToolkit().getImage("images/ArmoredPolarBearNormal60.gif"));
                break;
        }
    }

    public Image getImage() {
        return image;
    }

    public int getHp() {
        return hp;
    }

    public int getSpeed() {
        return speed;
    }

    public int getGoldgain() {
        return goldgain;
    }

    public int getDamage() {
        return damage;
    }

    public void setHp(final int damage) {
        this.hp -= damage;
    }

    public int getX() {
        return x;
    }

    public void setX(final int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(final int y) {
        this.y = y;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(final Direction direction) {
        this.direction = direction;
    }

    public int getEnemyWalk() {
        return enemyWalk;
    }

    public void setEnemyWalk(final int enemyWalk) {
        this.enemyWalk = enemyWalk;
    }

    public int getyC() {
        return yC;
    }

    public void setyC(final int yC) {
        this.yC = yC;
    }

    public int getxC() {
        return xC;
    }

    public void setxC(final int xC) {
        this.xC = xC;
    }

    public boolean isHasUp() {
        return hasUp;
    }

    public void setHasUp(final boolean hasUp) {
        this.hasUp = hasUp;
    }

    public boolean isHasDown() {
        return hasDown;
    }

    public void setHasDown(final boolean hasDown) {
        this.hasDown = hasDown;
    }

    public boolean isHasLeft() {
        return hasLeft;
    }

    public void setHasLeft(final boolean hasLeft) {
        this.hasLeft = hasLeft;
    }

    public boolean isHasRight() {
        return hasRight;
    }

    public void setHasRight(final boolean hasRight) {
        this.hasRight = hasRight;
    }

    public int[][] getHasWalked() {
        return hasWalked;
    }

    public void setHasWalked(final int[][] hasWalked) {
        this.hasWalked = hasWalked;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(final double angle) {
        this.angle = angle;
    }

    public void setHealthBarHp(int damage) {
        double x = damage/originalHp;
        this.healthBarHp = healthBarHp - (60*x);
    }

    public double getHealthBarHp() {
        return healthBarHp;
    }
}


