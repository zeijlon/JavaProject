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

    private static final int MENU_TAB_WIDTH = 150;
    private static final int MENU_TAB_HEIGHT = 25;

    private static final int MAP_TAB_WIDTH = 70;
    private static final int MAP_TAB_HEIGHT = 30;

    private static final int FIRST_COLUMN_X = 360;
    private static final int FIRST_COLUMN_Y = 165;

    private static final int SECOND_COLUMN_X = 550;
    private static final int SECOND_COLUMN_Y = 195;

    private static final int MENU_GRID_X = 650;
    private static final int MENU_GRID_Y = 200;
    private static final int DIFFICULTY_Y = 350;
    private static final int MENU_GRID_TILE_SIZE = 12;





    public Menu() {
        ifMenu = true;
        gameOn = false;
        gameRunning = true;
        levelSelect = false;
        ifGamePaused = false;
        mapSelected = 1;
        options = false;
        ifLost = false;
	mainTheme = new Sound("sounds/song.aiff");
        this.grid = new GRID(mapSelected);
        this.shop = new Shop(grid);
        this.spawner = new EnemySpawner(grid, shop);
        this.bulletHandler = new BulletHandler(grid, spawner);
        this.towerHandler = new TowerHandler(grid, shop, spawner, bulletHandler);
        this.keyHandler = new KeyHandler(grid, shop, spawner, towerHandler, this, bulletHandler);
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
    private Image audio = (Toolkit.getDefaultToolkit().getImage("images/gameaudiooff.png"));
    private Image music = (Toolkit.getDefaultToolkit().getImage("images/gamemusicoff.png"));


    private Rectangle audioRect = new Rectangle(SECOND_COLUMN_X, SECOND_COLUMN_Y, MENU_TAB_WIDTH, MENU_TAB_HEIGHT);
    private Rectangle musicRect = new Rectangle(SECOND_COLUMN_X, SECOND_COLUMN_Y + MENU_TAB_HEIGHT, MENU_TAB_WIDTH, MENU_TAB_HEIGHT);


    private Rectangle resumeGameButton = new Rectangle();
    private Rectangle newGameButton = new Rectangle(FIRST_COLUMN_X, FIRST_COLUMN_Y+MAP_TAB_HEIGHT, MENU_TAB_WIDTH, MENU_TAB_HEIGHT);
    private Rectangle selectLevel = new Rectangle(FIRST_COLUMN_X, FIRST_COLUMN_Y + MENU_TAB_HEIGHT*2, MENU_TAB_WIDTH, MENU_TAB_HEIGHT);
    private Rectangle optionsButton = new Rectangle(FIRST_COLUMN_X, FIRST_COLUMN_Y + MENU_TAB_HEIGHT*3, MENU_TAB_WIDTH, MENU_TAB_HEIGHT);
    //private Rectangle mapEditorButton = new Rectangle(FIRST_COLUMN_X, FIRST_COLUMN_Y + MENU_TAB_HEIGHT*4, MENU_TAB_WIDTH, MENU_TAB_HEIGHT);
    private Rectangle quitGameButton = new Rectangle(FIRST_COLUMN_X, FIRST_COLUMN_Y + MENU_TAB_HEIGHT*4, MENU_TAB_WIDTH, MENU_TAB_HEIGHT);

    private Rectangle rectMap1 = new Rectangle(SECOND_COLUMN_X-5, SECOND_COLUMN_Y, MAP_TAB_WIDTH, MAP_TAB_HEIGHT);
    private Rectangle rectMap2 = new Rectangle(SECOND_COLUMN_X-5, SECOND_COLUMN_Y+MAP_TAB_HEIGHT, MAP_TAB_WIDTH, MAP_TAB_HEIGHT);
    private Rectangle rectMap3 = new Rectangle(SECOND_COLUMN_X-5, SECOND_COLUMN_Y + MAP_TAB_HEIGHT*2, MAP_TAB_WIDTH, MAP_TAB_HEIGHT);
    private Rectangle rectMap4 = new Rectangle(SECOND_COLUMN_X-5, SECOND_COLUMN_Y+MAP_TAB_HEIGHT*3, MAP_TAB_WIDTH, MAP_TAB_HEIGHT);


    public void menuEdit(Point p) {
            if (newGameButton.contains(p)) {
                newGame();
            } else if (resumeGameButton.contains(p)) {
                resumeGame();
            } else if (quitGameButton.contains(p)) {
                quitGame();
            } else if (selectLevel.contains(p)) {
                levelSelect = true;
                options = false;
            } else if (optionsButton.contains(p)) {
                options = true;
                levelSelect = false;
            } else if (levelSelect) {
                chooseMap(p);
            } else if (options) {
                options(p);
            }else{
                levelSelect = false;
                options = false;
            }
        }


    public void newGame() {
        if (!Sound.isNoMusic()) {
            if (!Sound.getClipPlaying()) {
                mainTheme.play();
                mainTheme.loop();
                Sound.setClipPlaying(true);
            }
        }
        grid.setMapSize(mapSelected);
        shop.setHealth(100);
        shop.setGold(15);
        spawner.setEnemies(new ArrayList<>());
        spawner.setLevel();
        spawner.setArmoredEnemyCount(1);
        spawner.setSpyEnemyCount(1);
        towerHandler.setTowers(new ArrayList<>());
        bulletHandler.setBullets(new ArrayList<>());
        levelSelect = false;
        options = false;

                ifMenu = false;
                gameOn = true;
                ifLost = false;
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
        }
    }

    public void chooseMap(Point p) {
        if (rectMap1.contains(p)) {
            mapSelected = 1;
        } else if (rectMap2.contains(p)) {
            mapSelected = 2;
        } else if (rectMap3.contains(p)) {
            mapSelected = 3;
        } else if (rectMap4.contains(p)) {
            mapSelected = 4;
        } else {
            levelSelect = false;
        }
    }

    public void options(Point p) {
        if (audioRect.contains(p)) {
            if (Sound.isNoGameAudio()) {
		Sound.setNoGameAudio(false);
            } else if (!Sound.isNoGameAudio()) {
                Sound.setNoGameAudio(true);
            }
        } else if (musicRect.contains(p)) {
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
        resumeGameButton = new Rectangle(FIRST_COLUMN_X, FIRST_COLUMN_Y, MENU_TAB_WIDTH, MENU_TAB_HEIGHT);
    }

    public void draw(Graphics g2d) {
        g2d.drawImage(menuImage, 0, 0, null);
        g2d.drawImage(newGame, FIRST_COLUMN_X, FIRST_COLUMN_Y+MENU_TAB_HEIGHT, null);
        g2d.drawImage(levelSelectImage, FIRST_COLUMN_X, FIRST_COLUMN_Y+MENU_TAB_HEIGHT*2, null);
        g2d.drawImage(optionsImage, FIRST_COLUMN_X, FIRST_COLUMN_Y+MENU_TAB_HEIGHT*3, null);
        g2d.drawImage(quit, FIRST_COLUMN_X, FIRST_COLUMN_Y+MENU_TAB_HEIGHT*4, null);

        /*g2d.drawRect(SECOND_COLUMN_X-5, SECOND_COLUMN_Y, MAP_TAB_WIDTH, MAP_TAB_HEIGHT);
        g2d.drawRect(SECOND_COLUMN_X-5, SECOND_COLUMN_Y+MAP_TAB_HEIGHT, MAP_TAB_WIDTH, MAP_TAB_HEIGHT);
        g2d.drawRect(SECOND_COLUMN_X-5, SECOND_COLUMN_Y+MAP_TAB_HEIGHT*2, MAP_TAB_WIDTH, MAP_TAB_HEIGHT);
        g2d.drawRect(SECOND_COLUMN_X-5, SECOND_COLUMN_Y+MAP_TAB_HEIGHT*3, MAP_TAB_WIDTH, MAP_TAB_HEIGHT);
*/


        if (levelSelect) {
            g2d.drawImage(map1, SECOND_COLUMN_X, SECOND_COLUMN_Y, null);
            g2d.drawImage(map2, SECOND_COLUMN_X, SECOND_COLUMN_Y+MAP_TAB_HEIGHT, null);
            g2d.drawImage(map3, SECOND_COLUMN_X, SECOND_COLUMN_Y+MAP_TAB_HEIGHT*2, null);
            g2d.drawImage(map4, SECOND_COLUMN_X, SECOND_COLUMN_Y+MAP_TAB_HEIGHT*3, null);
            drawGrid(g2d, Maps.getMap(mapSelected));
            g2d.setFont(new Font("courier new", Font.BOLD, 24));
            g2d.setColor(Color.black);
            switch(mapSelected){
                case 1:
                g2d.drawString("VERY EASY", MENU_GRID_X, DIFFICULTY_Y);
                g2d.drawRect(SECOND_COLUMN_X -5, SECOND_COLUMN_Y, MAP_TAB_WIDTH, MAP_TAB_HEIGHT);
                    break;
                case 2:
                g2d.drawString("EASY", MENU_GRID_X, DIFFICULTY_Y);
                g2d.drawRect(SECOND_COLUMN_X -5, SECOND_COLUMN_Y+MAP_TAB_HEIGHT, MAP_TAB_WIDTH, MAP_TAB_HEIGHT);
                    break;
                case 3:
                g2d.drawString("MEDIUM", MENU_GRID_X, DIFFICULTY_Y);
                g2d.drawRect(SECOND_COLUMN_X -5, SECOND_COLUMN_Y+MAP_TAB_HEIGHT*2, MAP_TAB_WIDTH, MAP_TAB_HEIGHT);
                    break;
                case 4:
                g2d.drawString("VERY HARD", MENU_GRID_X, DIFFICULTY_Y);
                g2d.drawRect(SECOND_COLUMN_X -5, SECOND_COLUMN_Y+MAP_TAB_HEIGHT*3, MAP_TAB_WIDTH, MAP_TAB_HEIGHT);
                    break;
            }
        }
        else if (options) {
            g2d.drawImage(audio, SECOND_COLUMN_X, SECOND_COLUMN_Y, null);
            g2d.drawImage(music, SECOND_COLUMN_X, SECOND_COLUMN_Y+MENU_TAB_HEIGHT, null);
            if (Sound.isNoGameAudio()) {
                g2d.drawRect(SECOND_COLUMN_X, SECOND_COLUMN_Y, MENU_TAB_WIDTH, MAP_TAB_HEIGHT);
            }
            if (Sound.isNoMusic()) {
                g2d.drawRect(SECOND_COLUMN_X, SECOND_COLUMN_Y+MENU_TAB_HEIGHT, MENU_TAB_WIDTH, MAP_TAB_HEIGHT);

            }
        }
        if (ifGamePaused) {
            if(!ifLost){
            g2d.drawImage(resumeGame, FIRST_COLUMN_X -10, FIRST_COLUMN_Y +10, null);
        }}

    }

    private void drawGrid(Graphics g2d, int[][] squares) {
        for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares[i].length; j++) {
                ImageIcon squareType = new ImageIcon(grid.checkSquareType(squares[i][j]).getScaledInstance(MENU_GRID_TILE_SIZE, MENU_GRID_TILE_SIZE, Image.SCALE_DEFAULT));
                g2d.drawImage(squareType.getImage(), MENU_GRID_X + j * MENU_GRID_TILE_SIZE, MENU_GRID_Y + i * MENU_GRID_TILE_SIZE, null);
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