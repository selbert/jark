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

public abstract class Brick {
	private int lives;
	private int points;
	private Bonus bonus;
	
	@Override
	public abstract String toString();
	
	public Brick() {
		this.bonus = null;
	}
	
	public final Bonus getBonus() {
		return bonus;
	}
	
	public final void setLives(final int lives) {
		this.lives = lives;
	}
	
	public final void decrementLives() {
		lives--;
	}
	
	public final void setBonus(final Bonus bonus) {
		this.bonus = bonus;
	}
	
	public final int getLives() {
		return lives;
	}

	public final void setPoints(final int points) {
		this.points = points;
	}

	public final int getPoints() {
		return points;
	}
}
