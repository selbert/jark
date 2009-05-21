package ch.unisi.inf.pfii.teamblue.jark.view.main;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import ch.unisi.inf.pfii.teamblue.jark.implementation.StringEncrypt;

public class HighScorePanel extends JPanel {
	final GridBagConstraints gbc;
	private JLabel nameLabel;
	private JLabel scoreLabel;
	private JLabel timeLabel;

	public HighScorePanel() {

		setLayout(new GridBagLayout());
		gbc = new GridBagConstraints();

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 3;
		gbc.ipady = 20;
		final JLabel titleLabel = new JLabel("High Score");
		titleLabel.setFont(new Font(getFont().getFamily(), Font.BOLD, 18));
		add(titleLabel, gbc);
		gbc.gridwidth = 1;
		gbc.ipady = 0;

		final File file = new File("HighScore.jahs");
		try {
			if (!file.createNewFile()) {
					final BufferedReader myInput = new BufferedReader(
							new FileReader("HighScore.jahs"));
					String readLine = myInput.readLine();
					int y = 1;
					addLine("Name", "Score", "Time", y, true);
					y++;
					while (readLine != null && y <= 11) {
						final char a = readLine.charAt(0);
						final int x = a - '0';
						final String decryptedString = StringEncrypt.decrypt(
								readLine.substring(1), x);
						try {
							final String name = decryptedString.split(":")[0];
							final String score = ""
									+ Integer.parseInt(decryptedString
											.split(":")[1]);
							final int t = Integer.parseInt(decryptedString
									.split(":")[2]);
							final int m = (t / 60);
							final int s = t % 60;
							final String time = ((m < 10) ? "0" : "") + m + ":"
									+ ((s < 10) ? "0" : "") + s;

							addLine(name, score, time, y, false);
							y++;

						} catch (final Exception e) {
							System.out.println("Invalid highscore file format! Cheating ish baaad :D");
							return;
						}
						readLine = myInput.readLine();
					}
			} else {
				file.createNewFile();
			}
		} catch (final Exception ex) {
			System.out.println("Problem accessing Highscore file.");
		}
	}

	private final void addLine(final String name, final String score,
			final String time, final int y, final boolean title) {
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.WEST;
		Insets margin = new Insets(0, 0, 0, 70);
		gbc.insets = margin;
		gbc.gridx = 0;
		gbc.gridy = y;
		nameLabel = new JLabel(name, SwingConstants.LEFT);
		nameLabel.setFont(new Font(getFont().getFamily(), title ? Font.BOLD
				: Font.PLAIN, title ? 18 : 16));
		add(nameLabel, gbc);
		gbc.anchor = GridBagConstraints.CENTER;
		margin = new Insets(0, 0, 0, 70);
		gbc.insets = margin;
		gbc.gridx = 1;
		gbc.gridy = y;
		scoreLabel = new JLabel(score, SwingConstants.RIGHT);
		scoreLabel.setFont(new Font(getFont().getFamily(), title ? Font.BOLD
				: Font.PLAIN, title ? 18 : 16));
		add(scoreLabel, gbc);
		gbc.anchor = GridBagConstraints.EAST;
		margin = new Insets(0, 0, 0, 0);
		gbc.insets = margin;
		gbc.gridx = 2;
		gbc.gridy = y;
		timeLabel = new JLabel(time, SwingConstants.RIGHT);
		timeLabel.setFont(new Font(getFont().getFamily(), title ? Font.BOLD
				: Font.PLAIN, title ? 18 : 16));
		add(timeLabel, gbc);
	}
}
