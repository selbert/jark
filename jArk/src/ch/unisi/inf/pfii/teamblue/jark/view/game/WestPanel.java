package ch.unisi.inf.pfii.teamblue.jark.view.game;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import ch.unisi.inf.pfii.teamblue.jark.implementation.Constants;
import ch.unisi.inf.pfii.teamblue.jark.model.Game;

@SuppressWarnings("serial")
public class WestPanel extends JPanel implements Constants {
	private final InfoPanel infoPanel;
	private final BonusPanel bonusPanel;

	public WestPanel(final GamePanel gamePanel, final Game game) {
		setLayout(new BorderLayout(6, 6));
		setPreferredSize(new Dimension(110, getHeight()));

		infoPanel = new InfoPanel(game);
		bonusPanel = new BonusPanel(game);

		add(bonusPanel, BorderLayout.CENTER);
		add(infoPanel, BorderLayout.NORTH);
	}

	@Override
	public final void paintComponent(final Graphics g) {

	}

}
