package se.liu.ida.andze132.tddd78.javaproject;

import java.util.Vector;

/**
 * Created by Administratör on 2015-03-30.
 */
public class NormalBullet extends Bullet
{
    private static double velocity = 10;
    public NormalBullet(final float x, final float y, final int targetX, final int targetY) {
	super(x, y, velocity, targetX, targetY);
    }

}
