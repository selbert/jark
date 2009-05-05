package ch.unisi.inf.pfii.teamblue.jark.model.ball;

import ch.unisi.inf.pfii.teamblue.jark.model.level.Level;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.Vaus;

/**
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 *
 */

public final class GhostBall extends Ball {

	public GhostBall(final Vaus vaus, final Level level) {
		super(vaus,level);
		// TODO Auto-generated constructor stub
	}
	public Ball copy() {
		Ball returnBall = new GhostBall(vaus, level);
		returnBall.setX(x);
		returnBall.setY(y);
		return returnBall;
	}
	
	public void move() {
		float newX = x+speedX;
		float newY = y+speedY;
		

		if (newY >= GAME_HEIGHT) {
			dead = true; //remove ball
			return;
		}
		
		if (newX + (BALL_RADIUS*2) >= GAME_WIDTH) {
			speedX = -speedX;
			newX = GAME_WIDTH - (BALL_RADIUS*2);
		}
		if (newX < 0) {
			speedX = -speedX;
			newX = 0;
		}
		if (newY < 0) {
			speedY = -speedY;
			newY = 0;
		}
		
		
		if (bounceVaus(newX, newY)) {
			newY =  VAUS_Y-1 - (BALL_RADIUS*2);
		}
		x = newX;
		y = newY;
	}
}
