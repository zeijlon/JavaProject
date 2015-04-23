package se.liu.ida.andze132.tddd78.javaproject;


import java.awt.*;


/**
 * Created by Andreas Zeijlon on 2015-03-21.
 */
public class Shop {
    private int gold;
    private int health;
    public final static int SHOPBUTTON_SIZE = 80;
    public final static int MAGICSHOPCOORDONE =35;
    public final static int MAGICSHOPCOORDTWO =160;
    public final static int MAGICSHOPCOORDTHREE =140;
    public final static int MAGICSHOPMONEYINT = 95;
    public final static int BASICTOWERINFOWIDTH =500;
    public final static int  MAGICHEARTCOORD =60;
    public final static int STRINGSHOPHEIGHT =125;

    private Image button = Toolkit.getDefaultToolkit().getImage("images/shopButton60.png").getScaledInstance(SHOPBUTTON_SIZE, SHOPBUTTON_SIZE, Image.SCALE_DEFAULT);
    private Image buttonFocus = Toolkit.getDefaultToolkit().getImage("images/shopButtonFocus60.png").getScaledInstance(SHOPBUTTON_SIZE, SHOPBUTTON_SIZE, Image.SCALE_DEFAULT);
    private Image coin = Toolkit.getDefaultToolkit().getImage("images/coin.png");
    private Image heart = Toolkit.getDefaultToolkit().getImage("images/heart.png");
    private Image basicTowerInfo = Toolkit.getDefaultToolkit().getImage("images/basicTowerInfo.png");
    private Image apTowerInfo = Toolkit.getDefaultToolkit().getImage("images/apTowerInfo.png");


    private Rectangle[][] shopButtons = new Rectangle[4][2];
    private Image[][] towerImages = new Image[4][2];

    private Towers holdsItem;

    private GRID grid;
    private KeyHandler keyHandler;

    public final static int SHOP_MARGIN = 14;

    public Shop(GRID grid, KeyHandler keyHandler) {
        this.grid = grid;
        this.keyHandler = keyHandler;

        this.holdsItem = null;
        defineShopButtons();
        Image trashCan = Toolkit.getDefaultToolkit().getImage("images/trashCan60.png").getScaledInstance(SHOPBUTTON_SIZE, SHOPBUTTON_SIZE, Image.SCALE_DEFAULT);
        Image armorPiercingTowerImage = Toolkit.getDefaultToolkit().getImage("images/armorPiercingTower60.png").getScaledInstance(SHOPBUTTON_SIZE, SHOPBUTTON_SIZE, Image.SCALE_DEFAULT);
        Image basicTowerImage = Toolkit.getDefaultToolkit().getImage("images/basicTower60.png").getScaledInstance(SHOPBUTTON_SIZE, SHOPBUTTON_SIZE, Image.SCALE_DEFAULT);
        towerImages = new Image[][]{{basicTowerImage, armorPiercingTowerImage}, {basicTowerImage, basicTowerImage},
                {basicTowerImage, basicTowerImage}, {basicTowerImage, trashCan}};
    }


    public void defineShopButtons() {
        for (int i = 0; i < shopButtons.length; i++) {
            for (int j = 0; j < shopButtons[i].length; j++) {
                shopButtons[i][j] =
                        new Rectangle(grid.getWidth() + SHOP_MARGIN + j * (SHOPBUTTON_SIZE+10), i * (SHOPBUTTON_SIZE + 10) + MAGICSHOPCOORDTHREE, SHOPBUTTON_SIZE,
                                SHOPBUTTON_SIZE);

            }
        }

    }


    public void draw(Graphics g) {
        g.setColor(Color.black);
        g.setFont(new Font("Courier New", Font.BOLD, MAGICSHOPCOORDONE-1));
        g.drawString("SHOP", grid.getWidth() + SHOP_MARGIN + MAGICSHOPCOORDONE+10, STRINGSHOPHEIGHT);
        // Code below draws the Shop buttons on the screen.

        for (int y = 0; y < shopButtons.length; y++) {
            for (int x = 0; x < shopButtons[y].length; x++) {
                if (shopButtons[y][x].contains(keyHandler.getMotionPoint())) {
                    g.drawImage(buttonFocus, grid.getWidth() + SHOP_MARGIN + x *(SHOPBUTTON_SIZE + 10), y * (SHOPBUTTON_SIZE + 10) + MAGICSHOPCOORDTHREE, null);

                } else {

                    g.drawImage(button, grid.getWidth() + SHOP_MARGIN + x *(SHOPBUTTON_SIZE + 10), y * (SHOPBUTTON_SIZE + 10) + MAGICSHOPCOORDTHREE, null);
                }
                g.drawImage(towerImages[y][x], grid.getWidth() + SHOP_MARGIN + x *(SHOPBUTTON_SIZE + 10), y * (SHOPBUTTON_SIZE + 10) + MAGICSHOPCOORDTHREE, null);

            }
        }


        if (shopButtons[0][0].contains(keyHandler.getMotionPoint())) {
            g.drawImage(basicTowerInfo, grid.getWidth()+10, BASICTOWERINFOWIDTH, null);
        } else if (shopButtons[0][1].contains(keyHandler.getMotionPoint())) {
            g.drawImage(apTowerInfo, grid.getWidth()+10, BASICTOWERINFOWIDTH, null);

        }



        g.setFont(new Font("Courier New", Font.BOLD, SHOP_MARGIN+4));
        g.setColor(Color.yellow);
        g.drawString("$" + 5, grid.getWidth() + SHOP_MARGIN + 10, MAGICSHOPCOORDTWO);
        g.drawString("$" + 10, grid.getWidth() + SHOP_MARGIN +MAGICSHOPMONEYINT, MAGICSHOPCOORDTWO);

        //Code below draws health and gold on the screen.
        g.setColor(Color.black);
        g.drawImage(coin, grid.getWidth() + SHOP_MARGIN, SHOP_MARGIN+1, null);
        g.setFont(new Font("courier new", Font.BOLD, SHOP_MARGIN));
        g.drawString(String.valueOf(gold), grid.getWidth() + SHOP_MARGIN + MAGICSHOPCOORDONE, MAGICSHOPCOORDONE);
        g.drawImage(heart, grid.getWidth() + SHOP_MARGIN, MAGICHEARTCOORD, null);
        g.drawString(String.valueOf(health), grid.getWidth() + SHOP_MARGIN + MAGICSHOPCOORDONE, SHOPBUTTON_SIZE);

        g.setFont(new Font("Courier new", Font.PLAIN, 10+2));


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

    public void setGold(final int gold) {
        this.gold = gold;
    }

    public void setHealth(final int health) {
        this.health = health;
    }

    public void setHoldsItem(final Towers holdsItem) {
        this.holdsItem = holdsItem;
    }

}
