package ch.unisi.inf.pfii.teamblue.jark.model.ball;

import ch.unisi.inf.pfii.teamblue.jark.model.Game;
import ch.unisi.inf.pfii.teamblue.jark.model.level.Level;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.Vaus;

/**
 * Initial ball
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 * 
 */

public class StartBall extends Ball {

	public StartBall(final Vaus vaus, final Level level) {
		super(vaus, level);
	}

	@Override
	public final Ball copy() {
		final Ball returnBall = new DefaultBall(vaus, level);

		return transferBall(returnBall);
	}

	@Override
	public final String toString() {
		return "startBall";
	}

	@Override
	public void start(final Game game) {
		setSpeedX(0);
		setSpeedY(-3);
		final Ball newBall = new DefaultBall(vaus, level);
		newBall.setBoxEnabled(getBoxEnabled());
		newBall.setSpeedMod(getSpeedMod());
		newBall.setSpeedX(getSpeedX());
		newBall.setSpeedY(getSpeedY());
		newBall.setX(getX());
		newBall.setY(getY());
		game.replaceBall(this, newBall);
	}

	@Override
	public final void vausMoved(final int newX) {
		setX((VAUS_WIDTH / 2 - BALL_RADIUS) + newX);
	}

}
