package ch.unisi.inf.pfii.teamblue.jark.model.bonus;

import ch.unisi.inf.pfii.teamblue.jark.model.Game;

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
		game.getVaus().setWidth(LONGVAUS_WIDTH);
		super.apply(game);
	}
	
}
