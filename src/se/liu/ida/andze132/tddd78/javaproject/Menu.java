package se.liu.ida.andze132.tddd78.javaproject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


/**
 * Created by Administrat�r on 2015-04-13.
 */
public class Menu
{
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
    private Maps map;
    private boolean ifGamePaused;
    private int mapSelected;



    public Menu() {
        ifMenu = true;
        gameOn = false;
        gameRunning = true;
        drawlvlslct = false;
        ifGamePaused = false;
        mapSelected = 1;
        this.grid =  new GRID(mapSelected);
        this.shop = new Shop(grid);
        this.spawner = new EnemySpawner(grid, shop);
        this.bulletHandler = new BulletHandler(grid, spawner);
        this.towerHandler = new TowerHandler(grid, shop, spawner, bulletHandler);
        this.frame = new GameFrame(grid, shop, spawner, towerHandler, bulletHandler, this);
        this.gameloop = new GameLoop(shop,frame,spawner,towerHandler,bulletHandler,this);
    }

    private Image menuImage = (Toolkit.getDefaultToolkit().getImage("images/DawnofthePolarBears.png"));
    private Image resumeGame = (Toolkit.getDefaultToolkit().getImage("images/ResumeGame.png"));
    private Image newGame = (Toolkit.getDefaultToolkit().getImage("images/NewGameT.png"));
    private Image quit = (Toolkit.getDefaultToolkit().getImage("images/QuitGame.png"));
    private Image levelSelect = (Toolkit.getDefaultToolkit().getImage("images/LevelSelect.png"));
    private Image options = (Toolkit.getDefaultToolkit().getImage("images/Options.png"));
    private Image map1 = (Toolkit.getDefaultToolkit().getImage("images/map1.png"));
    private Image map2 = (Toolkit.getDefaultToolkit().getImage("images/map2.png"));
    private Image map3 = (Toolkit.getDefaultToolkit().getImage("images/map3.png"));



    private Rectangle resumeGameButton = new Rectangle(400, 175, 150, 27);
    private Rectangle newGameButton = new Rectangle(400, 200, 150, 27);
    private Rectangle selectLevel = new Rectangle(400, 225, 150, 27);
    private Rectangle optionsButton = new Rectangle(400, 250, 150, 27);
    private Rectangle quitGameButton = new Rectangle(400, 275, 129, 25);
    private Rectangle recmap1 = new Rectangle(595, 200, 70, 35);
    private Rectangle recmap2 = new Rectangle(595, 230, 70, 35);
    private Rectangle recmap3 = new Rectangle(595, 260, 70, 35);


    public void ifMenuedit() {
        if(GameFrame.clickPoint != null){
        if(newGameButton.contains(GameFrame.clickPoint)){
            ifMenu = false;
            gameOn = true;


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
        }
        else if (resumeGameButton.contains(GameFrame.clickPoint)) {
            ifMenu = false;
            gameOn = true;
            ifGamePaused = false;
            drawlvlslct = false;

        }

        else if (quitGameButton.contains(GameFrame.clickPoint)) {
            drawlvlslct = false;
            int answer = JOptionPane
                    .showConfirmDialog(null, "Are you sure you want to quit? ", "Confirm", JOptionPane.YES_NO_OPTION);
            if (answer == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
            else{
                GameFrame.clickPoint = null;
            }
        }

        else if (selectLevel.contains(GameFrame.clickPoint)) {
            drawlvlslct = true;
        }

        else if (optionsButton.contains(GameFrame.clickPoint)) {
            drawlvlslct = false;

        }

        else if (drawlvlslct && recmap1.contains(GameFrame.clickPoint)) {
            mapSelected = 1;
        }

        else if (drawlvlslct && recmap2.contains(GameFrame.clickPoint)) {
            mapSelected = 2;
        }

        else if (drawlvlslct && recmap3.contains(GameFrame.clickPoint)) {
            mapSelected = 4;
        }

            else{            drawlvlslct = false;
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