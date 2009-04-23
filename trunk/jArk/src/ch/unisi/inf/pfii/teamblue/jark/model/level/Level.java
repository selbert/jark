package ch.unisi.inf.pfii.teamblue.jark.model.level;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

import ch.unisi.inf.pfii.teamblue.jark.model.bonus.*;
import ch.unisi.inf.pfii.teamblue.jark.model.brick.*;


/**
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 * 
 */
public class Level {
	//field dimension in pixels
	private static final int FIELD_WIDTH = 800;
	private static final int FIELD_HEIGHT =  400;
	//field dimension in bricks
	private static final int COLUMNS = 14;
	private static final int ROWS = 8;
	
	//bonus handling field
	private static String bonusDistString = "";
	
	private Brick[][] bricks;
	private ArrayList<Bonus> freeBonuses;
	
	/**
	 * Constructor of Level, creates a new level (field of bricks).
	 * 
	 * @param numOfBonus total bonuses at disposition
	 * @param freeBonuses ArrayList of bonus dropping to the vaus 
	 */
	public Level(final int numOfBonus, final ArrayList<Bonus> freeBonuses) {
		bricks = new Brick[ROWS][COLUMNS];
		if (bonusDistString.equals("")) {
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
	private String getDistributionString() {
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
	private Bonus createBonus() {
		Random rnd = new Random();
		String[] bonusArray = bonusDistString.split(",");
		return Bonuses.getBonus(Integer.parseInt(bonusArray[rnd.nextInt(bonusArray.length)]));
	}
	
	/**
	 * Create a level from a file which format: lineID.rowID.blockType (i.e. 0.1.0)
	 * 
	 * @param levelNumber filename
	 */
	private void createLevel(final int levelNumber) {
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
				
					bricks[row][col] = intToBrick(type);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/**
	 * creates a level field full of defaultblocks for testing
	 */
	private void createFullFieldLevel() {
		for (int row = 0; row<ROWS; row++) {
			for (int col = 0; col<COLUMNS; col++) {
				bricks[row][col] = new DefaultBrick();
			}
		}

	}
	
	/**
	 * Converts an integer into a specific brick.
	 * Used when loading levels from files.
	 * 
	 * @param i block type (integer)
	 * @return selected brick 
	 */
	private Brick intToBrick(final int i) {
		switch(i) {
			case 1: return new ResistentBrick();
			case 2: return new VeryResistentBrick();
			case 3: return new PersistentBrick();
			default: return new DefaultBrick();
		}
	}
	
	/**
	 * Returns the current field as String
	 */
	public String toString() {
		String tab = "\n";

		for (int row = 0; row<ROWS; row++) {
			tab += "  ";
			for (int col = 0; col<COLUMNS; col++) {
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
	private void addBonus(final int numOfBonus) {
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
	 * Converts pixel coordinates into bricks coordinates. 
	 * Public for testing purposes.
	 * 
	 * @param x pixels from the left
	 * @param y pixels from the top
	 * @return
	 */
	public int[] getFieldPosition(final int x, final int y) {
		int posy = (int)((float) ROWS/ (float) FIELD_HEIGHT*y);
		int posx = (int)((float) COLUMNS/ (float)FIELD_WIDTH*x);
		return new int[] {posx, posy};
	}
	
	/**
	 * Returns true if the given position is inside a brick
	 * @param x
	 * @param y
	 * @return true if inside a brick
	 */
	public boolean insideBlock(final int x, final int y) {
		int[] pos = getFieldPosition(x,y);
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
	public BouncingDirection computeDirection(final int oldX, final int oldY, final int newX, final int newY) {
		int[] oldPos = getFieldPosition(oldX,oldY);
		int[] newPos = getFieldPosition(newX,newY);
		
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
	private void destroyBrick(final int[] pos) {
		bricks[pos[1]][pos[0]] = null;
	}
	
	public static int getFIELD_HEIGHT() {
		return FIELD_HEIGHT;
	}
	
}

