package se.liu.ida.andze132.tddd78.javaproject;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GRID
{
    private final static int SQUARE_WIDTH = 40;
    private final static int SQUARE_HEIGHT = 40;

    private SquareType[][] squares;

    public GRID(int n) {
	this.squares = Maps.getMap(n);
    }

    public SquareType[][] getSquares() {
	return squares;
    }


    public void draw(Graphics g2d){
        for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares[i].length; j++) {
                Image img = checkSquareType(squares[i][j]);
                g2d.drawImage(img,j * SQUARE_WIDTH, i * SQUARE_WIDTH, null);
            }
        }
    }

    public Image checkSquareType(SquareType squaretype) {
        switch (squaretype) {
            case GRASS:
                return Toolkit.getDefaultToolkit().getImage("images/grass.png");
            case PATH:
                return Toolkit.getDefaultToolkit().getImage("images/path.png");
            case TOWER:
                return Toolkit.getDefaultToolkit().getImage("images/grass.png");
            case START:
                return Toolkit.getDefaultToolkit().getImage("images/start.png");
            case FINISH:
                return Toolkit.getDefaultToolkit().getImage("images/finish.png");
            default:
                throw new IllegalArgumentException("Invalid input squaretype");
        }

    }

    public void buildTower(int y, int x){
        for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares[i].length; j++) {
                if(i*40<y && y<i*40+40 && j*40<x && x<j*40+40){
                    if(squares[i][j] == SquareType.GRASS){
                    squares[i][j] = SquareType.TOWER;}
                }
            }
        }
    }

}

