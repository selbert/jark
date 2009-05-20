package ch.unisi.inf.pfii.teamblue.jark.view.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
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
	final GridBagConstraints gbc;
	public HighScorePanel() {

		setLayout(new GridBagLayout());
		gbc = new GridBagConstraints();
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 3;
		gbc.ipady = 20;
		add(new JLabel("High Score"), gbc);
		gbc.gridwidth = 1;
		gbc.ipady = 0;
		
		final File file = new File("HighScore.jahs");
		try {
			if (!file.createNewFile()) {
				try{
					final BufferedReader myInput = new BufferedReader(new FileReader("HighScore.jahs"));
					String readLine = myInput.readLine();
					int y = 1;
					while(readLine != null && y < 10) {
						final char a = readLine.charAt(0); 
						final int x = a - '0';
						String decryptedString = StringEncrypt.decrypt(readLine.substring(1), x);
						try {
							final String name = decryptedString.split(":")[0];
							final Integer score = Integer.parseInt(decryptedString.split(":")[1]);
							final Integer time = Integer.parseInt(decryptedString.split(":")[2]);

							int m = (int)(time/60);
							int s = time%60;

							gbc.fill = GridBagConstraints.BOTH;
							gbc.anchor = GridBagConstraints.WEST;
							Insets asd = new Insets(0,0,0,50);
							gbc.insets = asd;
							gbc.gridx = 0;
							gbc.gridy = y;
							JLabel nameLabel = new JLabel(name, JLabel.LEFT);
							nameLabel.setFont(new Font(getFont().getFamily(), Font.PLAIN, 16));
							add(nameLabel, gbc);
							gbc.anchor = GridBagConstraints.CENTER;
							asd = new Insets(0,0,0,50);
							gbc.insets = asd;
							gbc.gridx = 1;
							gbc.gridy = y;
							JLabel scoreLabel = new JLabel(score+"", JLabel.RIGHT);
							scoreLabel.setFont(new Font(getFont().getFamily(), Font.PLAIN, 16));
							add(scoreLabel, gbc);
							gbc.anchor = GridBagConstraints.EAST;
							asd = new Insets(0,0,0,0);
							gbc.insets = asd;
							gbc.gridx = 2;
							gbc.gridy = y++;
							JLabel timeLabel = new JLabel(((m<10)?"0":"") + m + ":" + ((s<10)?"0":"") + s, JLabel.RIGHT);
							timeLabel.setFont(new Font(getFont().getFamily(), Font.PLAIN, 16));
							add(timeLabel, gbc);
						} catch (Exception e) {
							System.out.println("Invalid highscore file format! Don't cheat :D");
						}
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
