package se.liu.ida.andze132.tddd78.javaproject;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andreas Zeijlon on 2015-04-01.
 */
public class BulletHandler {

    private GRID grid;
    private Shop shop;
    private EnemySpawner spawner;
    private List<Bullet> bullets = new ArrayList<>();
    private int interval = 0;

    public BulletHandler(GRID grid, Shop shop, EnemySpawner spawner) {
        this.grid = grid;
        this.shop = shop;
        this.spawner = spawner;
    }

    public void shootEnemy(Enemy enemy, Towers tower) {
        Bullet bullet = new NormalBullet(tower.getX(), tower.getY());
        bullet.setAngle(Math.toDegrees(Math.atan2(enemy.getX() - bullet.getX(), enemy.getY() - bullet.getY())));
        bullets.add(bullet);
        interval = 0;

    }

    public void updateBullets() {
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).setX(bullets.get(i).getX() + Math.cos(Math.toRadians(Math.PI + bullets.get(i).getAngle())));
            bullets.get(i).setY(bullets.get(i).getY() + Math.sin(Math.toRadians(Math.PI + bullets.get(i).getAngle())));
            if (!grid.getGridSize().contains(bullets.get(i).getX(), bullets.get(i).getY())) {
                bullets.remove(i);
            }}
        }



    public void draw(Graphics g) {
        for (Bullet bullet : bullets) {
            Double x = bullet.getX();
            int X = x.intValue();
            Double y = bullet.getY();
            int Y = y.intValue();
            g.drawImage(bullet.getImage(), X, Y, null);
        }
    }
}
