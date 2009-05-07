package ch.unisi.inf.pfii.teamblue.jark.model.brick;


/**
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
	public String toString() {
		int i = getLives();
		if (i == 2) {
			return "resistentBrick2";
		} else {
			return "resistentBrick1";
		}
	}
}
