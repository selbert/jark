package ch.unisi.inf.pfii.teamblue.jark.model.vaus.ammunitions;

import ch.unisi.inf.pfii.teamblue.jark.model.ball.ExplosionBall;
import ch.unisi.inf.pfii.teamblue.jark.model.level.Level;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.Vaus;

public class Rocket extends ExplosionBall {
	public Rocket(final Vaus vaus, final Level level) {
		super(vaus, level);
	}

	@Override
	public final void move() {
		float newY = y + speedY;

		if (newY >= GAME_HEIGHT) {
			dead = true;
			return;
		}

		if (newY < FIELD_HEIGHT) {
			if (!level.persistentBrickHasBallInside(x, y) && bounceY(newY)) {
				newY = y;
			}
		}

		if (newY < 0) {
			dead = true;
			return;
		}

		y = newY;
	}

	/**
	 * Check the intersections of the Y component of the ball
	 * 
	 * @param newY
	 * @return
	 */
	@Override
	protected final boolean bounceY(final float newY) {
		if (level.brickHasBallInside(x, newY)) {
			if (level.brickHasBallInside(x + (2 * BALL_RADIUS), newY)) {
				dead = true;
				destroyBrick(x + BALL_RADIUS, newY);
			} else {
				dead = true;
				destroyBrick(x, newY);
			}
			return true;
		} else if (level.brickHasBallInside(x + (2 * BALL_RADIUS), newY)) {
			dead = true;
			destroyBrick(x + (2 * BALL_RADIUS), newY);
			return true;
		} else if (level.brickHasBallInside(x, newY + (2 * BALL_RADIUS))) {
			if (level.brickHasBallInside(x + (2 * BALL_RADIUS), newY
					+ (2 * BALL_RADIUS))) {
				dead = true;
				destroyBrick(x + BALL_RADIUS, newY + (2 * BALL_RADIUS));
			} else {
				dead = true;
				destroyBrick(x, newY + (2 * BALL_RADIUS));
			}
			return true;
		} else if (level.brickHasBallInside(x + (2 * BALL_RADIUS), newY
				+ (2 * BALL_RADIUS))) {
			dead = true;
			destroyBrick(x + (2 * BALL_RADIUS), newY + (2 * BALL_RADIUS));
			return true;
		}
		return false;
	}

	@Override
	public final String toString() {
		return "rocket";
	}
}
