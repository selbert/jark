package ch.unisi.inf.pfii.teamblue.jark.model;

import java.util.ArrayList;

import ch.unisi.inf.pfii.teamblue.jark.implementation.Constants;
import ch.unisi.inf.pfii.teamblue.jark.implementation.PlayerListener;

/**
 * This class contains information about the current player (the name, the score
 * and the lives).
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
	private int levelTime;
	private int score;
	private int lives;

	public Player(final String name, final int lives) {
		this.name = name;
		score = 0;
		this.lives = lives;
		listeners = new ArrayList<PlayerListener>();
	}

	//getters
	public final String getName() {
		return name;
	}

	public final int getLives() {
		return lives;
	}
	
	public final int getScore() {
		return score;
	}

	public final int getTime() {
		return gameTime;
	}
	
	public final int getLevelTime() {
		return levelTime;
	}
	
	//setters
	public final void setLives(final int lives) {
		this.lives = lives;
	}

	public void resetLevelTime() {
		levelTime = 0;
	}

	public final void incrementLives() {
		lives++;
		fireModifiedLives();
	}

	public final void decrementLives() {
		lives--;
		fireModifiedLives();
	}

	public final void setScore(final int score) {
		this.score = score;
	}

	public final void incrementScore(final int score) {
		this.score += score;
		fireModifiedScore();
	}
	
	/**
	 * method that increments level and game time by the tick
	 */
	public final void incrementTime() {
		gameTime += TICKS_PER_SECOND;
		levelTime += TICKS_PER_SECOND;
		if ((gameTime / 1000) > gameTimeInSeconds) {
			gameTimeInSeconds = (gameTime / 1000);
			fireModifiedTime();
		}
	}
	
	// listener handlers
	public final void addPlayerListener(final PlayerListener li) {
		listeners.add(li);
	}

	public final void removePlayerListener(final PlayerListener li) {
		listeners.remove(li);
	}

	private final void fireModifiedLives() {
		for (final PlayerListener li : listeners) {
			li.modifiedLives(lives);
		}
	}

	private final void fireModifiedScore() {
		for (final PlayerListener li : listeners) {
			li.modifiedScore(score);
		}
	}

	private final void fireModifiedTime() {
		for (final PlayerListener li : listeners) {
			li.modifiedTime(gameTimeInSeconds);
		}
	}
}
