package ch.unisi.inf.pfii.teamblue.jark.model.bonus;

import ch.unisi.inf.pfii.teamblue.jark.model.Game;

/**
 * Changes the size of the Vaus
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 * 
 */

public final class ShortVausBonus extends VausBonus {
	public ShortVausBonus() {
		super(1);
		super.setLife(PERSISTENT);
	}

	@Override
	public final String toString() {
		return "malus_shortvaus";
	}

	@Override
	public final void apply(final Game game) {
		game.getVaus().setWidth(SHORTVAUS_WIDTH);
		game.releaseBalls();
	}

	@Override
	public final void remove(final Game game) {
		game.getVaus().setWidth(VAUS_WIDTH);
		game.releaseBalls();
	}
}
