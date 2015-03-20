package se.liu.ida.andze132.tddd78.javaproject;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collection;

public class GRID
{
    private SquareType[][] squares;
    private Collection<GridListener> gridlisteners = new ArrayList<>();


    public GRID(int n) {
	this.squares = Maps.getMap(n);
    }

    public SquareType[][] getSquares() {
	return squares;
    }

    public void addGridListener(GridListener bl) {
        gridlisteners.add(bl);
    }

    private void notifyListeners() {
        gridlisteners.forEach(GridListener::towerBuilt);
    }


    public void buildTower(int y, int x){
        for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares.length; j++) {
                if(i*40<y && y<i*40+40 && j*40<x && x<j*40+40){
                    if(squares[i][j] == SquareType.GRASS){
                    squares[i][j] = SquareType.TOWER;}
                }
            }
        }
    }

}

