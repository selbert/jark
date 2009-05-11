package ch.unisi.inf.pfii.teamblue.jark.view.levelcreator;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import ch.unisi.inf.pfii.teamblue.jark.model.Game;

@SuppressWarnings("serial")
public class WestPanel extends JPanel {
	private final BricksPanel bricksPanel;
	private final InteractionPanel interactionPanel;

	public WestPanel(CenterPanel gamePanel, ButtonGroup group) {
		setLayout(new BorderLayout());
		//setBorder(BorderFactory.createLineBorder(Color.black));
		setBorder(new EtchedBorder());
		
		bricksPanel = new BricksPanel(group);
		interactionPanel = new InteractionPanel();	
		
		add(bricksPanel, BorderLayout.NORTH);
		add(interactionPanel, BorderLayout.SOUTH);
	}
}