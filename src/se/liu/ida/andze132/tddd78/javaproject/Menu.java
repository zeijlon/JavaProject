package se.liu.ida.andze132.tddd78.javaproject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


/**
 * Created by Administrat�r on 2015-04-13.
 */
public class Menu
{
    private KeyHandler keyHandler;
    private GRID grid;
    private Shop shop;
    private EnemySpawner spawner;
    private TowerHandler towerHandler;
    private BulletHandler bulletHandler;
    private JFrame frame;
    private GameLoop gameloop;
    private boolean ifMenu;
    private boolean gameOn;
    private boolean gameRunning;
    private boolean drawlvlslct;
    private boolean drawoptions;
    private Maps map;
    private boolean ifGamePaused;
    private int mapSelected;
    private boolean ifNoBearSound;
    public static boolean ifMainSound;



    public Menu() {
        ifMenu = true;
        gameOn = false;
        gameRunning = true;
        drawlvlslct = false;
        ifGamePaused = false;
        ifNoBearSound = false;
        ifMainSound = false;
        mapSelected = 1;
	drawoptions = false;
        this.keyHandler = new KeyHandler();
        this.grid =  new GRID(mapSelected);
        this.shop = new Shop(grid, keyHandler);
        this.spawner = new EnemySpawner(grid, shop, keyHandler);
        this.bulletHandler = new BulletHandler(grid, spawner);
        this.towerHandler = new TowerHandler(grid, shop, spawner, bulletHandler, keyHandler);
        this.frame = new GameFrame(grid, shop, spawner, towerHandler, bulletHandler, this, keyHandler);
        this.gameloop = new GameLoop(shop,frame,spawner,towerHandler,bulletHandler,this, keyHandler);
    }

    private Image menuImage = (Toolkit.getDefaultToolkit().getImage("images/DawnofthePolarBears.png"));
    private Image resumeGame = (Toolkit.getDefaultToolkit().getImage("images/ResumeGame.png"));
    private Image newGame = (Toolkit.getDefaultToolkit().getImage("images/NewGameT.png"));
    private Image quit = (Toolkit.getDefaultToolkit().getImage("images/QuitGame.png"));
    private Image levelSelect = (Toolkit.getDefaultToolkit().getImage("images/LevelSelect.png"));
    private Image map1 = (Toolkit.getDefaultToolkit().getImage("images/Map1.png"));
    private Image map2 = (Toolkit.getDefaultToolkit().getImage("images/Map2.png"));
    private Image map3 = (Toolkit.getDefaultToolkit().getImage("images/Map3.png"));
    private Image options = (Toolkit.getDefaultToolkit().getImage("images/Options.png"));
    private Image gameAudioOff = (Toolkit.getDefaultToolkit().getImage("images/gameaudiooff.png"));
    private Image gameMusicOff = (Toolkit.getDefaultToolkit().getImage("images/gamemusicoff.png"));



    private Rectangle resumeGameButton = new Rectangle(400, 175, 150, 27);
    private Rectangle newGameButton = new Rectangle(400, 200, 150, 27);
    private Rectangle selectLevel = new Rectangle(400, 225, 150, 27);
    private Rectangle optionsButton = new Rectangle(400, 250, 150, 27);
    private Rectangle quitGameButton = new Rectangle(400, 275, 129, 25);
    private Rectangle recmap1 = new Rectangle(595, 200, 70, 35);
    private Rectangle recmap2 = new Rectangle(595, 230, 70, 35);
    private Rectangle recmap3 = new Rectangle(595, 260, 70, 35);
    private Rectangle gameAudiooff = new Rectangle(595, 230, 150, 30);
    private Rectangle gameMusicoff = new Rectangle(595, 260, 150, 30);


