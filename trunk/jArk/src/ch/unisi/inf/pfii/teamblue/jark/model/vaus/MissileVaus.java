package ch.unisi.inf.pfii.teamblue.jark.model.vaus;

import ch.unisi.inf.pfii.teamblue.jark.model.Game;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.bullet.MissileBullet;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.bullet.RifleBullet;


/**
 * The vaus which shots missiles
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 *
 */

public final class MissileVaus extends Vaus {

	public MissileVaus(final int x) {
		super(x);
	}
	@Override
	public final String toString() {
		return "missileVaus";
	}
	@Override
	public final void shoot(Game game) {
		MissileBullet newBullet = new MissileBullet(this, game.getLevel());
		newBullet.setX(posX+getWidth()/2);
		newBullet.setSpeedY(-5);
		game.addBullet(newBullet);
	}
	
}
