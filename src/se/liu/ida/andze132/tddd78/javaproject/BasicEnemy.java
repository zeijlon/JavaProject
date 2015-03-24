package se.liu.ida.andze132.tddd78.javaproject;

public class BasicEnemy extends Enemy
{
    private static int HEALTH = 100;
    private static int SPEED = 1;
    private static int GOLDGAIN = 1;
    private static int DAMAGE = 1;


    public BasicEnemy() {
	super(HEALTH, SPEED, GOLDGAIN, DAMAGE);
    }
}
