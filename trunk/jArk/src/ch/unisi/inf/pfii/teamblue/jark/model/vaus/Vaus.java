package ch.unisi.inf.pfii.teamblue.jark.model.vaus;

/**
 * 
 * This class represents the Vaus (the paddle), it has a state, a size and a position (x).
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 * 
 */

public class Vaus {
	private static final int Y = 10;
	private int x;
	private int size;
	
	public Vaus(final int x, final int size) {
		this.x = x;
		this.size = size;
	}
	
	public void setX(final int x) {
		this.x = x;
	}
	
	public int getX() {
		return x;
	}

	public void setSize(final int size) {
		this.size = size;
	}

	public int getSize() {
		return size;
	}

	public static int getY() {
		return Y;
	}
	
}
