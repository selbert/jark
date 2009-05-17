package ch.unisi.inf.pfii.teamblue.jark.view.game;

import java.awt.Color;
import java.util.HashMap;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import ch.unisi.inf.pfii.teamblue.jark.implementation.BonusListener;
import ch.unisi.inf.pfii.teamblue.jark.implementation.Constants;
import ch.unisi.inf.pfii.teamblue.jark.implementation.GameListener;
import ch.unisi.inf.pfii.teamblue.jark.implementation.LevelListener;
import ch.unisi.inf.pfii.teamblue.jark.model.Game;
import ch.unisi.inf.pfii.teamblue.jark.model.bonus.BallBonus;
import ch.unisi.inf.pfii.teamblue.jark.model.bonus.Bonus;
import ch.unisi.inf.pfii.teamblue.jark.model.bonus.VausBonus;
import ch.unisi.inf.pfii.teamblue.jark.model.brick.Brick;
import ch.unisi.inf.pfii.teamblue.jark.model.level.Level;
import ch.unisi.inf.pfii.teamblue.jark.view.ImagesRepository;

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
		setBorder(BorderFactory.createLineBorder(Color.black));
		setLayout(new BoxLayout(BonusPanel.this, BoxLayout.Y_AXIS));
		labels = new HashMap<Bonus, JLabel>();
	
		final JLabel title = new JLabel("Active Bonuses");
		title.setForeground(Color.BLUE);
		title.setBorder(new EmptyBorder(6,0,0,0));
		title.setAlignmentX(CENTER_ALIGNMENT);
		add(title);
		
		
		game.addGameListener(new GameListener() {
			public void bonusErase() {
				removeAll();
				labels.clear();
				revalidate();
				repaint();
				add(title);
			}

			public void levelChanged(Level level) {
				setLevelListener(level);
			}

			public void gameOver() {
			}

			public void levelCleared(Level level) {
			}
		});
		
		setLevelListener(game.getLevel());
	}
	private final void setLevelListener(Level level) {
		level.addLevelListener(new LevelListener() {
			public void bonusReleased(Bonus bonus) {
				bonus.addBonusListener(new BonusListener() {
					public void bonusTaken(Bonus bonus) {
						int lifeSpan = bonus.getLife();
						
						if (lifeSpan > 0 && lifeSpan < PERSISTENT) {
							Set<Bonus> set = labels.keySet();
							for (Bonus b : set) {
								if (((b instanceof BallBonus && bonus instanceof BallBonus)
										|| (b instanceof VausBonus && bonus instanceof VausBonus))
										&& (b.getBonusClass() == bonus.getBonusClass())) {
									remove(labels.get(b));
									revalidate();
									repaint();
								}
							}		
							JLabel bonusLabel = new JLabel();
							bonusLabel.setIcon(ImagesRepository.getIcon(bonus.toString()));
							bonusLabel.setText(getBonusLifeString(bonus.getLife()));
							bonusLabel.setAlignmentX(CENTER_ALIGNMENT);
							bonusLabel.setHorizontalTextPosition(SwingConstants.RIGHT);
							bonusLabel.setBorder(new EmptyBorder(10,0,0,0));
							add(bonusLabel);
							revalidate();
							labels.put(bonus, bonusLabel);
						}
					}
					
					public void lifeDecreased(Bonus bonus) {
						int lifeSpan = bonus.getLife();
						if (lifeSpan > 0 && lifeSpan < PERSISTENT) {
							labels.get(bonus).setText(getBonusLifeString(lifeSpan));
						} else {
							remove(labels.get(bonus));
							labels.remove(bonus);
							revalidate();
							repaint();
						}
					}
					
					private final String getBonusLifeString(final int life) {
						final int secs = life/1000;
						return (secs < 10) ? "0"+secs : ""+secs;
					}
				});
			}
			public void brickHit(Brick brick) {	
			}
		});

	}
}
