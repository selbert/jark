package ch.unisi.inf.pfii.teamblue.jark.model;


/**
 * 
 * This class contains informations about bricks.
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 * 
 */

public class Brick {
	private final int size;
	
	private int lives;
	private Bonus bonus;
	
	public Brick(final int size) {
		this.size = size;
		this.bonus = null;
		
	}
	
	public Bonus getBonus() {
		return bonus;
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

	public int getSize() {
		return size;
	}
}
