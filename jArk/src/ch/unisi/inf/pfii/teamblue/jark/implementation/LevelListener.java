package ch.unisi.inf.pfii.teamblue.jark.implementation;

import ch.unisi.inf.pfii.teamblue.jark.model.bonus.Bonus;
import ch.unisi.inf.pfii.teamblue.jark.model.brick.Brick;

/**
 * Listen to level
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 * 
 */

public interface LevelListener {
	public void bonusReleased(Bonus bonus);

	public void brickHit(Brick brick);
}
