package se.liu.ida.andze132.tddd78.javaproject;

import java.util.Comparator;

/**
 * Created by Administratör on 2015-04-30.
 */
class ScoreComparator implements Comparator<HighScore>
{
    public int compare(HighScore highscore1, HighScore highscore2) {
	return highscore1.getHighScore() < highscore2.getHighScore() ? 1 : highscore1.getHighScore() == highscore2.getHighScore() ? 0 : -1;
    }

}
