package ch.unisi.inf.pfii.teamblue.jark.implementation;

import ch.unisi.inf.pfii.teamblue.jark.model.level.Level;

/**
 * Listen to game
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 * 
 */

public interface GameListener {
	public void levelChanged(Level level);

	public void gameOver();

	public void bonusErase();

	public void levelCleared(Level level);

	public void arcadeCleared();
}
