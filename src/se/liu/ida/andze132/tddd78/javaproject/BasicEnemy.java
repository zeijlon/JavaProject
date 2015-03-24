package se.liu.ida.andze132.tddd78.javaproject;

public class BasicEnemy extends Enemy
{
    private final static int HEALTH = 100;
    private final static int SPEED = 1;
    private final static int GOLDGAIN = 1;
    private final static int DAMAGE = 1;


    public BasicEnemy() {
	super(HEALTH, SPEED, GOLDGAIN, DAMAGE);
    }
}
