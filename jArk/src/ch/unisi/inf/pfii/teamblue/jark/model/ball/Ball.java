package ch.unisi.inf.pfii.teamblue.jark.model.ball;

import ch.unisi.inf.pfii.teamblue.jark.model.level.Level;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.Vaus;

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
	
	public Ball(final int x, final int y) {
		this.x = x;
		this.y = y;
		speedX = 0;
		speedY = 0;
	}
	
	public void setSpeedX(final int speedX) {
		this.speedX = speedX;
	}
	
	public void setSpeedY(final int speedY) {
		this.speedY = speedY;
	}
	
	public void move(Vaus vaus, Level level) {
		x = x+speedX;
		y = y+speedY;
	}
}
