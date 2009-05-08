package ch.unisi.inf.pfii.teamblue.jark.model.vaus;

import java.util.ArrayList;

import ch.unisi.inf.pfii.teamblue.jark.model.Game;
import ch.unisi.inf.pfii.teamblue.jark.model.ball.Ball;
import ch.unisi.inf.pfii.teamblue.jark.model.level.Level;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.bullet.RifleBullet;

/**
 * The Vaus with laser
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 *
 */

public final class LaserVaus extends Vaus {

	public LaserVaus(final int x) {
		super(x);
	}
	@Override
	public final String toString() {
		return "laserVaus";
	}
	
	@Override
	public final void shoot(Game game) {
		RifleBullet newBullet = new RifleBullet(this, game.getLevel());
		newBullet.setX(posX);
		newBullet.setSpeedY(-5);
		game.addBall(newBullet);
	}
	
}
