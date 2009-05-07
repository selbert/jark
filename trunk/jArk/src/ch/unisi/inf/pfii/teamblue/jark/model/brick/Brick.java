package ch.unisi.inf.pfii.teamblue.jark.model.brick;

import ch.unisi.inf.pfii.teamblue.jark.model.bonus.Bonus;

/**
 * This class contains informations about brick.
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 * 
 */

public abstract class Brick {
	private int lives;
	private int points;
	private Bonus bonus;
	
	@Override
	public abstract String toString();
	
	public Brick() {
		this.bonus = null;
	}
	
	//getters
	public final Bonus getBonus() {
		return bonus;
	}
	public final int getLives() {
		return lives;
	}
	public final int getPoints() {
		return points;
	}
	
	//setters
	public final void setLives(final int lives) {
		this.lives = lives;
	}
	public final void setBonus(final Bonus bonus) {
		this.bonus = bonus;
	}
	public final void setPoints(final int points) {
		this.points = points;
	}
	public final void decrementLives() {
		lives--;
	}
	
}
