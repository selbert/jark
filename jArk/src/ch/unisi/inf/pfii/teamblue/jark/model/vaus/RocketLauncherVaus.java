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

public final class RocketLauncherVaus extends Vaus implements Constants {
	private long lastRocketSent;
	
	public RocketLauncherVaus(final int x) {
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
		if (lastRocketSent+CANNON_DELAY <= System.currentTimeMillis()) {
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
