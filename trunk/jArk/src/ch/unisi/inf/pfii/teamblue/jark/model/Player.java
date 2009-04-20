package ch.unisi.inf.pfii.teamblue.jark.model;

/**
 * 
 * This class contains information about the current player (the name, the score and the lives).
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate: 2009-04-13 12:00:15 +0200 (Mon, 13 Apr 2009) $
 * 
 */
public class Player {
	private final String name;
	private int score;
	private int lives;
	
	public Player(String name, int lives) {
		this.name = name;
		this.score = 0;
		this.lives = lives;
	}
	
	public String getName() {
		return name;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}

	public int getLives() {
		return lives;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getScore() {
		return score;
	}
	
	
}
