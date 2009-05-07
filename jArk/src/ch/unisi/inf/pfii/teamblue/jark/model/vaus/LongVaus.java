package ch.unisi.inf.pfii.teamblue.jark.model.vaus;


/**
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 *
 */

public final class LongVaus extends Vaus {

	public LongVaus(int x) {
		super(x);
	}
	@Override
	public String toString() {
		return "longVaus";
	}
	@Override
	public int getWidth() {
		return LONGVAUS_WIDTH;
	}
}
