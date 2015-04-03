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

    public BulletHandler(GRID grid, Shop shop, EnemySpawner spawner) {
        this.grid = grid;
        this.shop = shop;
        this.spawner = spawner;
    }

    public void shootEnemy(Enemy enemy, Towers tower){
        Bullet bullet = new NormalBullet(tower.getX(), tower.getY());
        bullet.setAngle(Math.toDegrees(Math.atan2(enemy.getX() - bullet.getX(), enemy.getY() - bullet.getY())));
        if(bullet.getAngle()<0){
            bullet.setAngle(bullet.getAngle() + 180);
        }
        System.out.println(bullet.getAngle());
        bullets.add(bullet);

    }

    public void updateBullets(){
        for (Bullet bullet : bullets) {
            bullet.setX(bullet.getX() + bullet.getStepsPerFrame() * Math.cos(Math.toRadians(bullet.getAngle())));
            bullet.setY(bullet.getY() + bullet.getStepsPerFrame() * Math.sin(Math.toRadians(bullet.getAngle())));
            if(!grid.getGridSize().contains(bullet.getX(), bullet.getY())){
              bullets.remove(bullet);
            }
        }
    }

    public void draw(Graphics g){
        for (Bullet bullet : bullets) {
            Double x = bullet.getX();
            int X = x.intValue();
            Double y = bullet.getY();
            int Y = y.intValue();
            g.drawImage(bullet.getImage(), X, Y, null);
        }
    }
}