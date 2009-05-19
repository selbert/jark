package ch.unisi.inf.pfii.teamblue.jark.model.level;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
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
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.Vaus;
import ch.unisi.inf.pfii.teamblue.jark.view.ImagesRepository;

public class LevelManager implements Constants {
	private String[][] brickField;
	private String[][] bonusField;
	private String levelName;
	private String levelAuthor;
	
	public LevelManager() {
		brickField = new String[FIELD_ROWS][FIELD_COLUMNS];
		bonusField = new String[FIELD_ROWS][FIELD_COLUMNS];
	}
	
	public final void readLevelFromFile(String filepath) {
		try{
			final BufferedReader myInput = new BufferedReader(new FileReader(filepath));
			setLevelName(myInput.readLine());
			setLevelAuthor(myInput.readLine());
			myInput.readLine();
			loadField(brickField, myInput);
			myInput.readLine();
			loadField(bonusField, myInput);
		} catch (IOException ex) {
			System.out.println(ex);
		}
	}
	
	public final void setLevelAuthor(String levelAuthor) {
		this.levelAuthor = levelAuthor;
	}

	public final void readArcadeLevelFromFile(String filename) {
		try{
			final InputStreamReader streamReader = new InputStreamReader(LevelManager.class.getResourceAsStream(filename));
			final BufferedReader myInput = new BufferedReader(streamReader);
			setLevelName(myInput.readLine());
			setLevelAuthor(myInput.readLine());
			myInput.readLine();
			loadField(brickField, myInput);
			myInput.readLine();
			loadField(bonusField, myInput);
		} catch (IOException ex) {
			System.out.println(ex);
		}
	}
	private final String readLevelDetails(String filename) {
		String details = "Info not Found";
		try{
			final BufferedReader myInput = new BufferedReader(new FileReader(filename));
			details = myInput.readLine();
			details += " by ";
			details += myInput.readLine();
		} catch (IOException ex) {
			System.out.println(ex);
		}
		return details;
	}
	
	private final void loadField(String[][] field, BufferedReader input) {
		for (int i = 0; i < FIELD_ROWS; i++) {
			String[] tmp = null;
			try {
				tmp = input.readLine().split(" ");
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			for (int j = 0; j<FIELD_COLUMNS; j++) {
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
			for (int j=0; j < FIELD_COLUMNS; j++) {
				Brick brick = stringToBrick(brickField[i][j]);
				Bonus bonus = Bonuses.stringToBonus(bonusField[i][j]);
				if (bonus != null) {
					brick.setBonus(bonus);
				}
				field[i][j] = brick;
			}
		}
		return field;
	}
	
	private Brick stringToBrick(String brickString) {
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

	
	private String fieldToString(String[][] field) {
		String line ="";
		for(final String[] i : field) {
			for (final String j : i) {
				line += j+" ";
			}
			line += "\n";
		}
		return line;
	}
	
	public String[][] getBrickField() {
		return brickField;
	}
	
	public void setBrickField(String[][] brickField) {
		this.brickField = brickField;
	}
	public void setBonusField(String[][] bonusField) {
		this.bonusField = bonusField;
	}
	
	public String[][] getBonusField() {
		return bonusField;
	}

	public void writeLevelToFile(String name) {
		final File dir = new File("levels");
		dir.mkdir();
		final File file = new File(dir+"/"+name+".jark");
		try {
			if (file.createNewFile()) {
				FileWriter fstream = new FileWriter(file);
				BufferedWriter out = new BufferedWriter(fstream);
				out.write(levelName);
				out.write(levelAuthor);
				out.write("\n\n");
				out.write(fieldToString(brickField));
				out.write("\n");
				out.write(fieldToString(bonusField));
				out.close();
			} else {
				JOptionPane.showMessageDialog(null, "File \""+name+"\" already exist, choose a different name.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void reset() {
		brickField = new String[FIELD_ROWS][FIELD_COLUMNS];
		bonusField = new String[FIELD_ROWS][FIELD_COLUMNS];
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public String getLevelName() {
		return levelName;
	}

	public final String[] getLevelsPath() {
	    File dir = new File("levels/");
		if (!dir.isDirectory()) { 
			dir.mkdir();
		}
	    final String[] files = dir.list(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return (name.endsWith(".jark"));
			}
	    });
	    for (int i = 0; i<files.length; i++) {
	    	files[i] = files[i].split(".jark")[0];
	    }
	    return files;
	}
	
	public final String[] getLevelsDetail(final String[] paths) {
		final String[] details = new String[paths.length];
		for (int i = 0; i<paths.length; i++) {
			details[i] = readLevelDetails("levels/"+paths[i]+".jark");
	    }
		return details;
	}


	public void loadArcadeLevel(int arcadeLevel) {
		Properties properties = new Properties();
		try {
			properties.load(LevelManager.class.getResourceAsStream("defaultlevels/levelspath.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		final String levelPath = "defaultlevels/" + properties.getProperty(arcadeLevel+"");
		readArcadeLevelFromFile(levelPath);
	}

}
