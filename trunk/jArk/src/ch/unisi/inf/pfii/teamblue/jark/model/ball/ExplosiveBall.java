package ch.unisi.inf.pfii.teamblue.jark.model.ball;

import ch.unisi.inf.pfii.teamblue.jark.model.level.Level;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.Vaus;

/**
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 *
 */

public final class ExplosiveBall extends Ball {

	public ExplosiveBall(final Vaus vaus, final Level level) {
		super(vaus,level);
		// TODO Auto-generated constructor stub
	}
	@Override
	public Ball copy() {
		Ball returnBall = new ExplosiveBall(vaus, level);
		returnBall.setX(x);
		returnBall.setY(y);
		return returnBall;
	}
	
	@Override
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
	
	@Override
	protected boolean bounceX(final float newX) {
		if (level.brickHasBallInside(newX, y)) {
			speedX = -speedX;
			if(level.brickHasBallInside(newX, y + (2*BALL_RADIUS))) {
				explosionDestroy(newX, y + BALL_RADIUS);
			} else {
				explosionDestroy(newX, y);
			}
			return true;
		} else if (level.brickHasBallInside(newX, y + (2*BALL_RADIUS))) {
			speedX = -speedX;
			explosionDestroy(newX, y + (2*BALL_RADIUS));
			return true;
		} else if (level.brickHasBallInside(newX + (2*BALL_RADIUS), y)) {
			speedX = -speedX;
			if (level.brickHasBallInside(newX + (2*BALL_RADIUS), y + (2*BALL_RADIUS))) {
				explosionDestroy(newX + (2*BALL_RADIUS), y + BALL_RADIUS);
			} else {
				explosionDestroy(newX + (2*BALL_RADIUS), y);
			}
			return true;
		} else if (level.brickHasBallInside(newX + (2*BALL_RADIUS), y + (2*BALL_RADIUS))) {
			speedX = -speedX;
			explosionDestroy(newX + (2*BALL_RADIUS), y + (2*BALL_RADIUS));
			return true;
		} 
		return false;
	}
	
	
	@Override
	protected boolean bounceY(final float newY) {
		if (level.brickHasBallInside(x, newY)) {
			speedY = -speedY;
			if (level.brickHasBallInside(x + (2*BALL_RADIUS), newY)) {
				explosionDestroy(x + BALL_RADIUS, newY);
			} else {
				explosionDestroy(x, newY);
			}
			return true;
		} else if (level.brickHasBallInside(x + (2*BALL_RADIUS), newY)) {
			speedY = -speedY;
			explosionDestroy(x + (2*BALL_RADIUS), newY);
			return true;
		} else if (level.brickHasBallInside(x, newY + (2*BALL_RADIUS))) {
			speedY = -speedY;
			if (level.brickHasBallInside(x + (2*BALL_RADIUS), newY + (2*BALL_RADIUS))) {
				explosionDestroy(x + BALL_RADIUS, newY + (2*BALL_RADIUS));
			} else {
				explosionDestroy(x, newY + (2*BALL_RADIUS));
			}
			return true;
		} else if (level.brickHasBallInside(x + (2*BALL_RADIUS), newY + (2*BALL_RADIUS))) {
			speedY = -speedY;
			explosionDestroy(x + (2*BALL_RADIUS), newY + (2*BALL_RADIUS));
			return true;
		}
		return false;

	}
	
	private final void explosionDestroy(final float x, final float y) {
		level.removeBrick(x, y);
		level.removeBrick(x - BRICK_WIDTH, y);
		level.removeBrick(x + BRICK_WIDTH, y);
		level.removeBrick(x, y - BRICK_HEIGHT);
		level.removeBrick(x, y + BRICK_HEIGHT);
	}

	@Override
	public String toString() {
		return "explosionBall";
	}
}
