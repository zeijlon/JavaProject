package se.liu.ida.andze132.tddd78.javaproject;

import java.util.Comparator;

/**
 * Created by Administrat�r on 2015-04-30.
 */
class ScoreComparator implements Comparator<HighScore>
{
    public int compare(HighScore highscore1, HighScore highscore2) {
	if(highscore1.getHighScore() < highscore2.getHighScore()){return 1;}
	else if(highscore1.getHighScore() == highscore2.getHighScore()){return 0;}
	else{return -1;}
    }

}
