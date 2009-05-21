package ch.unisi.inf.pfii.teamblue.jark.model.bonus;

import ch.unisi.inf.pfii.teamblue.jark.model.vaus.Vaus;

public abstract class VausBonus extends Bonus {

	public VausBonus(final int bonusClass) {
		super(bonusClass);
	}

	@Override
	public abstract String toString();

	protected final Vaus translateVaus(final Vaus vaus, final Vaus newVaus) {
		newVaus.setWidth(vaus.getWidth());
		newVaus.setVausListenerLsit(vaus.getVausListenerLsit());
		return newVaus;
	}

}
