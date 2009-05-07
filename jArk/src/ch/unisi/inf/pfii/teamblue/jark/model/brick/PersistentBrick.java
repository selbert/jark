package ch.unisi.inf.pfii.teamblue.jark.model.brick;

/**
 * Persistent Brick - this brick cannot be destroyed
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 *
 */

public final class PersistentBrick extends Brick {

	public PersistentBrick() {
		setLives(-1);
		setPoints(0);
	}
	@Override
	public final String toString() {
		return "persistentBrick";
	}

}
