package se.liu.ida.andze132.tddd78.javaproject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


/**
 * Created by Administrat�r on 2015-04-13.
 */
public class Menu {
    private KeyHandler keyHandler;
    private GRID grid;
    private Shop shop;
    private EnemySpawner spawner;
    private TowerHandler towerHandler;
    private BulletHandler bulletHandler;
    private JFrame frame;
    private boolean ifMenu;
    private boolean gameOn;
    private boolean gameRunning;
    private boolean drawlvlslct;
    private boolean drawoptions;
    private boolean ifGamePaused;
    private int mapSelected;


    public Menu() {
        ifMenu = true;
        gameOn = false;
        gameRunning = true;
        drawlvlslct = false;
        ifGamePaused = false;
        //boolean ifNoBearSound = false;
        mapSelected = 1;
        drawoptions = false;
        this.keyHandler = new KeyHandler();
        this.grid = new GRID(mapSelected);
        this.shop = new Shop(grid, keyHandler);
        this.spawner = new EnemySpawner(grid, shop, keyHandler);
        this.bulletHandler = new BulletHandler(grid, spawner);
        this.towerHandler = new TowerHandler(grid, shop, spawner, bulletHandler, keyHandler);
        this.frame = new GameFrame(grid, shop, spawner, towerHandler, bulletHandler, this, keyHandler);
        GameLoop gameloop = new GameLoop(shop, frame, spawner, towerHandler, bulletHandler, this, keyHandler);
    }

    private Image menuImage = (Toolkit.getDefaultToolkit().getImage("images/DawnofthePolarBears.png"));
    private Image resumeGame = (Toolkit.getDefaultToolkit().getImage("images/ResumeGame.png"));
    private Image newGame = (Toolkit.getDefaultToolkit().getImage("images/NewGameT.png"));
    private Image quit = (Toolkit.getDefaultToolkit().getImage("images/QuitGame.png"));
    private Image levelSelect = (Toolkit.getDefaultToolkit().getImage("images/LevelSelect.png"));
    private Image map1 = (Toolkit.getDefaultToolkit().getImage("images/Map1.png"));
    private Image map2 = (Toolkit.getDefaultToolkit().getImage("images/Map2.png"));
    private Image map3 = (Toolkit.getDefaultToolkit().getImage("images/Map3.png"));
    private Image map4 = (Toolkit.getDefaultToolkit().getImage("images/Map4.png"));
    private Image options = (Toolkit.getDefaultToolkit().getImage("images/Options.png"));
    private Image gameAudioOff = (Toolkit.getDefaultToolkit().getImage("images/gameaudiooff.png"));
    private Image gameMusicOff = (Toolkit.getDefaultToolkit().getImage("images/gamemusicoff.png"));


    private Rectangle gameAudiooff = new Rectangle(595, 225, 150, 30);
    private Rectangle gameMusicoff = new Rectangle(595, 255, 150, 30);

    private Rectangle resumeGameButton = new Rectangle();
    private Rectangle newGameButton = new Rectangle(360, 200, 150, 25);
    private Rectangle selectLevel = new Rectangle(360, 230, 150, 25);
    private Rectangle optionsButton = new Rectangle(360, 255, 150, 25);
    private Rectangle quitGameButton = new Rectangle(360, 280, 129, 25);

    private Rectangle recmap1 = new Rectangle(545, 195, 70, 30);
    private Rectangle recmap2 = new Rectangle(545, 225, 70, 30);
    private Rectangle recmap3 = new Rectangle(545, 255, 70, 30);
    private Rectangle recmap4 = new Rectangle(545, 285, 70, 30);




