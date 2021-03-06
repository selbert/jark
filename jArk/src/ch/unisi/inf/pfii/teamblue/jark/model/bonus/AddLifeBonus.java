package ch.unisi.inf.pfii.teamblue.jark.model.bonus;

import ch.unisi.inf.pfii.teamblue.jark.model.Game;

/**
 * This bonus increases the lives of the player by one.
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 * 
 */

public final class AddLifeBonus extends PlayerBonus {

	public AddLifeBonus() {
		super(0);
		super.setLife(INSTANTANEOUS);
	}

	@Override
	public final String toString() {
		return "bonus_addlife";
	}

	@Override
	public final void apply(final Game game) {
		game.getPlayer().incrementLives();
	}

}
