package ch.unisi.inf.pfii.teamblue.jark.view.game;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import ch.unisi.inf.pfii.teamblue.jark.implementation.BonusListener;
import ch.unisi.inf.pfii.teamblue.jark.implementation.Constants;
import ch.unisi.inf.pfii.teamblue.jark.implementation.GameListener;
import ch.unisi.inf.pfii.teamblue.jark.implementation.LevelListener;
import ch.unisi.inf.pfii.teamblue.jark.model.Game;
import ch.unisi.inf.pfii.teamblue.jark.model.bonus.BallBonus;
import ch.unisi.inf.pfii.teamblue.jark.model.bonus.Bonus;
import ch.unisi.inf.pfii.teamblue.jark.model.bonus.ResetStatusBonus;
import ch.unisi.inf.pfii.teamblue.jark.model.bonus.VausBonus;
import ch.unisi.inf.pfii.teamblue.jark.model.brick.Brick;
import ch.unisi.inf.pfii.teamblue.jark.view.ImagesReference;

/**
 * The bonus panel displays the active bonuses with their life span
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 * 
 */

@SuppressWarnings("serial")
public final class BonusPanel extends JPanel implements Constants {
	private final HashMap<Bonus, JLabel> labels;
	
	public BonusPanel(final Game game) {
		setPreferredSize(new Dimension(120,200));
		setLayout(new BoxLayout(BonusPanel.this, BoxLayout.Y_AXIS));
		
		labels = new HashMap<Bonus, JLabel>();
		
		game.getLevel().addLevelListener(new LevelListener() {
			public void bonusReleased(Bonus bonus) {
				bonus.addBonusListener(new BonusListener() {
					public void bonusTaken(Bonus bonus) {
						int lifeSpan = bonus.getLife();
						
						if (bonus instanceof ResetStatusBonus) {
							removeAll();
							repaint();
							labels.clear();
						}
						
						if (lifeSpan > 0 && lifeSpan < PERSISTENT) {
							Set<Bonus> set = labels.keySet();
							for (Bonus b : set) {
								if (((b instanceof BallBonus && bonus instanceof BallBonus)
										|| (b instanceof VausBonus && bonus instanceof VausBonus))
										&& (b.getBonusClass() == bonus.getBonusClass())) {
									remove(labels.get(b));
									repaint();
								}
							}		
							JLabel bonusLabel = new JLabel();
							bonusLabel.setIcon(ImagesReference.getIcon(bonus.toString()));
							bonusLabel.setText(""+bonus.getLife()/1000);
							add(bonusLabel);
							labels.put(bonus, bonusLabel);
						}
					}
					public void lifeDecreased(Bonus bonus) {
						int lifeSpan = bonus.getLife();
						if (lifeSpan > 0 && lifeSpan < PERSISTENT) {
							labels.get(bonus).setText(""+lifeSpan/1000);
						} else {
							remove(labels.get(bonus));
							labels.remove(bonus);
						}
					}
				});
			}
			public void brickHit(Brick brick) {	
			}
		});
	}
}
