package se.liu.ida.andze132.tddd78.javaproject;

/**
 * Created by Administratör on 2015-03-23.
 */

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class KeyHandler extends MouseAdapter {
    private Point motionPoint = null;
    private Point clickPoint = null;
    private Boolean mouseReleased = true;

    @Override
    public void mousePressed(final MouseEvent e) {
        super.mousePressed(e);
        clickPoint = new Point(e.getX() - 8, e.getY() - 50);
    }

    @Override
    public void mouseReleased(final MouseEvent e) {
        super.mouseReleased(e);
        clickPoint = null;
        mouseReleased = true;
    }

    @Override
    public void mouseMoved(final MouseEvent e) {
        super.mouseMoved(e);
        motionPoint = new Point(e.getX() - 8, e.getY() - 50);
    }

    public Point getMotionPoint() {
        return motionPoint;
    }

    public void setMotionPoint(final Point motionPoint) {
        this.motionPoint = motionPoint;
    }

    public Point getClickPoint() {
        return clickPoint;
    }

    public void setClickPoint(final Point clickPoint) {
        this.clickPoint = clickPoint;
    }

    public Boolean getMouseReleased() {
        return mouseReleased;
    }

    public void setMouseReleased(final Boolean mouseReleased) {
        this.mouseReleased = mouseReleased;
    }
}
