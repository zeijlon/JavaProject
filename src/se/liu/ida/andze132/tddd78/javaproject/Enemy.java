package se.liu.ida.andze132.tddd78.javaproject;

/**
 * Created by Andreas Zeijlon on 2015-03-21.
 */
public class Enemy {

    private int hp;
    private int speed;
    private int goldgain;
    private int damage;

    public Enemy(final int hp, final int speed, final int goldgain, final int damage) {
        this.hp = hp;
        this.speed = speed;
        this.goldgain = goldgain;
        this.damage = damage;

        boolean alive = true;
    }

    public void spawnEnemy(){
        int spawnRate = 1000, spawnTime = 0;
        if(spawnTime >= spawnRate){

        }
    }
}


