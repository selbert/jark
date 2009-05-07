package ch.unisi.inf.pfii.teamblue.jark.model.level;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import ch.unisi.inf.pfii.teamblue.jark.implementation.Constants;
import ch.unisi.inf.pfii.teamblue.jark.implementation.Utils;
import ch.unisi.inf.pfii.teamblue.jark.implementation.VausListener;
import ch.unisi.inf.pfii.teamblue.jark.model.bonus.*;
import ch.unisi.inf.pfii.teamblue.jark.model.brick.*;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.Vaus;


/**
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 * 
 */
public final class Level implements Constants, VausListener {
	//bonus handling field
	private String bonusDistString;
	
	private Brick[][] bricks;
	private ArrayList<Bonus> freeBonuses;
	
	private Vaus vaus;
	
	/**
	 * Constructor of Level, creates a new level (field of bricks).
	 * 
	 * @param numOfBonus total bonuses at disposition
	 * @param freeBonuses ArrayList of bonus dropping to the vaus 
	 */
	public Level(final int numOfBonus, final ArrayList<Bonus> freeBonuses, final Vaus vaus) {
		bricks = new Brick[FIELD_ROWS][FIELD_COLUMNS];
		if (bonusDistString == null) {
			bonusDistString = getDistributionString();
		}
		this.freeBonuses = freeBonuses;
		this.vaus = vaus;
		createTestFieldLevel();
		//createLevel(0);
		addBonus(numOfBonus);
	}
	
	/**
	 * method that creates a string with the index of the bonus repeated 
	 * the times defined in the Bonuses enum
	 * 
	 */
	private final String getDistributionString() {
		String bonusString = "";
		int numberOfBonus = Bonuses.values().length;
		for (int a = 0; a < numberOfBonus; a++) {
			int probability = Bonuses.getProb(a);
			for (int i = 0; i < probability; i++) {
				bonusString += "" + a + ",";
			}
		}
		return bonusString.substring(0, bonusString.length()-1);
	}
	
	/**
	 * Method that randomly selects a bonus type from a string of bonus indexes separated by ","
	 * @return Bonus
	 */ 
	private final Bonus createBonus() {
		Random rnd = new Random();
		String[] bonusArray = bonusDistString.split(",");
		return Bonuses.getBonus(Integer.parseInt(bonusArray[rnd.nextInt(bonusArray.length)]));
	}
	
	/**
	 * Create a level from a file which format: lineID.rowID.blockType (i.e. 0.1.0)
	 * 
	 * @param levelNumber filename
	 */
	private final void createLevel(final int levelNumber) {
		try{
			URL filePath = getClass().getResource("defaultlevels/"+levelNumber);
			InputStreamReader streamReader = new InputStreamReader(filePath.openStream());
			BufferedReader myInput = new BufferedReader(streamReader);
			String thisLine = "";
			
			while ((thisLine = myInput.readLine()) != null) {
				
				String[] brickInfo = thisLine.split("\\.");
				if (brickInfo.length > 1) {
					int row = Integer.parseInt(brickInfo[0]);
					int col = Integer.parseInt(brickInfo[1]);
					int type = Integer.parseInt(brickInfo[2]);
				
					bricks[row][col] = Utils.intToBrick(type);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/**
	 * creates a level field full of defaultblocks for testing
	 */
	private final void createTestFieldLevel() {
		for (int row = 4; row<FIELD_ROWS; row++) {
			for (int col = 0; col<FIELD_COLUMNS; col++) {
				bricks[row][col] = new DefaultBrick();
			}
		}

	}

	/**
	 * Add bonuses to random bricks
	 * @param numOfBonus
	 */
	private final void addBonus(final int numOfBonus) {
		ArrayList<Brick> listOfBricks = new ArrayList<Brick>();
		Random rnd = new Random();
		
		for(Brick[] rowOfBricks : bricks) {
			for (Brick brick : rowOfBricks) {
				if (brick != null) {
					listOfBricks.add(brick);
				}
			}
		}
		
		for (int i = 0; i < numOfBonus; i++) {
			int index = rnd.nextInt(listOfBricks.size());
			Bonus bonus = createBonus();
			listOfBricks.remove(index).setBonus(bonus);
		}	
	}
	
	
	
	/**
	 * Returns true if the given position is inside a brick
	 * @param x
	 * @param y
	 * @return true if inside a brick
	 */
	public final boolean brickHasBallInside(final float x, final float y) {
		try {
			int[] pos = Utils.getFieldPosition((int)x,(int)y);
			if (bricks[pos[1]][pos[0]] != null) {
				return true;
			}
			return false;
		} catch (ArrayIndexOutOfBoundsException e) {
			return false;
		}
	}
	
	
	/**
	 * Removes a brick
	 * @param pos the position array
	 */
	public final void removeBrick(final float remX, final float remY) {
		if (remX > 0 && remX < FIELD_WIDTH && remY < FIELD_HEIGHT && remY > 0) {
			int[] pos = Utils.getFieldPosition((int)remX,(int)remY);
			final Brick brick = bricks[pos[1]][pos[0]];
			
			if (brick != null)  {
				final int lives = brick.getLives();
				if (lives == 1) {
					Bonus bonus = bricks[pos[1]][pos[0]].getBonus();
					if (bonus != null) { 
						int[] a = Utils.getPixelPosition(pos[0], pos[1]);
						bonus.setX(a[0]);
						bonus.setY(a[1]);
						bonus.setVaus(vaus);
						freeBonuses.add(bonus); 
					}
					bricks[pos[1]][pos[0]] = null;
				} else if (lives > 1) {
					bricks[pos[1]][pos[0]].decrementLives();
				} else {
					//persistent brick - do nothing
				}
			}
		}
	}
	
	
	public Brick[][] getBricks() {
		return bricks;
	}
	
	public void setVaus(Vaus vaus) {
		this.vaus = vaus;
		for (Bonus b : freeBonuses) {
			b.setVaus(vaus);
		}
	}
}

