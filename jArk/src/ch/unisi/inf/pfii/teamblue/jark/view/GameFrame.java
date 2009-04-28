package ch.unisi.inf.pfii.teamblue.jark.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * 
 * The main window of the game
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 * 
 */

public class GameFrame extends JFrame {

	// the game area
	private MainPanel center;
	
	public GameFrame() {
		makeFrame();
	}

	/**
	 * Create the main Window
	 */
	private void makeFrame() {
		setTitle("[ jArk ] [ Arkanoid as never seen before ]");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(300, 100);
		setResizable(false);
		setBackground(Color.ORANGE);
		((JPanel) getContentPane()).setBorder(new EmptyBorder(6, 6, 6, 6));

		makeMenu();
		center = new MainPanel();
		add(center);
		
		
		// pack the frame together
		pack();
		// and show it
		setVisible(true);
	}

	/**
	 * Create the Menu
	 */
	private void makeMenu() {
		final JMenuBar menubar = new JMenuBar();

		// menu file
		final JMenu fileMenu = new JMenu("File");
		menubar.add(fileMenu);

		// menu items: using anonymous inner classes for events
		final JMenuItem openItem = new JMenuItem("Open");
		openItem.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				openFile();
			}
		});
		fileMenu.add(openItem);

		// another item
		final JMenuItem quitItem = new JMenuItem("Quit");
		quitItem.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				quitApp();
			}
		});
		fileMenu.add(quitItem);

		// test of an item directly in the menubar (doesn't work quite as I
		// thought)
		final JMenuItem aboutMenu = new JMenuItem("About");
		aboutMenu.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				showAbout();
			}
		});
		menubar.add(aboutMenu);
		setJMenuBar(menubar);
	}


	

	private void openFile() {
		System.out.println("open file?");
	}

	private void quitApp() {
		// quit the application
		System.exit(0);
	}
	
	// a modal dialog using JOptionPane
	private void showAbout() {
		JOptionPane
				.showMessageDialog(
						this,
						"This is a test Dialog.\nBeing modal, you have to click Ok to proceed.",
						"About This Crap", JOptionPane.INFORMATION_MESSAGE);
	}
	
	
}
