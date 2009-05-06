package ch.unisi.inf.pfii.teamblue.jark.implementation;

import ch.unisi.inf.pfii.teamblue.jark.model.brick.Brick;
import ch.unisi.inf.pfii.teamblue.jark.model.brick.DefaultBrick;
import ch.unisi.inf.pfii.teamblue.jark.model.brick.PersistentBrick;
import ch.unisi.inf.pfii.teamblue.jark.model.brick.ResistentBrick;
import ch.unisi.inf.pfii.teamblue.jark.model.brick.VeryResistentBrick;

public abstract class Utils implements Constants {
	
	/**
	 * Converts pixel coordinates into bricks coordinates. 
	 * 
	 * @param x pixels from the left
	 * @param y pixels from the top
	 * @return
	 */
	public final static int[] getFieldPosition(final int x, final int y) {
		int posy = (int)((float) FIELD_ROWS/ (float) FIELD_HEIGHT*y);
		int posx = (int)((float) FIELD_COLUMNS/ (float)FIELD_WIDTH*x);
		return new int[] {posx, posy};
	}
	
	/**
	 * Converts brick coordinates into pixel coordinates. 
	 * 
	 * @param x bricks from the left
	 * @param y bricks from the top
	 * @return
	 */
	public final static int[] getPixelPosition(final int x, final int y) {
		int posy = y * (int)((float)FIELD_HEIGHT / (float)FIELD_ROWS);
		int posx = x * (int)((float)FIELD_WIDTH / (float)FIELD_COLUMNS);
		return new int[] {posx, posy};
	}

	/**
	 * Converts an integer into a specific brick.
	 * Used when loading levels from files.
	 * 
	 * @param i block type (integer)
	 * @return selected brick 
	 */
	public final static Brick intToBrick(final int i) {
		switch(i) {
			case 1: return new ResistentBrick();
			case 2: return new VeryResistentBrick();
			case 3: return new PersistentBrick();
			default: return new DefaultBrick();
		}
	}
	
}
