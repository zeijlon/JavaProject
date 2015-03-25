package se.liu.ida.andze132.tddd78.javaproject;

public final class TestClass
{
    private TestClass() {}

    public static void main(String[] args) {
	GRID grid = new GRID(1);
	Shop shop = new Shop(grid);
	EnemySpawner spawner = new EnemySpawner(grid, shop);
	TowerHandler towerHandler = new TowerHandler(grid, shop, spawner);
	final GameFrame frame = new GameFrame(grid, shop, spawner, towerHandler);
	GameLoop gameloop = new GameLoop(grid, shop, frame, spawner, towerHandler);
    }
}