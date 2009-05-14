package ch.unisi.inf.pfii.teamblue.jark.model.bonus;

import ch.unisi.inf.pfii.teamblue.jark.model.Game;


/**
 * Returns everything to normal.
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 *
 */

public final class ResetStatusBonus extends PlayerBonus {
	
	public ResetStatusBonus() {
		super(0);
		super.setLife(INSTANTANEOUS);
	}
	@Override
	public String toString() {
		return "neutral_resetstatus";
	}
	@Override
	public void apply(final Game game) {
		game.removeTakenBonuses();
	}
	
	
}
