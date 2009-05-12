package ch.unisi.inf.pfii.teamblue.jark.view.main;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import ch.unisi.inf.pfii.teamblue.jark.model.Game;
import ch.unisi.inf.pfii.teamblue.jark.model.level.LevelManager;
import ch.unisi.inf.pfii.teamblue.jark.view.GameFrame;
import ch.unisi.inf.pfii.teamblue.jark.view.levelcreator.EditorFrame;

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
	private final LevelManager levelManager;
	
	public InteractionPanel(CenterPanel gamePanel, final LevelManager levelManager) {
		this.levelManager = levelManager;
		
		setLayout(new GridLayout(2, 1));
		
		// first button
		startButton = new JButton("New Game");
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				final Game game = new Game(false, levelManager);
				EventQueue.invokeLater(new Runnable() {
				    public void run() {
				    	new GameFrame(game);
				    }
				});	
			}
		});

		// another button
		stopButton = new JButton("Level Editor");
		stopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				EventQueue.invokeLater(new Runnable() {
				    public void run() {
				    	new EditorFrame(levelManager);
				    }
				});	
				
			}
		});

		add(startButton);
		add(stopButton);
	}

}
