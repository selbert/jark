package ch.unisi.inf.pfii.teamblue.jark.view.levelcreator;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

@SuppressWarnings("serial")
public class OptionPanel extends JPanel {
	
	@SuppressWarnings("serial")
	public OptionPanel(final JTabbedPane tabbedPane, final FieldPanel fieldPanel) {
		setLayout(new GridBagLayout());
		final GridBagConstraints gbc = new GridBagConstraints();
		
		JPanel levelInfo = new JPanel();
		levelInfo.setLayout(new GridBagLayout());
		levelInfo.setBorder(BorderFactory.createTitledBorder("Level Info"));
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST;
		JLabel levelNameLabel = new JLabel("Level Name: ");
		levelInfo.add(levelNameLabel, gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		JTextField levelName = new JTextField(10);
		levelName.setDocument(new PlainDocument() {
			@Override
			public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
				if (str != null && str.matches("\\w") && offs < 12) {
					super.insertString(offs, str, a);
				} else {
					Toolkit.getDefaultToolkit().beep();
				}	
			}
		});
		levelName.setHorizontalAlignment(JTextField.CENTER);
		levelName.setBorder(new EtchedBorder());
		levelInfo.add(levelName, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		JLabel levelAuthLabel = new JLabel("Level Author: ");
		levelInfo.add(levelAuthLabel, gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		JTextField levelAuthor = new JTextField(10);
		levelAuthor.setDocument(new PlainDocument() {
			@Override
			public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
				if (str != null && str.matches("\\w") && offs < 12) {
					super.insertString(offs, str, a);
				} else {
					Toolkit.getDefaultToolkit().beep();
				}	
			}
		});
		levelAuthor.setHorizontalAlignment(JTextField.CENTER);
		levelAuthor.setBorder(new EtchedBorder());
		levelInfo.add(levelAuthor, gbc);
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(levelInfo, gbc);
		
		JPanel checks = new JPanel();
		checks.setBorder(BorderFactory.createTitledBorder("Others"));
		checks.setLayout(new GridBagLayout());
		gbc.gridx = 0;
		gbc.gridy = 0;
		JCheckBox overrideCheck = new JCheckBox("Enable bricks override", true);
		checks.add(overrideCheck, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		JCheckBox randomBonus = new JCheckBox("Auto insert % bonuses randomly:", false);
		randomBonus.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.DESELECTED) {
					tabbedPane.setEnabledAt(1, true);
					fieldPanel.setBonusDisplay(true);
				} else {
					tabbedPane.setEnabledAt(1, false);
					fieldPanel.setBonusDisplay(false);
				}
			}	
		});
		checks.add(randomBonus, gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.CENTER;
		final JTextField numOfBonuses = new JTextField(3);
		
		numOfBonuses.setDocument(new PlainDocument() {
			@Override
			public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
				if (str != null && str.matches("\\d") && offs < 2) {
					super.insertString(offs, str, a);
				} else {
					Toolkit.getDefaultToolkit().beep();
				}	
			}
		});
		numOfBonuses.setHorizontalAlignment(JTextField.CENTER);
		
		numOfBonuses.setBorder(new EtchedBorder());
		checks.add(numOfBonuses, gbc);
		
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = 1;
		gbc.gridy = 0;
		add(checks, gbc);
	}
}
