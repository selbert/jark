package ch.unisi.inf.pfii.teamblue.jark.view.levelcreator;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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
	private JTextField levelAuthor;
	private JTextField levelName;
	private JCheckBox randomBonus;
	private JTextField numOfBonuses;
	
	@SuppressWarnings("serial")
	public OptionPanel(final JTabbedPane tabbedPane, final FieldPanel fieldPanel) {
		setLayout(new GridBagLayout());
		
		class AcceptedDocument extends PlainDocument {
			private final JTextField parent;
			private final boolean numerical;
			
			public AcceptedDocument(final JTextField parent, final boolean numerical) {
				this.parent = parent;
				this.numerical = numerical;
			}
			@Override
			public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
				super.insertString(offs, str, a);
				checkAccepted();	
			}
			@Override
			protected void postRemoveUpdate(DefaultDocumentEvent chng) {
				super.postRemoveUpdate(chng);
				checkAccepted();
			}
			private void checkAccepted() {
				boolean accepted;
				if (numerical) {
					accepted = (!randomBonus.isSelected() || parent.getText().matches("[0-9]{1,2}"));
				} else {
					accepted = (parent.getText().matches("[a-zA-Z_][a-zA-Z_0-9]*") && parent.getText().length() < 13);
				}
				if (accepted) {
					parent.setBackground(new JTextField().getBackground());
				} else {
					parent.setBackground(Color.RED);
				}	
			}
			
		};
		
		final GridBagConstraints gbcOuter = new GridBagConstraints();
		final GridBagConstraints gbcInner = new GridBagConstraints();
		
		//------- level info -------
		gbcOuter.gridx = 0;
		gbcOuter.gridy = 0;
		gbcOuter.fill = GridBagConstraints.BOTH;
		gbcOuter.ipadx = 50;
		gbcOuter.ipady = 20;
		gbcOuter.insets = new Insets(0,0,0,10);
		JPanel levelInfo = new JPanel();
		levelInfo.setLayout(new GridBagLayout());
		levelInfo.setBorder(BorderFactory.createTitledBorder("Level Info"));
		
		gbcInner.gridx = 0;
		gbcInner.gridy = 0;
		gbcInner.insets = new Insets(2,2,2,2);
		gbcInner.anchor = GridBagConstraints.WEST;
		JLabel levelNameLabel = new JLabel("Level Name: ");
		levelInfo.add(levelNameLabel, gbcInner);
		
		gbcInner.gridx = 1;
		gbcInner.gridy = 0;
		levelName = new JTextField(10);
		levelName.setBorder(new EtchedBorder());
		levelName.setDocument(new AcceptedDocument(levelName, false));
		levelName.setHorizontalAlignment(JTextField.CENTER);
		levelInfo.add(levelName, gbcInner);
		
		gbcInner.gridx = 0;
		gbcInner.gridy = 1;
		JLabel levelAuthLabel = new JLabel("Level Author: ");
		levelInfo.add(levelAuthLabel, gbcInner);
		
		gbcInner.gridx = 1;
		gbcInner.gridy = 1;
		levelAuthor = new JTextField(10);
		levelAuthor.setDocument(new AcceptedDocument(levelAuthor, false));
		levelAuthor.setHorizontalAlignment(JTextField.CENTER);
		levelAuthor.setBorder(new EtchedBorder());
		levelInfo.add(levelAuthor, gbcInner);
		
		add(levelInfo, gbcOuter);
		
		//------- others -------
		gbcOuter.gridx = 1;
		gbcOuter.gridy = 0;
		gbcOuter.insets = new Insets(0,10,0,0);
		JPanel checks = new JPanel();
		checks.setBorder(BorderFactory.createTitledBorder("Others"));
		checks.setLayout(new GridBagLayout());
		
		gbcInner.gridx = 0;
		gbcInner.gridy = 0;
		JCheckBox overrideCheck = new JCheckBox("Enable bricks override", true);
		overrideCheck.setToolTipText("<html>When enabled bricks in the editor will<br>be replaced with the selected one</html>");
		overrideCheck.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.DESELECTED) {
					fieldPanel.setBricksOverride(false);
				} else {
					fieldPanel.setBricksOverride(true);
				}
			}	
		});
		checks.add(overrideCheck, gbcInner);
		
		gbcInner.gridx = 0;
		gbcInner.gridy = 1;
		randomBonus = new JCheckBox("Auto insert % bonuses randomly:", false);
		randomBonus.setToolTipText("<html>Enable random bonus assignment at level start</html>");
		randomBonus.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.DESELECTED) {
					tabbedPane.setEnabledAt(1, true);
					fieldPanel.setBonusDisplay(true);
					numOfBonuses.setEnabled(false);
				} else {
					tabbedPane.setEnabledAt(1, false);
					fieldPanel.setBonusDisplay(false);
					numOfBonuses.setEnabled(true);
				}
			}	
		});
		checks.add(randomBonus, gbcInner);
		
		gbcInner.gridx = 1;
		gbcInner.gridy = 1;
		gbcInner.anchor = GridBagConstraints.CENTER;
		numOfBonuses = new JTextField(3);
		numOfBonuses.setToolTipText("<html>Percentage of bonuses (bonus, malus and neutral) over the total number of bricks <br>that will be randomly assigned each time the level starts</html>");
		numOfBonuses.setDocument(new AcceptedDocument(numOfBonuses, true));
		numOfBonuses.setText("30");
		numOfBonuses.setEnabled(false);
		numOfBonuses.setHorizontalAlignment(JTextField.CENTER);
		numOfBonuses.setBorder(new EtchedBorder());
		checks.add(numOfBonuses, gbcInner);
		
		add(checks, gbcOuter);
	}
	
	public void setLevelName(String name) {
		levelName.setText(name);
	}
	public String getLevelName() {
		final String temp = levelName.getText();
		if (temp == null || !temp.matches("[a-zA-Z_][a-zA-Z_0-9]*")) {
			return "Anon";
		} else {
			return temp;
		}
	}
	
	public void setLevelAuthor(String author) {
		levelAuthor.setText(author);
	}
	public String getLevelAuthor() {
		final String temp = levelAuthor.getText();
		if (temp == null || !temp.matches("[a-zA-Z_][a-zA-Z_0-9]*")) {
			return "Anonymous";
		} else {
			return temp;
		}
	}
	
	public void setRandomBonusNum(int bonusPercentage) {
		if (bonusPercentage != 0) {
			randomBonus.setSelected(true);
			numOfBonuses.setText(bonusPercentage+"");
		}
	}
	public int getRandomBonusNum() {
		if (randomBonus.isSelected() && numOfBonuses.getText().matches("[0-9]{1,2}")) {
			return Integer.parseInt(numOfBonuses.getText());
		} else {
			return 0;
		}
	}
}
