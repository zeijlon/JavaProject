package se.liu.ida.andze132.tddd78.javaproject;

/**
 * Created by Administratör on 2015-03-23.
 */

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class KeyHandler extends MouseAdapter
{
    @Override public void mouseClicked(final MouseEvent e) {
	super.mouseClicked(e);
	GameFrame.clickPoint = new Point(e.getX() - 8, e.getY() - 30);
    }

    @Override public void mouseMoved(final MouseEvent e) {
	super.mouseMoved(e);
	GameFrame.motionPoint = new Point(e.getX() - 8, e.getY() - 30);
    }
}