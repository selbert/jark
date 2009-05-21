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
	private final JButton clearButton;

	public InteractionPanel(final CenterPanel centerPanel,
			final LevelManager levelManager, final FieldImage fieldImage) {
		setLayout(new GridLayout(4, 1, 0, 5));

		// first button
		saveButton = new JButton("Save");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				final FieldPanel fp = centerPanel.getFieldPanel();
				final OptionPanel op = centerPanel.getOptionPanel();
				if (op.getLevelAuthor() == null || op.getLevelName() == null) {
					JOptionPane.showMessageDialog(centerPanel, "You must input valid level name and author before saving it.", "Missing informations", JOptionPane.WARNING_MESSAGE);
					centerPanel.showOptionTab();
					return;
				}
				final String name = JOptionPane
						.showInputDialog("The level will be saved in the folder \"levels/\", \na \".jark\" extension will be automatically added.\n\nInput a name:");
				if (name != null) {
					
					levelManager.setLevelAuthor(op.getLevelAuthor());
					levelManager.setLevelName(op.getLevelName());
					levelManager.setRandomBonusPercentage(op.getRandomBonusNum()+ "");

					if (levelManager.writeLevelToFile(name)) {
						fp.togglePanelForPrintScreen();
						fieldImage.saveImage(name);
						fp.setSaved(true);
						fp.togglePanelForPrintScreen();
					}
				}
			}
		});

		// first button
		loadButton = new JButton("Load");
		loadButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				final JFileChooser fc = new JFileChooser();
				fc.setCurrentDirectory(new File("./levels"));
				fc.setFileFilter(new FileFilter() {
					@Override
					public boolean accept(final File file) {
						if (file.isDirectory() || file.getName().toLowerCase().endsWith(".jark")) {
							return true;
						}
						return false;
					}

					@Override
					public String getDescription() {
						return "jArk: level files";
					}
				});

				final int returnVal = fc.showOpenDialog(null);

				if (returnVal == JFileChooser.APPROVE_OPTION
						&& (centerPanel.getFieldPanel().hasBeenSaved() || JOptionPane.YES_OPTION == JOptionPane
								.showConfirmDialog(
										null,
										"The level has not been saved, all changes will be lost.\nLoad a new level anyway ?",
										"Load?", JOptionPane.YES_NO_OPTION,
										JOptionPane.QUESTION_MESSAGE))) {
					centerPanel.getFieldPanel().setSaved(true);
					levelManager.readLevelFromFile(fc.getSelectedFile()
							.getAbsolutePath());
					centerPanel.getOptionPanel().setLevelAuthor(
							levelManager.getLevelAuthor());
					centerPanel.getOptionPanel().setLevelName(
							levelManager.getLevelName());
					centerPanel.getOptionPanel().setRandomBonusNum(
							levelManager.getBonusPercentage());
					centerPanel.getFieldPanel().repaint();
				}
			}
		});

		// another button
		testButton = new JButton("Test");
		testButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				levelManager.setLevelName("Test Level");
				final OptionPanel op = centerPanel.getOptionPanel();
				levelManager.setRandomBonusPercentage(op.getRandomBonusNum()
						+ "");
				final Game game = new Game(true, levelManager);
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						new GameFrame(game);
					}
				});
			}
		});

		// another button
		clearButton = new JButton("Clear");
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				levelManager.reset();
				centerPanel.getFieldPanel().reset();
				centerPanel.getFieldPanel().repaint();
			}
		});

		add(testButton);
		add(saveButton);
		add(loadButton);
		add(clearButton);
	}

}
