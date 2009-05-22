package ch.unisi.inf.pfii.teamblue.jark.view.audio;

import java.applet.AudioClip;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AudioPlayer {
	private static ExecutorService executor;
	static {
		executor = Executors.newFixedThreadPool(5);
	}
	public final static void play(final String file) {
		executor.execute(new Runnable() {
			public void run() {
				AudioClip clip = java.applet.Applet.newAudioClip(getClass().getResource("sounds/"+file+".wav"));
				clip.play( );
			}
		});
	}
}