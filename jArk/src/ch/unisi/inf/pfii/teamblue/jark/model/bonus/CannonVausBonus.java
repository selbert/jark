package ch.unisi.inf.pfii.teamblue.jark.model.bonus;

import ch.unisi.inf.pfii.teamblue.jark.model.Game;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.CannonVaus;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.DefaultVaus;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.Vaus;

/**
 * The vaus gets able to launch missiles
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 * 
 */

public final class CannonVausBonus extends VausBonus {

	public CannonVausBonus() {
		super(2);
		super.setLife(CANNON_VAUS);
	}

	@Override
	public String toString() {
		return "bonus_cannonvaus";
	}

	@Override
	public void apply(final Game game) {
		final Vaus vaus = game.getVaus();
		final Vaus newVaus = new CannonVaus(vaus.getX());
		game.setVaus(translateVaus(vaus, newVaus));
	}

	@Override
	public final void remove(final Game game) {
		final Vaus vaus = game.getVaus();
		final Vaus newVaus = new DefaultVaus(vaus.getX());
		game.setVaus(translateVaus(vaus, newVaus));
	}

}
