package ch.unisi.inf.pfii.teamblue.jark.model.bonus;

import java.awt.Image;

import ch.unisi.inf.pfii.teamblue.jark.implementation.Constants;
import ch.unisi.inf.pfii.teamblue.jark.implementation.VausListener;
import ch.unisi.inf.pfii.teamblue.jark.model.Game;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.Vaus;

/**
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 * 
 */

public abstract class Bonus implements Constants, VausListener {
	private int x;
	private int y;
	private Vaus vaus;
	private boolean taken;
	private boolean dead;
	
	public Bonus() {
		x = 0;
		y = 0;
		vaus = null;
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

	public void setVaus(Vaus vaus) {
		this.vaus = vaus;
	}
	
	public void move() {
		y = y + BONUS_SPEED;
		checkVausCollision();
		checkOutOfReach();
	}

	private void checkVausCollision() {
		if (y + BRICK_HEIGHT > VAUS_Y 
				&& y + BRICK_HEIGHT < VAUS_Y + BRICK_HEIGHT
				&& x + BRICK_WIDTH >= vaus.getX()
				&& x <= vaus.getX() + VAUS_WIDTH) {
			taken = true;
		}
	}
	
	private void checkOutOfReach() {
		if (y > GAME_HEIGHT) {
			dead = true;
		}
	}
	
	public boolean isDead() {
		return dead;
	}
	
	public boolean isTaken() {
		return taken;
	}
	
	public abstract void apply(Game game);
	public abstract Image getImage();
}
