package ch.unisi.inf.pfii.teamblue.jark.model.brick;

/**
 * Resistent Brick - this brick has to be hit more times to be destroyed 
 *  
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 *
 */

public final class ResistentBrick extends Brick {

	public ResistentBrick() {
		setLives(2);
		setPoints(8);
	}
	@Override
	public final String toString() {
		final int i = getLives();
		if (i == 2) {
			return "resistentBrick2";
		} else {
			return "resistentBrick1";
		}
	}
	
}
