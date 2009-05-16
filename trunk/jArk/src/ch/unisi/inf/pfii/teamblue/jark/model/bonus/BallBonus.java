package ch.unisi.inf.pfii.teamblue.jark.model.bonus;

import ch.unisi.inf.pfii.teamblue.jark.model.ball.Ball;

public abstract class BallBonus extends Bonus {

	public BallBonus(int bonusClass) {
		super(bonusClass);
	}

	@Override
	public abstract String toString();
	
	protected final Ball translateBall(final Ball oldBall, final Ball newBall) {
		oldBall.release();
		newBall.setSpeedX(oldBall.getSpeedX());
		newBall.setSpeedY(oldBall.getSpeedY());
		newBall.setX(oldBall.getX());
		newBall.setY(oldBall.getY());
		newBall.setBoxEnabled(oldBall.getBoxEnabled());
		newBall.setSpeedMod(oldBall.getSpeedMod());
		return newBall;
	}

}
