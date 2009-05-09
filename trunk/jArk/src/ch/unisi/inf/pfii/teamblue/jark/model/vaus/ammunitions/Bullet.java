package ch.unisi.inf.pfii.teamblue.jark.model.vaus.ammunitions;

import ch.unisi.inf.pfii.teamblue.jark.model.ball.DefaultBall;
import ch.unisi.inf.pfii.teamblue.jark.model.level.Level;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.Vaus;

public class Bullet extends DefaultBall {
	public Bullet(Vaus vaus, Level level) {
		super(vaus, level);
	}

	public final void move() {
		float newY = y+speedY;
		
		if (newY >= GAME_HEIGHT) {
			dead = true; //remove ball
			return;
		}
		
		if (newY < FIELD_HEIGHT) { 
			if (!level.persistentBrickHasBallInside(x,y) && bounceY(newY)) {
				newY = y;
			}
		}
		
		if (newY < 0) {
			dead = true; //remove ball
			return;
		}
		
		y = newY;
	}
	
	/**
	 * Check the intersections of the Y component of the ball
	 * @param newY
	 * @return
	 */
	protected final boolean bounceY(final float newY) {
		if (level.brickHasBallInside(x, newY)) {
			if (level.brickHasBallInside(x + (2*BALL_RADIUS), newY)) {
				dead = true;
				level.removeBrick(x + BALL_RADIUS, newY);
			} else {
				dead = true;
				level.removeBrick(x, newY);
			}
			return true;
		} else if (level.brickHasBallInside(x + (2*BALL_RADIUS), newY)) {
			dead = true;
			level.removeBrick(x + (2*BALL_RADIUS), newY);
			return true;
		} else if (level.brickHasBallInside(x, newY + (2*BALL_RADIUS))) {
			if (level.brickHasBallInside(x + (2*BALL_RADIUS), newY + (2*BALL_RADIUS))) {
				dead = true;
				level.removeBrick(x + BALL_RADIUS, newY + (2*BALL_RADIUS));
			} else {
				dead = true;
				level.removeBrick(x, newY + (2*BALL_RADIUS));
			}
			return true;
		} else if (level.brickHasBallInside(x + (2*BALL_RADIUS), newY + (2*BALL_RADIUS))) {
			dead = true;
			level.removeBrick(x + (2*BALL_RADIUS), newY + (2*BALL_RADIUS));
			return true;
		}
		return false;
	}
	@Override
	public final String toString() {
		return "bullet";
	}
}
