package ch.unisi.inf.pfii.teamblue.jark.model.ball;

import ch.unisi.inf.pfii.teamblue.jark.model.level.Level;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.Vaus;

/**
 * This ball is made out of rubber, it won't destroy anything it touches.
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 *
 */

public final class RubberBall extends Ball {

	public RubberBall(final Vaus vaus, final Level level) {
		super(vaus,level);
	}
	@Override
	public final Ball copy() {
		Ball returnBall = new RubberBall(vaus, level);
		returnBall.setX(x);
		returnBall.setY(y);
		return returnBall;
	}
	@Override
	public final void move() {
		float newX = x+(speedX * speedModifier);
		float newY = y+(speedY * speedModifier);
		
		if (boxEnabled && newY + (2*BALL_RADIUS) >= VAUS_Y + VAUS_HEIGHT + 1) {
			speedY = -speedY;
			return;
		}

		if (newY >= GAME_HEIGHT) {
			dead = true; //remove ball
			return;
		}
		
		if (newY < FIELD_HEIGHT) { 
			if (!level.persistentBrickHasBallInside(x,y) & bounceY(newY)){
				speedY = -speedY;
				newY = y;
			}
			
			if (!level.persistentBrickHasBallInside(x,y) & bounceX(newX)){
				speedX = -speedX;
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
	protected final boolean bounceX(final float newX) {
		if ((level.brickHasBallInside(newX, y)) 
			|| (level.brickHasBallInside(newX, y + (2*BALL_RADIUS)))
			|| (level.brickHasBallInside(newX + (2*BALL_RADIUS), y))
			|| (level.brickHasBallInside(newX + (2*BALL_RADIUS), y + (2*BALL_RADIUS)))) {
			return true;
		}
		return false;
	}
	@Override
	protected final boolean bounceY(final float newY) {
		if ((level.brickHasBallInside(x, newY))
			|| (level.brickHasBallInside(x + (2*BALL_RADIUS), newY))
			|| (level.brickHasBallInside(x, newY + (2*BALL_RADIUS)))
			|| (level.brickHasBallInside(x + (2*BALL_RADIUS), newY + (2*BALL_RADIUS)))){
			return true;
		} 
		return false;

	}
	@Override
	public final String toString() {
		return "rubberBall";
	}
}
