package ch.unisi.inf.pfii.teamblue.jark.model.bonus;

import ch.unisi.inf.pfii.teamblue.jark.model.Game;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.DefaultVaus;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.RocketLauncherVaus;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.Vaus;

/**
 * The vaus gets able to launch missiles
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 *
 */

public final class MissileVausBonus extends VausBonus {
	
	public MissileVausBonus() {
		super(2);
	}
	@Override
	public String toString() {
		return "bonus_missilevaus";
	}
	@Override
	public void apply(final Game game) {
		Vaus vaus = game.getVaus();
		final Vaus newVaus = new RocketLauncherVaus(vaus.getX());
		game.setVaus(translateVaus(vaus, newVaus));
	}
	@Override
	public final void remove(final Game game) {
		Vaus vaus = game.getVaus();
		final Vaus newVaus = new DefaultVaus(vaus.getX());
		game.setVaus(translateVaus(vaus, newVaus));
	}
	
}
