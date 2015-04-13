package se.liu.ida.andze132.tddd78.javaproject;

import java.awt.*;

/**
 * Created by Administrat�r on 2015-03-30.
 */
public class NormalBullet extends Bullet {
    public static Image bullet = Toolkit.getDefaultToolkit().getImage("images/normalBullet.png");

    public static int bulletSpeed = 5;
    public static int damage = 10;

    public NormalBullet(int x, int y) {
        super(x, y, bullet, bulletSpeed, damage);
    }
}
