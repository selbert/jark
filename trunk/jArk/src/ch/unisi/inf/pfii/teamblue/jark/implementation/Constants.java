package ch.unisi.inf.pfii.teamblue.jark.implementation;

import ch.unisi.inf.pfii.teamblue.jark.model.level.LevelManager;

/**
 * All the constants of the game are taken from this interface
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 * 
 */

public interface Constants {
	int TICKS_PER_SECOND = 10;
	int GAME_WIDTH = 798;
	int GAME_HEIGHT = 600;
	int BALL_RADIUS = 8;
	float BALL_SPEED = 3;
	int BONUS_SPEED = 2;
	int VAUS_SPEED = 8;
	int VAUS_HEIGHT = 20;
	int VAUS_Y = GAME_HEIGHT - 40;
	int VAUS_WIDTH = 100;
	int LONGVAUS_WIDTH = 150;
	int SHORTVAUS_WIDTH = 65;
	int FIELD_WIDTH = 798;
	int FIELD_HEIGHT = 525;
	int FIELD_COLUMNS = 14;
	int FIELD_ROWS = 21;
	int BRICK_WIDTH = 57;
	int BRICK_HEIGHT = 25;
	int MAX_LEVEL = LevelManager.getMaxLevel();
	int BALL_LIFE = 20;
	// bonus lives (ms)
	int INSTANTANEOUS = 0;
	int PERSISTENT = Integer.MAX_VALUE;
	int DOUBLE_RIFLE_VAUS = 10000;
	int EXPLOSION_BALL = 20000;
	int FALSE_BALLS = 15000;
	int FAST_BALL = 20000;
	int GHOST_BALL = 15000;
	int RIFLE_VAUS = 15000;
	int CANNON_VAUS = 20000;
	int SLOW_BALL = 20000;
	int STICKY_BALL = 20000;
	int THE_BOX = 20000;
	int ULTRA_BALL = 10000;
	int LIGHT_OFF = 20000;
	// vaus shooting speed delay (ms)
	int DOUBLE_RIFLE_DELAY = 100;
	int RIFLE_DELAY = 150;
	int CANNON_DELAY = 500;
}
