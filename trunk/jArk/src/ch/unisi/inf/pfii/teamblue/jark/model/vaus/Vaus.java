package ch.unisi.inf.pfii.teamblue.jark.model.vaus;

import java.awt.event.KeyEvent;

import ch.unisi.inf.pfii.teamblue.jark.implementation.Constants;

/**
 * 
 * This class represents the Vaus (the paddle), it has a state, a size and a position (x).
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 * 
 */

public abstract class Vaus implements Constants {
	private int posX;
	private int moveX;

	public Vaus(final int posX) {
		this.posX = posX;
	}
	
	@Override
	public abstract String toString();
	
	public void move(final int deltaX) {
		if (deltaX <= 5) {
			posX = 5;
		} else if (deltaX + getWidth() + 5 >= GAME_WIDTH) {
			posX = GAME_WIDTH - getWidth() - 5;
		} else {
			posX = deltaX;
		}
	}
	
	public void move() {
		posX += moveX;
		if (posX <= 5) {
			posX = 5;
		}
		if (posX + getWidth() + 5 >= GAME_WIDTH) {
			posX = GAME_WIDTH - getWidth() - 5;
		}
	}

	public void pressedKey(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode == KeyEvent.VK_LEFT) {
			moveX = -5;
		}
		if (keyCode == KeyEvent.VK_RIGHT) {
			moveX = 5;
		}
	}

	public void releasedKey(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_RIGHT) {
			moveX = 0;
		}
	}
	
	public void setX(final int posX) {
		this.posX = posX;
	}
	
	public int getX() {
		return posX;
	}
	
	public int getWidth() { 
		return VAUS_WIDTH;
	}
}
