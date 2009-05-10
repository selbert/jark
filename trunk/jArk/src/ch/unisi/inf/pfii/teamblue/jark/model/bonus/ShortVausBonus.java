package ch.unisi.inf.pfii.teamblue.jark.model.bonus;

import ch.unisi.inf.pfii.teamblue.jark.model.Game;

/**
 * Changes the size of the Vaus
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 *
 */

public final class ShortVausBonus extends Bonus {
	
	@Override
	public String toString() {
		return "malus_shortvaus";
	}
	@Override
	public void apply(final Game game) {
		game.getVaus().setWidth(SHORTVAUS_WIDTH);
		super.apply(game);
	}
	@Override
	public final void remove(final Game game) {
		game.getVaus().setWidth(VAUS_WIDTH);
	}
}
