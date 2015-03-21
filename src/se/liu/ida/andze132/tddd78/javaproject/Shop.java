package se.liu.ida.andze132.tddd78.javaproject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Andreas Zeijlon on 2015-03-21.
 */
public class Shop {
    public static int gold = 10;
    public static int health = 100;

    public static Image button = Toolkit.getDefaultToolkit().getImage("images/shopButton.png");
    public static Image coin = Toolkit.getDefaultToolkit().getImage("images/coin.png");
    public static Image heart = Toolkit.getDefaultToolkit().getImage("images/heart.png");


    public static Button[] shopButtons = new Button[4];

    public void draw(Graphics g) {
        // Code below draws the Shop buttons on the screen.
        int height = button.getHeight(null);
        for (int i = 0; i < shopButtons.length; i++) {
            g.drawImage(button, 475, i * (height + 20) + 75, null);
        }

        //Code below draws health and gold on the screen.
        g.drawImage(coin, 445, 20, null);
        g.drawString(""+gold, 480, 40);
        g.drawImage(heart, 500, 20, null);
        g.drawString(""+health, 535, 40);
    }
}
