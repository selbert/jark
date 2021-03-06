package ch.unisi.inf.pfii.teamblue.jark.model.ball;

import ch.unisi.inf.pfii.teamblue.jark.model.level.Level;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.Vaus;

/**
 * This ball sticks to the Vaus.
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 * 
 */

public final class StickyBall extends Ball {

	private boolean sticked;
	private float vausRelPos;

	public StickyBall(final Vaus vaus, final Level level) {
		super(vaus, level);
		sticked = false;
	}

	@Override
	public final Ball copy() {
		final Ball returnBall = new StickyBall(vaus, level);
		returnBall.setSticky(getSticky());
		returnBall.setVausRelPos(vausRelPos);
		vaus.addVausListener(returnBall);
		return transferBall(returnBall);
	}

	@Override
	public void setSticky(final boolean setting) {
		sticked = setting;
	}

	@Override
	public void setVausRelPos(final float vausRelPos) {
		this.vausRelPos = vausRelPos;
	}

	private final boolean getSticky() {
		return sticked;
	}

	@Override
	public final String toString() {
		return "stickyBall";
	}

	@Override
	protected final boolean bounceVaus(final float newX, final float newY) {
		if (!sticked && newY + (BALL_RADIUS * 2) > VAUS_Y - 1
				&& newY + (BALL_RADIUS * 2) < VAUS_Y + (BALL_RADIUS * 2)
				&& newX + (BALL_RADIUS * 2) >= vaus.getX()
				&& newX <= vaus.getX() + vaus.getWidth()) {
			vausRelPos = getX() - vaus.getX();
			sticked = true;
			speedY = 0;
			speedX = 0;
			ballLastDestroy = System.currentTimeMillis();
			return true;
		}
		return false;
	}

	@Override
	public final void move() {
		if (!sticked) {
			super.move();
		} else {
			ballLastDestroy = System.currentTimeMillis();
		}
	}

	@Override
	public final void vausMoved(final int newX) {
		if (sticked) {
			setX(vausRelPos + newX);
		}
	}

	@Override
	public final void release() {
		if (sticked) {
			sticked = !sticked;
			setSpeedX(((getX() + BALL_RADIUS) - (vaus.getX() + (vaus.getWidth() / 2)))
					/ (vaus.getWidth() / 10));
			setSpeedY(-3);
		}
	}
}
