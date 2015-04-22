package se.liu.ida.andze132.tddd78.javaproject;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andreas Zeijlon on 2015-04-01.
 */
class BulletHandler {

    private static final int RECT_SIZE = 20;
    private GRID grid;
    private EnemySpawner spawner;
    private List<Bullet> bullets = new ArrayList<>();



    BulletHandler(GRID grid, EnemySpawner spawner) {
        this.grid = grid;
        this.spawner = spawner;

    }

    private void defineBullets(Bullet bullet) {
        Double x = bullet.getX();
        int xValue = x.intValue();
        Double y = bullet.getY();
        int yValue = y.intValue();
        bullet.setBulletRect(new Rectangle(xValue, yValue, RECT_SIZE, RECT_SIZE));
    }

    public void shootEnemy(Enemy enemy, Towers tower) {
        //Random random = new Random(); + Math.toRadians(random.nextInt(360))
        Bullet bullet = decideBullet(tower);
        assert bullet != null;
        bullet.setAngle(Math.toDegrees(Math.atan2(enemy.getY() + RECT_SIZE - bullet.getY(), enemy.getX() + RECT_SIZE - bullet.getX())));
        bullets.add(bullet);
    }

    private Bullet decideBullet(Towers tower) {
        TowerType type = tower.getType();
        switch (type) {
            case BASICTOWER:
                return new NormalBullet(tower.getX() + RECT_SIZE, tower.getY() + RECT_SIZE);
            case ARMORPIERCINGTOWER:
                return new FMJBullet(tower.getX() + RECT_SIZE, tower.getY() + RECT_SIZE);
            default:
                return null;
        }
    }

    public void updateBullets() {
        for (int i = 0; i < bullets.size(); i++) {
            defineBullets(bullets.get(i));
            bullets.get(i).setX(bullets.get(i).getX() + bullets.get(i).getBulletSpeed() * Math.cos(Math.toRadians(bullets.get(i).getAngle())));
            bullets.get(i).setY(bullets.get(i).getY() + bullets.get(i).getBulletSpeed() * Math.sin(Math.toRadians(bullets.get(i).getAngle())));
            if (!grid.getGridSize().contains(bullets.get(i).getX(), bullets.get(i).getY())) {
                bullets.remove(i);
            }
            for (int j = 0; j < spawner.getEnemies().size(); j++) {
                try {
                    if (spawner.getEnemies().get(j).getEnemyEllipse().intersects(bullets.get(i).getBulletRect())) {
                        spawner.getEnemies().get(j).setHp(-bullets.get(i).getDamage());
                        spawner.getEnemies().get(j).setHealthBarHp(bullets.get(i).getDamage());
                        bullets.remove(i);
                    }
                } catch (RuntimeException ignored) {
                }
            }
        }
    }


    public void draw(Graphics2D g) {
        for (int i = 0; i < bullets.size(); i++) {
            Double x = bullets.get(i).getX();
            int xValue = x.intValue();
            Double y = bullets.get(i).getY();
            int yValue = y.intValue();
            g.drawImage(bullets.get(i).getImage(), xValue, yValue, null);
            //g.drawRect((int)bullets.get(i).getBulletRect().getX(), (int)bullets.get(i).getBulletRect().getY(), (int)bullets.get(i).getBulletRect().getWidth(), (int)bullets.get(i).getBulletRect().getHeight());
        }
    }

    public void setBullets(List<Bullet> bullets) {
        this.bullets = bullets;
    }
}
