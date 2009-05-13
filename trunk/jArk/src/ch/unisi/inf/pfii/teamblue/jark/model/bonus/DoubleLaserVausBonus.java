package ch.unisi.inf.pfii.teamblue.jark.model.bonus;

import ch.unisi.inf.pfii.teamblue.jark.model.Game;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.DefaultVaus;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.DoubleRifleVaus;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.Vaus;

/**
 * Changes the vaus in a double laser equipped vaus
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 *
 */

public final class DoubleLaserVausBonus extends VausBonus {
	
	public DoubleLaserVausBonus() {
		super(2);
		super.setLife(DOUBLE_RIFLE_VAUS);
	}
	@Override
	public final String toString() {
		return "bonus_doublelaservaus";
	}
	@Override
	public final void apply(final Game game) {
		Vaus vaus = game.getVaus();
		final Vaus newVaus = new DoubleRifleVaus(vaus.getX());
		
		game.setVaus(translateVaus(vaus, newVaus));
	}
	
	@Override
	public final void remove(final Game game) {
		Vaus vaus = game.getVaus();
		final Vaus newVaus = new DefaultVaus(vaus.getX());
		game.setVaus(translateVaus(vaus, newVaus));
	}
	
}
