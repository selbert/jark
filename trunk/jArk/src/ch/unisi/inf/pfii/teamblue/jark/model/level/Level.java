package ch.unisi.inf.pfii.teamblue.jark.model.level;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

import ch.unisi.inf.pfii.teamblue.jark.implementation.Constants;
import ch.unisi.inf.pfii.teamblue.jark.implementation.Conversion;
import ch.unisi.inf.pfii.teamblue.jark.model.bonus.*;
import ch.unisi.inf.pfii.teamblue.jark.model.brick.*;


/**
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 * 
 */
public final class Level implements Constants {
	//bonus handling field
	private String bonusDistString;
	
	private Brick[][] bricks;
	private ArrayList<Bonus> freeBonuses;
	
	/**
	 * Constructor of Level, creates a new level (field of bricks).
	 * 
	 * @param numOfBonus total bonuses at disposition
	 * @param freeBonuses ArrayList of bonus dropping to the vaus 
	 */
	public Level(final int numOfBonus, final ArrayList<Bonus> freeBonuses) {
		bricks = new Brick[FIELD_ROWS][FIELD_COLUMNS];
		if (bonusDistString == null) {
			bonusDistString = getDistributionString();
		}
		this.freeBonuses = freeBonuses;
		createFullFieldLevel();
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
			FileInputStream fis = new FileInputStream("src/ch/unisi/inf/pfii/teamblue/jark/model/level/defaultlevels/"+levelNumber);
			BufferedReader myInput = new BufferedReader(new InputStreamReader(fis));
			String thisLine = "";
			
			while ((thisLine = myInput.readLine()) != null) {
				
				String[] brickInfo = thisLine.split("\\.");
				if (brickInfo.length > 1) {
					int row = Integer.parseInt(brickInfo[0]);
					int col = Integer.parseInt(brickInfo[1]);
					int type = Integer.parseInt(brickInfo[2]);
				
					bricks[row][col] = Conversion.intToBrick(type);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/**
	 * creates a level field full of defaultblocks for testing
	 */
	private final void createFullFieldLevel() {
		for (int row = 0; row<FIELD_ROWS; row++) {
			for (int col = 0; col<FIELD_COLUMNS; col++) {
				bricks[row][col] = new DefaultBrick();
			}
		}

	}
	

	/**
	 * Returns the current field as String
	 */
	public final String toString() {
		String tab = "\n";

		for (int row = 0; row<FIELD_ROWS; row++) {
			tab += "  ";
			for (int col = 0; col<FIELD_COLUMNS; col++) {
				if (bricks[row][col] != null) {
					if (bricks[row][col].getBonus() != null) {
						tab += "** ";
						
					} else {
						tab += "__ ";
					}
				} else {
					tab += "   ";
				}
			}
			tab = tab.substring(0, tab.length()-1)+"\n";

		}

		return tab+"\n\n\n             @";
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
			listOfBricks.remove(index).setBonus(createBonus());
		}	
	}
	
	
	
	/**
	 * Returns true if the given position is inside a brick
	 * @param x
	 * @param y
	 * @return true if inside a brick
	 */
	public final boolean brickHasBallInside(final int x, final int y) {
		int[] pos = Conversion.getFieldPosition(x,y);
		if (bricks[pos[1]][pos[0]] != null) {
			return true;
		}
		return false;
	}
	
	/**
	 * Action taken after the ball hits a brick, 
	 * calls the destruction of the brick and returns the direction of the ball
	 * 
	 * @param oldX current position x
	 * @param oldY current position y
	 * @param newX next position x
	 * @param newY next position y
	 * @return direction of the ball
	 */
	public final BouncingDirection computeDirection(final int oldX, final int oldY, final int newX, final int newY) {
		int[] oldPos = Conversion.getFieldPosition(oldX,oldY);
		int[] newPos = Conversion.getFieldPosition(newX,newY);
		
		if ((newPos[0] < oldPos[0] || newPos[0] > oldPos[0]) && newPos[1] == newPos[1]) {
			destroyBrick(newPos);
			return BouncingDirection.HORIZONTAL;
		} else if (newPos[0] == oldPos[0] && (newPos[1] < oldPos[1]) || newPos[1] > newPos[1]) {
			destroyBrick(newPos);
			return BouncingDirection.VERTICAL;
		} else {
			//destroy adiacent blocks
			destroyBrick(new int[] { oldPos[0], newPos[1] });
			destroyBrick(new int[] { newPos[0], oldPos[1] });
			return BouncingDirection.DIAGONAL;
		}
			
	}
	
	/**
	 * Destroy a brick
	 * @param pos the position array
	 */
	private final void destroyBrick(final int[] pos) {
		bricks[pos[1]][pos[0]] = null;
	}
	
}

