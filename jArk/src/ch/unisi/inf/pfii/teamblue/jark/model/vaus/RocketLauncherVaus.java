package ch.unisi.inf.pfii.teamblue.jark.model.vaus;

import ch.unisi.inf.pfii.teamblue.jark.implementation.Constants;
import ch.unisi.inf.pfii.teamblue.jark.model.Game;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.ammunitions.Bullet;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.ammunitions.Rocket;


/**
 * The vaus which shots missiles
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 *
 */

public final class RocketLauncherVaus extends Vaus implements Constants {
	private final static long rocketsDelay = 500;
	private long lastRocketSent;
	
	public RocketLauncherVaus(final int x) {
		super(x);
	}
	@Override
	public final String toString() {
		return "rocketLauncherVaus";
	}
	@Override
	public final void shoot(Game game) {
		if (lastRocketSent+rocketsDelay <= System.currentTimeMillis()) {
			lastRocketSent = System.currentTimeMillis();
			shootRocket(game);
		} 
	}
	private final void shootRocket(Game game) {
		Rocket newRocket = new Rocket(this, game.getLevel());
		newRocket.setX(posX-BALL_RADIUS+getWidth()/2);
		newRocket.setSpeedY(-3);
		game.addBullet(newRocket);
	}
}
