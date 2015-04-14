package se.liu.ida.andze132.tddd78.javaproject;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andreas Zeijlon on 2015-04-01.
 */
public class BulletHandler {

    private GRID grid;
    private EnemySpawner spawner;
    private List<Bullet> bullets = new ArrayList<>();

    public BulletHandler(GRID grid, EnemySpawner spawner) {
        this.grid = grid;
        this.spawner = spawner;

    }

    public void defineBullets(Bullet bullet) {
        Double x = bullet.getX();
        int xValue = x.intValue();
        Double y = bullet.getY();
        int yValue = y.intValue();
        bullet.setBulletRect(new Rectangle(xValue, yValue, 20, 20));
    }

    public void shootEnemy(Enemy enemy, Towers tower) {
        Bullet bullet = new NormalBullet(tower.getX() + 10, tower.getY() + 10);
        bullet.setAngle(Math.toDegrees(Math.atan2(enemy.getY() + 20 - bullet.getY(), enemy.getX() + 20 - bullet.getX())));
        tower.setAngle(Math.toDegrees(Math.atan2(enemy.getY() + 20 - bullet.getY(), enemy.getX() + 20 - bullet.getX())));
        bullets.add(bullet);
        System.out.println("shoot");
    }

    public void updateBullets() {
        for (int i = 0; i < bullets.size(); i++) {
            defineBullets(bullets.get(i));
            bullets.get(i).setX(bullets.get(i).getX() + bullets.get(i).getBulletSpeed() * Math.cos(Math.toRadians(bullets.get(i).getAngle())));
            bullets.get(i).setY(bullets.get(i).getY() + bullets.get(i).getBulletSpeed() * Math.sin(Math.toRadians(bullets.get(i).getAngle())));
            if (!grid.getGridSize().contains(bullets.get(i).getX(), bullets.get(i).getY())) {
                bullets.remove(i);
            }

        }
        for (int i = 0; i < spawner.getEnemies().size(); i++) {
            for (int j = 0; j < bullets.size(); j++) {
                try{
                if (spawner.getEnemies().get(i).getEnemyRect().intersects(bullets.get(j).getBulletRect())) {
                    spawner.getEnemies().get(i).setHp(bullets.get(j).getDamage());
                    bullets.remove(j);
                }}catch (RuntimeException ignored){}
            }
        }
    }


    public void draw(Graphics2D g) {
        for (Bullet bullet : bullets) {
            Double x = bullet.getX();
            int xValue = x.intValue();
            Double y = bullet.getY();
            int yValue = y.intValue();
            g.drawImage(bullet.getImage(), xValue, yValue, null);
        }
    }
}
