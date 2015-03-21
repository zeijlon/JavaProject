package se.liu.ida.andze132.tddd78.javaproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class TestClass
{
    private final static int INTERVAL = 100;

    public static void main(String[] args) {
	GRID grid = new GRID(1);
	Shop shop = new Shop();
	final GameFrame frame = new GameFrame(grid, shop);
		System.out.println(3/2);



	final Action doOneStep = new AbstractAction()
	{
	    public void actionPerformed(ActionEvent e) {
			frame.repaint();
	    }
	};

	final Timer clockTimer = new Timer(INTERVAL, doOneStep);
	clockTimer.setCoalesce(true);
	clockTimer.start();
    }
}

