package se.liu.ida.andze132.tddd78.javaproject;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame
{
    public GameFrame(GRID grid) throws HeadlessException {
	super("DAWN OF THE POLAR BEARS");
	GameComponent gameComponent = new GameComponent(grid);
	this.setLayout(new BorderLayout());
	this.add(gameComponent, BorderLayout.CENTER);
	this.pack();
	this.setVisible(true);
    }
}
