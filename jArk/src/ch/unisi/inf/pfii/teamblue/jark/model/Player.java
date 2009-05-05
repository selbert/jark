package ch.unisi.inf.pfii.teamblue.jark.model;

/**
 * 
 * This class contains information about the current player (the name, the score and the lives).
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 * 
 */
public class Player {
	private final String name;
	private int score;
	private int lives;
	
	public Player(final String name, final int lives) {
		this.name = name;
		this.score = 0;
		this.lives = lives;
	}
	
	public String getName() {
		return name;
	}

	public void setLives(final int lives) {
		this.lives = lives;
	}

	public int getLives() {
		return lives;
	}

	public void incrementLives() {
		lives++;
	}
	
	public void setScore(final int score) {
		this.score = score;
	}

	public int getScore() {
		return score;
	}
}
