package se.liu.ida.andze132.tddd78.javaproject;

/**
 * Created by Andreas Zeijlon on 2015-03-20.
 */
public class Towers {

    private int damage;
    private int speed;
    private int cost;
    private int sell;

    public Towers(final int damage, final int speed, final int cost, final int sell) {
        this.damage = damage;
        this.speed = speed;
        this.cost = cost;
        this.sell = sell;

        boolean alive = true;
    }
}
