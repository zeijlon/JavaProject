package se.liu.ida.andze132.tddd78.javaproject;

import java.awt.*;


/**
 * Created by Andreas Zeijlon on 2015-03-21.
 */
public class Shop {
    public static int gold = 10;
    public static int health = 100;

    public static Image button = Toolkit.getDefaultToolkit().getImage("images/shopButton.png");
    public static Image coin = Toolkit.getDefaultToolkit().getImage("images/coin.png");
    public static Image heart = Toolkit.getDefaultToolkit().getImage("images/heart.png");


    public static Button[][] shopButtons = new Button[4][2];

    private GRID grid;
    private int GRID_SIZE_X;
    private int GRID_SIZE_Y;

    public final static int SHOP_MARGIN = 25;

    public Shop(GRID grid) {
        this.grid = grid;
        this.GRID_SIZE_X = GRID.checkLargestRow(grid) * GameComponent.TILE_SIZE;
        this.GRID_SIZE_Y = grid.getSquares().length;
        }

    public void draw(Graphics g) {
        // Code below draws the Shop buttons on the screen.
        int height = button.getHeight(null);
        System.out.println(height + "   " + GRID_SIZE_X);
        for (int y = 0; y < shopButtons.length; y++) {
            for (int x = 0; x < shopButtons[y].length; x++) {
            g.drawImage(button, GRID_SIZE_X + SHOP_MARGIN + (x*70), y * (height + 20) + 75, null);
        }}

        //Code below draws health and gold on the screen.
        g.drawImage(coin, GRID_SIZE_X + SHOP_MARGIN, 20, null);
        g.setFont(new Font("courier new", Font.BOLD, 14));
        g.drawString("" + gold, GRID_SIZE_X + SHOP_MARGIN + 35, 40);
        g.drawImage(heart, GRID_SIZE_X + (SHOP_MARGIN*4), 20, null);
        g.drawString(""+health, GRID_SIZE_X + (SHOP_MARGIN*4) + 35, 40);
    }
}
