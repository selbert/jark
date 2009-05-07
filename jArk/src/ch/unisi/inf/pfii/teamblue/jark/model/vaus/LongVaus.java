package ch.unisi.inf.pfii.teamblue.jark.model.vaus;

/**
 * The long Vaus
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 *
 */

public final class LongVaus extends Vaus {

	public LongVaus(final int x) {
		super(x);
	}
	@Override
	public final String toString() {
		return "longVaus";
	}
	@Override
	public final int getWidth() {
		return LONGVAUS_WIDTH;
	}
	
}
