package se.liu.ida.andze132.tddd78.javaproject;

import java.awt.*;

/**
 * Created by Andreas Zeijlon on 2015-04-22.
 */
public class EnemyProperties {
    private static final int BASICSTARTHEALTH = 50;
    private static final int ARMOREDSTARTHEALTH = 200;
    private static final int SPYSTARTHEALTH = 60;


    private static int basicHealth = BASICSTARTHEALTH;
    private static final int BASIC_GOLD_GAIN = 1;
    private static final int BASIC_DAMAGE = 2;


    private static int armoredHealth = ARMOREDSTARTHEALTH;
    private static final int ARMORED_GOLD_GAIN = 5;
    private static final int ARMORED_DAMAGE = 5;

    private static int spyHealth = SPYSTARTHEALTH;
    private static final int SPY_GOLD_GAIN = 5;
    private static final int SPY_DAMAGE = 10;

    private static final int BOSS_HEALTH = 10000;
    private static final int BOSS_SPEED = 1;
    private static final int BOSS_GOLD_GAIN = 100;
    private static final int BOSS_DAMAGE = 100;

    public void decideEnemy(Enemy enemy) {
        switch (enemy.getType()) {
            case BASIC:
                enemy.setOriginalHp(basicHealth);
                enemy.setHp(basicHealth);
		final int basicSpeed = 2;
		enemy.setSpeed(basicSpeed);
                enemy.setGoldgain(BASIC_GOLD_GAIN);
                enemy.setDamage(BASIC_DAMAGE);
                enemy.setImage(Toolkit.getDefaultToolkit().getImage("images/PolarBearNormal.gif"));
                break;
            case ARMORED:
                enemy.setOriginalHp(armoredHealth);
                enemy.setHp(armoredHealth);
		final int armoredSpeed = 1;
		enemy.setSpeed(armoredSpeed);
                enemy.setGoldgain(ARMORED_GOLD_GAIN);
                enemy.setDamage(ARMORED_DAMAGE);
                enemy.setImage(Toolkit.getDefaultToolkit().getImage("images/ArmoredPolarBearNormal60.gif"));
                break;
            case SPY:
                enemy.setOriginalHp(spyHealth);
                enemy.setHp(spyHealth);
		final int spySpeed = 3;
		enemy.setSpeed(spySpeed);
                enemy.setGoldgain(SPY_GOLD_GAIN);
                enemy.setDamage(SPY_DAMAGE);
                enemy.setImage(Toolkit.getDefaultToolkit().getImage("images/SpyPolarBearFast.gif"));
                break;
	    case BOSS:
		enemy.setOriginalHp(BOSS_HEALTH);
		enemy.setHp(BOSS_HEALTH);
		enemy.setSpeed(BOSS_SPEED);
		enemy.setGoldgain(BOSS_GOLD_GAIN);
		enemy.setDamage(BOSS_DAMAGE);
		enemy.setImage(Toolkit.getDefaultToolkit().getImage("images/bossEnemy.png"));
		break;
        }
    }

    public static void setBasicHealth(final int health) {
        basicHealth = health;
    }

    public static void setArmoredHealth(final int health) {
        armoredHealth = health;
    }

    public static void setSpyHealth(final int health) {
        spyHealth = health;
    }

    public static int getBasicstarthealth() {
	return BASICSTARTHEALTH;
    }

    public static int getArmoredstarthealth() {
	return ARMOREDSTARTHEALTH;
    }

    public static int getSpystarthealth() {
	return SPYSTARTHEALTH;
    }
}
