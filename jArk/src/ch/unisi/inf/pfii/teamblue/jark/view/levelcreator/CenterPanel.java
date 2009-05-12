package ch.unisi.inf.pfii.teamblue.jark.view.levelcreator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JPanel;

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
	
	public CenterPanel(final LevelManager levelManager, final ButtonGroup group) {
		setLayout(new BorderLayout(0,6));
		JPanel borderPanel = new JPanel();
		borderPanel.setLayout(null);
		borderPanel.setPreferredSize(new Dimension(800,402));
		borderPanel.setBackground(Color.BLACK);
	    fieldPanel = new FieldPanel(levelManager, group);
		borderPanel.add(fieldPanel);
		BonusPanel bp = new BonusPanel(fieldPanel, group);
		add(bp, BorderLayout.CENTER);
		add(borderPanel, BorderLayout.NORTH);
	}

	public final FieldPanel getFieldPanel() {
		return fieldPanel;
	}

}
