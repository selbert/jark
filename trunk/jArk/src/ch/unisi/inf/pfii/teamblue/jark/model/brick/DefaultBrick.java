package ch.unisi.inf.pfii.teamblue.jark.model.brick;

import java.awt.Image;

import javax.swing.ImageIcon;


/**
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
	public Image getImage() {
		return new ImageIcon(getClass().getResource("../../view/images/bricks/defaultBrick.png")).getImage();
	}

}
