package ch.unisi.inf.pfii.teamblue.jark.model;

/**
 * 
 * This class contains information about a ball.
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 * 
 */

public class Ball {
	private int x;
	private int y;
	private int speedX;
	private int speedY;
	private int state;
	
	public Ball(final int x, final int y) {
		this.x = x;
		this.y = y;
		speedX = 0;
		speedY = 0;
		state = 0;
	}
	
	public void setSpeedX(final int speedX) {
		this.speedX = speedX;
	}
	
	public void setSpeedY(final int speedY) {
		this.speedY = speedY;
	}
	
	public void setState(final int state) {
		this.state = state;
	}
	
	public int getState() {
		return state;
	}
	
	public void move() {
		x = x+speedX;
		y = y+speedY;
	}
}
