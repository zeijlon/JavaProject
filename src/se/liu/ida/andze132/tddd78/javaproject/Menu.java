package se.liu.ida.andze132.tddd78.javaproject;

import javax.swing.*;
import java.awt.*;



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
    private boolean ifMenu;
    private boolean gameOn;
    private boolean gameRunning;

    public Menu(GRID grid, Shop shop, EnemySpawner spawner,TowerHandler towerHandler, BulletHandler bulletHandler) {
        ifMenu = true;
        gameOn = false;
        gameRunning = true;
    }

    private  Image menuImage = (Toolkit.getDefaultToolkit().getImage("images/DawnofthePolarBears.png"));
    private  Image newGame = (Toolkit.getDefaultToolkit().getImage("images/NewGameT.png"));
    private  Image quit = (Toolkit.getDefaultToolkit().getImage("images/QuitGame.png"));
    private  Image levelSelect = (Toolkit.getDefaultToolkit().getImage("images/LevelSelect.png"));
    private  Image options = (Toolkit.getDefaultToolkit().getImage("images/Options.png"));

    private Rectangle newGameButton = new Rectangle(400, 200 ,150,27);
    private Rectangle selectLevel = new Rectangle(400, 225 ,129,25);
    private Rectangle optionsButton = new Rectangle(400, 250 ,129,25);
    private Rectangle quitGameButton = new Rectangle(400, 275 ,129,25);


    public void ifNewGame(){
        if (newGameButton.contains(GameFrame.clickPoint)){
            ifMenu = false;
            gameOn = true;
        }
        else{
            GameFrame.clickPoint = new Point();

        }
    }
    public void ifQuitGame(){
        if (quitGameButton.contains(GameFrame.clickPoint)){
            int answer = JOptionPane.showConfirmDialog(null,"Are you sure you want to quit? ","Confirm",JOptionPane.YES_NO_OPTION);
                if (answer == JOptionPane.YES_OPTION){
                          System.exit(0);          }
                else{
                    GameFrame.clickPoint = new Point();
                }


        }
    }
    public void ifLevelSelect(){
           if (selectLevel.contains(GameFrame.clickPoint)){

           }
       }
    public void ifOptions(){
               if (optionsButton.contains(GameFrame.clickPoint)){

               }
           }
    public void escape(){
            ifMenu = true;
            gameOn = false;
        GameFrame.clickPoint = new Point();
    }


    public void draw(Graphics g2d) {

	g2d.drawImage(menuImage, 0, 0, null);
	g2d.drawImage(newGame, 400,200, null);
        g2d.drawImage(levelSelect,400,225,null);
        g2d.drawImage(options,400,250,null);
        g2d.drawImage(quit, 400, 275,null);

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