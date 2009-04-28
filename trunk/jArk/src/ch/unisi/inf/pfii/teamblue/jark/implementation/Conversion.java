package ch.unisi.inf.pfii.teamblue.jark.implementation;

import ch.unisi.inf.pfii.teamblue.jark.model.brick.Brick;
import ch.unisi.inf.pfii.teamblue.jark.model.brick.DefaultBrick;
import ch.unisi.inf.pfii.teamblue.jark.model.brick.PersistentBrick;
import ch.unisi.inf.pfii.teamblue.jark.model.brick.ResistentBrick;
import ch.unisi.inf.pfii.teamblue.jark.model.brick.VeryResistentBrick;

public abstract class Conversion implements Constants {
	
	/**
	 * Converts pixel coordinates into bricks coordinates. 
	 * Public for testing purposes.
	 * 
	 * @param x pixels from the left
	 * @param y pixels from the top
	 * @return
	 */
	public static int[] getFieldPosition(final int x, final int y) {
		int posy = (int)((float) FIELD_ROWS/ (float) FIELD_HEIGHT*y);
		int posx = (int)((float) FIELD_COLUMNS/ (float)FIELD_WIDTH*x);
		return new int[] {posx, posy};
	}

	/**
	 * Converts an integer into a specific brick.
	 * Used when loading levels from files.
	 * 
	 * @param i block type (integer)
	 * @return selected brick 
	 */
	public static Brick intToBrick(final int i) {
		switch(i) {
			case 1: return new ResistentBrick();
			case 2: return new VeryResistentBrick();
			case 3: return new PersistentBrick();
			default: return new DefaultBrick();
		}
	}
	
}
