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

public final class ResetStatusBonus extends Bonus {
	
	@Override
	public String toString() {
		return "neutral_resetstatus";
	}
	@Override
	public void apply(final Game game) {
		super.apply(game);
		final ArrayList<Ball> balls = game.getBalls();
		final int numberOfBalls = balls.size();
		
		for (int i = 0; i < numberOfBalls; i++) {
			final Ball newBall = new DefaultBall(balls.get(i).getVaus(), balls.get(i).getLevel());
			newBall.setSpeedX(balls.get(i).getSpeedX());
			newBall.setSpeedY(balls.get(i).getSpeedY());
			newBall.setX(balls.get(i).getX());
			newBall.setY(balls.get(i).getY());
			
			game.replaceBall(balls.get(i), newBall);
		}
		
		final Vaus newVaus = new DefaultVaus(game.getVaus().getX());
		game.setVaus(newVaus); 
	}
	@Override
	public final void remove(final Game game) {
		game.removeTakenBonuses();
	}
	
	
}
