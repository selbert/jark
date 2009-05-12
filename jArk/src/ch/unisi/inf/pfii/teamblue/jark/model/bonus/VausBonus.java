package ch.unisi.inf.pfii.teamblue.jark.model.bonus;

import ch.unisi.inf.pfii.teamblue.jark.model.vaus.Vaus;

public abstract class VausBonus extends Bonus {

	public VausBonus(int bonusClass) {
		super(bonusClass);
	}

	@Override
	public String toString() {
		return null;
	}
	protected final Vaus translateVaus(final Vaus vaus, final Vaus newVaus) {
		newVaus.setWidth(vaus.getWidth());
		newVaus.setVausListenerLsit(vaus.getVausListenerLsit());
		return newVaus;
	}

}
