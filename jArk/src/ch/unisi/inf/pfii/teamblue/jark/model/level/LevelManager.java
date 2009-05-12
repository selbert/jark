package ch.unisi.inf.pfii.teamblue.jark.model.level;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import ch.unisi.inf.pfii.teamblue.jark.implementation.Constants;
import ch.unisi.inf.pfii.teamblue.jark.implementation.Utils;
import ch.unisi.inf.pfii.teamblue.jark.model.bonus.Bonus;
import ch.unisi.inf.pfii.teamblue.jark.model.brick.Brick;
import ch.unisi.inf.pfii.teamblue.jark.model.brick.DefaultBrick;
import ch.unisi.inf.pfii.teamblue.jark.model.brick.PersistentBrick;
import ch.unisi.inf.pfii.teamblue.jark.model.brick.ResistentBrick;
import ch.unisi.inf.pfii.teamblue.jark.model.brick.VeryResistentBrick;
import ch.unisi.inf.pfii.teamblue.jark.model.bonus.Bonuses;

public class LevelManager implements Constants {
	private String[][] brickField;
	private String[][] bonusField;
	
	public LevelManager() {
		brickField = new String[FIELD_ROWS][FIELD_COLUMNS];
		bonusField = new String[FIELD_ROWS][FIELD_COLUMNS];
	}
	
	public void LevelFromFile(String filepath) {
		final ArrayList<String> lines = new ArrayList<String>();
		
		try{
			final URL filePath = getClass().getResource(filepath);
			final InputStreamReader streamReader = new InputStreamReader(filePath.openStream());
			final BufferedReader myInput = new BufferedReader(streamReader);

			for (int i = 0; i < FIELD_ROWS; i++) {
				lines.add(myInput.readLine());
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
}
