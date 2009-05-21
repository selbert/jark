package ch.unisi.inf.pfii.teamblue.jark.model.ball;

import java.util.Random;

import ch.unisi.inf.pfii.teamblue.jark.implementation.Constants;
import ch.unisi.inf.pfii.teamblue.jark.implementation.VausListener;
import ch.unisi.inf.pfii.teamblue.jark.implementation.VausSetListener;
import ch.unisi.inf.pfii.teamblue.jark.model.Game;
import ch.unisi.inf.pfii.teamblue.jark.model.level.Level;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.Vaus;

/**
 * This class contains information about a ball.
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 * 
 */

public abstract class Ball implements Constants, VausSetListener, VausListener {
	protected float x;
	protected float y;
	protected float speedX;
	protected float speedY;
	protected Vaus vaus;
	protected Level level;
	protected float speedModifier;
	protected float speed;
	protected boolean boxEnabled;
	protected boolean dead;
	protected boolean resetted;
	protected int life;
	
	public Ball(final Vaus vaus, final Level level) {
		this.vaus = vaus;
		this.level = level;
		x = vaus.getX() + ((float)vaus.getWidth() / 2) - BALL_RADIUS;
		y = (VAUS_Y - 2*BALL_RADIUS);
		speedX = 0;
		speedY = 0;
		speed = BALL_SPEED;
		speedModifier = 1;
		life = BALL_LIFE;
		resetted = false;
	}
	
	@Override
	public abstract String toString();
	public abstract Ball copy();
	
	//setters
	public final void setY(final float y) {
		this.y = y;
	}
	public final void setX(final float x) {
		this.x = x;
	}
	public final void setSpeedX(final float speedX) {
		this.speedX = speedX;
	}
	public final void setSpeedY(final float speedY) {
		this.speedY = speedY;
	}
	public final void setVaus(Vaus vaus) {
		this.vaus = vaus;
	}
	public final void setBoxEnabled(Boolean enabled) {
		boxEnabled = enabled;
	}
	public final void setSpeedMod(final float speedModifier) {
		this.speedModifier = speedModifier;
	}
	
	//getters
	public final boolean isDead() {
		return dead;
	}
	public final Vaus getVaus() {
		return vaus;
	}
	public final Level getLevel() {
		return level;
	}
	public final float getX() {
		return x;
	}
	public final float getY() {
		return y;
	}
	public final float getSpeedX() {
		return speedX;
	}
	public final float getSpeedY() {
		return speedY;
	}
	public final float getSpeedMod() {
		return speedModifier;
	}
	public final boolean getBoxEnabled() {
		return boxEnabled;
	}
	
