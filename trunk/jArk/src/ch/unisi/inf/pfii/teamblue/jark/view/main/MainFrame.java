package ch.unisi.inf.pfii.teamblue.jark.view.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ch.unisi.inf.pfii.teamblue.jark.model.level.LevelManager;
import ch.unisi.inf.pfii.teamblue.jark.view.ImagesReference;

/**
 * The main frame of the game
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 * 
 */

@SuppressWarnings("serial")
public final class MainFrame extends JFrame {
	private final CenterPanel centerPanel;
	private final InteractionPanel interactionPanel;
	private final LevelManager levelManager;
	private final ImagesReference imagesReference;
	
	public MainFrame() {
		setTitle("[ jArk ] [ Arkanoid/BreakOut ]");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setPreferredSize(new Dimension(800,600));
		setBackground(Color.LIGHT_GRAY);
		((JPanel) getContentPane()).setBorder(new EmptyBorder(6, 6, 6, 6));
		setLayout(new BorderLayout(6, 6));
		
		levelManager = new LevelManager();
		imagesReference = new ImagesReference();

		makeMenu();
		
		centerPanel = new CenterPanel();
		add(centerPanel, BorderLayout.CENTER);
		interactionPanel = new InteractionPanel(centerPanel, levelManager);
		add(interactionPanel, BorderLayout.SOUTH);
		
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
		final JMenuItem newGameItem = new JMenuItem("New Game");
		newGameItem.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				startNewGame();
			}
		});
		fileMenu.add(newGameItem);

		// another item
		final JMenuItem quitItem = new JMenuItem("Quit");
		quitItem.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				quitApp();
			}
		});
		fileMenu.add(quitItem);

		//set the menubar
		setJMenuBar(menubar);
	}

	private final void startNewGame() {
		//TODO ?
	}

	private final void quitApp() {
		System.exit(0);
	}

}
