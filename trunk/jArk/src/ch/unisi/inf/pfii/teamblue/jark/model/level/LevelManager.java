package ch.unisi.inf.pfii.teamblue.jark.model.level;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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
	
	public LevelManager() {
		brickField = new String[FIELD_ROWS][FIELD_COLUMNS];
		bonusField = new String[FIELD_ROWS][FIELD_COLUMNS];
	}
	
	public void readLevelFromFile(String filepath) {
		try{
			final BufferedReader myInput = new BufferedReader(new FileReader(filepath));

			setLevelName(myInput.readLine());
			myInput.readLine();
			
			for (int i = 0; i < FIELD_ROWS; i++) {
				String[] tmp = myInput.readLine().split(" ");
				for (int j = 0; j<FIELD_COLUMNS; j++) {
					if (!tmp[j].equals("null")) {
						brickField[i][j] = tmp[j];
					} else {
						brickField[i][j] = null;
					}
				}
			}
			
			myInput.readLine();
			for (int i = 0; i < FIELD_ROWS; i++) {
				String[] tmp = myInput.readLine().split(" ");
				for (int j = 0; j<FIELD_COLUMNS; j++) {
					if (!tmp[j].equals("null")) {
						bonusField[i][j] = tmp[j];
					} else {
						bonusField[i][j] = null;
					}
				}
			}
			
			
		} catch (IOException ex) {
			System.out.println(ex);
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
		final File file = new File(name+".jark");
		try {
			if (file.createNewFile()) {
				FileWriter fstream = new FileWriter(file);
				BufferedWriter out = new BufferedWriter(fstream);
				out.write(name);
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
}
