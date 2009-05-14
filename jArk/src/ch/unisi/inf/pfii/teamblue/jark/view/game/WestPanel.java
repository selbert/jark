package ch.unisi.inf.pfii.teamblue.jark.view.game;

import java.awt.BorderLayout;
import java.awt.Color;
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
import ch.unisi.inf.pfii.teamblue.jark.implementation.GameListener;
import ch.unisi.inf.pfii.teamblue.jark.model.Game;
import ch.unisi.inf.pfii.teamblue.jark.model.bonus.Bonus;
import ch.unisi.inf.pfii.teamblue.jark.view.ImagesReference;

@SuppressWarnings("serial")
public class WestPanel extends JPanel {
	private final InfoPanel infoPanel;
	private final InteractionPanel interactionPanel;
	private final JPanel bonusList;
	private int numberOfBonus;

	public WestPanel(GamePanel gamePanel, final Game game) {
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createLineBorder(Color.black));
		
		infoPanel = new InfoPanel(game);
		interactionPanel = new InteractionPanel(gamePanel, game);
		bonusList = new JPanel();
		numberOfBonus = 0;
		
		game.addGameListener(new GameListener() {
			public void bonusLifeDecreased() {
				ArrayList<Bonus> takenBonuses = game.getTakenBonuses();
				bonusList.removeAll();
				for(int i = 0; i < takenBonuses.size(); i++) {
					final Bonus b = takenBonuses.get(i);
					if (b.getLife() < Integer.MAX_VALUE) {
						final ImageIcon im = ImagesReference.getIcon(b.toString());
						JRadioButton button = new JRadioButton(im);
						bonusList.add(button, BorderLayout.EAST);
						bonusList.add(new JLabel(" "+(int)(b.getLife()/1000)), BorderLayout.WEST);
					}
				}
				String[] bonusesLifes = new String[takenBonuses.size()];
				for (int i = 0; i < bonusesLifes.length; i++) {
					bonusesLifes[i] = takenBonuses.get(i).getLife() + "";
				}
				WestPanel.this.repaint();
			}
		});
		
		add(bonusList, BorderLayout.CENTER);
		add(infoPanel, BorderLayout.NORTH);
		add(interactionPanel, BorderLayout.SOUTH);
	}
	
	public final void paintComponent(Graphics g) {
		
	}
	
}
