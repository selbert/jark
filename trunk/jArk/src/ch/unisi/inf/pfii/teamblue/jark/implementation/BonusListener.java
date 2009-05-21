package ch.unisi.inf.pfii.teamblue.jark.implementation;

import ch.unisi.inf.pfii.teamblue.jark.model.bonus.Bonus;

/**
 * Listen to bonuses
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 * 
 */

public interface BonusListener {
	public void bonusTaken(Bonus bonus);

	public void lifeDecreased(Bonus bonus);
}
