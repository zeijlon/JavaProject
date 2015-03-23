package se.liu.ida.andze132.tddd78.javaproject;

public final class Maps
{

    private Maps() {}

    public static int[][] getMap(int n) {
	switch (n) {
	    case 1:
		return map1();
	    default:
		throw new IllegalArgumentException("Invalid input n");

	}
    }

    public static int[][] map1() {
		int[][] squares = new int[][]{
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 0},
				{0, 1, 0, 0, 0, 0, 0, 0, 0, 4, 0, 1, 1},
				{0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
				{0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1},
				{0, 1, 0, 1, 1, 0, 3, 1, 1, 1, 1, 1, 1},
				{0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0},
				{0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0},
				{0, 1, 1, 1, 0, 1, 1, 1, 0, 0, 1, 1, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0}
	};
		return squares;
	}}

