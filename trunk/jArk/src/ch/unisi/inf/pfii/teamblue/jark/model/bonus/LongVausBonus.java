package ch.unisi.inf.pfii.teamblue.jark.model.bonus;

import ch.unisi.inf.pfii.teamblue.jark.model.Game;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.LongVaus;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.Vaus;

/**
 * Changes the size of the Vaus
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 *
 */

public final class LongVausBonus extends Bonus {
	
	@Override
	public String toString() {
		return "bonus_longvaus";
	}
	@Override
	public void apply(final Game game) {
		final Vaus newVaus = new LongVaus(game.getVaus().getX());
		game.setVaus(newVaus);
	}
	
}
