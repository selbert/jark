package ch.unisi.inf.pfii.teamblue.jark.model.bonus;

import ch.unisi.inf.pfii.teamblue.jark.model.Game;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.DefaultVaus;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.RifleVaus;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.Vaus;

/**
 * Laser equipped Vaus
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 *
 */

public final class LaserVausBonus extends VausBonus {
	
	public LaserVausBonus() {
		super(2);
	}
	@Override
	public final String toString() {
		return "bonus_laservaus";
	}
	@Override
	public void apply(final Game game) {
		Vaus vaus = game.getVaus();
		final Vaus newVaus = new RifleVaus(vaus.getX());
		game.setVaus(translateVaus(vaus, newVaus));
	}
	@Override
	public final void remove(final Game game) {
		Vaus vaus = game.getVaus();
		final Vaus newVaus = new DefaultVaus(vaus.getX());
		game.setVaus(translateVaus(vaus, newVaus));
	}
	
}
