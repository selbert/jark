package ch.unisi.inf.pfii.teamblue.jark.model.bonus;

import ch.unisi.inf.pfii.teamblue.jark.model.Game;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.RifleVaus;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.LongVaus;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.Vaus;

/**
 * Laser equipped Vaus
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 *
 */

public final class LaserVausBonus extends Bonus {
	
	@Override
	public final String toString() {
		return "bonus_laservaus";
	}
	@Override
	public void apply(final Game game) {
		final Vaus newVaus = new RifleVaus(game.getVaus().getX());
		game.setVaus(newVaus);
	}
	
}
