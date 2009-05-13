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
		if (lastBallSent+CANNON_DELAY <= System.currentTimeMillis()) {
			lastBallSent = System.currentTimeMillis();
			shootBall(game);
		} 
	}
	private final void shootBall(Game game) {
		Rocket newRocket = new Rocket(this, game.getLevel());
		newRocket.setX(posX-BALL_RADIUS+getWidth()/2);
		newRocket.setSpeedY(-3);
		game.addBullet(newRocket);
	}
}
