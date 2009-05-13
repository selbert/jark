package ch.unisi.inf.pfii.teamblue.jark.model.bonus;

import java.util.ArrayList;

import ch.unisi.inf.pfii.teamblue.jark.model.Game;
import ch.unisi.inf.pfii.teamblue.jark.model.ball.Ball;
import ch.unisi.inf.pfii.teamblue.jark.model.ball.DefaultBall;
import ch.unisi.inf.pfii.teamblue.jark.model.ball.StickyBall;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.Vaus;

/**
 * The balls sticks to the Vaus
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 *
 */

public final class StickyBallBonus extends BallBonus {

	public StickyBallBonus() {
		super(2);
		super.setLife(STICKY_BALL);
	}
	@Override
	public String toString() {
		return "neutral_stickyball";
	}
	@Override
	public final void apply(final Game game) {
		final ArrayList<Ball> balls = game.getBalls();
		final int numberOfBalls = balls.size();

		for (int i = 0; i < numberOfBalls; i++) {
			final Ball oldBall = balls.get(i);
			final Ball newBall = new StickyBall(oldBall.getVaus(), oldBall.getLevel());

			game.replaceBall(oldBall, translateBall(oldBall, newBall));

			game.getVaus().addVausListener(newBall);
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
			
			game.getVaus().removeVausListener(oldBall);
		}
	}
	
}
