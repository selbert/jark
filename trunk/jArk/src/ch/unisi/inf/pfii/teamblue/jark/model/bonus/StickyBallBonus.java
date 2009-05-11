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

			newBall.setSpeedX(oldBall.getSpeedX());
			newBall.setSpeedY(oldBall.getSpeedY());
			newBall.setX(oldBall.getX());
			newBall.setY(oldBall.getY());
			newBall.setBoxEnabled(oldBall.getBoxEnabled());
			newBall.setSpeedMod(oldBall.getSpeedMod());

			Vaus vaus = game.getVaus();
			vaus.addVausListener(newBall);
			
			game.replaceBall(oldBall, newBall);
		}
	}
	@Override
	public final void remove(final Game game) {
		final ArrayList<Ball> balls = game.getBalls();
		final int numberOfBalls = balls.size();
		
		for (int i = 0; i < numberOfBalls; i++) {
			final Ball oldBall = balls.get(i);
			final Ball newBall = new DefaultBall(oldBall.getVaus(), oldBall.getLevel());
			
			newBall.setSpeedX(oldBall.getSpeedX());
			newBall.setSpeedY(oldBall.getSpeedY());
			newBall.setX(oldBall.getX());
			newBall.setY(oldBall.getY());
			newBall.setBoxEnabled(oldBall.getBoxEnabled());
			newBall.setSpeedMod(oldBall.getSpeedMod());
			
			Vaus vaus = game.getVaus();
			vaus.removeVausListener(oldBall);
			
			game.replaceBall(oldBall, newBall);
		}
	}
	
}
