package se.liu.ida.andze132.tddd78.javaproject;

import java.awt.*;

/**
 * Created by Administratör on 2015-04-12.
 */
public class FMJBullet extends Bullet {
    public static Image fmjbullet = Toolkit.getDefaultToolkit().getImage("images/coin.png");

    public static int bulletSpeed = 3;
    public static int damage = 20;

    public FMJBullet(int x, int y) {
        super(x, y, fmjbullet, bulletSpeed, damage);
    }
}
