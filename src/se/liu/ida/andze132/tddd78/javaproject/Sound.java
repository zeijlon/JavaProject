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
    	private Clip clip = null;
	private static String bossbeardeath = "sounds/bossbeardeath.wav";
	private static boolean noMusic = false;
    private static boolean noGameAudio = false;
	private static boolean ifMainSound = false;
    	private static boolean clipPlaying = false;
    private static final int MAXVALUE = Integer.MAX_VALUE;



	public  Sound(String s){

	    try{
		AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(s));
		clip = AudioSystem.getClip();
		clip.open(inputStream);
	    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
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
	clip.loop(MAXVALUE);}
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

	public Clip getClip() {
		return clip;
	}

	public static String getBossbeardeath() {
		return bossbeardeath;
	}

	public static boolean isClipPlaying() {
		return clipPlaying;
	}

	public static int getMAXVALUE() {
		return MAXVALUE;
	}

	public static boolean getNoGameAudio(){
		return noGameAudio;
	}

	public static boolean getClipPlaying(){
		return clipPlaying;
	}
}
