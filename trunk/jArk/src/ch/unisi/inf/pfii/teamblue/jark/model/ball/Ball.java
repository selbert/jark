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
	
	private boolean dead;
	
	public Ball(Vaus vaus, Level level) {
		this.vaus = vaus;
		this.level = level;
		x = vaus.getX() + (VAUS_WIDTH / 2) - BALL_RADIUS;
		y = VAUS_Y - 2*BALL_RADIUS;
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
	 * Moves a ball
	 */
	public void move() {
		int newX = x+speedX;
		int newY = y+speedY;
		

		if (newY >= GAME_HEIGHT) {
			dead = true; //remove ball
			return;
		}
		
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
		
		
		if (newY + (BALL_RADIUS*2) > VAUS_Y-1 && newY + (BALL_RADIUS*2) < VAUS_Y+5 && newX >= vaus.getX() && newX <= vaus.getX() + VAUS_WIDTH) {
			speedY = -speedY;
			speedX = (newX - (vaus.getX() + (VAUS_WIDTH / 2))) / 20;
			newY =  VAUS_Y-1 - (BALL_RADIUS*2);
		}
		x = newX;
		y = newY;
	}

	public boolean isDead() {
		return dead;
	}
	
	public final int getX() {
		return x;
	}
	public final int getY() {
		return y;
	}
}
