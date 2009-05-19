package ch.unisi.inf.pfii.teamblue.jark.view.main;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import ch.unisi.inf.pfii.teamblue.jark.model.level.LevelManager;
import ch.unisi.inf.pfii.teamblue.jark.view.ImagesRepository;

public class CardPanel extends JPanel {
	private JLabel labelImage;
	private final LevelManager levelManager;
	private String selectedLevel;
	private final JList list;
	private boolean displaySelected;
	private String[] paths;
	private String[] levelsDetail;
	private int selectedIndex;
	
	public CardPanel(CardLayout cardLayout, final LevelManager levelManager) {
		setBorder(new EtchedBorder());
		setLayout(cardLayout);
		paths = levelManager.getLevelsPath();
		levelsDetail = levelManager.getLevelsDetail(paths);
		
		this.levelManager = levelManager;
		
		JPanel firstCard = new JPanel();
		firstCard.add(new HighScorePanel());
		add(firstCard, "arcade");
		
		
		JPanel levelCard = new JPanel();
		levelCard.setLayout(new BorderLayout());
		list = new JList(levelsDetail);		
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setVisibleRowCount(3);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent ev) {
				if (!ev.getValueIsAdjusting()) {
					selectedIndex = list.getSelectedIndex();
					if (selectedIndex != -1 && displaySelected) {
						selectedLevel = paths[selectedIndex];
						updateLevelImage(selectedLevel);
					}
					displaySelected = true;
				}
			}
		});
		JScrollPane optionPane = new JScrollPane(list);
		levelCard.add(optionPane, BorderLayout.SOUTH);
		labelImage = new JLabel(ImagesRepository.getIcon("initLevel"));
		labelImage.setBorder(new EtchedBorder());
		levelCard.add(labelImage, BorderLayout.CENTER);
		add(levelCard, "level");
	}
	
	private final void updateLevelImage(String path) {
		final String imagePath = "levels/"+path+".png";
		final File image = new File(imagePath);
		if (image.exists()) {
			labelImage.setIcon(new ImageIcon(imagePath));
		} else {
			labelImage.setIcon(ImagesRepository.getIcon("noimagefound"));
		}
	}
	
	public final String getSelectedLevel() {
		return selectedLevel;
	}

	public final void updateLevelList() {
		paths = levelManager.getLevelsPath();
		list.setListData(levelsDetail);
	}
	
	public final void setSelectedItem() {
		list.setSelectedIndex(selectedIndex);
	}
}
