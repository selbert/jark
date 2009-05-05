package ch.unisi.inf.pfii.teamblue.jark.model.ball;

import ch.unisi.inf.pfii.teamblue.jark.model.level.Level;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.Vaus;

/**
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 *
 */

public final class StickyBall extends Ball {

	public StickyBall(final Vaus vaus, final Level level) {
		super(vaus,level);
		// TODO Auto-generated constructor stub
	}
	public Ball copy() {
		Ball returnBall = new StickyBall(vaus, level);
		returnBall.setX(x);
		returnBall.setY(y);
		return returnBall;
	}
}
