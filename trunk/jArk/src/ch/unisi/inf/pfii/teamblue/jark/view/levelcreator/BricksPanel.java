package ch.unisi.inf.pfii.teamblue.jark.view.levelcreator;

import java.awt.Container;
import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import ch.unisi.inf.pfii.teamblue.jark.view.ImagesRepository;

/**
 * The info panel gives the player informations (score, lives, ..)
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 * 
 */

@SuppressWarnings("serial")
public final class BricksPanel extends JPanel {
	
	public BricksPanel(ButtonGroup group) {
		setLayout(new GridBagLayout());
		final GridBagConstraints gbc = new GridBagConstraints();
		
		final String[] str = new String[] { "defaultBrick", "resistentBrick", "veryResistentBrick", "persistentBrick", "removeBrick"  };

		gbc.ipadx = 20;
		gbc.ipady = 15;
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(createButtonPanel("defaultBrick", group), gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		add(createButtonPanel("resistentBrick", group), gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		add(createButtonPanel("veryResistentBrick", group), gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		add(createButtonPanel("persistentBrick", group), gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.gridheight = 2;
		add(createButtonPanel("removeBrick", group), gbc);
		
	}
	
	private final JPanel createButtonPanel(String buttonString, ButtonGroup group) {
		final ImageIcon im = ImagesRepository.getIcon(buttonString);
		final ImageIcon him = ImagesRepository.getHighlightedIcon(im);
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		final GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridy = 0;
		JRadioButton button = new JRadioButton(im);
		button.setSelectedIcon(him);
		button.setActionCommand(buttonString);
		button.setToolTipText(buttonString);
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
		group.add(button);
		panel.add(button,gbc);
		
		gbc.gridy = 1;
		JLabel desc = new JLabel(buttonString);
		panel.add(desc,gbc);
		
		return panel;
	}
}
