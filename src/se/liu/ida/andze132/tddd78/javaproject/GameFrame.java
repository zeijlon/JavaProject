package se.liu.ida.andze132.tddd78.javaproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameFrame extends JFrame
{
    private boolean basicenemy = false;

    public GameFrame(GRID grid, Shop shop, EnemySpawner spawner) throws HeadlessException {
	super("DAWN OF THE POLAR BEARS");
	GameComponent gameComponent = new GameComponent(grid, shop, spawner);
	this.setLayout(new BorderLayout());
	this.add(gameComponent, BorderLayout.CENTER);
	this.getContentPane().setBackground(Color.LIGHT_GRAY);
	this.pack();
	this.setVisible(true);

	class MyTracker extends MouseAdapter
	{
	    public void mouseClicked(MouseEvent e) {
		int x = e.getX() - 8;
		int y = e.getY() - 30;
		System.out.println(y+" "+x);

		if (spawner.betweenRounds) {
		    if (spawner.enemies.isEmpty()) {
			if (shop.GRID_SIZE_X + Shop.SHOP_MARGIN <= x && x <= shop.GRID_SIZE_X + Shop.SHOP_MARGIN+50 && y <= shop.GRID_SIZE_Y + Shop.SHOP_MARGIN &&
			    y <= shop.GRID_SIZE_Y + Shop.SHOP_MARGIN + 50) {
			    spawner.betweenRounds = false;
			}
		    }

		}
	    }
	}
	this.addMouseListener(new MyTracker());
    }
}

