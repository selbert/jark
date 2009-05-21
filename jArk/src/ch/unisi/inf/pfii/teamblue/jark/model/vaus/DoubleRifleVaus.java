package ch.unisi.inf.pfii.teamblue.jark.model.vaus;

import ch.unisi.inf.pfii.teamblue.jark.model.Game;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.ammunitions.Bullet;

/**
 * The Vaus with double lasers
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 * 
 */

public final class DoubleRifleVaus extends Vaus {
	private long lastBulletShoot;
	private boolean lastShotLeft;

	public DoubleRifleVaus(final int x) {
		super(x);
	}

	@Override
	public final String toString() {
		switch (vausWidth) {
		case LONGVAUS_WIDTH:
			return "longDoubleRifleVaus";
		case SHORTVAUS_WIDTH:
			return "shortDoubleRifleVaus";
		default:
			return "doubleRifleVaus";
		}
	}

	@Override
	public final void shoot(final Game game) {
		if (lastBulletShoot + DOUBLE_RIFLE_DELAY <= System.currentTimeMillis()) {
			lastBulletShoot = System.currentTimeMillis();
			shootBullet(game);
		}
	}

	private final void shootBullet(final Game game) {
		final Bullet newBullet = new Bullet(this, game.getLevel());
		if (lastShotLeft) {
			newBullet.setX(posX + getWidth() - (2 * BALL_RADIUS) - 9);
		} else {
			newBullet.setX(posX + 13);
		}
		lastShotLeft = !lastShotLeft;
		newBullet.setSpeedY(-5);
		game.addBullet(newBullet);
	}

}
