package se.liu.ida.andze132.tddd78.javaproject;

public class Enemies
{
    private int hp;
    private int speed;
    private int goldgain;
    private int damage;

    public Enemies(final int hp, final int speed, final int goldgain, final int damage) {
	this.hp = hp;
	this.speed = speed;
	this.goldgain = goldgain;
	this.damage = damage;

	boolean alive = true;
    }
}
