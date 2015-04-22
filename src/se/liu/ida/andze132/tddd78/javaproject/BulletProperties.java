package se.liu.ida.andze132.tddd78.javaproject;

import java.awt.*;

/**
 * Created by Andreas Zeijlon on 2015-04-22.
 */
class BulletProperties {

    private static final int NORMAL_BULLET_DAMAGE = 10;
    private static final int NORMAL_BULLET_SPEED = 10;

    private static final int FMJ_BULLET_DAMAGE = 20;
    private static final int FMJ_BULLET_SPEED = 5;


    public void decideBullet(Bullet bullet) {
        switch (bullet.getType()) {
            case NORMALBULLET:
                bullet.setBulletSpeed(NORMAL_BULLET_SPEED);
                bullet.setDamage(NORMAL_BULLET_DAMAGE);
                bullet.setImage(Toolkit.getDefaultToolkit().getImage("images/normalBullet.png"));
                break;
            case FMJ:
                bullet.setBulletSpeed(FMJ_BULLET_SPEED);
                bullet.setDamage(FMJ_BULLET_DAMAGE);
                bullet.setImage(Toolkit.getDefaultToolkit().getImage("images/coin.png"));
                break;
        }
    }
}
