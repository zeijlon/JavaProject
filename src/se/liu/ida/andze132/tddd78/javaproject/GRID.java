package se.liu.ida.andze132.tddd78.javaproject;

public class GRID
{
    private SquareType[][] squares;


    public GRID(int n) {
	this.squares = Maps.getMap(n);
    }

    public SquareType[][] getSquares() {
	return squares;
    }
}

