package ch.unisi.inf.pfii.teamblue.jark.implementation;

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

}
