package se.liu.ida.andze132.tddd78.javaproject;

/**
 * Created by Administratör on 2015-03-23.
 */

import javafx.scene.input.KeyEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class KeyHandler extends MouseAdapter
{
    @Override
    public void mousePressed(final MouseEvent e) {
        super.mousePressed(e);
        GameFrame.clickPoint = new Point(e.getX() - 8, e.getY() - 50);
    }

    @Override
    public void mouseReleased(final MouseEvent e) {
        super.mouseReleased(e);
        GameFrame.clickPoint = null;
        GameFrame.mouseReleased = true;
    }

    @Override
    public void mouseMoved(final MouseEvent e) {
        super.mouseMoved(e);
        GameFrame.motionPoint = new Point(e.getX() - 8, e.getY() - 50);
    }


     }
