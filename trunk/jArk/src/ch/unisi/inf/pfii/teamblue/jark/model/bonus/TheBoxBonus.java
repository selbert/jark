package ch.unisi.inf.pfii.teamblue.jark.model.bonus;

import java.util.ArrayList;

import ch.unisi.inf.pfii.teamblue.jark.model.Game;
import ch.unisi.inf.pfii.teamblue.jark.model.ball.Ball;

/**
 * Creates a Box sorrounding the game area (no ball can get out)
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 *
 */

public final class TheBoxBonus extends Bonus {
	
	@Override
	public String toString() {
		return "bonus_box";
	}
	@Override
	public void apply(final Game game) {
		super.apply(game);
		final ArrayList<Ball> balls = game.getBalls();
		final int numberOfBalls = balls.size();
		
		for (int i = 0; i < numberOfBalls; i++) {
			balls.get(i).setBoxEnabled(true);
		}
		
		//TODO draw line
	}
	
}
