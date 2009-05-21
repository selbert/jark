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
	private int score;
	private int lives;

	public Player(final String name, final int lives) {
		this.name = name;
		score = 0;
		this.lives = lives;
		listeners = new ArrayList<PlayerListener>();
	}

	public final String getName() {
		return name;
	}

	public final void setLives(final int lives) {
		this.lives = lives;
	}

	public final int getLives() {
		return lives;
	}

	public final void incrementTime() {
		gameTime += TICKS_PER_SECOND;
		if ((gameTime / 1000) > gameTimeInSeconds) {
			gameTimeInSeconds = (gameTime / 1000);
			fireModifiedTime();
		}
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

	public final int getScore() {
		return score;
	}

	public final int getTime() {
		return gameTime;
	}

	public final void addPlayerListener(final PlayerListener li) {
		listeners.add(li);
	}

	public final void removePlayerListener(final PlayerListener li) {
		listeners.remove(li);
	}

	public final void fireModifiedLives() {
		for (final PlayerListener li : listeners) {
			li.modifiedLives(lives);
		}
	}

	public final void fireModifiedScore() {
		for (final PlayerListener li : listeners) {
			li.modifiedScore(score);
		}
	}

	public final void fireModifiedTime() {
		for (final PlayerListener li : listeners) {
			li.modifiedTime(gameTimeInSeconds);
		}
	}
}
