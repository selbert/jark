package ch.unisi.inf.pfii.teamblue.jark.view.game;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import ch.unisi.inf.pfii.teamblue.jark.model.Game;

/**
 * The main frame of the game
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 * 
 */

@SuppressWarnings("serial")
public final class GameFrame extends JFrame {
	private final MainPanel mainPanel;

	public GameFrame(final Game game) {
		setTitle("[ jArk ] [ by Stefano Pongelli and Thomas Selber ]");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBackground(Color.LIGHT_GRAY);
		((JPanel) getContentPane()).setBorder(new EmptyBorder(6, 6, 6, 6));

		mainPanel = new MainPanel(game);
		add(mainPanel);

		// pack the frame together
		pack();
		// center
		setLocationRelativeTo(null);
		// and show it
		setVisible(true);
	}

}
