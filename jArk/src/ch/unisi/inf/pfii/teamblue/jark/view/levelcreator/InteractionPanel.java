package ch.unisi.inf.pfii.teamblue.jark.view.levelcreator;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import ch.unisi.inf.pfii.teamblue.jark.model.Game;
import ch.unisi.inf.pfii.teamblue.jark.model.level.LevelManager;
import ch.unisi.inf.pfii.teamblue.jark.view.game.GameFrame;

/**
 * The interaction panel hosts the buttons
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 * 
 */

@SuppressWarnings("serial")
public final class InteractionPanel extends JPanel {

	private final JButton saveButton;
	private final JButton loadButton;
	private final JButton testButton;
	
	public InteractionPanel(final LevelManager levelManager) {
		setLayout(new GridLayout(3, 1));

		// first button
		saveButton = new JButton("Save");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {

			}
		});
		
		// first button
		loadButton = new JButton("Load");
		loadButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {

			}
		});

		// another button
		testButton = new JButton("Test");
		testButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final Game game = new Game(true, levelManager);
				EventQueue.invokeLater(new Runnable() {
				    public void run() {
				    	new GameFrame(game);
				    }
				});	
			}
		});

		add(testButton);
		add(saveButton);
		add(loadButton);
	}

}
