package se.liu.ida.andze132.tddd78.javaproject;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andreas Zeijlon on 2015-04-01.
 */
public class BulletHandler {

    private static final int RECT_SIZE = 20;
    private GRID grid;
    private EnemySpawner spawner;
    private List<Bullet> bullets = new ArrayList<>();



    public BulletHandler(GRID grid, EnemySpawner spawner) {
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
        Bullet bullet = decideBullet(tower);
        assert bullet != null;
        bullet.setAngle(Math.atan2((enemy.getY() + (float)GameComponent.TILE_SIZE/2) - (bullet.getY()+(float)RECT_SIZE/2),
                                   (enemy.getX() + (float)GameComponent.TILE_SIZE/2) - (bullet.getX()+(float)RECT_SIZE/2)));
        bullets.add(bullet);
    }

    private Bullet decideBullet(Towers tower) {
        TowerType type = tower.getType();
        switch (type) {
            case BASIC:
                return new Bullet(tower,BulletType.NORMAL);
            case ARMORPIERCING:
                return new Bullet(tower, BulletType.ARMORPIERING);
            case SCOUT:
                return new Bullet(tower, BulletType.SCOUT);
            default:
                return null;
        }
    }

    public void updateBullets() {
        for (int i = 0; i < bullets.size(); i++) {
            defineBullets(bullets.get(i));
            bullets.get(i).setX(bullets.get(i).getX() + bullets.get(i).getBulletSpeed() * Math.cos(bullets.get(i).getAngle()));
            bullets.get(i).setY(bullets.get(i).getY() + bullets.get(i).getBulletSpeed() * Math.sin(bullets.get(i).getAngle()));
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
            AffineTransform at = new AffineTransform();
            AffineTransform old = g.getTransform();
            at.rotate(bullets.get(i).getAngle()+Math.PI/2, bullets.get(i).getX() + 10,
                      bullets.get(i).getY()+10);
                        g.transform(at);
                        g.drawImage(bullets.get(i).getImage(), (int)bullets.get(i).getX(),(int) bullets.get(i).getY(), null);
                        g.setTransform(old);

            }
    }

    public void setBullets(List<Bullet> bullets) {
        this.bullets = bullets;
    }
}
