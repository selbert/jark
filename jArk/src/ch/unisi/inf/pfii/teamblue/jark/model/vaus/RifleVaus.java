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
		if (lastBulletShoot+RIFLE_DELAY <= System.currentTimeMillis()) {
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
