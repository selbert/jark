package ch.unisi.inf.pfii.teamblue.jark.model.ball;

import ch.unisi.inf.pfii.teamblue.jark.model.level.Level;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.Vaus;

/**
 * This is the default ball.
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate: 2009-05-07 16:29:47 +0200 (Thu, 07 May 2009) $
 *
 */

public class DefaultBall extends Ball {

	public DefaultBall(final Vaus vaus, final Level level) {
		super(vaus,level);
	}
	@Override
	public final Ball copy() {
		Ball returnBall = new DefaultBall(vaus, level);
		returnBall.setX(x);
		returnBall.setY(y);
		return returnBall;
	}
	@Override
	public String toString() {
		return "defaultBall";
	}

}
