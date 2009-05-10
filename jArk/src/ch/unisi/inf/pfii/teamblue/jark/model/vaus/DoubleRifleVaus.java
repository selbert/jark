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
	private final static long doubleRifleDelay = 50;
	private boolean lastShotLeft;

	public DoubleRifleVaus(final int x) {
		super(x);
	}
	@Override
	public String toString() {
		switch(vausWidth) {
			case LONGVAUS_WIDTH:
				return "longVaus";
			case SHORTVAUS_WIDTH:
				return "shortVaus";
			default:
				return "defaultVaus";
		}
	}
	@Override
	public final void shoot(Game game) {
		if (lastBulletShoot+doubleRifleDelay <= System.currentTimeMillis()) {
			lastBulletShoot = System.currentTimeMillis();
			shootBullet(game);
		}
	}
	
	private final void shootBullet(Game game) {
		Bullet newBullet = new Bullet(this, game.getLevel());
		if (lastShotLeft) {
			newBullet.setX(posX+getWidth() - (2*BALL_RADIUS));
		} else {
			newBullet.setX(posX);
		}
		lastShotLeft = !lastShotLeft;
		newBullet.setSpeedY(-5);
		game.addBullet(newBullet);
	}
	
}
