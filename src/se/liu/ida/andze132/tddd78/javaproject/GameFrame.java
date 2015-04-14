package se.liu.ida.andze132.tddd78.javaproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame extends JFrame {
    public static Point motionPoint = new Point();
    public static Point clickPoint = new Point();

    public GameFrame(GRID grid, Shop shop, EnemySpawner spawner, TowerHandler towerHandler, BulletHandler bulletHandler) throws HeadlessException {
        super("DAWN OF THE POLAR BEARS");
        GameComponent gameComponent = new GameComponent(grid, shop, spawner, towerHandler, bulletHandler);
        this.setLayout(new BorderLayout());
        this.add(gameComponent, BorderLayout.CENTER);
        this.getContentPane().setBackground(Color.LIGHT_GRAY);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.createMenu();
        this.pack();
        this.setVisible(true);

        this.addMouseListener(new KeyHandler());
        this.addMouseMotionListener(new KeyHandler());

    }

    private void createMenu() {
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
        final JMenuBar menuBar = new JMenuBar();
        menuBar.add(menu);
        menuBar.add(Box.createHorizontalGlue());
        this.setJMenuBar(menuBar);
        this.setVisible(true);
    }

}