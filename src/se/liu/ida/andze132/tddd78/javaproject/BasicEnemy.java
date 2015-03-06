package se.liu.ida.andze132.tddd78.javaproject;

public class BasicEnemy extends Enemies
{
    private int hp = 100;
    private int speed = 10;
    private int goldgain = 1;
    private int damage = 1;


    public BasicEnemy(final int hp, final int speed, final int goldgain, final int damage) {
	super(hp, speed, goldgain, damage);
    }
}
