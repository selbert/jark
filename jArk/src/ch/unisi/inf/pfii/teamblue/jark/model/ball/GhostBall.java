package ch.unisi.inf.pfii.teamblue.jark.model.ball;


import ch.unisi.inf.pfii.teamblue.jark.model.level.Level;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.Vaus;

/**
 * This ball is so powerful it destroys everything it touches, except for the persistent bricks.
 * If the contact with the brick is long enough, it destroys also resistent bricks.
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
		Ball returnBall = new UltraBall(vaus, level);
		
		return transferBall(returnBall);
	}
	@Override
	public final void move() {
		float newX = x+(speedX * speedModifier);
		float newY = y+(speedY * speedModifier);
		
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
	protected final boolean bounceVaus(final float newX, final float newY) {
		if (newY + (BALL_RADIUS*2) > VAUS_Y-1 && newY + (BALL_RADIUS*2) < VAUS_Y+(BALL_RADIUS*2) && newX + (BALL_RADIUS*2) >= vaus.getX() && newX <= vaus.getX() + vaus.getWidth()) {
			speedY = -speedY;
			speedX = ((newX + BALL_RADIUS) - (vaus.getX() + (vaus.getWidth() / 2))) / (vaus.getWidth() / 10);
			return true;
		}
		return false;
	}
	
	@Override
	protected void destroyBrick(final float x, final float y) {
	}
	
	@Override
	public final String toString() {
		return "ghostBall";
	}
	
}
