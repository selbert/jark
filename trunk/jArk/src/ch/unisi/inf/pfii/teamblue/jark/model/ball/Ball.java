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
	private float x;
	private float y;
	private float speedX;
	private float speedY;
	private Vaus vaus;
	private Level level;
	
	private boolean dead;
	
	public Ball(Vaus vaus, Level level) {
		this.vaus = vaus;
		this.level = level;
		x = (float)vaus.getX() + ((float)VAUS_WIDTH / 2) - (float)BALL_RADIUS;
		y = (float)(VAUS_Y - 2*BALL_RADIUS);
		speedX = 0;
		speedY = 0;
	}
	
	public final void setSpeedX(final float speedX) {
		this.speedX = speedX;
	}
	
	public final void setSpeedY(final float speedY) {
		this.speedY = speedY;
	}

	
	/** 
	 * Moves a ball
	 */
	public void move() {
		float newX = x+speedX;
		float newY = y+speedY;
		

		if (newY >= GAME_HEIGHT) {
			dead = true; //remove ball
			return;
		}
		
		if (newY < FIELD_HEIGHT) { 
			if (bounceY(newY)){
				newY = y;
			}
			
			if (bounceX(newX)){
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
		
		
		if (bounceVaus(newX, newY)) {
			newY =  VAUS_Y-1 - (BALL_RADIUS*2);
		}
		x = newX;
		y = newY;
	}
	
	private boolean bounceVaus(final float newX, final float newY) {
		if (newY + (BALL_RADIUS*2) > VAUS_Y-1 && newY + (BALL_RADIUS*2) < VAUS_Y+5 && newX + (BALL_RADIUS*2) >= vaus.getX() && newX <= vaus.getX() + VAUS_WIDTH) {
			speedY = -speedY;
			speedX = ((newX + BALL_RADIUS) - (vaus.getX() + (VAUS_WIDTH / 2))) / 10;
			return true;
		}
		return false;
	}
	
	private boolean bounceX(final float newX) {
		if (level.brickHasBallInside(newX, y)) {
			speedX = -speedX;
			if(level.brickHasBallInside(newX, y + (2*BALL_RADIUS))) {
				level.removeBrick(newX, y + BALL_RADIUS);
			} else {
				level.removeBrick(newX, y);
			}
			return true;
		} else if (level.brickHasBallInside(newX, y + (2*BALL_RADIUS))) {
			speedX = -speedX;
			level.removeBrick(newX, y + (2*BALL_RADIUS));
			return true;
		} else if (level.brickHasBallInside(newX + (2*BALL_RADIUS), y)) {
			speedX = -speedX;
			if (level.brickHasBallInside(newX + (2*BALL_RADIUS), y + (2*BALL_RADIUS))) {
				level.removeBrick(newX + (2*BALL_RADIUS), y + BALL_RADIUS);
			} else {
				level.removeBrick(newX + (2*BALL_RADIUS), y);
			}
			return true;
		} else if (level.brickHasBallInside(newX + (2*BALL_RADIUS), y + (2*BALL_RADIUS))) {
			speedX = -speedX;
			level.removeBrick(newX + (2*BALL_RADIUS), y + (2*BALL_RADIUS));
			return true;
		} 
		return false;
	}
	
	
	private boolean bounceY(final float newY) {
		if (level.brickHasBallInside(x, newY)) {
			speedY = -speedY;
			if (level.brickHasBallInside(x + (2*BALL_RADIUS), newY)) {
				level.removeBrick(x + BALL_RADIUS, newY);
			} else {
				level.removeBrick(x, newY);
			}
			return true;
		} else if (level.brickHasBallInside(x + (2*BALL_RADIUS), newY)) {
			speedY = -speedY;
			level.removeBrick(x + (2*BALL_RADIUS), newY);
			return true;
		} else if (level.brickHasBallInside(x, newY + (2*BALL_RADIUS))) {
			speedY = -speedY;
			if (level.brickHasBallInside(x + (2*BALL_RADIUS), newY + (2*BALL_RADIUS))) {
				level.removeBrick(x + BALL_RADIUS, newY + (2*BALL_RADIUS));
			} else {
				level.removeBrick(x, newY + (2*BALL_RADIUS));
			}
			return true;
		} else if (level.brickHasBallInside(x + (2*BALL_RADIUS), newY + (2*BALL_RADIUS))) {
			speedY = -speedY;
			level.removeBrick(x + (2*BALL_RADIUS), newY + (2*BALL_RADIUS));
			return true;
		}
		return false;

	}
	
	public boolean isDead() {
		return dead;
	}
	
	public final float getX() {
		return x;
	}
	public final float getY() {
		return y;
	}
	
	public final String toString() {
		return x + " " + y + " " + speedX + " " + speedY;
	}
}
