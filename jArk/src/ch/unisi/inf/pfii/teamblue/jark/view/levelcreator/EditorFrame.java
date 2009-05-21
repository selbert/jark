package ch.unisi.inf.pfii.teamblue.jark.view.levelcreator;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import ch.unisi.inf.pfii.teamblue.jark.model.level.LevelManager;

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
	private final ButtonGroup group;
	private final LevelManager levelManager;

	public EditorFrame(final LevelManager levelManager, final boolean visible) {
		setTitle("[ jArk ] [ Level Creator ]");
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		setResizable(false);
		((JPanel) getContentPane()).setBorder(new EmptyBorder(6, 6, 6, 6));
		setLayout(new BorderLayout(6, 6));
		levelManager.reset();

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(final WindowEvent ev) {
				quitIfConfirmed();
			}
		});

		makeMenu();

		this.levelManager = levelManager;
		group = new ButtonGroup();
		centerPanel = new CenterPanel(levelManager, group);

		add(centerPanel, BorderLayout.CENTER);

		// pack the frame together
		pack();
		// center
		setLocationRelativeTo(null);
		// and show it
		setVisible(visible);
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

			}
		});
		fileMenu.add(newGameItem);

		// another item
		final JMenuItem quitItem = new JMenuItem("Quit");
		quitItem.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				quitIfConfirmed();
			}
		});
		fileMenu.add(quitItem);

		// set the menubar
		setJMenuBar(menubar);
	}

	private void quitIfConfirmed() {
		if (centerPanel.getFieldPanel().hasBeenSaved()
				|| JOptionPane.YES_OPTION == JOptionPane
						.showConfirmDialog(
								EditorFrame.this,
								"The level has not been saved, all changes will be lost.\nQuit anyway ?",
								"Quit?", JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE)) {
			levelManager.reset();
			dispose();
		}
	}

}
