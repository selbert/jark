package ch.unisi.inf.pfii.teamblue.jark.view.levelcreator;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import ch.unisi.inf.pfii.teamblue.jark.model.Game;

/**
 * The interaction panel hosts the buttons
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 * 
 */

@SuppressWarnings("serial")
public final class InteractionPanel extends JPanel {

	private final JButton startButton;
	private final JButton stopButton;

	public InteractionPanel() {
		setLayout(new GridLayout(2, 1));

		// first button
		startButton = new JButton("Save");
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {

			}
		});

		// another button
		stopButton = new JButton("Test");
		stopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	
			}
		});

		add(startButton);
		add(stopButton);
	}

}
