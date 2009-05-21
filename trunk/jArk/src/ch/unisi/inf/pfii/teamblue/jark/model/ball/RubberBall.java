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
		super(vaus, level);
	}

	@Override
	public final Ball copy() {
		final Ball returnBall = new RubberBall(vaus, level);

		return transferBall(returnBall);
	}

	@Override
	public final void move() {
		final float speedHypot = sqrt(speedX * speedX + speedY * speedY);
		final float speedx = (speedX / speedHypot) * speed * speedModifier;
		final float speedy = (speedY / speedHypot) * speed * speedModifier;
		float newX = x + speedx;
		float newY = y + speedy;

		if (boxEnabled && newY + (2 * BALL_RADIUS) >= VAUS_Y + VAUS_HEIGHT + 1) {
			speedY = -speedY;
			y = (VAUS_Y - (2 * BALL_RADIUS));
			return;
		}

		if (newY >= GAME_HEIGHT) {
			dead = true; // remove ball
			return;
		}

		if (newY < FIELD_HEIGHT && !brickHasBallInside(x, y)) {
			final boolean bounceX = bounceX(newX);
			final boolean bounceY = bounceY(newY);
			if (bounceY) {
				speedY = -speedY;
				newY = y;
			} else if (bounceX) {
				speedX = -speedX;
				newX = x;
			} else if (bounceDiag(newX, newY)) {
				speedY = -speedY;
				newY = y;
				speedX = -speedX;
				newX = x;
			}
		}

		if (newX + (BALL_RADIUS * 2) >= GAME_WIDTH) {
			speedX = -speedX;
			newX = GAME_WIDTH - (BALL_RADIUS * 2);
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
			newY = VAUS_Y - 1 - (BALL_RADIUS * 2);
		}
		if (!level.persistentBrickHasBallInside(newX, newY)
				|| (level.persistentBrickHasBallInside(newX, newY) && level
						.persistentBrickHasBallInside(x, y))) {
			x = newX;
			y = newY;
		}
	}

	@Override
	protected final boolean bounceX(final float newX) {
		if ((level.brickHasBallInside(newX, y))
				|| (level.brickHasBallInside(newX, y + (2 * BALL_RADIUS)))
				|| (level.brickHasBallInside(newX + (2 * BALL_RADIUS), y))
				|| (level.brickHasBallInside(newX + (2 * BALL_RADIUS), y
						+ (2 * BALL_RADIUS)))) {
			return true;
		}
		return false;
	}

	@Override
	protected final boolean bounceY(final float newY) {
		if ((level.brickHasBallInside(x, newY))
				|| (level.brickHasBallInside(x + (2 * BALL_RADIUS), newY))
				|| (level.brickHasBallInside(x, newY + (2 * BALL_RADIUS)))
				|| (level.brickHasBallInside(x + (2 * BALL_RADIUS), newY
						+ (2 * BALL_RADIUS)))) {
			return true;
		}
		return false;

	}

	public final boolean brickHasBallInside(final float x, final float y) {
		return (level.brickHasBallInside(x, y)
				|| level.brickHasBallInside((x + (2 * BALL_RADIUS)), y)
				|| level.brickHasBallInside(x, (y + (2 * BALL_RADIUS))) || level
				.brickHasBallInside((x + (2 * BALL_RADIUS)),
						(y + (2 * BALL_RADIUS))));
	}

	@Override
	protected final boolean bounceDiag(final float newX, final float newY) {
		if (level.brickHasBallInside(newX, newY)
				|| level.brickHasBallInside(newX, newY + (2 * BALL_RADIUS))
				|| level.brickHasBallInside(newX + (2 * BALL_RADIUS), newY)
				|| level.brickHasBallInside(newX + (2 * BALL_RADIUS), newY
						+ (2 * BALL_RADIUS))) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public final String toString() {
		return "rubberBall";
	}
}
