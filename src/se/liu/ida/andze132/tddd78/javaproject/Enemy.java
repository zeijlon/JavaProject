package se.liu.ida.andze132.tddd78.javaproject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andreas Zeijlon on 2015-03-21.
 */
public class Enemy {

    private int hp;
    private int speed;
    private int goldgain;
    private int damage;
    public int X;
    public int Y;
    public final static int right = 0, left = 1, up = 2, down = 3, still = 4;
    public int direction;
    public int enemyWalk = 0;
    public int yC = 0;
    public int xC = 0;
    public boolean hasUp = false;
    public boolean hasDown = false;
    public boolean hasLeft = false;
    public boolean hasRight = true;

    public int[][] hasWalked;





    public Enemy(final int hp, final int speed, final int goldgain, final int damage) {
        this.hp = hp;
        this.speed = speed;
        this.goldgain = goldgain;
        this.damage = damage;
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
}


