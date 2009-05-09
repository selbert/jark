package ch.unisi.inf.pfii.teamblue.jark.model.ball;

import ch.unisi.inf.pfii.teamblue.jark.model.level.Level;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.Vaus;

/**
 * This ball destroys the brick over, under, on the left and on the right side of the hit brick.
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 *
 */

public class ExplosionBall extends Ball {

	public ExplosionBall(final Vaus vaus, final Level level) {
		super(vaus,level);
	}
	@Override
	public final Ball copy() {
		Ball returnBall = new ExplosionBall(vaus, level);
		returnBall.setX(x);
		returnBall.setY(y);
		return returnBall;
	}
	@Override
	protected final boolean bounceX(final float newX) {
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
	//the bricks to destroy
	protected final void explosionDestroy(final float x, final float y) {
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
