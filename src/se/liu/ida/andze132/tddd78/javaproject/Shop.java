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


    public static Button[][] shopButtons = new Button[4][2];

    public void draw(Graphics g) {
        // Code below draws the Shop buttons on the screen.
        int height = button.getHeight(null);
        for (int y = 0; y < shopButtons.length; y++) {
            for (int x = 0; x < shopButtons[y].length; x++) {
            g.drawImage(button, 425 + (x*70), y * (height + 20) + 75, null);
        }}

        //Code below draws health and gold on the screen.
        g.drawImage(coin, 425, 20, null);
        g.setFont(new Font("courier new", Font.BOLD, 14));
        g.drawString("" + gold, 460, 40);
        g.drawImage(heart, 500, 20, null);
        g.drawString(""+health, 535, 40);
    }
}
