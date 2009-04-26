package ch.unisi.inf.pfii.teamblue.jark.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

/**
 * 
 * The main window of the game
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 * 
 */

public class GameView {
	// main window
	private JFrame frame;

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
		frame = new JFrame("[ jArk ] [ Arkanoid as never seen before ]");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(300, 100);
		frame.setResizable(false);
		// create menu
		makeMenu();
		// cast to use borders, Container (supertype) doesn't have setBorder
		// method
		contentPane = (JPanel) frame.getContentPane();
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
		frame.pack();
		// and show it
		frame.setVisible(true);
	}

	/**
	 * Create the Menu
	 */
	private void makeMenu() {
		JMenuBar menubar = new JMenuBar();
		frame.setJMenuBar(menubar);

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
						frame,
						"This is a test Dialog.\nBeing modal, you have to click Ok to proceed.",
						"About This Crap", JOptionPane.INFORMATION_MESSAGE);
	}

}
