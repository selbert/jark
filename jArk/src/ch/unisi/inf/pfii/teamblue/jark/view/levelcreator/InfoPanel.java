package ch.unisi.inf.pfii.teamblue.jark.view.levelcreator;

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
	private JLabel level;
	private JLabel score;
	
	public InfoPanel(Game game) {
		setLayout(new GridLayout(4,1));
		
		game.getPlayer().addPlayerListener(new PlayerListener() {
			public void modifiedLives(int l) {
				lives.setText("Lives: "+l);
			}

			public void modifiedScore(int s) {
				score.setText("Score: "+s);
			}
		});
		
		JLabel title = new JLabel("Informations", JLabel.CENTER);
		title.setForeground(Color.BLUE);
		add(title);
		level = new JLabel("Level: "+"0");
		add(level);
		lives = new JLabel("Lives: "+game.getPlayer().getLives());
		add(lives);
		score = new JLabel("Score: "+game.getPlayer().getScore());
		add(score);
	}
	
}
