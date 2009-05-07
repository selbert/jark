package ch.unisi.inf.pfii.teamblue.jark.model.bonus;

import ch.unisi.inf.pfii.teamblue.jark.model.Game;


/**
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 *
 */

public final class AddLifeBonus extends Bonus {
	@Override
	public String toString() {
		return "bonus_addlife";
	}
	
	@Override
	public void apply(Game game) {
		game.getPlayer().incrementLives();
		//game.getInfoPanel().setLives(game.getPlayer().getLives());
	}
}
