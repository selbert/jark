package ch.unisi.inf.pfii.teamblue.jark.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import ch.unisi.inf.pfii.teamblue.jark.model.Game;

public class InteractionPanel extends JPanel {

	private final JButton startButton;
	private final JButton stopButton;

	private final GamePanel gamePanel;
	private final Game game;
	

	public InteractionPanel(final GamePanel gamePanel, final Game game) {
		this.gamePanel = gamePanel;
		this.game = game;
		
		setLayout(new GridLayout(2, 1));
		
		// first button
		startButton = new JButton("Start");
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				game.play(); 
			}
		});

		// another button
		stopButton = new JButton("Stop");
		stopButton.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
				game.play(); 
				
			}
		});
		
		add(startButton);
		add(stopButton);

	}

}
