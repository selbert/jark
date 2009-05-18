package ch.unisi.inf.pfii.teamblue.jark.model.ball;

import ch.unisi.inf.pfii.teamblue.jark.model.level.Level;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.Vaus;

/**
 * This ball is so powerful it destroys everything it touches, except for the persistent bricks.
 * If the contact with the brick is long enough, it destroys also resistent bricks.
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 *
 */

public final class UltraBall extends Ball {

	public UltraBall(final Vaus vaus, final Level level) {
		super(vaus,level);
	}
	@Override
	public final Ball copy() {
		Ball returnBall = new UltraBall(vaus, level);
		return transferBall(returnBall);
	}
	@Override
	public final void move() {
		float speedHypot = sqrt(speedX*this.speedX + speedY*speedY);
		float speedx = (speedX / speedHypot)*speed*speedModifier;
		float speedy = (speedY / speedHypot)*speed*speedModifier;
		float newX = x+speedx;
		float newY = y+speedy;
		
		if (boxEnabled && newY + (2*BALL_RADIUS) >= VAUS_Y + VAUS_HEIGHT + 1) {
			speedY = -speedY;
			y = (VAUS_Y - (2*BALL_RADIUS));
			return;
		}

		if (newY >= GAME_HEIGHT) {
			dead = true; //remove ball
			return;
		}
		
		if (newY < FIELD_HEIGHT) { 
			bounceY(newY);
			bounceX(newX);
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
	
	@Override
	public final String toString() {
		return "ultraBall";
	}
	
}
