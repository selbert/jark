package ch.unisi.inf.pfii.teamblue.jark;

import java.awt.EventQueue;

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
		EventQueue.invokeLater(new Runnable() {
		    public void run() {
		    	new MainFrame();
		    }
		});		
	}

}
