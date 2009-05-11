package ch.unisi.inf.pfii.teamblue.jark.model.bonus;

import java.util.ArrayList;

import ch.unisi.inf.pfii.teamblue.jark.model.Game;
import ch.unisi.inf.pfii.teamblue.jark.model.ball.Ball;
import ch.unisi.inf.pfii.teamblue.jark.model.ball.DefaultBall;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.DefaultVaus;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.Vaus;


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
		super.setLife(0);
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
