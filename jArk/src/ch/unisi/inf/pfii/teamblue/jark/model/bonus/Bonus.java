package ch.unisi.inf.pfii.teamblue.jark.model.bonus;



/**
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 * 
 */

public class Bonus {
	private static final int SPEED = 3;
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
		y = y + SPEED;
	}
	
	/**
	 * To clone the Bonus (used by the Bonuses enum)
	 * @return the same Bonus as this
	 */
	public Bonus makeMe () {
		try {
			return (Bonus) this.clone();
		} catch (Exception ex) {
			return null;
		}
	}
}
