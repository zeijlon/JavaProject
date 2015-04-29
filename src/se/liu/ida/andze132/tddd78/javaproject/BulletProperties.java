package se.liu.ida.andze132.tddd78.javaproject;

import java.awt.*;

/**
 * Created by Andreas Zeijlon on 2015-04-22.
 */
public class BulletProperties {

    public void decideBullet(Bullet bullet, Towers tower) {
        switch (bullet.getType()) {
            case NORMALBULLET:
                bullet.setBulletSpeed(tower.getBulletSpeed());
                bullet.setDamage(tower.getDamage());
                bullet.setImage(Toolkit.getDefaultToolkit().getImage("images/normalBullet.png"));
                break;
            case APBULLET:
                bullet.setBulletSpeed(tower.getBulletSpeed());
                bullet.setDamage(tower.getDamage());
                bullet.setImage(Toolkit.getDefaultToolkit().getImage("images/apBullet.png"));
                break;
            case SCOUTBULLET:
                bullet.setBulletSpeed(tower.getBulletSpeed());
                bullet.setDamage(tower.getDamage());
                bullet.setImage(Toolkit.getDefaultToolkit().getImage("images/scoutBullet.png"));
                break;
        }
    }
}
