package ch.unisi.inf.pfii.teamblue.jark.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import ch.unisi.inf.pfii.teamblue.jark.implementation.StringEncrypt;

/**
 * The HighScores class handels highscores
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate: 2009-05-21 15:09:37 +0200 (Thu, 21 May 2009) $
 * 
 */

public class HighScores {
	private final ArrayList<String> highScoreListNames;
	private final ArrayList<Integer> highScoreListScores;
	private final ArrayList<Integer> highScoreListTimes;
	private final Random rnd;
	
	public HighScores() {
		highScoreListNames = new ArrayList<String>();
		highScoreListScores = new ArrayList<Integer>();
		highScoreListTimes = new ArrayList<Integer>();
		rnd = new Random();
		
		initializeHighScoreFile();
	}

	public final void initializeHighScoreFile() {
		final File file = new File("HighScore.jahs");
		try {
			if (!file.createNewFile()) {
					final BufferedReader myInput = new BufferedReader(
							new FileReader("HighScore.jahs"));
					String readLine = myInput.readLine();
					while (readLine != null) {
						final char a = readLine.charAt(0);
						final int x = a - '0';
						final String decryptedString = StringEncrypt.decrypt(
								readLine.substring(1), x);
						final String name = decryptedString.split(":")[0];
						final Integer score = Integer.parseInt(decryptedString
								.split(":")[1]);
						final Integer time = Integer.parseInt(decryptedString
								.split(":")[2]);
						highScoreListNames.add(name);
						highScoreListScores.add(score);
						highScoreListTimes.add(time);
						readLine = myInput.readLine();
					}
			} else {
				file.createNewFile();
			}
		} catch (final Exception ex) {
			System.out.println("Problem accessing Highscore file.");
		}
	}
	
	public final void writeHighScoreFile() {
		final File file = new File("HighScore.jahs");
		try {
			file.createNewFile();
		} catch (final IOException ex) {
			System.out.println("Problem accessing Highscore file.");
		}
		try {
			final FileWriter fstream = new FileWriter(file);
			final BufferedWriter out = new BufferedWriter(fstream);
			while (highScoreListNames.size() > 0) {
				final int index = getTopScore();
				final int x = rnd.nextInt(10);
				out.write(x
						+ StringEncrypt.encrypt(highScoreListNames.get(index)
								+ ":" + highScoreListScores.get(index) + ":"
								+ highScoreListTimes.get(index), x) + "\n");
				highScoreListNames.remove(index);
				highScoreListScores.remove(index);
				highScoreListTimes.remove(index);
			}
			out.close();
		} catch (final IOException ex) {
			System.out.println("Problem writing to Highscore file.");
		}
	}
	public final int getTopScore() {
		if (highScoreListScores.size() > 0) {
			int returnIndex = 0;
			for (int i = 0; i < highScoreListScores.size(); i++) {
				final int s = highScoreListScores.get(returnIndex);
				final int t = highScoreListScores.get(i);
				if (t > s) {
					returnIndex = i;
				}

			}
			return returnIndex;
		}
		return 0;
	}

	public final void addHighScore(final String name, final Player player) {
		highScoreListNames.add(name);
		highScoreListScores.add(player.getScore());
		highScoreListTimes.add((player.getTime() / 1000));
		writeHighScoreFile();
	}

	public final ArrayList<Integer> getHighScoreListScores() {
		return highScoreListScores;
	}
	
	public final int getLeastScore() {
		if (highScoreListScores.size() > 0) {
			int returnValue = highScoreListScores.get(0);
			for (int i = 0; i < highScoreListScores.size(); i++) {
				final int t = highScoreListScores.get(i);
				returnValue = Math.min(returnValue, t);
			}
			return returnValue;
		}
		return 0;
	}
}
