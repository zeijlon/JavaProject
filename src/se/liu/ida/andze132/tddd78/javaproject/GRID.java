package se.liu.ida.andze132.tddd78.javaproject;

import java.awt.*;

public class GRID {
    protected final static int GRASS = 0;
    protected final static int PATH = 1;
    protected final static int TOWER = 2;
    protected final static int START = 3;
    protected final static int FINISH = 4;
    protected final static int CROSSROAD = 5;

    private int Width;
    private int Height;

    private int[][] squares;
    private Rectangle[][] rectangles;

    private Rectangle gridSize;

    public GRID(int n) {
        this.squares = Maps.getMap(n);
        this.Width = checkLargestRow(this) * GameComponent.TILE_SIZE;
        this.Height = squares.length * GameComponent.TILE_SIZE;

        rectangles = new Rectangle[squares.length][checkLargestRow(this)];
        defineRectangles();
        gridSize = new Rectangle(0, 0, checkLargestRow(this) * GameComponent.TILE_SIZE, squares.length * GameComponent.TILE_SIZE);
    }

    public int[][] getSquares() {
        return squares;
    }


    public void defineRectangles() {
        for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares[i].length; j++) {
                rectangles[i][j] = new Rectangle(j * GameComponent.TILE_SIZE, i * GameComponent.TILE_SIZE, GameComponent.TILE_SIZE, GameComponent.TILE_SIZE);
            }
        }
    }

    public void draw(Graphics g2d) {
        for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares[i].length; j++) {
                Image img = checkSquareType(squares[i][j]);
                g2d.drawImage(img, j * GameComponent.TILE_SIZE, i * GameComponent.TILE_SIZE, null);
            }
        }
    }

    public Image checkSquareType(int squaretype) {
        switch (squaretype) {
            case GRASS:
                return Toolkit.getDefaultToolkit().getImage("images/grass60.png");
            case PATH:
                return Toolkit.getDefaultToolkit().getImage("images/path60.png");
            case TOWER:
                return Toolkit.getDefaultToolkit().getImage("images/grass60.png");
            case START:
                return Toolkit.getDefaultToolkit().getImage("images/start60.png");
            case FINISH:
                return Toolkit.getDefaultToolkit().getImage("images/finish60.png");
            case CROSSROAD:
                return Toolkit.getDefaultToolkit().getImage("images/path60.png");
            default:
                throw new IllegalArgumentException("Invalid input squaretype");
        }

    }

    public static int checkLargestRow(GRID grid) {
        int largestRow = 0;
        for (int i = 0; i < grid.squares.length; i++) {
            if (grid.squares[i].length > largestRow) {
                largestRow = grid.squares[i].length;
            }
        }
        return largestRow;
    }

    public Rectangle[][] getRectangles() {
        return rectangles;
    }

    public Shape getGridSize() {
        return gridSize;
    }

    public int getWidth() {
        return Width;
    }

    public int getHeight() {
        return Height;
    }

    public void setMapSize(int n)
    {
        squares = Maps.getMap(n);
    }

// --Commented out by Inspection START (2015-04-21 23:57):
//    public void setSquares(int[][] squares) {
//        this.squares = squares;
//    }
// --Commented out by Inspection STOP (2015-04-21 23:57)
}
