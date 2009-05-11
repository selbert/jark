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
	
	public StickyBall(final Vaus vaus, final Level level) {
		super(vaus,level);
		sticked = false;
	}
	@Override
	public final Ball copy() {
		Ball returnBall = new StickyBall(vaus, level);
		returnBall.setX(x);
		returnBall.setY(y);
		returnBall.setSpeedMod(speedModifier);
		return returnBall;
	}
	@Override
	public final String toString() {
		return "stickyBall";
	}
	@Override
	protected boolean bounceVaus(final float newX, final float newY) {
		if (!sticked && newY + (BALL_RADIUS*2) > VAUS_Y-1 && newY + (BALL_RADIUS*2) < VAUS_Y+(BALL_RADIUS*2) && newX + (BALL_RADIUS*2) >= vaus.getX() && newX <= vaus.getX() + vaus.getWidth()) {
			sticked = true;
			speedY = 0;
			speedX = 0;
			return true;
		}
		return false;
	}
	
	public final void vausMoved(final int delta) {
		if (sticked) {
			setX(x - delta);
		}
	}
	
	
}
