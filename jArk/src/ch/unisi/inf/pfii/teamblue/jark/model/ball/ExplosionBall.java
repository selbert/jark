package ch.unisi.inf.pfii.teamblue.jark.model.ball;

import ch.unisi.inf.pfii.teamblue.jark.model.level.Level;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.Vaus;

/**
 * This ball destroys the brick over, under, on the left and on the right side
 * of the hit brick.
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 * 
 */

public class ExplosionBall extends Ball {

	public ExplosionBall(final Vaus vaus, final Level level) {
		super(vaus, level);
	}

	@Override
	public final Ball copy() {
		final Ball returnBall = new ExplosionBall(vaus, level);
		return transferBall(returnBall);
	}

	// the bricks to destroy
	@Override
	protected final void destroyBrick(final float x, final float y) {
		super.destroyBrick(x, y);
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
