package ch.unisi.inf.pfii.teamblue.jark.model.bonus;

import java.awt.Image;

import javax.swing.ImageIcon;


/**
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 *
 */

public final class LongVausBonus extends Bonus {
	public Image getImage() {
		return new ImageIcon(getClass().getResource("../../view/images/bonuses/bonus_longvaus.png")).getImage();
	}
}
