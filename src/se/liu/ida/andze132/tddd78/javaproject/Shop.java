package se.liu.ida.andze132.tddd78.javaproject;


import java.awt.*;


/**
 * Created by Andreas Zeijlon on 2015-03-21.
 */
public class Shop {
    private int gold;
    private int health;
    public final static int SHOPBUTTON_SIZE = 52;

    private Image button = Toolkit.getDefaultToolkit().getImage("images/shopButton60.png");
    private Image buttonFocus = Toolkit.getDefaultToolkit().getImage("images/shopButtonFocus60.png");
    private Image coin = Toolkit.getDefaultToolkit().getImage("images/coin.png");
    private Image heart = Toolkit.getDefaultToolkit().getImage("images/heart.png");
    private Image trashCan = Toolkit.getDefaultToolkit().getImage("images/trashCan60.png");
    private Image basicTowerImage = (Toolkit.getDefaultToolkit().getImage("images/basicTower60.png"));


    private Rectangle[][] shopButtons = new Rectangle[4][2];
    private Image[][] towerImages = new Image[4][2];

    private Towers holdsItem;

    private GRID grid;

    public final static int SHOP_MARGIN = 25;

    public Shop(GRID grid) {
        this.grid = grid;

        this.gold = 10;
        this.health = 100;

        this.holdsItem = null;
        defineShopButtons();
        towerImages = new Image[][]{{basicTowerImage, basicTowerImage}, {basicTowerImage, basicTowerImage},
                {basicTowerImage, basicTowerImage}, {basicTowerImage, trashCan}};
    }


    public void defineShopButtons() {
        for (int i = 0; i < shopButtons.length; i++) {
            for (int j = 0; j < shopButtons[i].length; j++) {
                shopButtons[i][j] =
                        new Rectangle(grid.getWidth() + SHOP_MARGIN + (j * 70), i * (SHOPBUTTON_SIZE + 20) + 75, SHOPBUTTON_SIZE,
                                SHOPBUTTON_SIZE);

            }
        }

    }


    public void draw(Graphics g) {
        // Code below draws the Shop buttons on the screen.
        for (int y = 0; y < shopButtons.length; y++) {
            for (int x = 0; x < shopButtons[y].length; x++) {

                if (shopButtons[y][x].contains(GameFrame.motionPoint)) {
                    g.drawImage(buttonFocus, grid.getWidth() + SHOP_MARGIN + (x * 70), y * (SHOPBUTTON_SIZE + 20) + 75, null);

                } else {

                    g.drawImage(button, grid.getWidth() + SHOP_MARGIN + (x * 70), y * (SHOPBUTTON_SIZE + 20) + 75, null);
                }


                g.drawImage(towerImages[y][x], grid.getWidth() + SHOP_MARGIN + (x * 70), y * (SHOPBUTTON_SIZE + 20) + 75, null);

            }
        }

        //Code below draws health and gold on the screen.
        g.setColor(Color.black);
        g.drawImage(coin, grid.getWidth() + SHOP_MARGIN, 20, null);
        g.setFont(new Font("courier new", Font.BOLD, 14));
        g.drawString(String.valueOf(gold), grid.getWidth() + SHOP_MARGIN + 35, 40);
        g.drawImage(heart, grid.getWidth() + (SHOP_MARGIN * 4), 20, null);
        g.drawString(String.valueOf(health), grid.getWidth() + (SHOP_MARGIN * 4) + 35, 40);


    }

    public Rectangle[][] getShopButtons() {
        return shopButtons;
    }


    public Towers getHoldsItem() {
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

    public void setHoldsItem(final Towers holdsItem) {
        this.holdsItem = holdsItem;
    }

    public Image getBasicTowerImage() {
        return basicTowerImage;
    }
}
