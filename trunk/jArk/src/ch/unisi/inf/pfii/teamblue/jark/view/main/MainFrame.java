package ch.unisi.inf.pfii.teamblue.jark.view.main;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import ch.unisi.inf.pfii.teamblue.jark.model.Game;
import ch.unisi.inf.pfii.teamblue.jark.model.level.LevelManager;
import ch.unisi.inf.pfii.teamblue.jark.view.ImagesRepository;
import ch.unisi.inf.pfii.teamblue.jark.view.game.GameFrame;
import ch.unisi.inf.pfii.teamblue.jark.view.levelcreator.EditorFrame;

@SuppressWarnings("serial")
public final class MainFrame extends JFrame {

	private final CardLayout cardLayout;
	private final CardPanel cardPanel;
	private final LevelManager levelManager;
	private String selectedButton;

	public MainFrame(final LevelManager levelManager,
			final ImagesRepository imagesReference) {
		setTitle("[ jArk ] [ an Arkanoid/Breakout clone ]");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		selectedButton = "arcade";
		this.levelManager = levelManager;
		final JPanel p = new JPanel();
		p.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		p.setLayout(new GridBagLayout());
		final GridBagConstraints gbc = new GridBagConstraints();

		final JLabel title = new JLabel(ImagesRepository.getIcon("title"),
				SwingConstants.CENTER);
		gbc.gridwidth = 2;
		gbc.ipady = 10;
		gbc.insets = new Insets(0, 0, 5, 0);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		p.add(title, gbc);

		final JButton arcadeButton = new JButton("Arcade Mode");
		arcadeButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				selectedButton = "arcade";
				cardLayout.show(cardPanel, "base");
			}
		});
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.insets = new Insets(0, 0, 1, 5);
		p.add(arcadeButton, gbc);

		final JButton singleLevelButton = new JButton("Single Level");
		singleLevelButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				cardPanel.updateLevelList();
				cardPanel.setSelectedItem();
				cardPanel.setSelectedLevel();
				selectedButton = "singleLevel";
				cardLayout.show(cardPanel, "level");
			}
		});
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		p.add(singleLevelButton, gbc);

		final JButton levelEditorButton = new JButton("Level Editor");
		levelEditorButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent ev) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						new EditorFrame(levelManager, true);
					}
				});
				cardLayout.show(cardPanel, "base");
			}
		});
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		p.add(levelEditorButton, gbc);

		final JButton highScoreButton = new JButton("High Score");
		highScoreButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent ev) {
				cardPanel.updateHighScore();
				cardLayout.show(cardPanel, "arcade");
			}
		});
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 1;
		p.add(highScoreButton, gbc);

		final JButton quitButton = new JButton("Quit");
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				System.exit(0);
			}
		});
		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.gridwidth = 1;
		gbc.insets = new Insets(5, 0, 0, 5);
		p.add(quitButton, gbc);

		cardLayout = new CardLayout();
		cardPanel = new CardPanel(cardLayout, levelManager);

		final JButton playButton = new JButton("Play");
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				if (selectedButton.equals("arcade")) {
					final Game game = new Game(false, levelManager);
					startGameGUI(game);
				}
				if (selectedButton.equals("singleLevel")
						&& cardPanel.getSelectedLevel() != null) {
					try {
						levelManager.readLevelFromFile("levels/"
								+ cardPanel.getSelectedLevel() + ".jark");
						final Game game = new Game(true, levelManager);
						startGameGUI(game);
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(cardPanel, "Problem loading file level", "Problem loading level", JOptionPane.ERROR_MESSAGE);
					}
				}
				cardLayout.show(cardPanel, "base");
			}
		});
		gbc.gridx = 1;
		gbc.gridy = 6;
		gbc.gridwidth = 1;
		gbc.insets = new Insets(5, 0, 0, 0);
		p.add(playButton, gbc);

		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(0, 0, 0, 0);
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.ipadx = 10;
		gbc.gridwidth = 1;
		gbc.gridheight = 5;
		gbc.weighty = 2;
		gbc.weightx = 2;
		p.add(cardPanel, gbc);

		add(p);

		makeMenu();

		// pack the frame together
		pack();
		// center
		setLocationRelativeTo(null);
		// and show it
		setVisible(true);
	}

	/**
	 * Create the Menu
	 */
	private final void makeMenu() {
		final JMenuBar menubar = new JMenuBar();

		// menu file
		final JMenu fileMenu = new JMenu("File");
		menubar.add(fileMenu);

		// menu items: using anonymous inner classes for events
		final JMenuItem newGameItem = new JMenuItem("Play Arcade");
		newGameItem.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				final Game game = new Game(false, levelManager);
				startGameGUI(game);
			}
		});
		fileMenu.add(newGameItem);

		// another item
		final JMenuItem rndLevelItem = new JMenuItem("Random Level");
		rndLevelItem.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				final Game game = new Game(true, levelManager);
				game.setRandomLevel(50);
				startGameGUI(game);
			}
		});
		fileMenu.add(rndLevelItem);

		// another item
		final JMenuItem editorItem = new JMenuItem("Level Editor");
		editorItem.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						new EditorFrame(levelManager, true);
					}
				});
				cardLayout.show(cardPanel, "base");
			}
		});
		fileMenu.add(editorItem);

		// another item
		final JMenuItem highItem = new JMenuItem("High Score");
		highItem.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				cardPanel.updateHighScore();
				cardLayout.show(cardPanel, "arcade");
			}
		});
		fileMenu.add(highItem);

		// another item
		final JMenuItem quitItem = new JMenuItem("Quit");
		quitItem.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				System.exit(0);
			}
		});
		fileMenu.add(quitItem);

		// menu help
		final JMenu helpMenu = new JMenu("Help");
		menubar.add(helpMenu);

		// another item
		final JMenuItem helpItem = new JMenuItem("Manual");
		helpItem.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				JOptionPane.showMessageDialog(null, "<html>To view the jArk Manual go to:<br> <a href=\"http://code.google.com/p/jark/downloads\">http://code.google.com/p/jark/downloads</a></html>", "Online Manual", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		helpMenu.add(helpItem);

		// another item
		final JMenuItem aboutItem = new JMenuItem("About");
		aboutItem.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				JOptionPane.showMessageDialog(null, "<html>jArk has been made by<br><b>Stefano Pongelli</b> &#60;pongells@lu.unisi.ch&#62; <br>and<br><b>Thomas Selber</b> &#60;selbert@lu.unisi.ch&#62;<br>for Programming Fundamentals 2 at UNISI.ch", "About jArk", JOptionPane.WARNING_MESSAGE);
			}
		});
		helpMenu.add(aboutItem);

		// set the menubar
		setJMenuBar(menubar);
	}
	
	private final void startGameGUI(final Game game) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new GameFrame(game);
			}
		});
	}
	
}
