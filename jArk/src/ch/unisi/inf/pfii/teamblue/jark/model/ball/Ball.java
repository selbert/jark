package ch.unisi.inf.pfii.teamblue.jark.model.ball;

import ch.unisi.inf.pfii.teamblue.jark.implementation.Constants;
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

public abstract class Ball implements Constants {
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
		y = Vaus.getY() - BALL_RADIUS;
		speedX = 0;
		speedY = 0;
	}
	
	public final void setSpeedX(final int speedX) {
		this.speedX = speedX;
	}
	
	public final void setSpeedY(final int speedY) {
		this.speedY = speedY;
	}
	
	/**
	 * @Return ball main fields to string (test purpose only)
	 */
	public final String toString() {
		return x + " " + y + " " + speedX + " " + speedY;
	}
	
	/** 
	 * Moves a ball
	 */
	public void move() {
		int newX = x+speedX;
		int newY = y+speedY;
		

		//until here
		if (newY < FIELD_HEIGHT) { 
			if (level.brickHasBallInside(x, newY)) {
				speedY = -speedY;
				if (level.brickHasBallInside(x + (2*BALL_RADIUS), newY)) {
					level.removeBrick(x + BALL_RADIUS, newY);
				} else {
					level.removeBrick(x, newY);
				}
				newY = y;
			} else if (level.brickHasBallInside(x + (2*BALL_RADIUS), newY)) {
				speedY = -speedY;
				level.removeBrick(x + (2*BALL_RADIUS), newY);
				newY = y;
			} else if (level.brickHasBallInside(x, newY + (2*BALL_RADIUS))) {
				speedY = -speedY;
				if (level.brickHasBallInside(x + (2*BALL_RADIUS), newY + (2*BALL_RADIUS))) {
					level.removeBrick(x + BALL_RADIUS, newY + (2*BALL_RADIUS));
				} else {
					level.removeBrick(x, newY + (2*BALL_RADIUS));
				}
				newY = y;
			} else if (level.brickHasBallInside(x + (2*BALL_RADIUS), newY + (2*BALL_RADIUS))) {
				speedY = -speedY;
				level.removeBrick(x + (2*BALL_RADIUS), newY + (2*BALL_RADIUS));
				newY = y;
			}

			
			if (level.brickHasBallInside(newX, y)) {
				speedX = -speedX;
				if(level.brickHasBallInside(newX, y + (2*BALL_RADIUS))) {
					level.removeBrick(newX, y + BALL_RADIUS);
				} else {
					level.removeBrick(newX, y);
				}
				newX = x;
			} else if (level.brickHasBallInside(newX, y + (2*BALL_RADIUS))) {
				speedX = -speedX;
				level.removeBrick(newX, y + (2*BALL_RADIUS));
				newX = x;
			} else if (level.brickHasBallInside(newX + (2*BALL_RADIUS), y)) {
				speedX = -speedX;
				if (level.brickHasBallInside(newX + (2*BALL_RADIUS), y + (2*BALL_RADIUS))) {
					level.removeBrick(newX + (2*BALL_RADIUS), y + BALL_RADIUS);
				} else {
					level.removeBrick(newX + (2*BALL_RADIUS), y);
				}
				newX = x;
			} else if (level.brickHasBallInside(newX + (2*BALL_RADIUS), y + (2*BALL_RADIUS))) {
				speedX = -speedX;
				level.removeBrick(newX + (2*BALL_RADIUS), y + (2*BALL_RADIUS));
				newX = x;
			} 
			
			
			
			
			
			
		}
		if (newX + (BALL_RADIUS*2) >= GAME_WIDTH) {
			speedX = -speedX;
			newX = GAME_WIDTH - (BALL_RADIUS*2);
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
		if (newY + (BALL_RADIUS*2) > GAME_HEIGHT) {
			speedY = -speedY;
			newY = GAME_HEIGHT - (BALL_RADIUS*2);
		}
		x = newX;
		y = newY;
	}

	public final int getX() {
		return x;
	}
	public final int getY() {
		return y;
	}
}