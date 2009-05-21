package ch.unisi.inf.pfii.teamblue.jark.model.vaus;

import ch.unisi.inf.pfii.teamblue.jark.model.Game;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.ammunitions.Bullet;

/**
 * The Vaus with a rifle
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 * 
 */

public final class RifleVaus extends Vaus {
	private long lastBulletShoot;

	public RifleVaus(final int x) {
		super(x);
	}

	@Override
	public String toString() {
		switch (vausWidth) {
		case LONGVAUS_WIDTH:
			return "longRifleVaus";
		case SHORTVAUS_WIDTH:
			return "shortRifleVaus";
		default:
			return "rifleVaus";
		}
	}

	@Override
	public final void shoot(final Game game) {
		if (lastBulletShoot + RIFLE_DELAY <= System.currentTimeMillis()) {
			lastBulletShoot = System.currentTimeMillis();
			shootBullet(game);
		}
	}

	private final void shootBullet(final Game game) {
		final Bullet newBullet = new Bullet(this, game.getLevel());
		newBullet.setX(posX - BALL_RADIUS + getWidth() / 2 + 2);
		newBullet.setSpeedY(-5);
		game.addBullet(newBullet);
	}

}
