package ch.unisi.inf.pfii.teamblue.jark.view.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JRadioButton;

import ch.unisi.inf.pfii.teamblue.jark.implementation.BonusListener;
import ch.unisi.inf.pfii.teamblue.jark.implementation.Constants;
import ch.unisi.inf.pfii.teamblue.jark.implementation.GameListener;
import ch.unisi.inf.pfii.teamblue.jark.model.Game;
import ch.unisi.inf.pfii.teamblue.jark.model.bonus.Bonus;
import ch.unisi.inf.pfii.teamblue.jark.view.ImagesReference;

@SuppressWarnings("serial")
public class WestPanel extends JPanel implements Constants {
	private final InfoPanel infoPanel;
	private final InteractionPanel interactionPanel;
	private final BonusPanel bonusPanel;

	public WestPanel(GamePanel gamePanel, final Game game) {
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createLineBorder(Color.black));
		
		infoPanel = new InfoPanel(game);
		interactionPanel = new InteractionPanel(gamePanel, game);
		bonusPanel = new BonusPanel(game);
		
		add(bonusPanel, BorderLayout.CENTER);
		add(infoPanel, BorderLayout.NORTH);
		add(interactionPanel, BorderLayout.SOUTH);
	}
	
	public final void paintComponent(Graphics g) {
		
	}
	
}
