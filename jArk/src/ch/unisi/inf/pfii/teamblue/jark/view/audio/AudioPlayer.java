package ch.unisi.inf.pfii.teamblue.jark.view.audio;

import java.applet.AudioClip;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AudioPlayer {
	
	public final static void play(String file) {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		executor.execute(new PlayFile(file));
	}
	
	class PlayFile implements Runnable {
		private String file;
		
		public PlayFile(String file) {
			this.file = file;
		}
		
		public void run() {
			AudioClip clip = java.applet.Applet.newAudioClip(getClass().getResource("sounds/"+file+".wav"));
			clip.play( );
		}

	}
}
