package se.liu.ida.andze132.tddd78.javaproject;

/**
 * Created by Administratör on 2015-03-23.
 */

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 */
public class KeyHandler extends MouseAdapter {
    private static final int MENU_HEIGHT = 30;
    private Point motionPoint = null;
    private Point clickPoint = null;

    @Override
    public void mousePressed(final MouseEvent e) {
        super.mousePressed(e);
        clickPoint = new Point(e.getX() - 8, e.getY() - MENU_HEIGHT);
    }

    @Override
    public void mouseReleased(final MouseEvent e) {
        super.mouseReleased(e);
        clickPoint = null;
    }

    @Override
    public void mouseMoved(final MouseEvent e) {
        super.mouseMoved(e);
        motionPoint = new Point(e.getX() - 8, e.getY() - MENU_HEIGHT);
    }

    public Point getMotionPoint() {
        return motionPoint;
    }

    public Point getClickPoint() {
        return clickPoint;
    }

    public void setClickPoint() {
        this.clickPoint = null;
    }
}
