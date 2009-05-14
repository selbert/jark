package ch.unisi.inf.pfii.teamblue.jark.view.game;

import java.awt.BorderLayout;
import java.awt.Graphics;

import javax.swing.JPanel;

import ch.unisi.inf.pfii.teamblue.jark.implementation.Constants;
import ch.unisi.inf.pfii.teamblue.jark.model.Game;

@SuppressWarnings("serial")
public class WestPanel extends JPanel implements Constants {
	private final InfoPanel infoPanel;
	private final InteractionPanel interactionPanel;
	private final BonusPanel bonusPanel;

	public WestPanel(GamePanel gamePanel, final Game game) {
		setLayout(new BorderLayout(6,6));
		
		infoPanel = new InfoPanel(game);
		interactionPanel = new InteractionPanel(gamePanel, game);
		bonusPanel = new BonusPanel(game);
		
		add(bonusPanel, BorderLayout.CENTER);
		add(infoPanel, BorderLayout.NORTH);
		add(interactionPanel, BorderLayout.SOUTH);
	}
	
	@Override
	public final void paintComponent(Graphics g) {
		
	}
	
}
