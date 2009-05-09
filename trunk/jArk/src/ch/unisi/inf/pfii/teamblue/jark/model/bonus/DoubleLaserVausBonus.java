package ch.unisi.inf.pfii.teamblue.jark.model.bonus;

import ch.unisi.inf.pfii.teamblue.jark.model.Game;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.DoubleLaserVaus;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.LaserVaus;
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
		final Vaus newVaus = new DoubleLaserVaus(game.getVaus().getX());
		game.setVaus(newVaus);
	}
	
}
