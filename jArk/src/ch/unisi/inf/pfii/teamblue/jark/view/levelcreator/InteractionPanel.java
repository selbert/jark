package ch.unisi.inf.pfii.teamblue.jark.view.levelcreator;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;


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
	
	public InteractionPanel(final CenterPanel centerPanel, final LevelManager levelManager) {
		setLayout(new GridLayout(3, 1));

		// first button
		saveButton = new JButton("Save");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				final String name = JOptionPane.showInputDialog("Level name:");
				if (name != null) {
					levelManager.writeLevelToFile(name);
				}
			}
		});
		
		// first button
		loadButton = new JButton("Load");
		loadButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				FileFilter f1 = new FileFilter() {

					public boolean accept(File file) {
						if (file.isDirectory() || file.getName().toLowerCase().endsWith(".jark")) {
							return true;
						}
						return false;
					}

					@Override
					public String getDescription() {
						return "jArk: level files";
					}
					
				};
				fc.setFileFilter(f1);
				
				int returnVal = fc.showOpenDialog(null);
				
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					levelManager.readLevelFromFile(fc.getSelectedFile().getAbsolutePath());
					centerPanel.getFieldPanel().repaint();
				}
				
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
