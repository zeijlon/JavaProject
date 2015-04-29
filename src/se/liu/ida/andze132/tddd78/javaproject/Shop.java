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
    public final static int BASICTOWERINFOHEIGHT =320;
    public final static int  MAGICHEARTCOORD =60;
    public final static int STRINGSHOPHEIGHT =125;
    public final static int SHOP_MARGIN = 14;


    private Image button = Toolkit.getDefaultToolkit().getImage("images/shopButton60.png").getScaledInstance(SHOPBUTTON_SIZE, SHOPBUTTON_SIZE, Image.SCALE_DEFAULT);
    private Image buttonFocus = Toolkit.getDefaultToolkit().getImage("images/shopButtonFocus60.png").getScaledInstance(
            SHOPBUTTON_SIZE, SHOPBUTTON_SIZE, Image.SCALE_DEFAULT);
    private Image coin = Toolkit.getDefaultToolkit().getImage("images/coin.png");
    private Image heart = Toolkit.getDefaultToolkit().getImage("images/heart.png");
    private Image basicTowerInfo = Toolkit.getDefaultToolkit().getImage("images/basicTowerInfo.png");
    private Image apTowerInfo = Toolkit.getDefaultToolkit().getImage("images/apTowerInfo.png");
    private Image theX = Toolkit.getDefaultToolkit().getImage("images/X.png");
    private Image speaker = Toolkit.getDefaultToolkit().getImage("images/speaker.png");
    private Image note = Toolkit.getDefaultToolkit().getImage("images/note.png");
    private Rectangle inGameAudioRect = new Rectangle(920, 50, 30, 30);
    private Rectangle inGameMusicRect = new Rectangle(880, 50, 30, 30);

    private Rectangle audioRect = new Rectangle(880,50,30,30);
    private Rectangle musicRect = new Rectangle(920,50,30,30);


    private Rectangle[][] shopButtons = new Rectangle[2][2];
    private Image[][] shopImages = new Image[2][2];

    private Towers holdsItem;

    private GRID grid;

    public Shop(GRID grid) {
        this.grid = grid;

        this.holdsItem = null;
        defineShopButtons();
        Image trashCan = Toolkit.getDefaultToolkit().getImage("images/trashCan60.png").getScaledInstance(SHOPBUTTON_SIZE, SHOPBUTTON_SIZE, Image.SCALE_DEFAULT);
        Image armorPiercingTowerImage = Toolkit.getDefaultToolkit().getImage("images/armorPiercingTower60.png").getScaledInstance(SHOPBUTTON_SIZE, SHOPBUTTON_SIZE, Image.SCALE_DEFAULT);
        Image basicTowerImage = Toolkit.getDefaultToolkit().getImage("images/basicTower60.png").getScaledInstance(SHOPBUTTON_SIZE, SHOPBUTTON_SIZE, Image.SCALE_DEFAULT);
        shopImages = new Image[][]{{basicTowerImage, armorPiercingTowerImage}, {basicTowerImage, trashCan}};
    }


    public void defineShopButtons() {
        for (int i = 0; i < shopButtons.length; i++) {
            for (int j = 0; j < shopButtons[i].length; j++) {
                shopButtons[i][j] =
                        new Rectangle(grid.getWidth() + SHOP_MARGIN + j * (SHOPBUTTON_SIZE+10), i * (SHOPBUTTON_SIZE + 10) + MAGICSHOPCOORDTHREE, SHOPBUTTON_SIZE,
                                SHOPBUTTON_SIZE);

        }
	}/*
			if(inGameAudioRect.contains(keyHandler.getClickPoint())){
	    			System.out.println("fitta");
	    				if (Sound.isNoGameAudio()) {
	        		                    //Sound.setNoGameAudio(false);
	        		                } else if (!Sound.isNoGameAudio()) {
	        		                    //Sound.setNoGameAudio(true);
	        		}}
	        		else if(inGameMusicRect.contains(keyHandler.getClickPoint())){
	    				System.out.println("kuk");
	    					if (Sound.isNoMusic()) {
	        		                    if(!Sound.getClipPlaying()){
	        		                        //mainTheme.play();
	        		                        //mainTheme.loop();
	        		                        //Sound.setClipPlaying(true);
	        				}
	                    }}
*/
    }




    public void draw(Graphics g) {
        g.setColor(Color.black);
        g.setFont(new Font("Courier New", Font.BOLD, MAGICSHOPCOORDONE-1));
        g.drawString("SHOP", grid.getWidth() + SHOP_MARGIN + MAGICSHOPCOORDONE+10, STRINGSHOPHEIGHT);
	g.drawImage(note, 880,50,null);
		g.drawImage(speaker, 920,50,null);
			if(Sound.isNoMusic()){
			    g.drawImage(theX,880,50,null);
			}
			if(Sound.isNoGameAudio()){
				    g.drawImage(theX,920,50,null);
				}


        // Code below draws the Shop buttons on the screen.

        for (int y = 0; y < shopButtons.length; y++) {
            for (int x = 0; x < shopButtons[y].length; x++) {
                if (shopButtons[y][x].contains(KeyHandler.motionPoint)) {
                    g.drawImage(buttonFocus, grid.getWidth() + SHOP_MARGIN + x *(SHOPBUTTON_SIZE + 10), y * (SHOPBUTTON_SIZE + 10) + MAGICSHOPCOORDTHREE, null);

                } else {

                    g.drawImage(button, grid.getWidth() + SHOP_MARGIN + x *(SHOPBUTTON_SIZE + 10), y * (SHOPBUTTON_SIZE + 10) + MAGICSHOPCOORDTHREE, null);
                }
                g.drawImage(shopImages[y][x], grid.getWidth() + SHOP_MARGIN + x *(SHOPBUTTON_SIZE + 10), y * (SHOPBUTTON_SIZE + 10) + MAGICSHOPCOORDTHREE, null);

            }
        }


        if (shopButtons[0][0].contains(KeyHandler.motionPoint)) {
            g.drawImage(basicTowerInfo, grid.getWidth()+15, BASICTOWERINFOHEIGHT, null);
        } else if (shopButtons[0][1].contains(KeyHandler.motionPoint)) {
            g.drawImage(apTowerInfo, grid.getWidth()+15, BASICTOWERINFOHEIGHT, null);
        }



        g.setFont(new Font("Courier New", Font.BOLD, SHOP_MARGIN+4));
        g.setColor(Color.yellow);
        g.drawString("$" + 10, grid.getWidth() + SHOP_MARGIN + 10, MAGICSHOPCOORDTWO);
        g.drawString("$" + 20, grid.getWidth() + SHOP_MARGIN +MAGICSHOPMONEYINT, MAGICSHOPCOORDTWO);
        g.drawString("$" + 40, grid.getWidth() +SHOP_MARGIN + 10, MAGICSHOPCOORDTWO + SHOPBUTTON_SIZE + 10);


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


    public Image[][] getShopImages() {
        return shopImages;
    }

    public void setShopImages(final Image[][] shopImages) {
        this.shopImages = shopImages;
    }
}
