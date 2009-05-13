package ch.unisi.inf.pfii.teamblue.jark.view.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JList;

import ch.unisi.inf.pfii.teamblue.jark.implementation.BonusListener;
import ch.unisi.inf.pfii.teamblue.jark.implementation.GameListener;
import ch.unisi.inf.pfii.teamblue.jark.model.Game;
import ch.unisi.inf.pfii.teamblue.jark.model.bonus.Bonus;

@SuppressWarnings("serial")
public class WestPanel extends JPanel {
	private final InfoPanel infoPanel;
	private final InteractionPanel interactionPanel;
	private final JList bonusList;

	public WestPanel(GamePanel gamePanel, final Game game) {
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createLineBorder(Color.black));
		
		infoPanel = new InfoPanel(game);
		interactionPanel = new InteractionPanel(gamePanel, game);
		bonusList = new JList();
		
		game.addGameListener(new GameListener() {
			public void addedBonus(Bonus bonus) {
				bonus.addBonusListener(new BonusListener() {
					public void bonusTaken(Bonus bonus) {
					}

					public void lifeDecreased(Bonus bonus) {
						ArrayList<Bonus> takenBonuses = game.getTakenBonuses();
						String[] bonusesLifes = new String[takenBonuses.size()];
						for (int i = 0; i < bonusesLifes.length; i++) {
							bonusesLifes[i] = takenBonuses.get(i).getLife() + "";
						}
						bonusList.setListData(bonusesLifes);
						WestPanel.this.repaint();
					}
					
				});
			}
		});
		
		add(bonusList, BorderLayout.CENTER);
		add(infoPanel, BorderLayout.NORTH);
		add(interactionPanel, BorderLayout.SOUTH);
	}
	
	public final void paintComponent(Graphics g) {
		
	}
	
}
