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
    private Sound mainTheme = null;
    private boolean ifMenu;
    private boolean gameOn;
    private boolean gameRunning;
    private boolean levelSelect;
    private boolean options;
    private boolean ifGamePaused;
    private boolean ifLost;
    private int mapSelected;
    private static final int MENUPICWIDTH = 150;
    private static final int MENUPICWIDTHTWO = 70;
    private static final int MENUPICALTWIDTH = 129;
    private static final int MENUPICHEIGHT = 25;
    private static final int MENUPICHEIGHTTWO = 30;
    private static final int RESUMECOORDHIGHT = 165;
    private static final int OPTIONSALTCOORDWIDTH = 595;
    private static final int FIRSTMAPCOORDHEIGHT = 195;
    private static final int MAPCOORDWIDTH = 545;
    private static final int FIRSTCOLUMNCOORDWIDTH = 360;
    private static final int FIRSTCOLUMNCOORDHEIGHTONE = 200;
    private static final int ALTCORDHEIGHT = 225;
    private static final int FIRSTCOLUMNCOORDHEIGHTTWO = 230;
    private static final int FIRSTCOLUMNCOORDHEIGHTTHREE = 255;
    private static final int FIRSTCOLUMNCOORDHEIGHTFOUR = 280;
    private static final int MAPFOURCOORDHEIGHT = 285;
    private static final int MENUSTRINGWIDTH = 650;
    private static final int MENUSTRINGHEIGHT = 350;
    private static final int MAGICGRIDMULTIPLYER = 12;
    private static final int MAGICGRIDWIDTH = 200;




    public Menu() {
        ifMenu = true;
        gameOn = false;
        gameRunning = true;
        levelSelect = false;
        ifGamePaused = false;
        mapSelected = 1;
        options = false;
        ifLost = false;
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
    private Image levelSelectImage = (Toolkit.getDefaultToolkit().getImage("images/LevelSelect.png"));
    private Image map1 = (Toolkit.getDefaultToolkit().getImage("images/Map1.png"));
    private Image map2 = (Toolkit.getDefaultToolkit().getImage("images/Map2.png"));
    private Image map3 = (Toolkit.getDefaultToolkit().getImage("images/Map3.png"));
    private Image map4 = (Toolkit.getDefaultToolkit().getImage("images/Map4.png"));
    private Image optionsImage = (Toolkit.getDefaultToolkit().getImage("images/Options.png"));
    private Image gameAudioOff = (Toolkit.getDefaultToolkit().getImage("images/gameaudiooff.png"));
    private Image gameMusicOff = (Toolkit.getDefaultToolkit().getImage("images/gamemusicoff.png"));


    private Rectangle gameAudiooff = new Rectangle(OPTIONSALTCOORDWIDTH, ALTCORDHEIGHT, MENUPICWIDTH, MENUPICHEIGHT);
    private Rectangle gameMusicoff = new Rectangle(OPTIONSALTCOORDWIDTH, FIRSTCOLUMNCOORDHEIGHTTHREE + 5, MENUPICWIDTH, MENUPICHEIGHT);

    private Rectangle resumeGameButton = new Rectangle();
    private Rectangle newGameButton = new Rectangle(FIRSTCOLUMNCOORDWIDTH, FIRSTCOLUMNCOORDHEIGHTONE, MENUPICWIDTH, MENUPICHEIGHT);
    private Rectangle selectLevel = new Rectangle(FIRSTCOLUMNCOORDWIDTH, FIRSTCOLUMNCOORDHEIGHTTWO, MENUPICWIDTH, MENUPICHEIGHT);
    private Rectangle optionsButton = new Rectangle(FIRSTCOLUMNCOORDWIDTH, FIRSTCOLUMNCOORDHEIGHTTHREE, MENUPICWIDTH, MENUPICHEIGHT);
    private Rectangle quitGameButton = new Rectangle(FIRSTCOLUMNCOORDWIDTH, FIRSTCOLUMNCOORDHEIGHTFOUR, MENUPICALTWIDTH, MENUPICHEIGHT);

    private Rectangle recmap1 = new Rectangle(MAPCOORDWIDTH, FIRSTMAPCOORDHEIGHT, MENUPICWIDTHTWO, MENUPICHEIGHTTWO);
    private Rectangle recmap2 = new Rectangle(MAPCOORDWIDTH, ALTCORDHEIGHT, MENUPICWIDTHTWO, MENUPICHEIGHTTWO);
    private Rectangle recmap3 = new Rectangle(MAPCOORDWIDTH, FIRSTCOLUMNCOORDHEIGHTTHREE, MENUPICWIDTHTWO, MENUPICHEIGHTTWO);
    private Rectangle recmap4 = new Rectangle(MAPCOORDWIDTH, MAPFOURCOORDHEIGHT, MENUPICWIDTHTWO, MENUPICHEIGHTTWO);

    public void menuEdit() {
        if (keyHandler.getClickPoint() != null) {
            if (newGameButton.contains(keyHandler.getClickPoint())) {
                newGame();
            } else if (resumeGameButton.contains(keyHandler.getClickPoint())) {
                resumeGame();
            } else if (quitGameButton.contains(keyHandler.getClickPoint())) {
                quitGame();
            } else if (selectLevel.contains(keyHandler.getClickPoint())) {
                levelSelect = true;
                options = false;
            } else if (optionsButton.contains(keyHandler.getClickPoint())) {
                options = true;
                levelSelect = false;
            } else if (levelSelect) {
                chooseMap();
            } else if (options) {
                options();
            } else {
                levelSelect = false;
                options = false;
            }
        }
        keyHandler.setClickPoint();
    }

    public void newGame() {
        ifMenu = false;
        gameOn = true;
        ifLost = false;
        mainTheme = new Sound("sounds/song.aiff");

        if (!Sound.isNoMusic()) {
            if (!Sound.getClipPlaying()) {
                mainTheme.play();
                mainTheme.loop();
                Sound.setClipPlaying(true);
            }
        }

        grid.setMapSize(mapSelected);
        shop.setHealth(1);
        shop.setGold(10);
        spawner.setEnemies(new ArrayList<>());
        spawner.setLevel();
        spawner.setEnemyCount(1);
        spawner.setArmoredEnemyCount(1);
        towerHandler.setTowers(new ArrayList<>());
        bulletHandler.setBullets(new ArrayList<>());
        levelSelect = false;
        options = false;
    }

    public void resumeGame() {
        ifMenu = false;
        gameOn = true;
        ifGamePaused = false;
        levelSelect = false;
        options = false;
        
    }

    public void quitGame() {
        levelSelect = false;
        options = false;

        int answer = JOptionPane
                .showConfirmDialog(null, "Are you sure you want to quit? ", "Confirm", JOptionPane.YES_NO_OPTION);
        if (answer == JOptionPane.YES_OPTION) {
            System.exit(0);
        } else {
            keyHandler.setClickPoint();
        }
    }

    public void chooseMap() {
        if (recmap1.contains(keyHandler.getClickPoint())) {
            mapSelected = 1;
        } else if (recmap2.contains(keyHandler.getClickPoint())) {
            mapSelected = 2;
        } else if (recmap3.contains(keyHandler.getClickPoint())) {
            mapSelected = 3;
        } else if (recmap4.contains(keyHandler.getClickPoint())) {
            mapSelected = 4;
        } else {
            levelSelect = false;
        }
    }

    public void options() {
        if (gameAudiooff.contains(keyHandler.getClickPoint())) {
            if (Sound.isNoGameAudio()) {
                Sound.setNoGameAudio(false);
            } else if (!Sound.isNoGameAudio()) {
                Sound.setNoGameAudio(true);
            }
        } else if (gameMusicoff.contains(keyHandler.getClickPoint())) {
            if (Sound.isNoMusic()) {
                if(!Sound.getClipPlaying()){
                    mainTheme.play();
                    mainTheme.loop();
                    Sound.setClipPlaying(true);
                }
                Sound.setNoMusic(false);
            } else if (!Sound.isNoMusic()) {
                mainTheme.stop();
                Sound.setClipPlaying(false);
                Sound.setNoMusic(true);
            }
        } else {
            options = false;
        }

    }

    public void escape() {
        ifMenu = true;
        gameOn = false;
        ifGamePaused = true;
        frame.pack();
        resumeGameButton = new Rectangle(FIRSTCOLUMNCOORDWIDTH, RESUMECOORDHIGHT, MENUPICWIDTH, MENUPICHEIGHTTWO);
    }

    public void draw(Graphics g2d) {
        g2d.drawImage(menuImage, 0, 0, null);
        g2d.drawImage(newGame, FIRSTCOLUMNCOORDWIDTH, FIRSTCOLUMNCOORDHEIGHTONE, null);
        g2d.drawImage(levelSelectImage, FIRSTCOLUMNCOORDWIDTH, ALTCORDHEIGHT, null);
        g2d.drawImage(optionsImage, FIRSTCOLUMNCOORDWIDTH, FIRSTCOLUMNCOORDHEIGHTTHREE - 5, null);
        g2d.drawImage(quit, FIRSTCOLUMNCOORDWIDTH, FIRSTCOLUMNCOORDHEIGHTFOUR - 5, null);

        if (levelSelect) {
            g2d.drawImage(map1, MAPCOORDWIDTH + 5, FIRSTCOLUMNCOORDHEIGHTONE, null);
            g2d.drawImage(map2, MAPCOORDWIDTH + 5, FIRSTCOLUMNCOORDHEIGHTTWO, null);
            g2d.drawImage(map3, MAPCOORDWIDTH + 5, FIRSTCOLUMNCOORDHEIGHTTHREE + 5, null);
            g2d.drawImage(map4, MAPCOORDWIDTH + 5, FIRSTCOLUMNCOORDHEIGHTFOUR + 10, null);
            drawGrid(g2d, Maps.getMap(mapSelected));
            g2d.setFont(new Font("courier new", Font.BOLD, MENUPICHEIGHTTWO-2));
            g2d.setColor(Color.black);
            if (mapSelected == 1) {
                g2d.drawString("VERY EASY", MENUSTRINGWIDTH, MENUSTRINGHEIGHT);
                g2d.drawRect(MAPCOORDWIDTH-10, FIRSTMAPCOORDHEIGHT, MENUPICWIDTHTWO, MENUPICHEIGHTTWO+5);
            } else if (mapSelected == 2) {
                g2d.drawString("EASY", MENUSTRINGWIDTH, MENUSTRINGHEIGHT);
                g2d.drawRect(MAPCOORDWIDTH-10, FIRSTCOLUMNCOORDHEIGHTTWO, MENUPICWIDTHTWO, MENUPICHEIGHTTWO+5);
            } else if (mapSelected == 3) {
                g2d.drawString("MEDIUM", MENUSTRINGWIDTH, MENUSTRINGHEIGHT);
                g2d.drawRect(MAPCOORDWIDTH-10, ALTCORDHEIGHT, MENUPICWIDTHTWO, MENUPICHEIGHTTWO+5);
            } else if (mapSelected == 4) {
                g2d.drawString("VERY HARD", MENUSTRINGWIDTH, MENUSTRINGHEIGHT);
                g2d.drawRect(MAPCOORDWIDTH-10, FIRSTCOLUMNCOORDHEIGHTFOUR+5, MENUPICWIDTHTWO, MENUPICHEIGHTTWO+5);
            }
        }
        if (options) {
            g2d.drawImage(gameAudioOff, OPTIONSALTCOORDWIDTH, FIRSTCOLUMNCOORDHEIGHTTWO, null);
            g2d.drawImage(gameMusicOff, OPTIONSALTCOORDWIDTH, FIRSTCOLUMNCOORDHEIGHTTHREE + 5, null);
            if (Sound.isNoGameAudio()) {
                g2d.drawRect(OPTIONSALTCOORDWIDTH, FIRSTCOLUMNCOORDHEIGHTTWO, MENUPICWIDTH, MENUPICHEIGHTTWO);
            }
            if (Sound.isNoMusic()) {
                g2d.drawRect(OPTIONSALTCOORDWIDTH, FIRSTCOLUMNCOORDHEIGHTTHREE + 5, MENUPICWIDTH, MENUPICHEIGHTTWO);

            }
        }
        if (ifGamePaused) {
            if(!ifLost){
            g2d.drawImage(resumeGame, FIRSTCOLUMNCOORDWIDTH-10, RESUMECOORDHIGHT+10, null);
        }
    }}

    private void drawGrid(Graphics g2d, int[][] squares) {
        for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares[i].length; j++) {
                ImageIcon squareType = new ImageIcon(grid.checkSquareType(squares[i][j]).getScaledInstance(MAGICGRIDMULTIPLYER, MAGICGRIDMULTIPLYER, Image.SCALE_DEFAULT));
                g2d.drawImage(squareType.getImage(), MENUSTRINGWIDTH + j * MAGICGRIDMULTIPLYER, MAGICGRIDWIDTH + i * MAGICGRIDMULTIPLYER, null);
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

    public void setIfLost(final boolean ifLost) {
        this.ifLost = ifLost;
    }
}