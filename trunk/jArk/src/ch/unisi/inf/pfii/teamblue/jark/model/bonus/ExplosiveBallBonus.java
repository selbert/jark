package ch.unisi.inf.pfii.teamblue.jark.model.bonus;

import java.util.ArrayList;

import ch.unisi.inf.pfii.teamblue.jark.model.Game;
import ch.unisi.inf.pfii.teamblue.jark.model.ball.Ball;
import ch.unisi.inf.pfii.teamblue.jark.model.ball.ExplosiveBall;


/**
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 *
 */

public final class ExplosiveBallBonus extends Bonus {
	@Override
	public String toString() {
		return "bonus_explosionball";
	}
	@Override
	public void apply(Game game) {
		ArrayList<Ball> balls = game.getBalls();
		int numberOfBalls = balls.size();
		
		for (int i = 0; i < numberOfBalls; i++) {
			Ball newBall = new ExplosiveBall(balls.get(i).getVaus(), balls.get(i).getLevel());
			newBall.setSpeedX(balls.get(i).getSpeedX());
			newBall.setSpeedY(balls.get(i).getSpeedY());
			newBall.setX(balls.get(i).getX());
			newBall.setY(balls.get(i).getY());
			
			game.replaceBall(balls.get(i), newBall);
			
			
		}
	}
}
