package ch.unisi.inf.pfii.teamblue.jark.view;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class InfoPanel extends JPanel {

	private final JLabel label;
	
	public InfoPanel() {
		
		setBackground(Color.YELLOW);
		
		label = new JLabel("Early tests with SWING");
		
		add(label);
		
	}
	
}
