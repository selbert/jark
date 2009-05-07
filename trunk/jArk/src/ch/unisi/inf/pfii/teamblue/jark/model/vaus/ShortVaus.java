package ch.unisi.inf.pfii.teamblue.jark.model.vaus;


/**
 * The short Vaus
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 *
 */

public final class ShortVaus extends Vaus {

	public ShortVaus(final int x) {
		super(x);
	}
	@Override
	public final String toString() {
		return "shortVaus";
	}
	@Override
	public final int getWidth() {
		return SHORTVAUS_WIDTH;
	}
	
}
