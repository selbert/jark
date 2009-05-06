package ch.unisi.inf.pfii.teamblue.jark.model.ball;

import ch.unisi.inf.pfii.teamblue.jark.model.level.Level;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.Vaus;

/**
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 *
 */

public final class UltraBall extends Ball {

	public UltraBall(final Vaus vaus, final Level level) {
		super(vaus,level);
		// TODO Auto-generated constructor stub
	}
	@Override
	public Ball copy() {
		Ball returnBall = new UltraBall(vaus, level);
		returnBall.setX(x);
		returnBall.setY(y);
		return returnBall;
	}
	
	@Override
	public void move() {
		float newX = x + speedX;
		float newY = y + speedY;
		

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
	
	@Override
	protected boolean bounceVaus(final float newX, final float newY) {
		if (newY + (BALL_RADIUS*2) > VAUS_Y-1 && newY + (BALL_RADIUS*2) < VAUS_Y+(BALL_RADIUS*2) && newX + (BALL_RADIUS*2) >= vaus.getX() && newX <= vaus.getX() + vaus.getWidth()) {
			speedY = -speedY;
			speedX = ((newX + BALL_RADIUS) - (vaus.getX() + (vaus.getWidth() / 2))) / (vaus.getWidth() / 10);
			return true;
		}
		return false;
	}
	
	@Override
	protected boolean bounceX(final float newX) {
		if (level.brickHasBallInside(newX, y)) {
			if(level.brickHasBallInside(newX, y + (2*BALL_RADIUS))) {
				level.removeBrick(newX, y + BALL_RADIUS);
			} else {
				level.removeBrick(newX, y);
			}
			return true;
		} else if (level.brickHasBallInside(newX, y + (2*BALL_RADIUS))) {
			level.removeBrick(newX, y + (2*BALL_RADIUS));
			return true;
		} else if (level.brickHasBallInside(newX + (2*BALL_RADIUS), y)) {
			if (level.brickHasBallInside(newX + (2*BALL_RADIUS), y + (2*BALL_RADIUS))) {
				level.removeBrick(newX + (2*BALL_RADIUS), y + BALL_RADIUS);
			} else {
				level.removeBrick(newX + (2*BALL_RADIUS), y);
			}
			return true;
		} else if (level.brickHasBallInside(newX + (2*BALL_RADIUS), y + (2*BALL_RADIUS))) {
			level.removeBrick(newX + (2*BALL_RADIUS), y + (2*BALL_RADIUS));
			return true;
		} 
		return false;
	}
	
	
	@Override
	protected boolean bounceY(final float newY) {
		if (level.brickHasBallInside(x, newY)) {
			if (level.brickHasBallInside(x + (2*BALL_RADIUS), newY)) {
				level.removeBrick(x + BALL_RADIUS, newY);
			} else {
				level.removeBrick(x, newY);
			}
			return true;
		} else if (level.brickHasBallInside(x + (2*BALL_RADIUS), newY)) {
			level.removeBrick(x + (2*BALL_RADIUS), newY);
			return true;
		} else if (level.brickHasBallInside(x, newY + (2*BALL_RADIUS))) {
			if (level.brickHasBallInside(x + (2*BALL_RADIUS), newY + (2*BALL_RADIUS))) {
				level.removeBrick(x + BALL_RADIUS, newY + (2*BALL_RADIUS));
			} else {
				level.removeBrick(x, newY + (2*BALL_RADIUS));
			}
			return true;
		} else if (level.brickHasBallInside(x + (2*BALL_RADIUS), newY + (2*BALL_RADIUS))) {
			level.removeBrick(x + (2*BALL_RADIUS), newY + (2*BALL_RADIUS));
			return true;
		}
		return false;

	}
}
