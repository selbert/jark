package ch.unisi.inf.pfii.teamblue.jark.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import ch.unisi.inf.pfii.teamblue.jark.implementation.BonusListener;
import ch.unisi.inf.pfii.teamblue.jark.implementation.Constants;
import ch.unisi.inf.pfii.teamblue.jark.implementation.GameListener;
import ch.unisi.inf.pfii.teamblue.jark.implementation.LevelListener;
import ch.unisi.inf.pfii.teamblue.jark.implementation.StringEncrypt;
import ch.unisi.inf.pfii.teamblue.jark.implementation.VausSetListener;
import ch.unisi.inf.pfii.teamblue.jark.model.ball.Ball;
import ch.unisi.inf.pfii.teamblue.jark.model.ball.StartBall;
import ch.unisi.inf.pfii.teamblue.jark.model.bonus.BallBonus;
import ch.unisi.inf.pfii.teamblue.jark.model.bonus.Bonus;
import ch.unisi.inf.pfii.teamblue.jark.model.bonus.VausBonus;
import ch.unisi.inf.pfii.teamblue.jark.model.brick.Brick;
import ch.unisi.inf.pfii.teamblue.jark.model.level.Level;
import ch.unisi.inf.pfii.teamblue.jark.model.level.LevelManager;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.DefaultVaus;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.Vaus;

/**
 * The game core class (inits and runs the model of the game)
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 * 
 */

public final class Game implements Constants {

	private final ArrayList<Ball> balls;
	private final ArrayList<Ball> bullets;
	private final ArrayList<Bonus> freeBonuses;
	private final ArrayList<Bonus> takenBonuses;
	private final ArrayList<String> highScoreListNames;
	private final ArrayList<Integer> highScoreListScores;
	private final ArrayList<Integer> highScoreListTimes;

	private final ArrayList<VausSetListener> vausListeners;
	private final ArrayList<GameListener> gameListeners;

	private final Random rnd;

	private final LevelManager levelManager;

	private Vaus vaus;
	private final Player player;
	private Level level;
	private boolean started;
	private boolean gameOver;

	private boolean arcadeMode;

	private int arcadeLevelNumber;

	public Game(final boolean isTest, final LevelManager levelManager) {

		// init
		rnd = new Random();
		highScoreListNames = new ArrayList<String>();
		highScoreListScores = new ArrayList<Integer>();
		highScoreListTimes = new ArrayList<Integer>();
		vausListeners = new ArrayList<VausSetListener>();
		gameListeners = new ArrayList<GameListener>();
		balls = new ArrayList<Ball>();
		bullets = new ArrayList<Ball>();
		freeBonuses = new ArrayList<Bonus>();
		takenBonuses = new ArrayList<Bonus>();
		vaus = new DefaultVaus(GAME_WIDTH / 2 - VAUS_WIDTH / 2);
		player = new Player("pippo", 3);
		started = false;
		gameOver = false;
		arcadeMode = !isTest;
		arcadeLevelNumber = 0;
		this.levelManager = levelManager;
		if (!isTest && arcadeMode) {
			levelManager.loadArcadeLevel(arcadeLevelNumber);
			final Brick[][] field = levelManager.fieldFromArrays();
			final String levelName = levelManager.getLevelName();
			final int bonusPercentage = levelManager.getBonusPercentage();
			setLevel(new Level(levelName, field, freeBonuses, vaus,
					bonusPercentage));
		} else {
			final Brick[][] field = levelManager.fieldFromArrays();
			final String levelName = levelManager.getLevelName();
			final int bonusPercentage = levelManager.getBonusPercentage();
			setLevel(new Level(levelName, field, freeBonuses, vaus,
					bonusPercentage));
		}

		addRandomBall();
		initializeHighScoreFile();
	}

