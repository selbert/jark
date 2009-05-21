package ch.unisi.inf.pfii.teamblue.jark.model.brick;

/**
 * Very Resistent Brick - this brick has to be hit more times to be destroyed
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 * 
 */

public final class VeryResistentBrick extends Brick {

	public VeryResistentBrick() {
		setLives(3);
		setPoints(7);
	}

	@Override
	public final String toString() {
		final int i = getLives();
		if (i == 3) {
			return "veryResistentBrick";
		} else if (i == 2) {
			return "veryResistentBrick2";
		} else {
			return "veryResistentBrick1";
		}
	}

}
