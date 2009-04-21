package ch.unisi.inf.pfii.teamblue.jark.model;


/**
 * 
 * This class contains informations about bricks.
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate: 2009-04-13 15:29:08 +0200 (Mon, 13 Apr 2009) $
 * 
 */

public class Brick {
	private static int SIZE;
	
	private final int type;
	
	private int lives;
	private Bonus bonus;
	
	public Brick(final int type) {
		this.type = type;
		this.bonus = null;
		
	}
	
	public Bonus getBonus() {
		return bonus;
	}
	
	public int getType() {
		return type;
	}

	public void setLives(final int lives) {
		this.lives = lives;
	}
	
	public void setBonus() {
		this.bonus = new Bonus();
	}
	
	public int getLives() {
		return lives;
	}

	public static void setSIZE(final int size) {
		SIZE = size;
	}

	public static int getSIZE() {
		return SIZE;
	}
}
