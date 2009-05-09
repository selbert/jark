package ch.unisi.inf.pfii.teamblue.jark.model.bonus;

import java.util.ArrayList;

import ch.unisi.inf.pfii.teamblue.jark.model.Game;
import ch.unisi.inf.pfii.teamblue.jark.model.ball.Ball;

/**
 * Doubles the balls in the game (inverting speed X of the new balls)
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 *
 */

public final class DoubleBallBonus extends Bonus {

	@Override
	public final String toString() {
		return "bonus_doubleball";
	}
	@Override
	public final void apply(final Game game) {
		super.apply(game);
		ArrayList<Ball> balls = game.getBalls();
		int numberOfBalls = balls.size();
		
		for (int i = 0; i < numberOfBalls; i++) {
			final Ball oldBall = balls.get(i);
			final Ball newBall = oldBall.copy();
			
			newBall.setSpeedX(-1 * oldBall.getSpeedX());
			newBall.setSpeedY(oldBall.getSpeedY());
			
			game.addBall(newBall);
		}
	}
	
}
