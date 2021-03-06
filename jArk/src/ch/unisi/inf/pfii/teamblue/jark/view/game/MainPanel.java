package ch.unisi.inf.pfii.teamblue.jark.view.game;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import ch.unisi.inf.pfii.teamblue.jark.model.Game;

/**
 * The main panel of the game
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 * 
 */

public final class MainPanel extends JPanel {
	private final GamePanel gamePanel;
	private final WestPanel westPanel;

	public MainPanel(final Game game) {
		setLayout(new BorderLayout(6, 6));

		gamePanel = new GamePanel(game);
		westPanel = new WestPanel(gamePanel, game);

		add(gamePanel, BorderLayout.CENTER);
		add(westPanel, BorderLayout.WEST);
	}

}
