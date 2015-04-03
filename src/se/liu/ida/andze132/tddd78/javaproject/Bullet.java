package se.liu.ida.andze132.tddd78.javaproject;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrat�r on 2015-03-30.
 */
public class Bullet
{
    private double angle;
    private double x;
    private double y;
    private double stepsPerFrame;

    private Image image;

    public Bullet(int x, int y, Image image) {
        this.x = x;
        this.y = y;
        this.image = image;
    }

    public double getAngle() {
        return angle;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getStepsPerFrame() {
        return stepsPerFrame;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setStepsPerFrame(int stepsPerFrame) {
        this.stepsPerFrame = stepsPerFrame;
    }

    public Image getImage() {
        return image;
    }
}
