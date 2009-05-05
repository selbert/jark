package ch.unisi.inf.pfii.teamblue.jark.model.bonus;

import java.awt.Image;

import javax.swing.ImageIcon;

import ch.unisi.inf.pfii.teamblue.jark.model.Game;
import ch.unisi.inf.pfii.teamblue.jark.model.ball.Ball;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.LongVaus;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.Vaus;


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
	public void apply(Game game) {
		Vaus newVaus = new LongVaus(game.getVaus().getX());
		game.setVaus(newVaus);
	}
}
