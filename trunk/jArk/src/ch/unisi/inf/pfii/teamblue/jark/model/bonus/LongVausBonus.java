package ch.unisi.inf.pfii.teamblue.jark.model.bonus;

import ch.unisi.inf.pfii.teamblue.jark.model.Game;

/**
 * Changes the size of the Vaus
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 * 
 */

public final class LongVausBonus extends VausBonus {
	public LongVausBonus() {
		super(1);
		super.setLife(PERSISTENT);
	}

	@Override
	public final String toString() {
		return "bonus_longvaus";
	}

	@Override
	public final void apply(final Game game) {
		game.getVaus().setWidth(LONGVAUS_WIDTH);
		game.releaseBalls();
	}

	@Override
	public final void remove(final Game game) {
		game.getVaus().setWidth(VAUS_WIDTH);
		game.releaseBalls();
	}

}
