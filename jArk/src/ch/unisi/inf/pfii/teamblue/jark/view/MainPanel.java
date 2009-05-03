package ch.unisi.inf.pfii.teamblue.jark.view;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import ch.unisi.inf.pfii.teamblue.jark.model.Game;

/**
 * 
 * The main panel of the game
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 * 
 */

public class MainPanel extends JPanel {

	private final GamePanel gamePanel;
	private final InfoPanel infoPanel;
	private final InteractionPanel interactionPanel;

	private final Game game;

	public MainPanel() {

		setLayout(new BorderLayout(6, 6));

		gamePanel = new GamePanel();
		infoPanel = new InfoPanel();
		game = new Game(gamePanel);
		interactionPanel = new InteractionPanel(gamePanel, game);

		add(gamePanel, BorderLayout.CENTER);
		add(infoPanel, BorderLayout.NORTH);
		add(interactionPanel, BorderLayout.WEST);
	}

}
