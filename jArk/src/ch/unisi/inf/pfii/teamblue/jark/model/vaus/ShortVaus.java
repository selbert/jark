package ch.unisi.inf.pfii.teamblue.jark.model.vaus;


/**
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 *
 */

public final class ShortVaus extends Vaus {

	public ShortVaus(int x) {
		super(x);
	}
	@Override
	public String toString() {
		return "shortVaus";
	}
	@Override
	public int getWidth() {
		return SHORTVAUS_WIDTH;
	}
	
}
