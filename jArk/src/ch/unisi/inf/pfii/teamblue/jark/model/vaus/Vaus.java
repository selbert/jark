package ch.unisi.inf.pfii.teamblue.jark.model.vaus;

import ch.unisi.inf.pfii.teamblue.jark.implementation.Constants;
import ch.unisi.inf.pfii.teamblue.jark.model.Game;

/**
 * 
 * This class represents the Vaus (the paddle), it has a state, a size and a position (x).
 * It is extended by the different types of vaus.
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 * 
 */

public abstract class Vaus implements Constants {
	protected int posX;
	private int moveLeft;
	private int moveRight;
	protected int vausWidth;
	
	public Vaus(final int posX) {
		this.posX = posX;
		vausWidth = VAUS_WIDTH;
	}
	
	@Override
	public abstract String toString();
	
	//getters and setters
	public void setX(final int posX) {
		this.posX = posX;
	}
	public int getX() {
		return posX;
	}
	public void setWidth(int width) {
		vausWidth = width;
	}
	public int getWidth() {
		return vausWidth;
	}
	
	/**
	 * Move the Vaus of a specified delta
	 * @param deltaX
	 */
	public void move(final int delta) {
		posX += delta;
	}
	
	/**
	 * Move the Vaus based on the moveX field
	 */
	public void move() {
		posX += moveRight * VAUS_SPEED - moveLeft * VAUS_SPEED;
		if (posX <= 5) {
			posX = 5;
		}
		if (posX + getWidth() + 5 >= GAME_WIDTH) {
			posX = GAME_WIDTH - getWidth() - 5;
		}
	}

	public final void moveLeft() {
		moveLeft = 1;
	}
	public final void moveRight() {
		moveRight = 1;
	}

	public void stopLeft() {
		moveLeft = 0;
	}
	public void stopRight() {
		moveRight = 0;
	}
	public void shoot(Game game) {
		return;
	}
	
}
