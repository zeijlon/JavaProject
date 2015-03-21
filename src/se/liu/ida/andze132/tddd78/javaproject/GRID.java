package se.liu.ida.andze132.tddd78.javaproject;

import java.awt.*;

public class GRID
{
    private final static int SQUARE_WIDTH = 40;
    private final static int SQUARE_HEIGHT = 40;

    protected final static int GRASS = 0;
    protected final static int PATH = 1;
    protected final static int TOWER = 2;
    protected final static int START = 3;
    protected final static int FINISH = 4;


    private int[][] squares;

    public GRID(int n) {
	this.squares = Maps.getMap(n);
    }

    public int[][] getSquares() {
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

    public Image checkSquareType(int squaretype) {
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
                    if(squares[i][j] == GRASS){
                    squares[i][j] = TOWER;}
                }
            }
        }
    }

}

