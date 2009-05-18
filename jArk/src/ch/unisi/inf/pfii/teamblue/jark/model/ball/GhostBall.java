package ch.unisi.inf.pfii.teamblue.jark.model.ball;


import ch.unisi.inf.pfii.teamblue.jark.model.level.Level;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.Vaus;

/**
 * This ball does nothing
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate: 2009-05-11 23:23:13 +0200 (Mon, 11 May 2009) $
 *
 */

public final class GhostBall extends Ball {

	public GhostBall(final Vaus vaus, final Level level) {
		super(vaus,level);
	}
	@Override
	public final Ball copy() {
		Ball returnBall = new GhostBall(vaus, level);
		
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
	protected void destroyBrick(final float x, final float y) {
	}
	
	@Override
	public final String toString() {
		return "ghostBall";
	}
	
}
