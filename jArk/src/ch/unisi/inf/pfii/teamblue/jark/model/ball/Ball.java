package ch.unisi.inf.pfii.teamblue.jark.model.ball;

import ch.unisi.inf.pfii.teamblue.jark.model.Game;
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
		y = Vaus.getY() - RADIUS;
		speedX = 0;
		speedY = 0;
	}
	
	public void setSpeedX(final int speedX) {
		this.speedX = speedX;
	}
	
	public void setSpeedY(final int speedY) {
		this.speedY = speedY;
	}
	
	/**
	 * @Return ball main fields to string (test purpose only)
	 */
	public String toString() {
		return x + " " + y + " " + speedX + " " + speedY;
	}
	
	/** 
	 * Moves a ball
	 */
	public void move() {
		int newX = x+speedX;
		int newY = y+speedY;
		if (newX >= Game.getGAME_WIDTH()) {
			speedX = -speedX;
			newX = Game.getGAME_WIDTH() - 1;
		}
		if (newX < 0) {
			speedX = -speedX;
			newX = 0;
		}
		if (newY < 0) {
			speedY = -speedY;
			newY = 0;
		}
		
		//test purpose condition
		if (newY > Game.getGAME_HEIGHT()) {
			speedY = -speedY;
			newY = Game.getGAME_HEIGHT();
		}
		
		//until here
		if(newY < Level.getFIELD_HEIGHT()) { 
			if(level.insideBlock(newX, newY)) {
				BouncingDirection direction = level.computeDirection(x, y, newX, newY);
				switch(direction) {
					case VERTICAL:
						speedY = -speedY;
						newX = x;
						newY = y + speedY;
						break;
					case HORIZONTAL:
						speedX = -speedX;
						newY = y;
						newX = x + speedX;
						break;
					case DIAGONAL:
						speedY = -speedY;
						speedX = -speedX;
						newY = y + speedY;
						newX = x + speedX;
						break;
					default:
				}
			}
		}
		x = newX;
		y = newY;
	}
}
