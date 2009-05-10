package ch.unisi.inf.pfii.teamblue.jark.model.bonus;

import java.util.ArrayList;

import ch.unisi.inf.pfii.teamblue.jark.implementation.BonusListener;
import ch.unisi.inf.pfii.teamblue.jark.implementation.Constants;
import ch.unisi.inf.pfii.teamblue.jark.implementation.VausListener;
import ch.unisi.inf.pfii.teamblue.jark.model.Game;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.Vaus;

/**
 * This is the main bonus class, extended by all the bonuses.
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 * 
 */

public abstract class Bonus implements Constants, VausListener {
	private int x;
	private int y;
	private Vaus vaus;
	private boolean taken;
	private boolean dead;
	
	private final ArrayList<BonusListener> listeners;
	
	public Bonus() {
		x = 0;
		y = 0;
		vaus = null;
		listeners = new ArrayList<BonusListener>();
	}
	
	@Override
	public abstract String toString();
	public void apply(final Game game) {
		fireBonusTaken();
	}
	
	//getters
	public final int getX() {
		return x;
	}
	public final int getY() {
		return y;
	}
	public final boolean isDead() {
		return dead;
	}
	public final boolean isTaken() {
		return taken;
	}
	
	//setters
	public final void setX(final int x) {
		this.x = x;
	}
	public final void setY(final int y) {
		this.y = y;
	}
	public final void setVaus(final Vaus vaus) {
		this.vaus = vaus;
	}
	
	/**
	 * Called each time the bonus has to move
	 */
	public final void move() {
		y = y + BONUS_SPEED;
		checkVausCollision();
		checkOutOfReach();
	}
	
	private final void checkVausCollision() {
		if (y + BRICK_HEIGHT > VAUS_Y 
				&& y + BRICK_HEIGHT < VAUS_Y + BRICK_HEIGHT
				&& x + BRICK_WIDTH >= vaus.getX()
				&& x <= vaus.getX() + vaus.getWidth()) {
			taken = true;
		}
	}
	
	private final void checkOutOfReach() {
		if (y > GAME_HEIGHT) {
			dead = true;
		}
	}

    public void addBonusListener(final BonusListener li) {
        listeners.add(li);
    }

    public void removeBonusListener(final BonusListener li) {
        listeners.remove(li);
    }
    
    protected void fireBonusTaken() {
        for (final BonusListener li : listeners) {
            li.bonusTaken(this);
        }
    }

	public void fireBonusReleased() {
		for (final BonusListener li : listeners) {
	         li.bonusTaken(this);
	    }
	}

	public void remove(Game game) {
		return;
	}
}
