package ch.unisi.inf.pfii.teamblue.jark.model.bonus;

import java.awt.Image;

import javax.swing.ImageIcon;

import ch.unisi.inf.pfii.teamblue.jark.model.Game;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.ShortVaus;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.Vaus;


/**
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 *
 */

public final class ShortVausBonus extends Bonus {
	@Override
	public Image getImage() {
		return new ImageIcon(getClass().getResource("../../view/images/bonuses/malus_shortvaus.png")).getImage();
	}
	@Override
	public void apply(Game game) {
		Vaus newVaus = new ShortVaus(game.getVaus().getX());
		game.setVaus(newVaus);
	}
}