    public void ifMenuedit() {
        if (keyHandler.getClickPoint() != null) {
            if (newGameButton.contains(keyHandler.getClickPoint())) {
                ifMenu = false;
                gameOn = true;

                if(!Sound.isNoMusic()){
                Sound.playMainTheme();
                Sound.setIfMainSound(true);}


                grid.setMapSize(mapSelected);

                shop.setHealth(1);
                shop.setGold(10);
                spawner.setEnemies(new ArrayList<>());
                spawner.setLevel(0);
                spawner.setEnemyCount(1);
                spawner.setArmoredEnemyCount(1);

                towerHandler.setTowers(new ArrayList<>());
                bulletHandler.setBullets(new ArrayList<>());
                drawlvlslct = false;
                drawoptions = false;

            } else if (resumeGameButton.contains(keyHandler.getClickPoint())) {
                ifMenu = false;
                gameOn = true;
                ifGamePaused = false;
                drawlvlslct = false;
                drawoptions = false;


            } else if (quitGameButton.contains(keyHandler.getClickPoint())) {
                drawlvlslct = false;
                drawoptions = false;

                int answer = JOptionPane
                        .showConfirmDialog(null, "Are you sure you want to quit? ", "Confirm", JOptionPane.YES_NO_OPTION);
                if (answer == JOptionPane.YES_OPTION) {
                    System.exit(0);
                } else {
                    keyHandler.setClickPoint();
                }
            } else if (selectLevel.contains(keyHandler.getClickPoint())) {
                drawlvlslct = true;
                drawoptions = false;

            } else if (optionsButton.contains(keyHandler.getClickPoint())) {
                drawlvlslct = false;
                drawoptions = true;
            } else if (drawoptions && gameAudiooff.contains(keyHandler.getClickPoint())) {
                if (Sound.isNoGameAudio()) {
                    Sound.setNoGameAudio(false);
                }
                else if (!Sound.isNoGameAudio()) {
                    Sound.setNoGameAudio(true);
                }
            } else if (drawoptions && gameMusicoff.contains(keyHandler.getClickPoint())) {
                if (Sound.isNoMusic()) {
                    Sound.setNoMusic(false);
                }
                else if (!Sound.isNoMusic()) {
                    Sound.setNoMusic(true);
                }
            } else if (drawlvlslct && recmap1.contains(keyHandler.getClickPoint())) {
                mapSelected = 1;
            } else if (drawlvlslct && recmap2.contains(keyHandler.getClickPoint())) {
                mapSelected = 2;
            } else if (drawlvlslct && recmap3.contains(keyHandler.getClickPoint())) {
                mapSelected = 3;
            } else if (drawlvlslct && recmap4.contains(keyHandler.getClickPoint())) {
                mapSelected = 4;
            } else {
                drawlvlslct = false;
                drawoptions = false;

            }
        }
        keyHandler.setClickPoint();
    }

    public void escape() {
        ifMenu = true;
        gameOn = false;
        ifGamePaused = true;
        frame.pack();
        resumeGameButton = new Rectangle(360, 165, 150, 27);


    }

    public void draw(Graphics g2d) {
        g2d.drawImage(menuImage, 0, 0, null);
        g2d.drawImage(newGame, 360, 200, null);
        g2d.drawImage(levelSelect, 360, 225, null);
        g2d.drawImage(options, 360, 250, null);
        g2d.drawImage(quit, 360, 275, null);

        if (drawlvlslct) {
            g2d.drawImage(map1, 550, 200, null);
            g2d.drawImage(map2, 550, 230, null);
            g2d.drawImage(map3, 550, 260, null);
            g2d.drawImage(map4, 550, 290, null);
            drawGrid(g2d, Maps.getMap(mapSelected));
            g2d.setFont(new Font("courier new", Font.BOLD, 28));
            g2d.setColor(Color.black);
            if (mapSelected == 1) {
                g2d.drawString("VERY EASY", 650, 350);
                g2d.drawRect(545, 195, 70, 35);
            } else if (mapSelected == 2) {
                g2d.drawString("EASY", 650, 350);
                g2d.drawRect(545, 225, 70, 35);
            } else if (mapSelected == 3) {
                g2d.drawString("MEDIUM", 650, 350);
                g2d.drawRect(545, 255, 70, 35);
            } else if (mapSelected == 4) {
                g2d.drawString("VERY HARD", 650, 350);
                g2d.drawRect(545, 285, 70, 35);
            }
        }
        if (drawoptions) {
            g2d.drawImage(gameAudioOff, 595, 230, null);
            g2d.drawImage(gameMusicOff, 595, 260, null);
            if (Sound.isNoGameAudio()) {
                g2d.drawRect(595, 230, 150, 30);
            }
            if (Sound.isNoMusic()) {
                g2d.drawRect(595, 260, 150, 30);

            }
        }
        if (ifGamePaused) {
            g2d.drawImage(resumeGame, 350, 175, null);
        }
    }

    private void drawGrid(Graphics g2d, int[][] squares) {
        for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares[i].length; j++) {
                ImageIcon squareType = new ImageIcon(grid.checkSquareType(squares[i][j]).getScaledInstance(12, 12, Image.SCALE_DEFAULT));
                g2d.drawImage(squareType.getImage(), 650 + j * 12, 200 + i * 12, null);
            }
        }
    }

    public boolean isIfMenu() {
        return ifMenu;
    }

    public void setIfMenu(final boolean ifMenu) {
        this.ifMenu = ifMenu;
    }

    public boolean isGameOn() {
        return gameOn;
    }

    public boolean isGameRunning() {
        return gameRunning;
    }

    public void setGameOn(final boolean gameOn) {
        this.gameOn = gameOn;
    }

}