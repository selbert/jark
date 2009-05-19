package ch.unisi.inf.pfii.teamblue.jark.view.levelcreator;

import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import ch.unisi.inf.pfii.teamblue.jark.view.ImagesRepository;

/**
 * The info panel gives the player informations (score, lives, ..)
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 * 
 */

@SuppressWarnings("serial")
public final class BonusPanel extends JPanel {
	
	public BonusPanel(final FieldPanel fp, ButtonGroup group) {
		setBorder(new EtchedBorder());
		setLayout(new GridLayout(3,1));

		JPanel bonus = new JPanel();
		bonus.setLayout(new BoxLayout(bonus,BoxLayout.X_AXIS));
			JLabel bonusLabel = new JLabel(" Bonus:  ", SwingConstants.CENTER);
			JPanel bonusButtons = new JPanel();
				bonusButtons.setLayout(new FlowLayout(FlowLayout.LEFT));
			bonus.add(bonusLabel);
			bonus.add(bonusButtons);
		add(bonus);
		
		JPanel malus = new JPanel();
		malus.setLayout(new BoxLayout(malus,BoxLayout.X_AXIS));
			JLabel malusLabel = new JLabel(" Malus:  ", SwingConstants.CENTER);
			JPanel malusButtons = new JPanel();
				malusButtons.setLayout(new FlowLayout(FlowLayout.LEFT));
			malus.add(malusLabel);
			malus.add(malusButtons);
		add(malus);
		
		JPanel neutral = new JPanel();
		neutral.setLayout(new BoxLayout(neutral,BoxLayout.X_AXIS));
			JLabel neutralLabel = new JLabel(" Neutral:", SwingConstants.CENTER);
			JPanel neutralButtons = new JPanel();
				neutralButtons.setLayout(new FlowLayout(FlowLayout.LEFT));
			neutral.add(neutralLabel);
			neutral.add(neutralButtons);
		add(neutral);
		
		Enumeration<Object> keys = ImagesRepository.getKeys();
		while (keys.hasMoreElements()) {
			String key = (String) keys.nextElement();
			if (key.indexOf("bonus_") != -1 || key.indexOf("malus_") != -1 || key.indexOf("neutral_") != -1) {
				final ImageIcon im = ImagesRepository.getIcon(key);
				final ImageIcon him = ImagesRepository.getHighlightedIcon(im);
				 JRadioButton button = new JRadioButton(im);
				button.setSelectedIcon(him);
				button.setHorizontalAlignment(SwingConstants.CENTER);
				button.setVerticalAlignment(SwingConstants.CENTER);
				button.setActionCommand(key);
				button.setToolTipText(key);
				button.setCursor(new Cursor(Cursor.HAND_CURSOR));
				
				group.add(button);
				
				if (key.indexOf("bonus_") != -1) {
					bonusButtons.add(button);
				} else if (key.indexOf("malus_") != -1) {
					malusButtons.add(button);
				} else {
					neutralButtons.add(button);
				}
			}
		}
	}
	
}
