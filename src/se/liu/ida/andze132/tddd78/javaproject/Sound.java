package se.liu.ida.andze132.tddd78.javaproject;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;


/**
 * Created by Administrat�r on 2015-04-21.
 */
public class Sound
{
    private static String beardeath = "sounds/beardeath.wav";
    private static String bossbeardeath = "sounds/bossbeardeath.wav";
    private static String mainTheme = "sounds/song.aiff";
    public static boolean noMusic;
    public static boolean noGameAudio;

    public Sound() {
	this.noMusic = false;
	this.noGameAudio = false;
    }

    public static synchronized void playSound(final String sound) {
      new Thread(() -> {
	try {
	  Clip clip = AudioSystem.getClip();
	    Clip clip2 = AudioSystem.getClip();
	    if(noMusic){clip2.stop();}
	    while(!noGameAudio){

	    	    AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(sound));
	    	    clip.open(inputStream);
	    	  clip.start();
	    	}
	    while(!noMusic){
	    if(sound == "sounds/song.aiff"){
		String sound2 = sound;
		AudioInputStream inputStream2 = AudioSystem.getAudioInputStream(new File(sound2));

		clip2.open(inputStream2);
		clip2.start();
		    while(Menu.ifMainSound && !noMusic){
		    		clip2.loop(1);
	    }}
	    }

	    } catch (Exception e) {
	  System.err.println(e.getMessage());
	}
      }).start();
    }
  public static void playDyingBear(){
      playSound(beardeath);
  }
    public static void playDyingBossBear(){
        playSound("sounds/bossbeardeath.wav");
    }
    public static void playMainTheme(){
	playSound(mainTheme);
    }

}
