package ch.unisi.inf.pfii.teamblue.jark.model.bonus;

import java.util.ArrayList;

import ch.unisi.inf.pfii.teamblue.jark.model.Game;
import ch.unisi.inf.pfii.teamblue.jark.model.ball.Ball;
import ch.unisi.inf.pfii.teamblue.jark.model.ball.RubberBall;

/**
 * Changes all the balls in the game into rubber balls, and doubles them (false balls)
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 *
 */

public final class FalseBallsBonus extends Bonus {
	
	@Override
	public final String toString() {
		return "malus_falseball";
	}
	@Override
	public final void apply(final Game game) {
		final ArrayList<Ball> balls = game.getBalls();
		final int numberOfBalls = balls.size();
		
		for (int i = 0; i < numberOfBalls;i++) {
			final Ball oldBall = balls.get(i);
			final Ball newBall = new RubberBall(oldBall.getVaus(), oldBall.getLevel());
			
			newBall.setSpeedX(oldBall.getSpeedX());
			newBall.setSpeedY(oldBall.getSpeedY());
			newBall.setX(oldBall.getX());
			newBall.setY(oldBall.getY());
			newBall.setBoxEnabled(oldBall.getBoxEnabled());
			newBall.setSpeedMod(oldBall.getSpeedMod());
			
			game.replaceBall(oldBall, newBall);
			
			for (int j = 0; j < 5; j++) {
				final Ball newBall1 = oldBall.copy();
				
				newBall1.setSpeedX(-1 * oldBall.getSpeedX());
				newBall1.setSpeedY(oldBall.getSpeedY());
				newBall1.setBoxEnabled(oldBall.getBoxEnabled());
				newBall1.setSpeedMod(oldBall.getSpeedMod());
				
				game.addBall(newBall1);
			}
		}
	}
	
}
