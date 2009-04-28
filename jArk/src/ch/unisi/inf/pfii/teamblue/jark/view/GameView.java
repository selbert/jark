package ch.unisi.inf.pfii.teamblue.jark.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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

public class GameView extends JFrame {
	// the main panel (Container)
	private JPanel contentPane;

	// the game area
	GamePanel center;
	
	public GameView() {
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
		// create menu
		makeMenu();
		// cast to use borders, Container (supertype) doesn't have setBorder
		// method
		contentPane = (JPanel) getContentPane();
		// border between frame and inner components
		contentPane.setBorder(new EmptyBorder(6, 6, 6, 6));
		// border between inner components
		// BorderLayout splits the window into 5 sections (north, south, center,
		// west, east)
		contentPane.setLayout(new BorderLayout(6, 6));
		contentPane.setBackground(Color.ORANGE);

		// create a toolbar on the south
		makeToolBar();

		// test north
		JPanel north = new JPanel();
		JLabel label = new JLabel("Early tests with SWING/AWG");
		north.add(label);
		north.setBackground(Color.YELLOW);
		contentPane.add(north, BorderLayout.NORTH);
		
		// center content
		center = new GamePanel();
		// add a border
		//center.setBorder(new EtchedBorder());
		center.setBackground(Color.GREEN);
		center.setPreferredSize(new Dimension(800,600));
		contentPane.add(center, BorderLayout.CENTER);
		
		// pack the frame together
		pack();
		// and show it
		setVisible(true);
	}

	/**
	 * Create the Menu
	 */
	private void makeMenu() {
		JMenuBar menubar = new JMenuBar();
		setJMenuBar(menubar);

		// menu file
		JMenu fileMenu = new JMenu("File");
		menubar.add(fileMenu);

		// menu items: using anonymous inner classes for events
		JMenuItem openItem = new JMenuItem("Open");
		openItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openFile();
			}
		});
		fileMenu.add(openItem);

		// another item
		JMenuItem quitItem = new JMenuItem("Quit");
		quitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quitApp();
			}
		});
		fileMenu.add(quitItem);

		// test of an item directly in the menubar (doesn't work quite as I
		// thought)
		JMenuItem aboutMenu = new JMenuItem("About");
		aboutMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showAbout();
			}
		});
		menubar.add(aboutMenu);
	}

	/**
	 * Create a toolbar (with 2 buttons)
	 */
	private void makeToolBar() {
		JPanel toolbar = new JPanel();
		// grid layout!
		toolbar.setLayout(new GridLayout(1, 2));

		// first button
		JButton startButton = new JButton("Start");
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showCenterText();
			}
		});
		toolbar.add(startButton);

		// another button
		JButton stopButton = new JButton("Stop");
		toolbar.add(stopButton);

		contentPane.add(toolbar, BorderLayout.SOUTH);
	}

	private void showCenterText() {
		center.setBackground(Color.cyan);
	}

	private void openFile() {
		System.out.println("open file?");
	}

	private void quitApp() {
		// quit the application
		System.exit(0);
	}
	
	//a modal dialog using JOptionPane
	private void showAbout() {
		JOptionPane
				.showMessageDialog(
						this,
						"This is a test Dialog.\nBeing modal, you have to click Ok to proceed.",
						"About This Crap", JOptionPane.INFORMATION_MESSAGE);
	}

}
