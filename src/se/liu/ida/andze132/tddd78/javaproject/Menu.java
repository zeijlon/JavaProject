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
    private boolean drawlvlslct;
    private Maps map;
    private int intmap1 =1;
    private int intmap2 =2;
    private int intmap3 =3;



    public Menu(GRID grid, Shop shop, EnemySpawner spawner,TowerHandler towerHandler, BulletHandler bulletHandler) {
        ifMenu = true;
        gameOn = false;
        gameRunning = true;
	drawlvlslct = false;
    }

    private  Image menuImage = (Toolkit.getDefaultToolkit().getImage("images/DawnofthePolarBears.png"));
    private  Image newGame = (Toolkit.getDefaultToolkit().getImage("images/NewGameT.png"));
    private  Image quit = (Toolkit.getDefaultToolkit().getImage("images/QuitGame.png"));
    private  Image levelSelect = (Toolkit.getDefaultToolkit().getImage("images/LevelSelect.png"));
    private  Image options = (Toolkit.getDefaultToolkit().getImage("images/Options.png"));
    private  Image map1 = (Toolkit.getDefaultToolkit().getImage("images/map1.png"));
    private  Image map2 = (Toolkit.getDefaultToolkit().getImage("images/map2.png"));
    private  Image map3 = (Toolkit.getDefaultToolkit().getImage("images/map3.png"));


    private Rectangle newGameButton = new Rectangle(400, 200 ,150,27);
    private Rectangle selectLevel = new Rectangle(400, 225 ,150,27);
    private Rectangle optionsButton = new Rectangle(400, 250 ,150,27);
    private Rectangle quitGameButton = new Rectangle(400, 275 ,129,25);
    private Rectangle recmap1 = new Rectangle(550, 210 ,75,30);
    private Rectangle recmap2 = new Rectangle(550, 235 ,75,30);
    private Rectangle recmap3 = new Rectangle(550, 360 ,75,30);



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
	       System.out.println("KUUUUK");
		drawlvlslct = true;
           }
	   else{
	               GameFrame.clickPoint = new Point();

	           }
       }

    public void ifOptions(){
               if (optionsButton.contains(GameFrame.clickPoint)){

               }
	       else{
	                   GameFrame.clickPoint = new Point();

	               }
           }
    public void escape(){
            ifMenu = true;
            gameOn = false;
        GameFrame.clickPoint = new Point();
    }

    public int chooseMap1(){
	if (drawlvlslct && recmap1.contains(GameFrame.clickPoint)){
	    System.out.println("lahahal");
	    return intmap1;
	   // GameFrame.clickPoint = new Point();
	}
	return 0;
    }
    public int chooseMap2(){
    	if (drawlvlslct && recmap2.contains(GameFrame.clickPoint)){
    	    System.out.println("lolol");
	    return intmap2;
	    //GameFrame.clickPoint = new Point();
    	}return 0;
        }
    public int chooseMap3(){
    	if (drawlvlslct && recmap3.contains(GameFrame.clickPoint)){
    	    System.out.println("leleele");
	    return intmap3;
	    //GameFrame.clickPoint = new Point();
    	}return 0;
        }

    public void draw(Graphics g2d) {

	g2d.drawImage(menuImage, 0, 0, null);
	g2d.drawImage(newGame, 400,200, null);
        g2d.drawImage(levelSelect,400,225,null);
        g2d.drawImage(options,400,250,null);
        g2d.drawImage(quit, 400, 275,null);
	if(drawlvlslct){
	    g2d.drawImage(map1,550, 210,null);
	    g2d.drawImage(map2,550, 235,null);
	    g2d.drawImage(map3,550, 260,null);


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