	/** 
	 * Moves a ball
	 */
	public void move() {
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
		
		if (newY < FIELD_HEIGHT && !level.persistentBrickHasBallInside(x,y)) { 
			boolean bounceX = bounceX(newX);
			boolean bounceY = bounceY(newY);
			if (bounceY && !resetted) {
				speedY = -speedY;
				newY = y;
			} else if (bounceX && !resetted) {
				speedX = -speedX;
				newX = x;
			} else if (bounceDiag(newX, newY) && !resetted) {
				speedY = -speedY;
				newY = y;
				speedX = -speedX;
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
		if (!resetted && (!level.persistentBrickHasBallInside(newX,newY) || 
				(level.persistentBrickHasBallInside(newX,newY) && 
						level.persistentBrickHasBallInside(x,y)))) {
			x = newX;
			y = newY;
		} else if (resetted) {
			resetted = false;
		}
	}
	
	/**
	 * When the ball intersects the Vaus
	 * @param newX
	 * @param newY
	 * @return true if the ball intersects the vaus
	 */
	protected boolean bounceVaus(final float newX, final float newY) {
		if (newY + (BALL_RADIUS*2) > VAUS_Y-1 && newY + (BALL_RADIUS*2) < VAUS_Y+(BALL_RADIUS*2) && newX + (BALL_RADIUS*2) >= vaus.getX() && newX <= vaus.getX() + vaus.getWidth()) {
			speedY = -speedY;
			speedX = ((newX + BALL_RADIUS) - (vaus.getX() + (vaus.getWidth() / 2))) / (vaus.getWidth() / 10);
			life = BALL_LIFE;
			return true;
		}
		return false;
	}
	
	protected float sqrt(final float coef) {
		float ans = coef / 2f; 
		int i = 1;

		while (i < 7) {
			ans = ans-((ans*ans-coef)/(2f*ans));
			i = i+1;   
		}
		return ans;  
	} 
	
	/**
	 * Check the intersections of the X component of the ball
	 * @param newX
	 * @return
	 */
	protected boolean bounceX(final float newX) {
		if (level.brickHasBallInside(newX, y)) {
			if(level.brickHasBallInside(newX, y + (2*BALL_RADIUS))) {
				destroyBrick(newX, y + BALL_RADIUS);
			} else {
				destroyBrick(newX, y);
			}
			return true;
		} else if (level.brickHasBallInside(newX, y + (2*BALL_RADIUS))) {
			destroyBrick(newX, y + (2*BALL_RADIUS));
			return true;
		} else if (level.brickHasBallInside(newX + (2*BALL_RADIUS), y)) {
			if (level.brickHasBallInside(newX + (2*BALL_RADIUS), y + (2*BALL_RADIUS))) {
				destroyBrick(newX + (2*BALL_RADIUS), y + BALL_RADIUS);
			} else {
				destroyBrick(newX + (2*BALL_RADIUS), y);
			}
			return true;
		} else if (level.brickHasBallInside(newX + (2*BALL_RADIUS), y + (2*BALL_RADIUS))) {
			destroyBrick(newX + (2*BALL_RADIUS), y + (2*BALL_RADIUS));
			return true;
		} 
		return false;
	}
	
	protected boolean bounceDiag(final float newX, final float newY) {
		if (level.brickHasBallInside(newX, newY)) {
			destroyBrick(newX, newY);
			return true;
		} else if (level.brickHasBallInside(newX, newY + (2*BALL_RADIUS))) {
			destroyBrick(newX, newY + (2*BALL_RADIUS));
			return true;
		} else if (level.brickHasBallInside(newX + (2*BALL_RADIUS), newY)) {
			destroyBrick(newX + (2*BALL_RADIUS), newY);
			return true;
		} else if (level.brickHasBallInside(newX + (2*BALL_RADIUS), newY + (2*BALL_RADIUS))) {
			destroyBrick(newX + (2*BALL_RADIUS), newY + (2*BALL_RADIUS));
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Check the intersections of the Y component of the ball
	 * @param newY
	 * @return
	 */
	protected boolean bounceY(final float newY) {
		if (level.brickHasBallInside(x, newY)) {
			if (level.brickHasBallInside(x + (2*BALL_RADIUS), newY)) {
				destroyBrick(x + BALL_RADIUS, newY);
			} else {
				destroyBrick(x, newY);
			}
			return true;
		} else if (level.brickHasBallInside(x + (2*BALL_RADIUS), newY)) {
			destroyBrick(x + (2*BALL_RADIUS), newY);
			return true;
		} else if (level.brickHasBallInside(x, newY + (2*BALL_RADIUS))) {
			if (level.brickHasBallInside(x + (2*BALL_RADIUS), newY + (2*BALL_RADIUS))) {
				destroyBrick(x + BALL_RADIUS, newY + (2*BALL_RADIUS));
			} else {
				destroyBrick(x, newY + (2*BALL_RADIUS));
			}
			return true;
		} else if (level.brickHasBallInside(x + (2*BALL_RADIUS), newY + (2*BALL_RADIUS))) {
			destroyBrick(x + (2*BALL_RADIUS), newY + (2*BALL_RADIUS));
			return true;
		}
		return false;
	}
	
	protected void destroyBrick(final float x, final float y) {
		int i = level.removeBrick(x, y);
		if (!(this instanceof UltraBall)) {
			if (i >= BALL_LIFE) {
				life = BALL_LIFE;
			} else {
				life += i;
			}
			if (life <= 0) {
				setX(vaus.getX()+(vaus.getWidth()/2)-BALL_RADIUS);
				setY(VAUS_Y-(2*BALL_RADIUS));
				Random rnd = new Random();
				setSpeedX(rnd.nextInt(7)-3);
				setSpeedY(rnd.nextInt(3)-3);
				resetted = true;
				life = BALL_LIFE;
			}
		}
	}
	
	public void vausMoved(int delta) {
	}
	
	/**
	 * For test purposes only
	 * @return
	 */
	public final String toStringTest() {
		return x + " " + y + " " + speedX + " " + speedY;
	}

	public void release() {
	}

	public void setSticky(boolean sticky) {
	}

	public void setVausRelPos(float vausRelPos) {
	}
	
	protected Ball transferBall(final Ball returnBall) {
		returnBall.setX(x);
		returnBall.setY(y);
		returnBall.setSpeedMod(speedModifier);
		returnBall.setBoxEnabled(getBoxEnabled());
		return returnBall;
	}

	public void start(Game game) {
	}
}
