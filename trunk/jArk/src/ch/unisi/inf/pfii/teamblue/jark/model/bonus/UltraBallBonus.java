package ch.unisi.inf.pfii.teamblue.jark.model.bonus;

import java.util.ArrayList;

import ch.unisi.inf.pfii.teamblue.jark.model.Game;
import ch.unisi.inf.pfii.teamblue.jark.model.ball.Ball;
import ch.unisi.inf.pfii.teamblue.jark.model.ball.UltraBall;

/**
 * Changes the balls into ultra balls
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 *
 */

public final class UltraBallBonus extends Bonus {
	
	@Override
	public String toString() {
		return "bonus_ultraball";
	}
	@Override
	public void apply(final Game game) {
		final ArrayList<Ball> balls = game.getBalls();
		final int numberOfBalls = balls.size();
		
		for (int i = 0; i < numberOfBalls; i++) {
			final Ball oldBall = balls.get(i);
			final Ball newBall = new UltraBall(oldBall.getVaus(), oldBall.getLevel());
			
			newBall.setSpeedX(oldBall.getSpeedX());
			newBall.setSpeedY(oldBall.getSpeedY());
			newBall.setX(oldBall.getX());
			newBall.setY(oldBall.getY());
			newBall.setBoxEnabled(oldBall.getBoxEnabled());
			newBall.setSpeedMod(oldBall.getSpeedMod());
			
			game.replaceBall(oldBall, newBall);
		}
	}
	
}
