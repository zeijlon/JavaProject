package se.liu.ida.andze132.tddd78.javaproject;

import javax.swing.*;
import java.awt.event.ActionEvent;


public class TestClass
{
    private final static int INTERVAL = 100;

    public static void main(String[] args) {
	GRID test = new GRID(1);
	final GameFrame frame = new GameFrame(test);


	final Action doOneStep = new AbstractAction()
	{
	    public void actionPerformed(ActionEvent e) {
		System.out.println("hej");
	    }
	};

	final Timer clockTimer = new Timer(INTERVAL, doOneStep);
	clockTimer.setCoalesce(true);
	clockTimer.start();
    }
}

