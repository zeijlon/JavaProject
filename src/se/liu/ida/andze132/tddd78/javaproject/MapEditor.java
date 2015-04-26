package se.liu.ida.andze132.tddd78.javaproject;

/**
 * Created by josefinezeijlon on 15-04-26.
 */
public class MapEditor {
    private GRID grid;
    private KeyHandler keyHandler;

    public MapEditor(GRID grid, KeyHandler keyHandler){
        this.grid = grid;
        this.keyHandler = keyHandler;
        for (int i = 0; i < grid.getSquares().length; i++) {
            for (int j = 0; j < grid.getSquares()[i].length; j++) {
                grid.getSquares()[i][j]=GRID.GRASS;
            }
        }

    }


}
