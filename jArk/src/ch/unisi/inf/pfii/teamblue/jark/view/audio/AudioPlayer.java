package ch.unisi.inf.pfii.teamblue.jark.view.audio;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class AudioPlayer {
	 	private static Player player;
	 
	   public static void play(final String filename) {
	        try {
	            BufferedInputStream bis = new BufferedInputStream(AudioPlayer.class.getResourceAsStream("sounds/"+filename+".mp3"));
	            player = new Player(bis);
	        }
	        catch (Exception e) {
	            System.out.println("Problem playing file " + filename);
	            System.out.println(e);
	        }

	        new Thread() {
	            public void run() {
	                try { player.play(); }
	                catch (Exception e) { System.out.println(e); }
	            }
	        }.start();

	    }
}