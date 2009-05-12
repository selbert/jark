package ch.unisi.inf.pfii.teamblue.jark.view.game;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import ch.unisi.inf.pfii.teamblue.jark.model.Game;

@SuppressWarnings("serial")
public class WestPanel extends JPanel {
	private final InfoPanel infoPanel;
	private final InteractionPanel interactionPanel;

	public WestPanel(GamePanel gamePanel, Game game) {
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createLineBorder(Color.black));
		
		infoPanel = new InfoPanel(game);
		interactionPanel = new InteractionPanel(gamePanel, game);	
		
		add(infoPanel, BorderLayout.NORTH);
		add(interactionPanel, BorderLayout.SOUTH);
	}
}
