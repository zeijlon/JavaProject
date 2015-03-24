package se.liu.ida.andze132.tddd78.javaproject;


import java.awt.*;


/**
 * Created by Andreas Zeijlon on 2015-03-21.
 */
public class Shop
{
    public static int gold = 10000;
    public static int health = 100;
    public static int SHOPBUTTON_SIZE = 52;


    public static Image button = Toolkit.getDefaultToolkit().getImage("images/shopButton.png");
    public static Image coin = Toolkit.getDefaultToolkit().getImage("images/coin.png");
    public static Image heart = Toolkit.getDefaultToolkit().getImage("images/heart.png");


    public static Rectangle shopButtons[][] = new Rectangle[4][2];

    private GRID grid;


    public final static int SHOP_MARGIN = 25;

    public Shop(GRID grid) {
	this.grid = grid;
	defineShopButtons();
    }


    public void defineShopButtons() {
	for (int i = 0; i < shopButtons.length; i++) {
	    for (int j = 0; j < shopButtons[i].length; j++) {
		shopButtons[i][j] =
			new Rectangle(grid.gridWidth + SHOP_MARGIN + (j * 70), i * (SHOPBUTTON_SIZE + 20) + 75, SHOPBUTTON_SIZE,
				      SHOPBUTTON_SIZE);

	    }
	}

    }


    public void draw(Graphics g) {
	// Code below draws the Shop buttons on the screen.
	for (int y = 0; y < shopButtons.length; y++) {
	    for (int x = 0; x < shopButtons[y].length; x++) {

		if (shopButtons[y][x].contains(GameFrame.motionPoint)) {
		    g.setColor(new Color(0, 0, 0, 150));
		    g.fillRect(grid.gridWidth + SHOP_MARGIN + (x * 70), y * (SHOPBUTTON_SIZE + 20) + 75, SHOPBUTTON_SIZE,
			       SHOPBUTTON_SIZE);
		} else {

		    g.drawImage(button, grid.gridWidth + SHOP_MARGIN + (x * 70), y * (SHOPBUTTON_SIZE + 20) + 75, null);
		}

	    }
	}

	//Code below draws health and gold on the screen.
	g.setColor(Color.black);
	g.drawImage(coin, grid.gridWidth + SHOP_MARGIN, 20, null);
	g.setFont(new Font("courier new", Font.BOLD, 14));
	g.drawString(String.valueOf(gold), grid.gridWidth + SHOP_MARGIN + 35, 40);
	g.drawImage(heart, grid.gridWidth + (SHOP_MARGIN * 4), 20, null);
	g.drawString(String.valueOf(health), grid.gridWidth + (SHOP_MARGIN * 4) + 35, 40);


    }

    public void checkButtonClick() {
	if (shopButtons[0][0].contains(GameFrame.clickPoint)) {
	    grid.basicTowerButtonClicked = true;
	} else if (shopButtons[3][1].contains(GameFrame.clickPoint)) {
	    grid.trashCanClicked = true;
	}
	grid.createTower();
    }


}
