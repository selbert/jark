package ch.unisi.inf.pfii.teamblue.jark.model.brick;

import ch.unisi.inf.pfii.teamblue.jark.model.bonus.Bonus;

/**
 * 
 * This class contains informations about bricks.
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 * 
 */

public class Brick {
	private int lives;
	private Bonus bonus;
	
	public Brick() {
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
}
