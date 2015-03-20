package se.liu.ida.andze132.tddd78.javaproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameFrame extends JFrame {
    private boolean basicenemy = false;

    public GameFrame(GRID grid) throws HeadlessException {
        super("DAWN OF THE POLAR BEARS");
        GameComponent gameComponent = new GameComponent(grid);
        this.setLayout(new BorderLayout());
        this.add(gameComponent, BorderLayout.CENTER);
        this.pack();
        this.setVisible(true);
        
        class MyTracker extends MouseAdapter {
            public void mouseClicked(MouseEvent e){
                int x = (int) e.getX();
                int y = (int) e.getY()-30;
                System.out.println(y+"  "+x);
                if(basicenemy){
                    grid.buildTower(y, x);
                    basicenemy = false;
                }
                if (900+10<=x && x<= 940+10 && 300<=y && y<=340){
                    System.out.println("clicked");
                    basicenemy = true;
                }

            }
        }
        this.addMouseListener(new MyTracker());
    }
}

