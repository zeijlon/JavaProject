package se.liu.ida.andze132.tddd78.javaproject;

import java.awt.*;

/**
 * Created by Administrat�r on 2015-03-30.
 */
public class NormalBullet extends Bullet {
    public static Image coin = Toolkit.getDefaultToolkit().getImage("images/coin.png");

    public NormalBullet(int x, int y) {
        super(x, y, coin);
    }
}
