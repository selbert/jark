package ch.unisi.inf.pfii.teamblue.jark.view.main;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.DefaultListSelectionModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import ch.unisi.inf.pfii.teamblue.jark.model.level.LevelManager;

public class CardPanel extends JPanel {
	private JLabel labelImage;
	private final LevelManager levelManager;
	private String selectedLevel;
	private final JList list;
	private String[] paths;
	
	public CardPanel(CardLayout cardLayout, final LevelManager levelManager) {
		setBorder(new EtchedBorder());
		setLayout(cardLayout);
		paths = levelManager.getLevelsPath("jark");
		this.levelManager = levelManager;
		
		JPanel firstCard = new JPanel();
		firstCard.add(new JLabel("test"));
		add(firstCard, "arcade");
		
		
		JPanel levelCard = new JPanel();
		levelCard.setLayout(new BorderLayout());
		list = new JList(paths);		
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setVisibleRowCount(3);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent ev) {
				if (!ev.getValueIsAdjusting()) {
					final int index = list.getSelectedIndex();
					if (index != -1) {
					selectedLevel = paths[index];
					updateLevelImage(selectedLevel);
					}
				}
			}
		});
		JScrollPane optionPane = new JScrollPane(list);
		levelCard.add(optionPane, BorderLayout.SOUTH);
		labelImage = new JLabel(new ImageIcon(getClass().getResource("test.png")));
		labelImage.setBorder(new EtchedBorder());
		levelCard.add(labelImage, BorderLayout.CENTER);
		add(levelCard, "level");
	}
	
	private final void updateLevelImage(String path) {
		labelImage.setIcon(new ImageIcon("levels/"+path+".png"));
	}
	
	public final String getSelectedLevel() {
		return selectedLevel;
	}

	public void updateLevelList() {
		paths = levelManager.getLevelsPath("jark");
		list.setListData(paths);
	}
}
