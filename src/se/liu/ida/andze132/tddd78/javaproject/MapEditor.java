package se.liu.ida.andze132.tddd78.javaproject;

/**
 * Created by Andreaszeijlon on 15-04-26.
 *//*
public class MapEditor {
    private GRID grid;
    private KeyHandler keyHandler;
    private Shop shop;
    private int holdsItem;

    public MapEditor(GRID grid, Shop shop, KeyHandler keyHandler){
        this.grid = grid;
        this.keyHandler = keyHandler;
        this.shop = shop;
        this.shop.setShopImages(new Image[][] { { grid.getGrassImg(), grid.getPathImg() }, { grid.getCrossRoadImg(), grid.getTowerImg()},
                { grid.getCrossRoadImg(), grid.getStartImg()}, { grid.getFinishImg(), grid.getTowerImg()}});
        for (int i = 0; i < grid.getSquares().length; i++) {
            for (int j = 0; j < grid.getSquares()[i].length; j++) {
                grid.getSquares()[i][j]=GRID.GRASS;
            }
        }

    }

    public void checkButtonClick() {
            if (keyHandler.getClickPoint() != null) {
                if (shop.getShopButtons()[0][0].contains(keyHandler.getClickPoint())) {
                    holdsItem = GRID.GRASS;
                } else if (shop.getShopButtons()[0][1].contains(keyHandler.getClickPoint())) {
                    holdsItem = GRID.PATH;
                } else if (shop.getShopButtons()[1][0].contains(keyHandler.getClickPoint())) {
                    holdsItem = GRID.CROSSROAD;
                } else if (shop.getShopButtons()[1][1].contains(keyHandler.getClickPoint())) {
                    holdsItem = GRID.START;
                }
                else if (shop.getShopButtons()[2][0].contains(keyHandler.getClickPoint())) {
                    holdsItem = GRID.FINISH;
                }
                else if (shop.getShopButtons()[2][1].contains(keyHandler.getClickPoint())) {
                    holdsItem = GRID.TOWER;
                                }
                else if (shop.getShopButtons()[3][0].contains(keyHandler.getClickPoint())) {
                    holdsItem = GRID.NOTHING;
                }


            }
        }


}*/
