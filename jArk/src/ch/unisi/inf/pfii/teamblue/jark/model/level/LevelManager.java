package ch.unisi.inf.pfii.teamblue.jark.model.level;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Properties;

import javax.swing.JOptionPane;

import ch.unisi.inf.pfii.teamblue.jark.implementation.Constants;
import ch.unisi.inf.pfii.teamblue.jark.model.bonus.Bonus;
import ch.unisi.inf.pfii.teamblue.jark.model.bonus.Bonuses;
import ch.unisi.inf.pfii.teamblue.jark.model.brick.Brick;
import ch.unisi.inf.pfii.teamblue.jark.model.brick.DefaultBrick;
import ch.unisi.inf.pfii.teamblue.jark.model.brick.PersistentBrick;
import ch.unisi.inf.pfii.teamblue.jark.model.brick.ResistentBrick;
import ch.unisi.inf.pfii.teamblue.jark.model.brick.VeryResistentBrick;

public class LevelManager implements Constants {
	private String[][] brickField;
	private String[][] bonusField;
	private String levelName;
	private String levelAuthor;
	private int randomBonusPercentage;

	public LevelManager() {
		brickField = new String[FIELD_ROWS][FIELD_COLUMNS];
		bonusField = new String[FIELD_ROWS][FIELD_COLUMNS];
	}

	public final void readLevelFromFile(final String filepath) {
		try {
			final BufferedReader myInput = new BufferedReader(new FileReader(
					filepath));
			final String[] infos = myInput.readLine().split(",");
			setLevelName(infos[0]);
			setLevelAuthor(infos[1]);
			setRandomBonusPercentage(infos[2]);
			myInput.readLine();
			loadField(brickField, myInput);
			myInput.readLine();
			loadField(bonusField, myInput);
		} catch (final IOException ex) {
			System.out.println(ex);
		}
	}

	public void setRandomBonusPercentage(final String string) {
		randomBonusPercentage = Integer.parseInt(string);
	}

	public final void setLevelAuthor(final String levelAuthor) {
		this.levelAuthor = levelAuthor;
	}

	public final String getLevelAuthor() {
		return levelAuthor;
	}

	public final int getBonusPercentage() {
		return randomBonusPercentage;
	}

	public final void readArcadeLevelFromFile(final String filename) {
		try {
			final InputStreamReader streamReader = new InputStreamReader(
					LevelManager.class.getResourceAsStream(filename));
			final BufferedReader myInput = new BufferedReader(streamReader);
			final String[] infos = myInput.readLine().split(",");
			setLevelName(infos[0]);
			setLevelAuthor(infos[1]);
			setRandomBonusPercentage(infos[2]);
			myInput.readLine();
			loadField(brickField, myInput);
			myInput.readLine();
			loadField(bonusField, myInput);
		} catch (final IOException ex) {
			System.out.println(ex);
		}
	}

	private final String readLevelDetails(final String filename) {
		String details = "Info not Found";
		try {
			final BufferedReader myInput = new BufferedReader(new FileReader(
					filename));
			final String[] infos = myInput.readLine().split(",");
			details = infos[0];
			details += " by ";
			details += infos[1];
		} catch (final IOException ex) {
			System.out.println(ex);
		} catch (final NullPointerException ex) {
			System.out.println("Problem reading the level " + filename);
			details = null;
		}
		return details;
	}

	private final void loadField(final String[][] field,
			final BufferedReader input) {
		for (int i = 0; i < FIELD_ROWS; i++) {
			String[] tmp = null;
			try {
				tmp = input.readLine().split(" ");
			} catch (final IOException ex) {
				System.out.println(ex);
			}
			for (int j = 0; j < FIELD_COLUMNS; j++) {
				if (!tmp[j].equals("null")) {
					field[i][j] = tmp[j];
				} else {
					field[i][j] = null;
				}
			}
		}
	}

	public Brick[][] fieldFromArrays() {
		final Brick[][] field = new Brick[FIELD_ROWS][FIELD_COLUMNS];

		for (int i = 0; i < FIELD_ROWS; i++) {
			for (int j = 0; j < FIELD_COLUMNS; j++) {
				final Brick brick = stringToBrick(brickField[i][j]);
				final Bonus bonus = Bonuses.stringToBonus(bonusField[i][j]);
				if (bonus != null) {
					brick.setBonus(bonus);
				}
				field[i][j] = brick;
			}
		}
		return field;
	}

