package se.liu.ida.andze132.tddd78.javaproject;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GRID
{
    private final static int SQUARE_WIDTH = 40;
    private final static int SQUARE_HEIGHT = 40;

    public static Image basicTower = (Toolkit.getDefaultToolkit().getImage("images/coin.png"));

    protected final static int GRASS = 0;
    protected final static int PATH = 1;
    protected final static int TOWER = 2;
    protected final static int START = 3;
    protected final static int FINISH = 4;
    protected final static int CROSSROAD = 5;

    protected int gridWidth;
    protected int gridHeight;

    public boolean basicTowerButtonClicked;
    public boolean trashCanClicked;
    public boolean holdsItem;


    public List<Towers> towers = new ArrayList<>();

    private int[][] squares;
    private Rectangle[][] rectangles;

    public GRID(int n) {
	this.squares = Maps.getMap(n);
	this.gridWidth = checkLargestRow(this) * GameComponent.TILE_SIZE;
	this.gridHeight = squares.length * GameComponent.TILE_SIZE;

	this.basicTowerButtonClicked = false;
	this.trashCanClicked = false;
	this.holdsItem = false;


	rectangles = new Rectangle[squares.length][checkLargestRow(this)];
	defineRectangles();
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

	for (int i = 0; i < towers.size(); i++) {
	    g2d.drawImage(basicTower, towers.get(i).X, towers.get(i).Y, null);
	}
	if (holdsItem) {
	    Double dX = new Double(GameFrame.motionPoint.getX());
	    Double dY = new Double(GameFrame.motionPoint.getY());
	    int X = dX.intValue();
	    int Y = dY.intValue();
	    g2d.drawImage(basicTower, X - SQUARE_WIDTH/2, Y - SQUARE_HEIGHT/2, null);
	    System.out.println("Holds item");
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

    public void createTower() {
	if (basicTowerButtonClicked) {
	    holdsItem = true;
	    basicTowerButtonClicked = false;
	} else if (trashCanClicked) {
	    holdsItem = false;
	    trashCanClicked = false;
	}
	buildTower();
    }

    public void buildTower() {
	if (holdsItem) {
	    for (int i = 0; i < rectangles.length; i++) {
		for (int j = 0; j < rectangles[i].length; j++) {
		    if (rectangles[i][j].contains(GameFrame.clickPoint)) {
			if (squares[i][j] == GRASS) {
			    Towers tower = new BasicTower();
			    if (Shop.gold >= tower.getCost()) {
				towers.add(tower);
				squares[i][j] = TOWER;
				tower.X = j * SQUARE_WIDTH;
				tower.Y = i * SQUARE_WIDTH;
				tower.targeted = false;
				holdsItem = false;
				Shop.gold -= tower.getCost();
			    }
			}
		    }
		}

	    }
	}
    }
}
