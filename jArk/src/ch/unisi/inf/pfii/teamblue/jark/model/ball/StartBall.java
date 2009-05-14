package ch.unisi.inf.pfii.teamblue.jark.model.ball;

import ch.unisi.inf.pfii.teamblue.jark.model.Game;
import ch.unisi.inf.pfii.teamblue.jark.model.level.Level;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.Vaus;

public class StartBall extends Ball {

	
	public StartBall(Vaus vaus, Level level) {
		super(vaus, level);
	}

	@Override
	public final Ball copy() {
		Ball returnBall = new DefaultBall(vaus, level);
		
		return transferBall(returnBall);
	}
	@Override
	public String toString() {
		return "startBall";
	}
	public void start(Game game) {
		setSpeedX(((getX() + BALL_RADIUS) - (vaus.getX() + (vaus.getWidth() / 2))) / (vaus.getWidth() / 10));
		setSpeedY(-3);
		Ball newBall = new DefaultBall(vaus, level);
		newBall.setBoxEnabled(getBoxEnabled());
		newBall.setSpeedMod(getSpeedMod());
		newBall.setSpeedX(getSpeedX());
		newBall.setSpeedY(getSpeedY());
		newBall.setX(getX());
		newBall.setY(getY());
		game.replaceBall(this, newBall);
	}
	public final void vausMoved(final int newX) {
		setX((VAUS_WIDTH/2 - BALL_RADIUS) + newX);
	}

}
