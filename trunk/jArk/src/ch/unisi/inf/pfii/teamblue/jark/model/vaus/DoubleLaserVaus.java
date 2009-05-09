package ch.unisi.inf.pfii.teamblue.jark.model.vaus;

import ch.unisi.inf.pfii.teamblue.jark.model.Game;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.bullet.RifleBullet;


/**
 * The Vaus with double lasers
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 *
 */

public final class DoubleLaserVaus extends Vaus {
	
	private boolean lastShotLeft;

	public DoubleLaserVaus(final int x) {
		super(x);
		lastShotLeft = false;
	}
	@Override
	public final String toString() {
		return "doubleLaserVaus";
	}
	@Override
	public final void shoot(Game game) {
		RifleBullet newBullet = new RifleBullet(this, game.getLevel());
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
