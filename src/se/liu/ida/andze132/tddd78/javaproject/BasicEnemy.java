package se.liu.ida.andze132.tddd78.javaproject;

public class BasicEnemy extends Enemy
{
    private final static int hp = 100;
    private final static int speed = 10;
    private final static int goldgain = 1;
    private final static int damage = 1;


    public BasicEnemy() {
	super(hp, speed, goldgain, damage);
    }
}
