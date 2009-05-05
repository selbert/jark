package ch.unisi.inf.pfii.teamblue.jark.model.vaus;

import java.awt.Image;

import javax.swing.ImageIcon;


/**
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 *
 */

public final class LongVaus extends Vaus {

	public LongVaus(int x) {
		super(x);
	}
	public Image getImage() {
		return new ImageIcon(getClass().getResource("../../view/images/vauses/longVaus.png")).getImage();
	}
	public int getWidth() {
		return LONGVAUS_WIDTH;
	}
}
