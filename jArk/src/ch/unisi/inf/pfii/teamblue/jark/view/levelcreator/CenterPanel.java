package ch.unisi.inf.pfii.teamblue.jark.view.levelcreator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.plaf.TabbedPaneUI;
import javax.swing.plaf.metal.MetalTabbedPaneUI;

import ch.unisi.inf.pfii.teamblue.jark.implementation.Constants;
import ch.unisi.inf.pfii.teamblue.jark.model.level.LevelManager;
import ch.unisi.inf.pfii.teamblue.jark.view.ImagesRepository;

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
		setLayout(new BorderLayout(0,6));
		JPanel borderPanel = new JPanel();
		borderPanel.setLayout(null);
		borderPanel.setPreferredSize(new Dimension(800,402));
		borderPanel.setBackground(Color.BLACK);
		
	    fieldPanel = new FieldPanel(levelManager, group);
	    fieldImage = new FieldImage(fieldPanel);
		borderPanel.add(fieldPanel);
		add(borderPanel, BorderLayout.NORTH);
		
		JPanel south = new JPanel();
		JTabbedPane tabbedPane = new JTabbedPane();
		
		JPanel bricks = new JPanel();
		
		final String[] str = new String[] { "defaultBrick", "resistentBrick", "veryResistentBrick", "persistentBrick", "removeBrick"  };
	
		for (int i = 0; i<str.length; i++) {
			final ImageIcon im = ImagesRepository.getIcon(str[i]);
			final ImageIcon him = ImagesRepository.getHighlightedIcon(im);
			 JRadioButton button = new JRadioButton(im);
			button.setSelectedIcon(him);
			button.setHorizontalAlignment(SwingConstants.CENTER);
			button.setActionCommand(str[i]);
			button.setToolTipText(str[i]);
			
			group.add(button);
			bricks.add(button);
		}
		tabbedPane.addTab("Bricks", bricks);
		
		BonusPanel bp = new BonusPanel(fieldPanel, group);
		tabbedPane.addTab("Bonus", bp);
		
		JPanel options = new JPanel();
		tabbedPane.addTab("Options", options);
		
		south.add(tabbedPane, BorderLayout.WEST);
		
		InteractionPanel test = new InteractionPanel(this, levelManager, fieldImage);
		south.add(test, BorderLayout.EAST);
		
		add(south, BorderLayout.CENTER);
		
		
	}

	public final FieldPanel getFieldPanel() {
		return fieldPanel;
	}

}
