package se.liu.ida.andze132.tddd78.javaproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameFrame extends JFrame {
    private boolean basicenemy = false;

    public GameFrame(GRID grid, Shop shop, EnemySpawner spawner) throws HeadlessException {
        super("DAWN OF THE POLAR BEARS");
        GameComponent gameComponent = new GameComponent(grid, shop, spawner);
        this.setLayout(new BorderLayout());
        this.add(gameComponent, BorderLayout.CENTER);
        this.getContentPane().setBackground(Color.LIGHT_GRAY);
        this.pack();
        this.setVisible(true);
        
        class MyTracker extends MouseAdapter {
            public void mouseClicked(MouseEvent e){
                int x = e.getX() + 10;
                int y = e.getY() - 30;
                if(basicenemy){
                    grid.buildTower(y, x);
                    basicenemy = false;
                }
                else if (grid.getSquares()[1].length*40+40 <= x &&  y>=40 && x<=grid.getSquares()[1].length*40+80 && y<=80){
                    System.out.println("clicked");
                    basicenemy = true;
                }

            }
        }
        this.addMouseListener(new MyTracker());
    }
}

