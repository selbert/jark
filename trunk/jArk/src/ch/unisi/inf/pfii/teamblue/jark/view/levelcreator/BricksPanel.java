package ch.unisi.inf.pfii.teamblue.jark.view.levelcreator;

import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Properties;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
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
public final class BricksPanel extends JPanel {
	private final Properties properties;

	public BricksPanel(final ButtonGroup group, final Properties properties) {
		setLayout(new GridBagLayout());
		setBorder(new EtchedBorder());
		final GridBagConstraints gbc = new GridBagConstraints();
		this.properties = properties;

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

	private final JPanel createButtonPanel(final String buttonString,
			final ButtonGroup group) {
		final ImageIcon im = ImagesRepository.getIcon(buttonString);
		final ImageIcon him = ImagesRepository.getHighlightedIcon(im);

		final JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		final GridBagConstraints gbc = new GridBagConstraints();

		gbc.gridy = 0;
		final JRadioButton button = new JRadioButton(im);
		button.setSelectedIcon(him);
		button.setActionCommand(buttonString);
		button.setToolTipText(properties.getProperty(buttonString));
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
		group.add(button);
		panel.add(button, gbc);

		gbc.gridy = 1;
		final JLabel desc = new JLabel(buttonString);
		panel.add(desc, gbc);

		return panel;
	}
}
