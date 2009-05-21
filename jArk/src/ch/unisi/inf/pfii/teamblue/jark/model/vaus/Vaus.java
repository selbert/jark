package ch.unisi.inf.pfii.teamblue.jark.model.vaus;

import java.util.ArrayList;

import ch.unisi.inf.pfii.teamblue.jark.implementation.Constants;
import ch.unisi.inf.pfii.teamblue.jark.implementation.VausListener;
import ch.unisi.inf.pfii.teamblue.jark.model.Game;

/**
 * 
 * This class represents the Vaus (the paddle), it has a state, a size and a
 * position (x). It is extended by the different types of vaus.
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
	private ArrayList<VausListener> vausListenerList;

	public Vaus(final int posX) {
		this.posX = posX;
		vausWidth = VAUS_WIDTH;
		vausListenerList = new ArrayList<VausListener>();
	}

	@Override
	public abstract String toString();

	// getters and setters
	public final void setX(final int posX) {
		fireVausMoved(posX);
		this.posX = posX;

	}

	private final void fireVausMoved(final int i) {
		for (final VausListener li : vausListenerList) {
			li.vausMoved(i);
		}
	}

	public final int getX() {
		return posX;
	}

	public final void setWidth(final int width) {
		vausWidth = width;
	}

	public final int getWidth() {
		return vausWidth;
	}

	/**
	 * Move the Vaus of a specified delta
	 * 
	 * @param deltaX
	 */
	public final void move(final int delta) {
		setX(posX + delta);
	}

	/**
	 * Move the Vaus based on the moveX field
	 */
	public void move() {
		setX(posX + moveRight * VAUS_SPEED - moveLeft * VAUS_SPEED);
		if (posX <= 5) {
			setX(5);
		}
		if (posX + getWidth() + 5 >= GAME_WIDTH) {
			setX(GAME_WIDTH - getWidth() - 5);
		}
	}

	public final void moveLeft() {
		moveLeft = 1;
	}

	public final void moveRight() {
		moveRight = 1;
	}

	public final void stopLeft() {
		moveLeft = 0;
	}

	public final void stopRight() {
		moveRight = 0;
	}

	public void shoot(final Game game) {
		return;
	}

	public final void addVausListener(final VausListener li) {
		vausListenerList.add(li);
	}

	public final void removeVausListener(final VausListener li) {
		vausListenerList.remove(li);
	}

	public final void setVausListenerLsit(final ArrayList<VausListener> listenerList) {
		vausListenerList = listenerList;
	}

	public final ArrayList<VausListener> getVausListenerLsit() {
		return vausListenerList;
	}

}
