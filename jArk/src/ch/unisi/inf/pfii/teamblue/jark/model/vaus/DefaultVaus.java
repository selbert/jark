package ch.unisi.inf.pfii.teamblue.jark.model.vaus;

/**
 * The default Vaus
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 * 
 */

public final class DefaultVaus extends Vaus {

	public DefaultVaus(final int x) {
		super(x);
	}

	@Override
	public String toString() {
		switch (vausWidth) {
		case LONGVAUS_WIDTH:
			return "longVaus";
		case SHORTVAUS_WIDTH:
			return "shortVaus";
		default:
			return "defaultVaus";
		}
	}

}
