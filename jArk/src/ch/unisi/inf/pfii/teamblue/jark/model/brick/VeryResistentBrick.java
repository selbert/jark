package ch.unisi.inf.pfii.teamblue.jark.model.brick;


/**
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 *
 */

public final class VeryResistentBrick extends Brick {

	public VeryResistentBrick() {
		setLives(3);
		setPoints(5);
	}
	@Override
	public String toString() {
		int i = getLives();
		if (i == 3) {
			return "veryResistentBrick3";
		} else if (i == 2) {
			return "veryResistentBrick2";
		} else {
			return "veryResistentBrick1";
		}
	}

}
