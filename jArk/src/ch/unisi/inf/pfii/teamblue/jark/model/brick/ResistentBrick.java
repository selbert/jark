package ch.unisi.inf.pfii.teamblue.jark.model.brick;

import java.awt.Image;

import javax.swing.ImageIcon;


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
	public Image getImage() {
		return new ImageIcon(getClass().getResource("../../view/images/bricks/resistentBrick.png")).getImage();
	}

}
