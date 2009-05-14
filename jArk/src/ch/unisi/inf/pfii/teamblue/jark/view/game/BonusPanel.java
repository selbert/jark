package ch.unisi.inf.pfii.teamblue.jark.view.game;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import ch.unisi.inf.pfii.teamblue.jark.implementation.Constants;
import ch.unisi.inf.pfii.teamblue.jark.implementation.GameListener;
import ch.unisi.inf.pfii.teamblue.jark.model.Game;
import ch.unisi.inf.pfii.teamblue.jark.model.bonus.Bonus;
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
	
	public BonusPanel(final Game game) {
		setPreferredSize(new Dimension(150,200));
		setLayout(new GridLayout(0,2));
		
		game.addGameListener(new GameListener() {
			public void bonusLifeDecreased() {
				ArrayList<Bonus> takenBonuses = game.getTakenBonuses();
				removeAll();
				for(int i = 0; i < takenBonuses.size(); i++) {
					final Bonus b = takenBonuses.get(i);
					if (b.getLife() < PERSISTENT) {
						final ImageIcon im = ImagesReference.getIcon(b.toString());
						JRadioButton button = new JRadioButton(im);
						add(button);
						add(new JLabel(" "+(int)(b.getLife()/1000)));
					}
				}
			}
		});
	}
	
}
