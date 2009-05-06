package ch.unisi.inf.pfii.teamblue.jark.model.bonus;

import java.awt.Image;

import javax.swing.ImageIcon;

import ch.unisi.inf.pfii.teamblue.jark.model.Game;


/**
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 *
 */

public final class AddLifeBonus extends Bonus {
	@Override
	public Image getImage() {
		return new ImageIcon(getClass().getResource("../../view/images/bonuses/bonus_addlife.png")).getImage();
	}
	
	@Override
	public void apply(Game game) {
		game.getPlayer().incrementLives();
		//game.getInfoPanel().setLives(game.getPlayer().getLives());
	}
}
