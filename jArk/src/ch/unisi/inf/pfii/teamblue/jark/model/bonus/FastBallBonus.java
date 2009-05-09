package ch.unisi.inf.pfii.teamblue.jark.model.bonus;

import java.util.ArrayList;

import ch.unisi.inf.pfii.teamblue.jark.model.Game;
import ch.unisi.inf.pfii.teamblue.jark.model.ball.Ball;

/**
 * Speeds up the balls in the game
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 *
 */

public final class FastBallBonus extends Bonus {
	
	@Override
	public final String toString() {
		return "neutral_fastball";
	}
	@Override
	public void apply(final Game game) {
		super.apply(game);
		final ArrayList<Ball> balls = game.getBalls();
		final int numberOfBalls = balls.size();
		
		for (int i = 0; i < numberOfBalls; i++) {
			balls.get(i).setSpeedMod((float) 1.5);
		}
	}
	
}
