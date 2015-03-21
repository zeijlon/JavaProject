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
    public boolean enemySpawned = false;
    public boolean moveLeft = false;
    public boolean moveRight = true;
    public boolean moveUp = false;
    public boolean moveDown = false;


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


