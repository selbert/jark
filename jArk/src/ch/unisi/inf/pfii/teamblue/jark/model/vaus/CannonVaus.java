package ch.unisi.inf.pfii.teamblue.jark.model.vaus;

import ch.unisi.inf.pfii.teamblue.jark.implementation.Constants;
import ch.unisi.inf.pfii.teamblue.jark.model.Game;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.ammunitions.Rocket;

/**
 * The vaus which shots missiles
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 * 
 */

public final class CannonVaus extends Vaus implements Constants {
	private long lastBallSent;

	public CannonVaus(final int x) {
		super(x);
	}

	@Override
	public final String toString() {
		switch (vausWidth) {
		case LONGVAUS_WIDTH:
			return "longCannonVaus";
		case SHORTVAUS_WIDTH:
			return "shortCannonVaus";
		default:
			return "cannonVaus";
		}
	}

	@Override
	public final void shoot(final Game game) {
		if (lastBallSent + CANNON_DELAY <= System.currentTimeMillis()) {
			lastBallSent = System.currentTimeMillis();
			shootBall(game);
		}
	}

	private final void shootBall(final Game game) {
		final Rocket newRocket = new Rocket(this, game.getLevel());
		newRocket.setX(posX - BALL_RADIUS + getWidth() / 2);
		newRocket.setSpeedY(-3);
		game.addBullet(newRocket);
	}
}
