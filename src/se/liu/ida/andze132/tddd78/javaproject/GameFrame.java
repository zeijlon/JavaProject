package se.liu.ida.andze132.tddd78.javaproject;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame
{
    public static Point motionPoint = new Point();
    public static Point clickPoint = new Point();

    public GameFrame(GRID grid, Shop shop, EnemySpawner spawner, TowerHandler towerHandler) throws HeadlessException {
	super("DAWN OF THE POLAR BEARS");
	GameComponent gameComponent = new GameComponent(grid, shop, spawner, towerHandler);
	this.setLayout(new BorderLayout());
	this.add(gameComponent, BorderLayout.CENTER);
	this.getContentPane().setBackground(Color.LIGHT_GRAY);
	createMenu();
	this.pack();
	this.setVisible(true);

	this.addMouseListener(new KeyHandler());
	this.addMouseMotionListener(new KeyHandler());

    }

    public void createMenu() {
	final JMenu menu = new JMenu("Menu");
	menu.add(new JMenuItem("exit", 'E'));
	final JMenuBar bar = new JMenuBar();
	bar.add(menu);
	bar.add(Box.createHorizontalGlue());
	this.setJMenuBar(bar);
    }

}