package se.liu.ida.andze132.tddd78.javaproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame extends JFrame {
    public static Point motionPoint = null;
    public static Point clickPoint = null;
    public static Boolean mouseReleased = true;

    public GameFrame(GRID grid, Shop shop, EnemySpawner spawner, TowerHandler towerHandler, BulletHandler bulletHandler, Menu menu) throws HeadlessException {
        super("DAWN OF THE POLAR BEARS");
        GameComponent gameComponent = new GameComponent(grid, shop, spawner, towerHandler, bulletHandler, menu);
        this.setLayout(new BorderLayout());
        this.add(gameComponent, BorderLayout.CENTER);
        this.getContentPane().setBackground(Color.LIGHT_GRAY);
        this.createMenus();
        this.pack();
        this.setVisible(true);
        this.isResizable();

        this.addMouseListener(new KeyHandler());
        this.addMouseMotionListener(new KeyHandler());

    }

    private void createMenus() {
        class ExitListener implements ActionListener {
            public void actionPerformed(ActionEvent exit) {
                int answer = JOptionPane.showConfirmDialog(null, "Do you want to exit?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (answer == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        }
        final JMenu menu = new JMenu("Menu");
        JMenuItem exit = new JMenuItem("exit", 'E');
        exit.addActionListener(new ExitListener());
        menu.add(exit);
        final JMenuBar bar = new JMenuBar();
        bar.add(menu);
        bar.add(Box.createHorizontalGlue());
        this.setJMenuBar(bar);
        this.setVisible(true);
    }

}