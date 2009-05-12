package ch.unisi.inf.pfii.teamblue.jark;

import java.awt.EventQueue;

import ch.unisi.inf.pfii.teamblue.jark.model.level.LevelManager;
import ch.unisi.inf.pfii.teamblue.jark.view.ImagesReference;
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
		final ImagesReference imagesReference = new ImagesReference();
		
		EventQueue.invokeLater(new Runnable() {
		    public void run() {
		    	new MainFrame(levelManager, imagesReference);
		    }
		});		
	}

}
