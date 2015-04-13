package se.liu.ida.andze132.tddd78.javaproject;

import java.awt.*;

public class GRID {
    private final static int SQUARE_WIDTH = 40;
    private final static int SQUARE_HEIGHT = 40;

    protected final static int GRASS = 0;
    protected final static int PATH = 1;
    protected final static int TOWER = 2;
    protected final static int START = 3;
    protected final static int FINISH = 4;
    protected final static int CROSSROAD = 5;

    protected int gridWidth;
    protected int gridHeight;

    private int[][] squares;
    private Rectangle[][] rectangles;

    private Rectangle gridSize;

    public GRID(int n) {
        this.squares = Maps.getMap(n);
        this.gridWidth = checkLargestRow(this) * GameComponent.TILE_SIZE;
        this.gridHeight = squares.length * GameComponent.TILE_SIZE;

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
                rectangles[i][j] = new Rectangle(j * SQUARE_WIDTH, i * SQUARE_HEIGHT, SQUARE_WIDTH, SQUARE_HEIGHT);
            }
        }
    }

    public void draw(Graphics g2d) {
        for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares[i].length; j++) {
                Image img = checkSquareType(squares[i][j]);
                g2d.drawImage(img, j * SQUARE_WIDTH, i * SQUARE_HEIGHT, null);
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
            case CROSSROAD:
                return Toolkit.getDefaultToolkit().getImage("images/path.png");
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

    public static int getSquareWidth() {
        return SQUARE_WIDTH;
    }

    public static int getSquareHeight() {
        return SQUARE_HEIGHT;
    }

    public Rectangle getGridSize() {
        return gridSize;
    }
}
