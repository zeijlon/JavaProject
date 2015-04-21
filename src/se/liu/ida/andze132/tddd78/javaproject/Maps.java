package se.liu.ida.andze132.tddd78.javaproject;

public final class Maps {

    public static int[][][] maps = new int[][][]{map1(), map2(), map3(), map4()};

    private Maps() {
    }

    public static int[][] getMap(int n) {
        switch (n) {
            case 1:
                return map1();
            case 2:
                return map2();
            case 3:
                return map3();
            case 4:
                return map4();
            default:
                throw new IllegalArgumentException("Invalid input n");

        }
    }

    public static int[][] map1() {
        int[][] squares = new int[][]{
                {3, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 4},
                {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 1, 1, 5, 1, 1, 1, 0, 1, 0, 0},
                {0, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0},
                {0, 1, 1, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0},
                {0, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
        return squares;
    }

    public static int[][] map2() {
        int[][] squares = new int[][]
        {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0},
            {3, 1, 1, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 1, 0, 1, 1, 1, 5, 1, 1, 1, 0},
            {0, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 1, 0},
            {0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 4},
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
        return squares;
    }

    public static int[][] map3() {
        int[][] squares = new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {4, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0},
                {0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 4},
                {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
        return squares;
    }

    public static int[][] map4() {
        int[][] squares = new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 4},
                {3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4},
                {0, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 4},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
        return squares;
    }
}

