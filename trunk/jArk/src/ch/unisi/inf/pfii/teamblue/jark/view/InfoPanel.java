package ch.unisi.inf.pfii.teamblue.jark.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import ch.unisi.inf.pfii.teamblue.jark.implementation.PlayerListener;
import ch.unisi.inf.pfii.teamblue.jark.model.Game;

/**
 * The info panel gives the player informations (score, lives, ..)
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 * 
 */

@SuppressWarnings("serial")
public final class InfoPanel extends JPanel {
	
	private JLabel lives;
	
	public InfoPanel(Game game) {
		setLayout(new GridLayout(3,1));
		
		game.getPlayer().addPlayerListener(new PlayerListener() {
			public void modifiedLives(int l) {
				lives.setText("Lives: "+l);
			}
		});
		
		JLabel title = new JLabel("Informations", JLabel.CENTER);
		title.setForeground(Color.BLUE);
		add(title);
		lives = new JLabel("Lives: "+game.getPlayer().getLives());
		add(lives);
	}
	
}
