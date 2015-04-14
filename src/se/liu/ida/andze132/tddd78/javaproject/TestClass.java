package se.liu.ida.andze132.tddd78.javaproject;

public final class TestClass {
    private TestClass() {
    }

    public static void main(String[] args) {
        GRID grid = new GRID(1);
        Shop shop = new Shop(grid);
        EnemySpawner spawner = new EnemySpawner(grid, shop);
        BulletHandler bulletHandler = new BulletHandler(grid, spawner);
        TowerHandler towerHandler = new TowerHandler(grid, shop, spawner, bulletHandler);
        final GameFrame frame = new GameFrame(grid, shop, spawner, towerHandler, bulletHandler);
        GameLoop gameloop = new GameLoop(shop, frame, spawner, towerHandler, bulletHandler);
    }
}