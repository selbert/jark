package ch.unisi.inf.pfii.teamblue.jark;

import java.awt.EventQueue;

import ch.unisi.inf.pfii.teamblue.jark.model.level.LevelManager;
import ch.unisi.inf.pfii.teamblue.jark.view.ImagesRepository;
import ch.unisi.inf.pfii.teamblue.jark.view.main.MainFrame;

/**
 * The main class, to run the game.
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 *
 */

public class Main {
	
	public static void main(String[] args) {
		final LevelManager levelManager = new LevelManager();
		final ImagesRepository imagesRepo = new ImagesRepository();
		
		EventQueue.invokeLater(new Runnable() {
		    public void run() {
		    	new MainFrame(levelManager, imagesRepo);
		    }
		});		
	}

}