	private Brick stringToBrick(final String brickString) {
		if (brickString == null) {
			return null;
		} else if (brickString.equals("defaultBrick")) {
			return new DefaultBrick();
		} else if (brickString.equals("resistentBrick")) {
			return new ResistentBrick();
		} else if (brickString.equals("veryResistentBrick")) {
			return new VeryResistentBrick();
		} else if (brickString.equals("persistentBrick")) {
			return new PersistentBrick();
		}
		return null;
	}

	private String fieldToString(final String[][] field) {
		String line = "";
		for (final String[] i : field) {
			for (final String j : i) {
				line += j + " ";
			}
			line += "\n";
		}
		return line;
	}

	public String[][] getBrickField() {
		return brickField;
	}

	public void setBrickField(final String[][] brickField) {
		this.brickField = brickField;
	}

	public void setBonusField(final String[][] bonusField) {
		this.bonusField = bonusField;
	}

	public String[][] getBonusField() {
		return bonusField;
	}

	public void writeLevelToFile(final String name) {
		final File dir = new File("levels");
		dir.mkdir();
		final File file = new File(dir + "/" + name + ".jark");
		try {
			if (file.createNewFile()) {
				final FileWriter fstream = new FileWriter(file);
				final BufferedWriter out = new BufferedWriter(fstream);
				out.write(levelName + "," + levelAuthor + ","
						+ randomBonusPercentage);
				out.write("\n\n");
				out.write(fieldToString(brickField));
				out.write("\n");
				out.write(fieldToString(bonusField));
				out.close();
			} else {
				JOptionPane.showMessageDialog(null, "File \"" + name
						+ "\" already exist, choose a different name.");
			}
		} catch (final IOException ex) {
			System.out.println(ex);
		}
	}

	public void reset() {
		brickField = new String[FIELD_ROWS][FIELD_COLUMNS];
		bonusField = new String[FIELD_ROWS][FIELD_COLUMNS];
	}

	public void setLevelName(final String levelName) {
		this.levelName = levelName;
	}

	public String getLevelName() {
		return levelName;
	}

	public final String[] getLevelsPath() {
		final File dir = new File("levels/");
		if (!dir.isDirectory()) {
			dir.mkdir();
		}
		final String[] files = dir.list(new FilenameFilter() {
			public boolean accept(final File dir, final String name) {
				return (name.endsWith(".jark"));
			}
		});
		for (int i = 0; i < files.length; i++) {
			files[i] = files[i].split(".jark")[0];
		}
		return files;
	}

	public final String[] getLevelsDetail(final String[] paths) {
		final String[] details = new String[paths.length];
		for (int i = 0; i < paths.length; i++) {
			final String temp = readLevelDetails("levels/" + paths[i] + ".jark");
			if (temp != null) {
				details[i] = temp;
			} else {
				details[i] = paths[i];
			}
		}
		return details;
	}

	public final void loadArcadeLevel(final int arcadeLevel) {
		final String path = getArcadeLevelPath(arcadeLevel);
		readArcadeLevelFromFile(path);
	}

	public final String getArcadeLevelPath(final int arcadeLevel) {
		final Properties properties = new Properties();
		try {
			properties
					.load(LevelManager.class
							.getResourceAsStream("defaultlevels/levelspath.properties"));
		} catch (final IOException ex) {
			System.out.println(ex);
		}
		return "defaultlevels/" + properties.getProperty(arcadeLevel + "");
	}

	private final void copyLevelFileOutside(final String path)
			throws IOException, NullPointerException {
		final String levelPath = path;
		final String destPath = "levels/"
				+ levelPath.split("defaultlevels/")[1];
		final File destFile = new File(destPath);
		if (destFile.exists()) {
			return;
		}
		final File dir = new File("levels/");
		if (!dir.isDirectory()) {
			dir.mkdir();
		}
		final InputStream level = getClass().getResourceAsStream(levelPath);
		final OutputStream out = new FileOutputStream(destPath);

		final byte[] buf = new byte[1024];
		int len = 0;
		while ((len = level.read(buf)) > 0) {
			out.write(buf, 0, len);
		}

		level.close();
		out.close();
	}

	public final void copyLevelFilesOutside(final String path) {
		try {
			copyLevelFileOutside(path);
			copyLevelFileOutside(path.split(".jark")[0] + ".png");
		} catch (final NullPointerException e) {
			System.out.println("Couldn't find some level file.");
		} catch (final IOException e) {
			System.out
					.println("Couldn't copy the level files outside the JAR.");
		}
	}
}
