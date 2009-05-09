package ch.unisi.inf.pfii.teamblue.jark.model.bonus;

import ch.unisi.inf.pfii.teamblue.jark.model.Game;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.RifleVaus;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.RocketLauncherVaus;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.Vaus;

/**
 * The vaus gets able to launch missiles
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 *
 */

public final class MissileVausBonus extends Bonus {
	
	@Override
	public String toString() {
		return "bonus_missilevaus";
	}
	@Override
	public void apply(final Game game) {
		final Vaus newVaus = new RocketLauncherVaus(game.getVaus().getX());
		game.setVaus(newVaus);
	}
	
}
