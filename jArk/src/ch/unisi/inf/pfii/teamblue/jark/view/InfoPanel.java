package ch.unisi.inf.pfii.teamblue.jark.view;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * The info panel gives the player informations (score, lives, ..)
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 * 
 */

public class InfoPanel extends JPanel {

	private final JLabel label;

	public InfoPanel() {
		
		setBorder(BorderFactory.createLineBorder(Color.black));

		label = new JLabel("Score:  Lives: [other info here]");

		add(label);

	}

}
