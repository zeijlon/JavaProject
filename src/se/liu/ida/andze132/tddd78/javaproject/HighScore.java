package se.liu.ida.andze132.tddd78.javaproject;

/**
 * Created by Administratör on 2015-04-30.
 */
public class HighScore
{
        private int highScore;
        private String name;

        public HighScore(final String name, final int score) {
    	this.name = name;
    	this.highScore = score;

        }

        public int getHighScore() {
    	return highScore;
        }

        public String getName() {
    	return name;
        }

}
