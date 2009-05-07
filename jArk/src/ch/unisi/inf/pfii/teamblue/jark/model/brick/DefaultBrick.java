package ch.unisi.inf.pfii.teamblue.jark.model.brick;

/**
 * Default Brick
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 *
 */

public final class DefaultBrick extends Brick {

	public DefaultBrick() {
		setLives(1);
		setPoints(10);
	}
	@Override
	public final String toString() {
		return "defaultBrick";
	}

}
