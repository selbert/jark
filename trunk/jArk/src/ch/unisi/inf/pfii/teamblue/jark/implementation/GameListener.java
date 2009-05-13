package ch.unisi.inf.pfii.teamblue.jark.implementation;

import ch.unisi.inf.pfii.teamblue.jark.model.bonus.Bonus;

/**
 * Listen to game
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate: 2009-05-10 01:19:22 +0200 (Sun, 10 May 2009) $
 * 
 */

public interface GameListener {
	public void addedBonus(Bonus bonus);
}