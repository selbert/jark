package ch.unisi.inf.pfii.teamblue.jark.model.level;

import java.util.ArrayList;
import java.util.Random;

import ch.unisi.inf.pfii.teamblue.jark.implementation.Constants;
import ch.unisi.inf.pfii.teamblue.jark.implementation.LevelListener;
import ch.unisi.inf.pfii.teamblue.jark.implementation.Utils;
import ch.unisi.inf.pfii.teamblue.jark.implementation.VausSetListener;
import ch.unisi.inf.pfii.teamblue.jark.model.bonus.Bonus;
import ch.unisi.inf.pfii.teamblue.jark.model.bonus.Bonuses;
import ch.unisi.inf.pfii.teamblue.jark.model.brick.Brick;
import ch.unisi.inf.pfii.teamblue.jark.model.brick.PersistentBrick;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.Vaus;

/**
 * The level is defined by an array of bricks
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 * 
 */
public final class Level implements Constants, VausSetListener {
	private final Random rnd;
	private String bonusDistString;

	private final String name;
	private final Brick[][] bricks;
	private final ArrayList<Bonus> freeBonuses;
	private final ArrayList<LevelListener> listeners;

	private Vaus vaus;

	/**
	 * Constructor of Level, creates a new level (field of bricks).
	 */
	
	public Level(final int bonusPercentage, final ArrayList<Bonus> freeBonuses, final Vaus vaus) {
		rnd = new Random();
		listeners = new ArrayList<LevelListener>();
		this.name = "Random";
		this.vaus = vaus;
		this.freeBonuses = freeBonuses;
		bricks = createRandomLevel();
		if (bonusPercentage > 0) {
			if (bonusDistString == null) {
				bonusDistString = getDistributionString();
			}
			addRandomBonus((int) ((float) getNumberOfDestroyableBrick() / 100 * bonusPercentage));
		}
		
	}

	public Level(final String name, final Brick[][] brickField,
			final ArrayList<Bonus> freeBonuses, final Vaus vaus,
			final int bonusPercentage) {
		rnd = new Random();
		listeners = new ArrayList<LevelListener>();
		this.name = name;
		this.vaus = vaus;
		this.freeBonuses = freeBonuses;
		bricks = brickField;
		if (bonusPercentage > 0) {
			if (bonusDistString == null) {
				bonusDistString = getDistributionString();
			}
			addRandomBonus((int) ((float) getNumberOfDestroyableBrick() / 100 * bonusPercentage));
		}
	}

	// getters and setters

	public final Brick[][] getBricks() {
		return bricks;
	}

	public final void setVaus(final Vaus vaus) {
		this.vaus = vaus;
		for (final Bonus b : freeBonuses) {
			b.setVaus(vaus);
		}
	}

	private final int getNumberOfDestroyableBrick() {
		int returnNumber = 0;
		for (final Brick[] rowOfBricks : bricks) {
			for (final Brick brick : rowOfBricks) {
				if (brick != null && !(brick instanceof PersistentBrick)) {
					returnNumber++;
				}
			}
		}
		return returnNumber;
	}
	
	public final String getName() {
		return name;
	}

	/**
	 * Creates a string with the index of the bonus repeated the times defined
	 * in the Bonuses enum
	 */
	private final String getDistributionString() {
		String bonusString = "";
		final int numberOfBonus = Bonuses.values().length;
		for (int a = 0; a < numberOfBonus; a++) {
			final int probability = Bonuses.getProb(a);
			for (int i = 0; i < probability; i++) {
				bonusString += "" + a + ",";
			}
		}
		return bonusString.substring(0, bonusString.length() - 1);
	}

	/**
	 * Randomly selects a bonus type from a string of bonus indexes separated by
	 * ","
	 * 
	 * @return Bonus
	 */
	private final Bonus createBonus() {
		final String[] bonusArray = bonusDistString.split(",");
		return Bonuses.getBonus(Integer.parseInt(bonusArray[rnd
				.nextInt(bonusArray.length)]));
	}


	/**
	 * Add bonuses to random bricks
	 * 
	 * @param numOfBonus
	 */
	private final void addRandomBonus(final int numOfBonus) {
		final ArrayList<Brick> listOfBricks = new ArrayList<Brick>();

		for (final Brick[] rowOfBricks : bricks) {
			for (final Brick brick : rowOfBricks) {
				if (brick != null && !(brick instanceof PersistentBrick)) {
					listOfBricks.add(brick);
				}
			}
		}

		for (int i = 0; i < numOfBonus; i++) {
			final int index = rnd.nextInt(listOfBricks.size());
			final Bonus bonus = createBonus();
			listOfBricks.remove(index).setBonus(bonus);
		}
	}
	
