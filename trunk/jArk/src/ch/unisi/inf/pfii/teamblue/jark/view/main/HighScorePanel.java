package ch.unisi.inf.pfii.teamblue.jark.view.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import ch.unisi.inf.pfii.teamblue.jark.implementation.StringEncrypt;

public class HighScorePanel extends JPanel {
	public HighScorePanel() {
		final File file = new File("HighScore.jahs");
		setLayout(new GridBagLayout());
		final GridBagConstraints gbc = new GridBagConstraints();
		try {
			if (!file.createNewFile()) {
				try{
					final BufferedReader myInput = new BufferedReader(new FileReader("HighScore.jahs"));
					String readLine = myInput.readLine();
					int y = 0;
					while(readLine != null) {
						final char a = readLine.charAt(0); 
						final int x = a - '0';
						String decryptedString = StringEncrypt.decrypt(readLine.substring(1), x);
						final String name = decryptedString.split(":")[0];
						final Integer score = Integer.parseInt(decryptedString.split(":")[1]);
						gbc.fill = GridBagConstraints.BOTH;
						gbc.anchor = GridBagConstraints.WEST;
						Insets asd = new Insets(0,0,0,50);
						gbc.insets = asd;
						gbc.gridx = 0;
						gbc.gridy = y;
						JLabel nameLabel = new JLabel(name, JLabel.LEFT);
						nameLabel.setFont(new Font(getFont().getFamily(), Font.PLAIN, 16));
						nameLabel.setBorder(new EtchedBorder());
						add(nameLabel, gbc);
						gbc.anchor = GridBagConstraints.EAST;
						asd = new Insets(0,0,0,0);
						gbc.insets = asd;
						gbc.gridx = 1;
						gbc.gridy = y++;
						JLabel scoreLabel = new JLabel(score+"", JLabel.RIGHT);
						scoreLabel.setFont(new Font(getFont().getFamily(), Font.PLAIN, 16));
						scoreLabel.setBorder(new EtchedBorder());
						add(scoreLabel, gbc);
						readLine = myInput.readLine();
					}
				} catch (IOException ex) {
					System.out.println(ex);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
