package ch.unisi.inf.pfii.teamblue.jark.model.ball;

import ch.unisi.inf.pfii.teamblue.jark.model.level.Level;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.Vaus;

public final class DefaultBall extends Ball {

	public DefaultBall(final Vaus vaus, final Level level) {
		super(vaus,level);
	}
	
	@Override
	public Ball copy() {
		Ball returnBall = new DefaultBall(vaus, level);
		returnBall.setX(x);
		returnBall.setY(y);
		return returnBall;
	}
	
	@Override
	public void move() {
		super.move(1);
	}

}
