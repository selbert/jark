package ch.unisi.inf.pfii.teamblue.jark.model.bonus;

import ch.unisi.inf.pfii.teamblue.jark.model.Game;

public class LightOffBonus extends PlayerBonus {

	public LightOffBonus() {
		super(3);
		super.setLife(LIGHT_OFF);
	}

	@Override
	public String toString() {
		return "malus_lightoff";
	}

	@Override
	public void apply(Game game) {
	}

}
