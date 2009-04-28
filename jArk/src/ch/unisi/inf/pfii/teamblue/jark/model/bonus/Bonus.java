package ch.unisi.inf.pfii.teamblue.jark.model.bonus;

import ch.unisi.inf.pfii.teamblue.jark.implementation.Constants;



/**
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 * 
 */

public abstract class Bonus implements Constants {
	private int x;
	private int y;
	
	public Bonus() {
		x = 0;
		y = 0;
		
	}

	public void setX(int x) {
		this.x = x;
	}
	
	public int getX() {
		return x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getY() {
		return y;
	}
	
	public void move() {
		y = y + BONUS_SPEED;
	}
	
	
}
