package ch.unisi.inf.pfii.teamblue.jark.view;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ch.unisi.inf.pfii.teamblue.jark.model.Game;

/**
 * 
 * The info panel gives the player informations (score, lives, ..)
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 * 
 */

public class InfoPanel extends JPanel {

	private final JLabel lblLives;
	
	public InfoPanel(Game game) {
		
		setBorder(BorderFactory.createLineBorder(Color.black));

		JLabel label = new JLabel("Lives:");
		lblLives = new JLabel("");
		add(label);
		add(lblLives);

	}
	
	public void setLives(int lives) {
		lblLives.setText(lives+"");
	}
}
