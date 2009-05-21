package ch.unisi.inf.pfii.teamblue.jark.model.bonus;

import java.util.ArrayList;
import java.util.Random;

import ch.unisi.inf.pfii.teamblue.jark.model.Game;
import ch.unisi.inf.pfii.teamblue.jark.model.ball.Ball;
import ch.unisi.inf.pfii.teamblue.jark.model.ball.DefaultBall;
import ch.unisi.inf.pfii.teamblue.jark.model.ball.RubberBall;

/**
 * Changes all the balls in the game into rubber balls, and doubles them (false
 * balls)
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 * 
 */

public final class FalseBallsBonus extends BallBonus {
	
	private final Random rnd;

	public FalseBallsBonus() {
		super(2);
		super.setLife(FALSE_BALLS);
		rnd = new Random();
	}

	@Override
	public final String toString() {
		return "malus_falseball";
	}

	@Override
	public final void apply(final Game game) {
		final ArrayList<Ball> balls = game.getBalls();
		final int numberOfBalls = balls.size();

		for (int i = 0; i < numberOfBalls; i++) {
			final Ball oldBall = balls.get(i);
			Ball newBall = new RubberBall(oldBall.getVaus(), oldBall.getLevel());

			newBall = translateBall(oldBall, newBall);

			game.replaceBall(oldBall, newBall);

			for (int j = 0; j < 3; j++) {
				final Ball newBall1 = newBall.copy();

				newBall1.setSpeedX((float) (rnd.nextInt(70)-30)/10);
				newBall1.setSpeedY(newBall.getSpeedY());
				newBall1.setBoxEnabled(newBall.getBoxEnabled());
				newBall1.setSpeedMod(newBall.getSpeedMod());

				game.addBall(newBall1);
			}
		}
	}

	@Override
	public final void remove(final Game game) {
		final ArrayList<Ball> balls = game.getBalls();
		final int numberOfBalls = balls.size();

		for (int i = 0; i < numberOfBalls; i++) {
			final Ball oldBall = balls.get(i);
			final Ball newBall = new DefaultBall(oldBall.getVaus(), oldBall
					.getLevel());

			game.replaceBall(oldBall, translateBall(oldBall, newBall));
		}
	}

}
