package se.liu.ida.andze132.tddd78.javaproject;

import java.awt.*;

/**
 * Created by Andreas Zeijlon on 2015-04-22.
 */
class EnemyProperties {

    private static final int BASIC_HEALTH = 100;
    private static final int BASIC_SPEED = 1;
    private static final int BASIC_GOLD_GAIN = 1;
    private static final int BASIC_DAMAGE = 2;


    private static final int ARMORED_HEALTH = 200;
    private static final int ARMORED_SPEED = 2;
    private static final int ARMORED_GOLD_GAIN = 5;
    private static final int ARMORED_DAMAGE = 5;


    public void decideEnemy(Enemy enemy) {
        switch (enemy.getType()) {
            case BASICENEMY:
                enemy.setOriginalHp(BASIC_HEALTH);
                enemy.setHp(BASIC_HEALTH);
                enemy.setSpeed(BASIC_SPEED);
                enemy.setGoldgain(BASIC_GOLD_GAIN);
                enemy.setDamage(BASIC_DAMAGE);
                enemy.setImage(Toolkit.getDefaultToolkit().getImage("images/PolarBearNormal.gif"));
                break;
            case ARMOREDENEMY:
                enemy.setOriginalHp(ARMORED_HEALTH);
                enemy.setHp(ARMORED_HEALTH);
                enemy.setSpeed(ARMORED_SPEED);
                enemy.setGoldgain(ARMORED_GOLD_GAIN);
                enemy.setDamage(ARMORED_DAMAGE);
                enemy.setImage(Toolkit.getDefaultToolkit().getImage("images/ArmoredPolarBearNormal60.gif"));
                break;
        }
    }
}