	private final Brick[][] createRandomLevel() {
		Brick[][] bricks = new Brick[FIELD_ROWS][FIELD_COLUMNS];
		for (int row = 4; row<FIELD_ROWS; row++) {
			for (int col = 0; col<FIELD_COLUMNS; col++) {
				bricks[row][col] = Utils.intToBrick(rnd.nextInt(15));
			}
		}
		return bricks;
	}

	/**
	 * Returns true if the given position is inside a brick
	 * 
	 * @param x
	 * @param y
	 * @return true if inside a brick
	 */
	public final boolean brickHasBallInside(final float x, final float y) {
		try {
			final int[] pos = Utils.getFieldPosition((int) x, (int) y);
			if (bricks[pos[1]][pos[0]] != null) {
				return true;
			}
			return false;
		} catch (final ArrayIndexOutOfBoundsException e) {
			return false;
		}
	}
	
	/**
	 * Returns true if the given position is inside a  persistent brick
	 * 
	 * @param x
	 * @param y
	 * @return true if inside a brick
	 */
	public final boolean persistentBrickHasBallInside(final float x,
			final float y) {
		// return false;
		try {
			final int[] pos = Utils.getFieldPosition((int) x, (int) y);
			final int[] pos1 = Utils.getFieldPosition(
					(int) (x + (2 * BALL_RADIUS)), (int) y);
			final int[] pos2 = Utils.getFieldPosition((int) x,
					(int) (y + (2 * BALL_RADIUS)));
			final int[] pos3 = Utils.getFieldPosition(
					(int) (x + (2 * BALL_RADIUS)),
					(int) (y + (2 * BALL_RADIUS)));
			if ((bricks[pos[1]][pos[0]] != null && bricks[pos[1]][pos[0]] instanceof PersistentBrick)
					|| (bricks[pos1[1]][pos1[0]] != null && bricks[pos1[1]][pos1[0]] instanceof PersistentBrick)
					|| (bricks[pos2[1]][pos2[0]] != null && bricks[pos2[1]][pos2[0]] instanceof PersistentBrick)
					|| (bricks[pos3[1]][pos3[0]] != null && bricks[pos3[1]][pos3[0]] instanceof PersistentBrick)) {
				return true;
			}
			return false;
		} catch (final ArrayIndexOutOfBoundsException e) {
			return false;
		}
	}

	/**
	 * Removes a brick
	 * 
	 * @param pos
	 *            the position array
	 */
	public final boolean removeBrick(final float remX, final float remY) {
		if (remX > 0 && remX < FIELD_WIDTH && remY < FIELD_HEIGHT && remY > 0) {
			final int[] pos = Utils.getFieldPosition((int) remX, (int) remY);
			final Brick brick = bricks[pos[1]][pos[0]];

			if (brick != null) {
				fireBrickHit(brick);
				final int lives = brick.getLives();
				if (lives == 1) {
					final Bonus bonus = bricks[pos[1]][pos[0]].getBonus();
					if (bonus != null) {
						final int[] a = Utils.getPixelPosition(pos[0], pos[1]);
						bonus.setX(a[0]);
						bonus.setY(a[1]);
						bonus.setVaus(vaus);
						freeBonuses.add(bonus);
						fireBonusReleased(bonus);
					}
					bricks[pos[1]][pos[0]] = null;
				} else if (lives > 1) {
					bricks[pos[1]][pos[0]].decrementLives();
				} else {
					// persistent brick - do nothing
				}
				if (!(brick instanceof PersistentBrick)) {
					return true;
				}
			}
		}
		return false;
	}

	public final void addLevelListener(final LevelListener li) {
		listeners.add(li);
	}

	public final void removeLevelListener(final LevelListener li) {
		listeners.remove(li);
	}

	public final void fireBonusReleased(final Bonus bonus) {
		for (final LevelListener li : listeners) {
			li.bonusReleased(bonus);
		}
	}

	public final void fireBrickHit(final Brick brick) {
		for (final LevelListener li : listeners) {
			li.brickHit(brick);
		}
	}

	/**
	 * Returns true if the level has no more destroyable brick inside  
	 *
	 * @return true if level is cleared
	 */
	public final boolean isCleared() {
		boolean accumulator = true;
		for (int i = 0; (i < bricks.length) && accumulator; i++) {
			for (int j = 0; (j < bricks[i].length) && accumulator; j++) {

				accumulator = ((bricks[i][j] == null) || (bricks[i][j] instanceof PersistentBrick));
			}
		}
		return accumulator;
	}
}
