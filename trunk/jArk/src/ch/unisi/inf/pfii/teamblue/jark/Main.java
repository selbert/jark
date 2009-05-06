package ch.unisi.inf.pfii.teamblue.jark;

import java.awt.EventQueue;

import ch.unisi.inf.pfii.teamblue.jark.model.Game;
import ch.unisi.inf.pfii.teamblue.jark.view.GameFrame;

/**
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 *
 */

public class Main {
	
	public static void main(String[] args) {
		final Game game = new Game();
		
		EventQueue.invokeLater(new Runnable() {
		    public void run() {
		    	new GameFrame(game);
		    }
		});		
	}

}
