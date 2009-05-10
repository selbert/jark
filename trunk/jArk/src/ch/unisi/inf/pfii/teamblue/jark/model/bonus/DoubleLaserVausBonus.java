package ch.unisi.inf.pfii.teamblue.jark.model.bonus;

import ch.unisi.inf.pfii.teamblue.jark.model.Game;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.DoubleRifleVaus;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.Vaus;

/**
 * Changes the vaus in a double laser equipped vaus
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 *
 */

public final class DoubleLaserVausBonus extends Bonus {
	
	@Override
	public final String toString() {
		return "bonus_doublelaservaus";
	}
	@Override
	public void apply(final Game game) {
		super.apply(game);
		final Vaus newVaus = new DoubleRifleVaus(game.getVaus().getX());
		newVaus.setWidth(game.getVaus().getWidth());
		game.setVaus(newVaus);
	}
	
}
