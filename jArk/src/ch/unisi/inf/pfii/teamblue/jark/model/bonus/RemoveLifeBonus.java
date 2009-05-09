package ch.unisi.inf.pfii.teamblue.jark.model.bonus;

import ch.unisi.inf.pfii.teamblue.jark.model.Game;

/**
 * Decrease the player lives by one.
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 *
 */

public final class RemoveLifeBonus extends Bonus {
	
	@Override
	public String toString() {
		return "malus_removelife";
	}
	@Override
	public void apply(final Game game) {
		super.apply(game);
		//TODO
	}
	
}
