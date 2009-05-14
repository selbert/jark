package ch.unisi.inf.pfii.teamblue.jark.view.game;

import java.awt.Color;
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

	public InteractionPanel(final GamePanel gamePanel, final Game game) {
		setLayout(new GridLayout(2, 1));
	
		
		// first button
		startButton = new JButton("Add Ball");
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				game.addRandomBall();
				gamePanel.requestFocusInWindow();
			}
		});

		// another button
		stopButton = new JButton("Play/Pause");
		stopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gamePanel.play();
				gamePanel.requestFocusInWindow();
			}
		});
		

		add(startButton);
		add(stopButton);
	}

}
