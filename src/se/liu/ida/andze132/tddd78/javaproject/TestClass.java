package se.liu.ida.andze132.tddd78.javaproject;

public class TestClass
{

    public static void main(String[] args) {
	GRID grid = new GRID(1);
	Shop shop = new Shop(grid);
	EnemySpawner spawner = new EnemySpawner(grid);
	final GameFrame frame = new GameFrame(grid, shop, spawner);
	GameLoop gameloop = new GameLoop(grid, shop, frame, spawner);
    }
}

