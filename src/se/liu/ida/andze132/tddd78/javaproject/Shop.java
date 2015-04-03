package se.liu.ida.andze132.tddd78.javaproject;


import java.awt.*;


/**
 * Created by Andreas Zeijlon on 2015-03-21.
 */
public class Shop {
    private int gold;
    private int health;
    public final static int SHOPBUTTON_SIZE = 52;


    private Image button = Toolkit.getDefaultToolkit().getImage("images/shopButton.png");
    private Image coin = Toolkit.getDefaultToolkit().getImage("images/coin.png");
    private Image heart = Toolkit.getDefaultToolkit().getImage("images/heart.png");
    private Image trashCan = Toolkit.getDefaultToolkit().getImage("images/trashCan.png");


    private Rectangle[][] shopButtons = new Rectangle[4][2];
    private Image[][] towerImages = new Image[4][2];

    private int holdsItem;
    private int basicTower = 0, nothing = 7;

    private GRID grid;


    public final static int SHOP_MARGIN = 25;

    public Shop(GRID grid) {
        this.grid = grid;

        this.gold = 10;
        this.health = 100;

        this.holdsItem = nothing;
        defineShopButtons();
        towerImages = new Image[][]{{BasicTower.image, BasicTower.image}, {BasicTower.image, BasicTower.image},
                {BasicTower.image, BasicTower.image}, {BasicTower.image, trashCan}};
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

                g.drawImage(towerImages[y][x], grid.gridWidth + SHOP_MARGIN + (x * 70), y * (SHOPBUTTON_SIZE + 20) + 75, null);

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

    public Rectangle[][] getShopButtons() {
        return shopButtons;
    }


    public int getHoldsItem() {
        return holdsItem;
    }

    public int getGold() {
        return gold;
    }

    public int getHealth() {
        return health;
    }

    public void setGold(final int cost) {
        this.gold -= cost;
    }

    public void withdrawGold(final int goldgain) {
        this.gold += goldgain;
    }

    public void setHealth(final int damage) {
        this.health -= damage;
    }

    public void setHoldsItem(final int holdsItem) {
        this.holdsItem = holdsItem;
    }

    public int getBasicTower() {
        return basicTower;
    }

    public int getNothing() {
        return nothing;
    }
}
