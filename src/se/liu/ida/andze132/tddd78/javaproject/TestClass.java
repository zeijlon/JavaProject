package se.liu.ida.andze132.tddd78.javaproject;

public class TestClass {
    private final static int INTERVAL = 100;

    public static void main(String[] args) {
        GRID grid = new GRID(1);
        Shop shop = new Shop();
        final GameFrame frame = new GameFrame(grid, shop);
        new GameLoop(grid, shop, frame);
    }
}

