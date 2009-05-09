package ch.unisi.inf.pfii.teamblue.jark.model.vaus;

import java.util.ArrayList;

import ch.unisi.inf.pfii.teamblue.jark.model.Game;
import ch.unisi.inf.pfii.teamblue.jark.model.ball.Ball;
import ch.unisi.inf.pfii.teamblue.jark.model.level.Level;
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
	private final static long rifleDelay = 100;
	
	public RifleVaus(final int x) {
		super(x);
	}
	@Override
	public final String toString() {
		return "rifleVaus";
	}
	
	@Override
	public final void shoot(Game game) {
		if (lastBulletShoot+rifleDelay <= System.currentTimeMillis()) {
			lastBulletShoot = System.currentTimeMillis();
			shootBullet(game);
		} 
	}
	
	private final void shootBullet(Game game) {
		Bullet newBullet = new Bullet(this, game.getLevel());
		newBullet.setX(posX-BALL_RADIUS+getWidth()/2);
		newBullet.setSpeedY(-5);
		game.addBullet(newBullet);
	}
	
}