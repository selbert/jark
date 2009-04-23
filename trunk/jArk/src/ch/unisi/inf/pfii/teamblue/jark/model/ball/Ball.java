package ch.unisi.inf.pfii.teamblue.jark.model.ball;

import ch.unisi.inf.pfii.teamblue.jark.model.level.*;
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
	private static final int RADIUS = 8;
	
	private int x;
	private int y;
	private int speedX;
	private int speedY;
	private Vaus vaus;
	private Level level;
	
	
	public Ball(Vaus vaus, Level level) {
		this.vaus = vaus;
		this.level = level;
		x = vaus.getX() + (vaus.getSize() / 2);
		y = vaus.getY() - RADIUS;
		speedX = 0;
		speedY = 0;
	}
	
	public void setSpeedX(final int speedX) {
		this.speedX = speedX;
	}
	
	public void setSpeedY(final int speedY) {
		this.speedY = speedY;
	}
	
	
	public void move() {
		int newX = x+speedX;
		int newY = y+speedY;
		if(newY < Level.getFIELD_HEIGHT()) { 
			if(level.insideBlock(newX, newY)) {
				BouncingDirection direction = level.computeDirection(x, y, newX, newY);
				switch(direction) {
					case VERTICAL:
						speedY = -speedY;
					case HORIZONTAL:
						speedX = -speedX;
					case DIAGONAL:
						speedY = -speedY;
						speedX = -speedX;
					default:
				}
			}
		}
	}
}
