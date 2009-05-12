package ch.unisi.inf.pfii.teamblue.jark.model.bonus;

import java.util.ArrayList;

import ch.unisi.inf.pfii.teamblue.jark.model.Game;
import ch.unisi.inf.pfii.teamblue.jark.model.ball.Ball;
import ch.unisi.inf.pfii.teamblue.jark.model.ball.DefaultBall;
import ch.unisi.inf.pfii.teamblue.jark.model.ball.GhostBall;
import ch.unisi.inf.pfii.teamblue.jark.model.ball.UltraBall;

/**
 * Changes the balls into ultra balls
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate: 2009-05-11 21:38:29 +0200 (Mon, 11 May 2009) $
 *
 */

public final class GhostBallBonus extends BallBonus {

	public GhostBallBonus() {
		super(2);
	}
	@Override
	public String toString() {
		return "malus_ghostball";
	}
	@Override
	public void apply(final Game game) {
		final ArrayList<Ball> balls = game.getBalls();
		final int numberOfBalls = balls.size();
		
		for (int i = 0; i < numberOfBalls; i++) {
			final Ball oldBall = balls.get(i);
			final Ball newBall = new GhostBall(oldBall.getVaus(), oldBall.getLevel());
			
			game.replaceBall(oldBall, translateBall(oldBall, newBall));
		}
	}
	@Override
	public final void remove(final Game game) {
		final ArrayList<Ball> balls = game.getBalls();
		final int numberOfBalls = balls.size();
		
		for (int i = 0; i < numberOfBalls; i++) {
			final Ball oldBall = balls.get(i);
			final Ball newBall = new DefaultBall(oldBall.getVaus(), oldBall.getLevel());
			
			game.replaceBall(oldBall, translateBall(oldBall, newBall));
		}
	}
	
}
