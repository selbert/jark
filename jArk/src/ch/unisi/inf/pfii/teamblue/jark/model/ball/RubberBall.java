package ch.unisi.inf.pfii.teamblue.jark.model.ball;

import ch.unisi.inf.pfii.teamblue.jark.model.level.Level;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.Vaus;

/**
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 *
 */

public final class RubberBall extends Ball {

	public RubberBall(final Vaus vaus, final Level level) {
		super(vaus,level);
		// TODO Auto-generated constructor stub
	}
	@Override
	public Ball copy() {
		Ball returnBall = new RubberBall(vaus, level);
		returnBall.setX(x);
		returnBall.setY(y);
		return returnBall;
	}
	
	@Override
	public void move() {
		float newX = x+speedX;
		float newY = y+speedY;
		

		if (newY >= GAME_HEIGHT) {
			dead = true; //remove ball
			return;
		}
		
		if (newY < FIELD_HEIGHT) { 
			if (bounceY(newY)){
				newY = y;
			}
			
			if (bounceX(newX)){
				newX = x;
			}
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
	protected boolean bounceX(final float newX) {
		if ((level.brickHasBallInside(newX, y)) 
			|| (level.brickHasBallInside(newX, y + (2*BALL_RADIUS)))
			|| (level.brickHasBallInside(newX + (2*BALL_RADIUS), y))
			|| (level.brickHasBallInside(newX + (2*BALL_RADIUS), y + (2*BALL_RADIUS)))) {
			speedX = -speedX;
			return true;
		}
		return false;
	}
	
	
	@Override
	protected boolean bounceY(final float newY) {
		if ((level.brickHasBallInside(x, newY))
			|| (level.brickHasBallInside(x + (2*BALL_RADIUS), newY))
			|| (level.brickHasBallInside(x, newY + (2*BALL_RADIUS)))
			|| (level.brickHasBallInside(x + (2*BALL_RADIUS), newY + (2*BALL_RADIUS)))){
			speedY = -speedY;
			return true;
		} 
		return false;

	}
	
	@Override
	public String toString() {
		return "rubberBall";
	}
}
