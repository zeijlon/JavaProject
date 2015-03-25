package se.liu.ida.andze132.tddd78.javaproject;

import java.util.ArrayList;
import java.util.List;
import java.awt.*;

/**
 * Created by Administratör on 2015-03-24.
 */
public class TowerHandler
{

    private int basicTower = 0, trashCan = 1, not = 2;
    private int buttonClicked;

    private List<Towers> towers = new ArrayList<>();

    private GRID grid;
    private Shop shop;
    private EnemySpawner spawner;

    public TowerHandler(GRID grid, Shop shop, EnemySpawner spawner) {
	this.grid = grid;
	this.shop = shop;
	this.spawner = spawner;
	this.buttonClicked = not;
    }

    public void checkButtonClick() {
	if (shop.getShopButtons()[0][0].contains(GameFrame.clickPoint)) {
	    buttonClicked = basicTower;
	} else if (shop.getShopButtons()[3][1].contains(GameFrame.clickPoint)) {
	    buttonClicked = trashCan;
	}
	createTower();
    }

    public void createTower() {
	if (buttonClicked == basicTower) {
	    shop.setHoldsItem(shop.getBasicTower());
	} else if (buttonClicked == trashCan) {
	    shop.setHoldsItem(shop.getNothing());
	}
	buildTower();
    }

    public void buildTower() {
	if (shop.getHoldsItem() == shop.getBasicTower()) {
	    for (int i = 0; i < grid.getRectangles().length; i++) {
		for (int j = 0; j < grid.getRectangles()[i].length; j++) {
		    if (grid.getRectangles()[i][j].contains(GameFrame.clickPoint)) {
			if (grid.getSquares()[i][j] == GRID.GRASS) {
			    Towers tower = new BasicTower();
			    if (shop.getGold() >= tower.getCost()) {
				towers.add(tower);
				grid.getSquares()[i][j] = GRID.TOWER;
				tower.setX(j * GRID.getSquareWidth());
				tower.setY(i * GRID.getSquareHeight());
				tower.setTargeted(false);
				shop.setGold(tower.getCost());
			    }
			}
		    }
		}

	    }
	}
    }

    public void draw(Graphics g) {
	for (int i = 0; i < towers.size(); i++) {
	    g.drawImage(towers.get(i).getImage(), towers.get(i).getX(), towers.get(i).getY(), null);
	}
		Double dX = new Double(GameFrame.motionPoint.getX());
		Double dY = new Double(GameFrame.motionPoint.getY());
		int x = dX.intValue();
		int y = dY.intValue();
	if (shop.getHoldsItem()==shop.getBasicTower()) {
		g.drawImage(BasicTower.image, x - GRID.getSquareWidth() / 2, y - GRID.getSquareHeight() / 2, null);
	    }
	}
    }

