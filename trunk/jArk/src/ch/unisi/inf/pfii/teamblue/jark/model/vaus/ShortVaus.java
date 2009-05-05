package ch.unisi.inf.pfii.teamblue.jark.model.vaus;

import java.awt.Image;

import javax.swing.ImageIcon;


/**
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 *
 */

public final class ShortVaus extends Vaus {

	public ShortVaus(int x) {
		super(x);
	}
	public Image getImage() {
		return new ImageIcon(getClass().getResource("../../view/images/vauses/defaultVaus.png")).getImage();
	}
	public int getWidth() {
		return SHORTVAUS_WIDTH;
	}
	
}
