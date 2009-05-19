package ch.unisi.inf.pfii.teamblue.jark.model;

import java.util.ArrayList;

import ch.unisi.inf.pfii.teamblue.jark.implementation.Constants;
import ch.unisi.inf.pfii.teamblue.jark.implementation.PlayerListener;

/**
 * This class contains information about the current player (the name, the score and the lives).
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 * 
 */
public final class Player implements Constants {
	private final ArrayList<PlayerListener> listeners;
	private final String name;
	private int gameTime;
	private int gameTimeInSeconds;
	private int score;
	private int lives;
	
	public Player(final String name, final int lives) {
		this.name = name;
		this.score = 0;
		this.lives = lives;
		listeners = new ArrayList<PlayerListener>();
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
	public void incrementTime() {
		gameTime += TICKS_PER_SECOND;
		if ((int)(gameTime/1000) > gameTimeInSeconds) {
			gameTimeInSeconds = (int)(gameTime/1000);
			fireModifiedTime();
		}
	}

	public void incrementLives() {
		lives++;
		fireModifiedLives();
	}
	public void decrementLives() {
		lives--;
		fireModifiedLives();
	}
	
	public void setScore(final int score) {
		this.score = score;
	}

	public void incrementScore(final int score) {
		this.score += score;
		fireModifiedScore();
	}
	
	public int getScore() {
		return score;
	}
	
	public int getTime() {
		return gameTime;
	}
	
    public void addPlayerListener(final PlayerListener li) {
        listeners.add(li);
    }

    public void removePlayerListener(final PlayerListener li) {
        listeners.remove(li);
    }

	public void fireModifiedLives() {
		for (final PlayerListener li : listeners) {
	         li.modifiedLives(lives);
	    }
	}
	public void fireModifiedScore() {
		for (final PlayerListener li : listeners) {
	         li.modifiedScore(score);
	    }
	}
	public void fireModifiedTime() {
		for (final PlayerListener li : listeners) {
	         li.modifiedTime(gameTimeInSeconds);
	    }
	}
}
