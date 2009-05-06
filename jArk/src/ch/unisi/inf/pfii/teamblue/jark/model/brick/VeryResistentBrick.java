package ch.unisi.inf.pfii.teamblue.jark.model.brick;

import java.awt.Image;

import javax.swing.ImageIcon;


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
	public Image getImage() {
		return new ImageIcon(getClass().getResource("../../view/images/bricks/veryResistentBrick.png")).getImage();
	}

}
