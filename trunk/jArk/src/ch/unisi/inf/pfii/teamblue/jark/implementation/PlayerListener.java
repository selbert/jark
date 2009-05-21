package ch.unisi.inf.pfii.teamblue.jark.implementation;

/**
 * Listen to player
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 * 
 */

public interface PlayerListener {
	public void modifiedLives(final int lives);

	public void modifiedScore(final int score);

	public void modifiedTime(final int time);
}
