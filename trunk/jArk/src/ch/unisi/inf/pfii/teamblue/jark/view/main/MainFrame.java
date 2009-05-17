package ch.unisi.inf.pfii.teamblue.jark.view.main;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;

import ch.unisi.inf.pfii.teamblue.jark.model.Game;
import ch.unisi.inf.pfii.teamblue.jark.model.level.LevelManager;
import ch.unisi.inf.pfii.teamblue.jark.view.ImagesRepository;
import ch.unisi.inf.pfii.teamblue.jark.view.game.GameFrame;
import ch.unisi.inf.pfii.teamblue.jark.view.levelcreator.EditorFrame;

@SuppressWarnings("serial")
public final class MainFrame extends JFrame {
	
	private final CardLayout cardLayout;
	private final JPanel cardPanel;
	private String selectedButton;
	
	public MainFrame(final LevelManager levelManager, final ImagesRepository imagesReference) {
		setTitle("[ jArk ] [ Arkanoid/BreakOut ]");
		setDefaultLookAndFeelDecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		selectedButton = "arcade";
		final JPanel p = new JPanel();
		p.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		p.setLayout(new GridBagLayout());
		final GridBagConstraints gbc = new GridBagConstraints();
		
		JLabel title = new JLabel("jArk", JLabel.CENTER); 
		gbc.gridwidth = 2;
		gbc.ipady = 10;
		gbc.insets = new Insets(0,0,5,0);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		p.add(title, gbc);
		
		final ButtonGroup group = new ButtonGroup();
		
		JButton arcadeButton = new JButton("Arcade Mode");
		arcadeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedButton = "arcade";
				cardLayout.show(cardPanel, "arcade");
			}	
		});
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.insets = new Insets(0,0,1,5);
		p.add(arcadeButton, gbc);
		
		JButton singleLevelButton = new JButton("Single Level");
		singleLevelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedButton = "singleLevel";
				cardLayout.show(cardPanel, "level");
			}	
		});
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		p.add(singleLevelButton, gbc);
		
		JButton levelEditorButton = new JButton("Level Editor");
		levelEditorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						new EditorFrame(levelManager);
					}
				});	
			}
		});
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		p.add(levelEditorButton, gbc);
		
		JButton highScoresButton = new JButton("High Scores");
		highScoresButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(cardPanel, "high");
			}	
		});
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 1;
		p.add(highScoresButton, gbc);
		group.add(highScoresButton);
		
		JButton quitButton = new JButton("Quit");
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);			
			}
		});
		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.gridwidth = 1;
		gbc.insets = new Insets(5,0,0,5);
		p.add(quitButton, gbc);
		
		JButton playButton = new JButton("Play");
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selectedButton.equals("arcade")) {
					final Game game = new Game(false, levelManager);
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							new GameFrame(game);
						}
					});		
				} 
			}
		});
		gbc.gridx = 1;
		gbc.gridy = 6;
		gbc.gridwidth = 1;
		gbc.insets = new Insets(5,0,0,0);
		p.add(playButton, gbc);
		
        
		cardPanel = new JPanel();
		cardPanel.setBorder(new EtchedBorder());
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(0,0,0,0);
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.ipadx = 10;
		gbc.gridwidth = 1;
		gbc.gridheight = 5;
		gbc.weighty = 2;
		gbc.weightx = 2;
		p.add(cardPanel, gbc);

		cardLayout = new CardLayout();
		cardPanel.setLayout(cardLayout);
		
		
		JPanel firstCard = new JPanel();
		firstCard.add(new JLabel("test"));
		cardPanel.add(firstCard, "arcade");
		
		JPanel levelCard = new JPanel();
		levelCard.setLayout(new BorderLayout());

//		SpinnerModel model1 = new SpinnerListModel(new String[] { "asd", "lol asd lol", "asd lol", "ciao" } );
//		final JSpinner spinner1 = new JSpinner(model1);
//		JFormattedTextField tf = ((JSpinner.DefaultEditor) spinner1.getEditor()).getTextField();
//		tf.setEditable(false);
//	
//		JComboBox combo = new JComboBox(new String[] { "asd", "lol asd lol", "asd lol", "ciao" } );
//		combo.setEditable(false);

		JList list = new JList(new String[] { "asd", "lol asd lol", "asd lol", "ciao" });
		list.setVisibleRowCount(3);
		JScrollPane optionPane = new JScrollPane(list);

		levelCard.add(optionPane, BorderLayout.SOUTH);
		
		JLabel label = new JLabel(new ImageIcon(getClass().getResource("test.png")));
		label.setBorder(new EtchedBorder());
		levelCard.add(label, BorderLayout.CENTER);
		
		cardPanel.add(levelCard, "level");
		
		JPanel highScoreCard = new JPanel();
		cardPanel.add(highScoreCard, "high");
		
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
		
	}

}