	public void initializeHighScoreFile() {
		final File file = new File("HighScore.jahs");
		try {
			if (!file.createNewFile()) {
				try {
					final BufferedReader myInput = new BufferedReader(
							new FileReader("HighScore.jahs"));
					String readLine = myInput.readLine();
					while (readLine != null) {
						final char a = readLine.charAt(0);
						final int x = a - '0';
						final String decryptedString = StringEncrypt.decrypt(
								readLine.substring(1), x);
						final String name = decryptedString.split(":")[0];
						final Integer score = Integer.parseInt(decryptedString
								.split(":")[1]);
						final Integer time = Integer.parseInt(decryptedString
								.split(":")[2]);
						highScoreListNames.add(name);
						highScoreListScores.add(score);
						highScoreListTimes.add(time);
						readLine = myInput.readLine();
					}
				} catch (final IOException ex) {
					System.out.println(ex);
				}
			}
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	public void writeHighScoreFile() {
		final File file = new File("HighScore.jahs");
		try {
			file.createNewFile();
		} catch (final IOException ex) {
			System.out.println(ex);
		}
		try {
			final FileWriter fstream = new FileWriter(file);
			final BufferedWriter out = new BufferedWriter(fstream);
			while (highScoreListNames.size() > 0) {
				final int index = getTopScore();
				final int x = rnd.nextInt(10);
				out.write(x
						+ StringEncrypt.encrypt(highScoreListNames.get(index)
								+ ":" + highScoreListScores.get(index) + ":"
								+ highScoreListTimes.get(index), x) + "\n");
				highScoreListNames.remove(index);
				highScoreListScores.remove(index);
				highScoreListTimes.remove(index);
			}
			out.close();
		} catch (final IOException ex) {
			System.out.println(ex);
		}
	}

	// getters
	public final Brick[][] getBricks() {
		return level.getBricks();
	}

	public final ArrayList<Bonus> getBonuses() {
		return freeBonuses;
	}

	public final ArrayList<Bonus> getTakenBonuses() {
		return takenBonuses;
	}

	public final ArrayList<Ball> getBalls() {
		return balls;
	}

	public final int getPlayerLives() {
		return player.getLives();
	}

	public final Player getPlayer() {
		return player;
	}

	public final Vaus getVaus() {
		return vaus;
	}

	public Level getLevel() {
		return level;
	}

	public ArrayList<Ball> getBullets() {
		return bullets;
	}

	public boolean isStarted() {
		return started;
	}

	public boolean isArcade() {
		return arcadeMode;
	}

	// setters
	public void setStarted(final boolean started) {
		this.started = started;
	}

	public void setArcadeMode(final boolean arcade) {
		arcadeMode = arcade;
	}

	private final void setLevel(final Level level) {
		this.level = level;
		fireLevelChanged();
		level.addLevelListener(new LevelListener() {
			public void bonusReleased(final Bonus bonus) {
				final BonusListener bl = new BonusListener() {
					public void bonusTaken(final Bonus bonus) {
						addBonus(bonus);
					}

					public void lifeDecreased(final Bonus bonus) {
					}
				};
				bonus.addBonusListener(bl);
			}

			public void brickHit(final Brick brick) {
				final int score = Math.max((brick.getPoints() / 4), Math.min(
						brick.getPoints(), 2 * brick.getPoints()
								- (int) (0.2 * (player.getTime() / 1000))));
				player.incrementScore(score);
			}
		});
		addVausListener(level);
	}

	public final void setVaus(final Vaus vaus) {
		this.vaus = vaus;
		fireVausChanged();
	}

	/**
	 * Actions to do at each tick of the game
	 */
	public final void tick() {
		if (!gameOver) {
			if (started) {
				moveBalls();
			}
			player.incrementTime();
			moveBullets();
			moveBonuses();
			checkBallsInGame();
			checkTakenBonuses();
			vaus.move();
			gameOver = checkGameOver();
		}
	}

	/**
	 * Add a ball to the game
	 * 
	 * @param ball
	 */
	public final void addBall(final Ball ball) {
		balls.add(ball);
		addVausListener(ball);
	}

	public final void replaceBall(final Ball oldBall, final Ball newBall) {
		removeVausListener(oldBall);
		addVausListener(newBall);
		balls.set(balls.indexOf(oldBall), newBall);
	}

	private void removeBall(final Ball ball) {
		balls.remove(ball);
		removeVausListener(ball);
	}

	/**
	 * Add a bullet to the game
	 * 
	 * @param bullet
	 */
	public final void addBullet(final Ball bullet) {
		bullets.add(bullet);
	}

	private void removeBullet(final Ball bullet) {
		bullets.remove(bullet);
		removeVausListener(bullet);
	}

	private void addBonus(final Bonus bonus) {
		for (int i = 0; i < takenBonuses.size(); i++) {
			final Bonus b = takenBonuses.get(i);
			if (((b instanceof BallBonus && bonus instanceof BallBonus) || (b instanceof VausBonus && bonus instanceof VausBonus))
					&& (b.getBonusClass() == bonus.getBonusClass())) {
				freeBonuses.remove(bonus);
				bonus.apply(this);
				takenBonuses.set(i, bonus);

				return;
			}
		}
		bonus.apply(this);
		freeBonuses.remove(bonus);
		if (bonus.getLife() != 0) {
			takenBonuses.add(bonus);
		}
	}

	/**
	 * Move all the balls in the game
	 */
	public final void moveBalls() {
		for (int i = 0; i < balls.size();) {
			if (balls.get(i).isDead()) {
				removeBall(balls.get(i));
			} else {
				balls.get(i).move();
				i++;
			}
		}
	}

	/**
	 * Move all the bullets in the game
	 */
	public final void moveBullets() {
		for (int i = 0; i < bullets.size();) {
			if (bullets.get(i).isDead()) {
				removeBullet(bullets.get(i));
			} else {
				bullets.get(i).move();
				i++;
			}
		}
	}

	/**
	 * Move all the free bonuses in the game
	 */
	public final void moveBonuses() {
		for (int i = 0; i < freeBonuses.size();) {
			final Bonus bonus = freeBonuses.get(i);
			if (bonus.isDead()) {
				freeBonuses.remove(i);
			} else {
				bonus.move();
				i++;
			}
		}
	}

	/**
	 * Check taken bonuses for expiration
	 */
	public final void checkTakenBonuses() {
		for (int i = 0; i < takenBonuses.size();) {
			final Bonus bonus = takenBonuses.get(i);

			if (bonus.getLife() < Integer.MAX_VALUE) {
				bonus.decrementLife();
				if (bonus.getLife() <= 0) {
					bonus.remove(this);
					takenBonuses.remove(i);
					continue;
				}
			}
			i++;
		}
	}

	/**
	 * remove all taken bonuses
	 */
	public final void removeTakenBonuses() {
		for (; 0 < takenBonuses.size();) {
			takenBonuses.get(0).remove(this);
			takenBonuses.remove(0);
		}
		fireBonusErase();
	}

	/**
	 * Get a float number from -5 to 5
	 */
	private final float getRandomSpeed() {
		final float rndSpeed = rnd.nextFloat() * 5;
		if (rnd.nextBoolean()) {
			return -1 * rndSpeed;
		}
		return rndSpeed;
	}

	/**
	 * Add a ball at random x speed to the game
	 */
	public final void addRandomBall() {
		final Ball newBall = new StartBall(vaus, level);
		newBall.setSpeedX(getRandomSpeed());
		newBall.setSpeedY(-3);
		vaus.addVausListener(newBall);
		addBall(newBall);
	}

	// vaus listeners
	public final void addVausListener(final VausSetListener li) {
		vausListeners.add(li);
	}

	public final void removeVausListener(final VausSetListener li) {
		vausListeners.remove(li);
	}

	private final void fireVausChanged() {
		for (final VausSetListener li : vausListeners) {
			li.setVaus(vaus);
		}
	}

	// game listeners
	public final void addGameListener(final GameListener li) {
		gameListeners.add(li);
	}

	public final void removeGameListener(final GameListener li) {
		gameListeners.remove(li);
	}

	private final void fireLevelChanged() {
		for (final GameListener li : gameListeners) {
			li.levelChanged(level);
		}
	}

	private final void fireLevelCleared() {
		for (final GameListener li : gameListeners) {
			li.levelCleared(level);
		}
	}

	private final void fireGameOver() {
		for (final GameListener li : gameListeners) {
			li.gameOver();
		}
	}

	private final void fireBonusErase() {
		for (final GameListener li : gameListeners) {
			li.bonusErase();
		}
	}

	private void fireArcadeCleared() {
		for (final GameListener li : gameListeners) {
			li.arcadeCleared();
		}
	}

	public void releaseBalls() {
		for (final Ball b : balls) {
			b.release();
		}
	}

	/**
	 * Start the game
	 */
	public void startGame() {
		started = true;
		for (final Ball b : balls) {
			b.start(this);
		}
	}

	/**
	 * Check if there are balls in game, if none remove life and restart ball
	 */
	public void checkBallsInGame() {
		if (balls.size() <= 0 && started) {
			player.decrementLives();
			started = false;
			balls.clear();
			removeTakenBonuses();
			freeBonuses.clear();
			final Ball newBall = new StartBall(vaus, level);
			vaus.addVausListener(newBall);
			balls.add(newBall);
		}
	}

	public boolean checkGameOver() {
		if (level.isCleared()) {
			fireLevelCleared();
			levelManager.copyLevelFilesOutside(levelManager
					.getArcadeLevelPath(arcadeLevelNumber));
			started = false;
			balls.clear();
			bullets.clear();
			freeBonuses.clear();
			removeTakenBonuses();
			if (arcadeMode && arcadeLevelNumber < MAX_LEVEL) {
				arcadeLevelNumber++;
				setVaus(new DefaultVaus(GAME_WIDTH / 2 - VAUS_WIDTH / 2));
				levelManager.loadArcadeLevel(arcadeLevelNumber);
				final Brick[][] field = levelManager.fieldFromArrays();
				final String levelName = levelManager.getLevelName();
				final int bonusPercentage = levelManager.getBonusPercentage();
				setLevel(new Level(levelName, field, freeBonuses, vaus,
						bonusPercentage));
				final Ball newBall = new StartBall(vaus, level);
				vaus.addVausListener(newBall);
				addBall(newBall);
			} else {
				fireArcadeCleared();
			}
			return false;
		}
		if (player.getLives() <= 0 && !gameOver) {
			fireGameOver();
			started = false;
			balls.clear();
			bullets.clear();
			freeBonuses.clear();
			removeTakenBonuses();
			return true;
		}
		return false;
	}

	public int getLeastScore() {
		if (highScoreListScores.size() > 0) {
			int returnValue = highScoreListScores.get(0);
			for (int i = 0; i < highScoreListScores.size(); i++) {
				final int t = highScoreListScores.get(i);
				returnValue = Math.min(returnValue, t);
			}
			return returnValue;
		}
		return 0;
	}

	public int getTopScore() {
		if (highScoreListScores.size() > 0) {
			int returnIndex = 0;
			for (int i = 0; i < highScoreListScores.size(); i++) {
				final int s = highScoreListScores.get(returnIndex);
				final int t = highScoreListScores.get(i);
				if (t > s) {
					returnIndex = i;
				}

			}
			return returnIndex;
		}
		return 0;
	}

	public void addHighScore(final String name) {
		highScoreListNames.add(name);
		highScoreListScores.add(player.getScore());
		highScoreListTimes.add((player.getTime() / 1000));
		writeHighScoreFile();
	}

	public ArrayList<Integer> getHighScoreListScores() {
		return highScoreListScores;
	}
}