    public void ifMenuedit() {
        if(keyHandler.getClickPoint() != null){
        if(newGameButton.contains(keyHandler.getClickPoint())){
            ifMenu = false;
            gameOn = true;
            Sound.playMainTheme();
            ifMainSound = true;



            grid.setMapSize(mapSelected);

            shop.setHealth(1);
            shop.setGold(10);
            spawner.setEnemies(new ArrayList<>());
            spawner.setLevel(0);
            spawner.setEnemyCount(1);
            spawner.setBasicEnemyCount(1);
            spawner.setArmoredEnemyCount(1);

            towerHandler.setTowers(new ArrayList<>());
            bulletHandler.setBullets(new ArrayList<>());
            drawlvlslct = false;
	    drawoptions = false;

        }
        else if (resumeGameButton.contains(keyHandler.getClickPoint())) {
            ifMenu = false;
            gameOn = true;
            ifGamePaused = false;
            drawlvlslct = false;
	    drawoptions = false;


        }

        else if (quitGameButton.contains(keyHandler.getClickPoint())) {
            drawlvlslct = false;
	    drawoptions = false;

            int answer = JOptionPane
                    .showConfirmDialog(null, "Are you sure you want to quit? ", "Confirm", JOptionPane.YES_NO_OPTION);
            if (answer == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
            else{
                keyHandler.setClickPoint(null);
            }
        }

        else if (selectLevel.contains(keyHandler.getClickPoint())) {
            drawlvlslct = true;
	    drawoptions = false;

        }

        else if (optionsButton.contains(keyHandler.getClickPoint())) {
            drawlvlslct = false;
	    drawoptions = true;
        }
	else if (drawoptions && gameAudiooff.contains(keyHandler.getClickPoint())) {
	    if(Sound.noGameAudio = true){Sound.noGameAudio = false;}
	    if(Sound.noGameAudio = false){Sound.noGameAudio = true;}
	        }
	else if (drawoptions && gameMusicoff.contains(keyHandler.getClickPoint())) {
	    if(Sound.noMusic = true){Sound.noMusic = false;}
	    if(Sound.noMusic = false){Sound.noMusic = true;}
	        }


        else if (drawlvlslct && recmap1.contains(keyHandler.getClickPoint())) {
            mapSelected = 1;
        }

        else if (drawlvlslct && recmap2.contains(keyHandler.getClickPoint())) {
            mapSelected = 2;
        }

        else if (drawlvlslct && recmap3.contains(keyHandler.getClickPoint())) {
            mapSelected = 4;
        }

            else{drawlvlslct = false;
	    drawoptions = false;

        }
    }}

    public void escape() {
        ifMenu = true;
        gameOn = false;
        ifGamePaused = true;
        frame.pack();
    }

    public void draw(Graphics g2d) {

        g2d.drawImage(menuImage, 0, 0, null);
        g2d.drawImage(newGame, 400, 200, null);
        g2d.drawImage(levelSelect, 400, 225, null);
        g2d.drawImage(options, 400, 250, null);
        g2d.drawImage(quit, 400, 275, null);
        if (drawlvlslct) {
            g2d.drawImage(map1, 600, 200, null);
            g2d.drawImage(map2, 600, 230, null);
            g2d.drawImage(map3, 600, 260, null);
            if (mapSelected == 1) {
                g2d.drawRect(595, 195, 70, 35);
            } else if (mapSelected == 2) {
                g2d.drawRect(595, 225, 70, 35);
            } else if (mapSelected == 4) {
                g2d.drawRect(595, 255, 70, 35);
	    }}
	if(drawoptions){
	    g2d.drawImage(gameAudioOff,595, 230,null);
	    g2d.drawImage(gameMusicOff,595, 260,null);
	    if(Sound.noGameAudio){
		g2d.drawRect(595, 230,150,30);
	    }
	     if(Sound.noMusic){
		g2d.drawRect(595, 260,150,30);

	    }
		}
        if(ifGamePaused){
            g2d.drawImage(resumeGame, 400,175,null);
        }

    }

    public boolean isIfMenu() {
        return ifMenu;
    }

    public void setIfMenu(final boolean ifMenu) {
        this.ifMenu = ifMenu;
    }

    public Image getnewGame() {
        return newGame;
    }

    public Image getMenuImage() {
        return menuImage;
    }

    public boolean isGameOn() {
        return gameOn;
    }

    public boolean isGameRunning() {
        return gameRunning;
    }

    public void setGrid(final GRID grid) {
        this.grid = grid;
    }

    public void setGameOn(final boolean gameOn) {
        this.gameOn = gameOn;
    }

}