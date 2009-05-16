package ch.unisi.inf.pfii.teamblue.jark.model.bonus;

import java.util.ArrayList;

import ch.unisi.inf.pfii.teamblue.jark.implementation.BonusListener;
import ch.unisi.inf.pfii.teamblue.jark.implementation.Constants;
import ch.unisi.inf.pfii.teamblue.jark.implementation.VausSetListener;
import ch.unisi.inf.pfii.teamblue.jark.model.Game;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.Vaus;

/**
 * This is the main bonus class, extended by all the bonuses.
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 * 
 */

public abstract class Bonus implements Constants, VausSetListener {
	private int x;
	private int y;
	private Vaus vaus;
	private boolean dead;
	private int lifeInMill;
	private final int bonusClass;
	
	private final ArrayList<BonusListener> listeners;
	
	public Bonus(final int bonusClass) {
		x = 0;
		y = 0;
		vaus = null;
		listeners = new ArrayList<BonusListener>();
		lifeInMill = 30000;
		this.bonusClass = bonusClass;
	}
	
	@Override
	public abstract String toString();
	public abstract void apply(final Game game);
	
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
	public int getLife() {
		return lifeInMill;
	}
	public int getBonusClass() {
		return bonusClass;
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
	public final void setLife(int life) {
		lifeInMill = life;
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
			fireBonusTaken();
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
    protected void fireLifeDecreased() {
        for (final BonusListener li : listeners) {
            li.lifeDecreased(this);
        }
    }

	public void remove(Game game) {
		return;
	}

	public void decrementLife() {
		lifeInMill -= TICKS_PER_SECOND;
		fireLifeDecreased();
	}

}
