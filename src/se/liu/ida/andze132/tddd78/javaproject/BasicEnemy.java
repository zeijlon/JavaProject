package se.liu.ida.andze132.tddd78.javaproject;

import java.awt.*;

public class BasicEnemy extends Enemy {
    private static Image image = (Toolkit.getDefaultToolkit().getImage("images/basicEnemy.png"));


    private static int health = 100;
    private static int speed = 1;
    private static int goldgain = 1;
    private static int damage = 1;


    public BasicEnemy() {
        super(health, speed, goldgain, damage, image);
    }
}
