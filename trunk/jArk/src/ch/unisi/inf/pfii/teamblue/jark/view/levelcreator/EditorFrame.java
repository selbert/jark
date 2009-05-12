package ch.unisi.inf.pfii.teamblue.jark.view.levelcreator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ch.unisi.inf.pfii.teamblue.jark.model.Game;
import ch.unisi.inf.pfii.teamblue.jark.model.level.LevelManager;
import ch.unisi.inf.pfii.teamblue.jark.view.ImagesReference;
import ch.unisi.inf.pfii.teamblue.jark.view.game.GameFrame;

/**
 * The main frame of the game
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 * 
 */

@SuppressWarnings("serial")
public final class EditorFrame extends JFrame {
	private final CenterPanel centerPanel;
	private final WestPanel westPanel;
	private final ButtonGroup group;
	
	public EditorFrame(LevelManager levelManager) {
		setTitle("[ jArk ] [ Level Creator ]");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBackground(Color.LIGHT_GRAY);
		((JPanel) getContentPane()).setBorder(new EmptyBorder(6, 6, 6, 6));
		setLayout(new BorderLayout(6, 6));

		makeMenu();
		
		group = new ButtonGroup();
		centerPanel = new CenterPanel(levelManager, group);
		westPanel = new WestPanel(levelManager, group);
	
		add(centerPanel, BorderLayout.CENTER);
		add(westPanel, BorderLayout.WEST);
	
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
