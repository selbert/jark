package ch.unisi.inf.pfii.teamblue.jark.view.levelcreator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import ch.unisi.inf.pfii.teamblue.jark.implementation.Constants;
import ch.unisi.inf.pfii.teamblue.jark.model.level.LevelManager;

/**
 * The game panel, it should show the bricks the balls and the vaus
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 * 
 */

public final class CenterPanel extends JComponent implements Constants {
	private final FieldPanel fieldPanel;
	private final FieldImage fieldImage;
	
	public CenterPanel(final LevelManager levelManager, final ButtonGroup group) {
		final GridBagLayout gbl = new GridBagLayout();
		final GridBagConstraints gbc = new GridBagConstraints();
		setLayout(gbl);
		
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.insets = new Insets(0,0,5,0);
		
		JPanel borderPanel = new JPanel();
		borderPanel.setLayout(null);
		borderPanel.setPreferredSize(new Dimension(800,402));
		borderPanel.setBackground(Color.BLACK);
	    fieldPanel = new FieldPanel(levelManager, group);
	    fieldImage = new FieldImage(fieldPanel);
		borderPanel.add(fieldPanel);
		add(borderPanel, gbc);
		
	
		gbc.insets = new Insets(0,0,0,0);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		
		JTabbedPane tabbedPane = new JTabbedPane();
		
		BricksPanel bricks = new BricksPanel(group);
		tabbedPane.addTab("Bricks", bricks);
		BonusPanel bp = new BonusPanel(fieldPanel, group);
		tabbedPane.addTab("Bonus", bp);
		OptionPanel optionPanel = new OptionPanel(tabbedPane, fieldPanel);
		tabbedPane.addTab("Options", optionPanel);
		
		add(tabbedPane, gbc);
		
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridx = 1;
		gbc.gridy = 1;
		InteractionPanel test = new InteractionPanel(this, levelManager, fieldImage);
		add(test, gbc);
	}

	public final FieldPanel getFieldPanel() {
		return fieldPanel;
	}

}
