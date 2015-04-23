package se.liu.ida.andze132.tddd78.javaproject;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
/*
import java.io.File;
import java.io.IOException;
import java.util.Objects;
*/


/**
 * Created by Administrat�r on 2015-04-21.
 */
final public class Sound
{
    	private Clip clip;
	private static String bossbeardeath = "sounds/bossbeardeath.wav";
	private static boolean noMusic = false;
    public static boolean noGameAudio = false;
	private static boolean ifMainSound = false;
    	public static boolean clipPlaying = false;


	public  Sound(String s){

	    try{
		AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(s));
		clip = AudioSystem.getClip();
		clip.open(inputStream);
	    }
	    catch(Exception e){
		e.printStackTrace();
	    }
	}
    public void play(){
	if(clip == null) return;clip.setFramePosition(0);clip.start();

    }
    public void stop(){
	if(clip.isRunning())
	    clip.stop();
    }
    public void loop(){
	if(!noMusic){
	clip.loop(999999);}
    }


    public static void setClipPlaying(final boolean clipPlaying) {
	Sound.clipPlaying = clipPlaying;
    }

    public static boolean isNoMusic() {
    		return noMusic;
    	}

    	public static void setNoMusic(boolean noMusic) {
    		Sound.noMusic = noMusic;
    	}

    	public static boolean isNoGameAudio() {
    		return noGameAudio;
    	}

    	public static void setNoGameAudio(boolean noGameAudio) {
    		Sound.noGameAudio = noGameAudio;
    	}

    	public static boolean isIfMainSound() {
    		return ifMainSound;
    	}

    	public static void setIfMainSound(boolean ifMainSound) {
    		Sound.ifMainSound = ifMainSound;
    	}



/*
	public static void playSound(final String sound) {
      new Thread(() -> {
	try(Clip clip = AudioSystem.getClip();
		Clip clip2 = AudioSystem.getClip()){
	    AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(sound));
	  	    	    clip.open(inputStream);
	    if(Objects.equals(sound, "sounds/song.aiff")){
	    AudioInputStream inputStream2 = AudioSystem.getAudioInputStream(new File(sound));
	    		clip2.open(inputStream2);}
		if(noMusic){clip2.stop();}
		while(!noGameAudio){


	    	  clip.start();
	    	}
		if(!noMusic){


		clip2.start();
			while(ifMainSound && !noMusic){
		    		clip2.loop(1);
	    }
	    }

	    } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
		e.printStackTrace();
	}
		  catch(IllegalStateException ignored){}
	  }).start();
    }
  public static void playDyingBear(){
	  String beardeath = "sounds/beardeath.wav";
	  playSound(beardeath);
  }
    public static void playDyingBossBear(){
        playSound("sounds/bossbeardeath.wav");
    }
    public static void playMainTheme(){
		String mainTheme = "sounds/song.aiff";
		playSound(mainTheme);
    }


	*/
}
