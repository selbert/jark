package ch.unisi.inf.pfii.teamblue.jark.model;

/**
 * 
 * This class represents the Vaus (the paddle), it has a state, a size and a position (x).
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate: 2009-04-13 11:23:44 +0200 (Mon, 13 Apr 2009) $
 * 
 */

public class Vaus {
	private int x;
	private int size;
	private int state;
	
	public Vaus(final int x, final int size, final int state) {
		this.x = x;
		this.size = size;
		this.state = state;
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

	public void setState(final int state) {
		this.state = state;
	}

	public int getState() {
		return state;
	}
	
	
}
