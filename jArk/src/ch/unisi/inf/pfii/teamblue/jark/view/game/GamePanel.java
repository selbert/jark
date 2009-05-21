package ch.unisi.inf.pfii.teamblue.jark.view.game;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.event.MouseInputAdapter;

import ch.unisi.inf.pfii.teamblue.jark.implementation.BonusListener;
import ch.unisi.inf.pfii.teamblue.jark.implementation.Constants;
import ch.unisi.inf.pfii.teamblue.jark.implementation.GameListener;
import ch.unisi.inf.pfii.teamblue.jark.implementation.LevelListener;
import ch.unisi.inf.pfii.teamblue.jark.implementation.VausSetListener;
import ch.unisi.inf.pfii.teamblue.jark.model.Game;
import ch.unisi.inf.pfii.teamblue.jark.model.ball.Ball;
import ch.unisi.inf.pfii.teamblue.jark.model.bonus.Bonus;
import ch.unisi.inf.pfii.teamblue.jark.model.brick.Brick;
import ch.unisi.inf.pfii.teamblue.jark.model.level.Level;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.Vaus;
import ch.unisi.inf.pfii.teamblue.jark.view.ImagesRepository;

/**
 * The game panel, it should show the bricks the balls and the vaus
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 * 
 */

public final class GamePanel extends JComponent implements Constants,
		VausSetListener {
	private final Timer timer;

	private Level level;
	private final ArrayList<Ball> balls;
	private final ArrayList<Bonus> bonuses;
	private final ArrayList<Ball> bullets;
	private Vaus vaus;

	private boolean drawBox;
	private boolean lightOff;
	private boolean running;
	private boolean gameOver;
	private boolean firstTimeRun;
	private boolean vausIsShooting;
	private boolean levelCleared;
	private boolean gameCleared;

	private final Cursor transparentCursor;

	private BufferedImage image;

	public GamePanel(final Game game) {

		game.addGameListener(new GameListener() {

			public void levelChanged(final Level level) {
				setLevel(level);
			}

			public void bonusErase() {
				drawBox = false;
				lightOff = false;
			}

			public void gameOver() {
				gameOver = true;
			}

			public void levelCleared(final Level level) {
				levelCleared = true;
			}

			public void arcadeCleared() {
				gameCleared = true;
			}

		});

		setLevel(game.getLevel());

		final KeyListener keyListener = new KeyListener() {
			public final void keyPressed(final KeyEvent ev) {
				switch (ev.getKeyCode()) {
				case KeyEvent.VK_ENTER:
					if (gameCleared || gameOver) {
						if (game.isArcade()
								&& (game.getLeastScore() < game.getPlayer()
										.getScore() || game
										.getHighScoreListScores().size() < 10)) {
							boolean correctInput = false;
							String name = "";
							while (!correctInput) {
								name = JOptionPane
										.showInputDialog("You reached the Highscore list!\n\nInsert name:");
								if (name != null
										&& name
												.matches("[a-zA-Z_][a-zA-Z_0-9]{0,11}")) {
									game.addHighScore(name);
									correctInput = true;
								}
							}
						}
						getTopLevelAncestor().setVisible(false);
					}
					if (levelCleared) {
						levelCleared = false;
					}
					return;
				case KeyEvent.VK_LEFT:
					vaus.moveLeft();
					break;
				case KeyEvent.VK_RIGHT:
					vaus.moveRight();
					break;
				case KeyEvent.VK_ESCAPE:
					image = new BufferedImage(GAME_WIDTH, GAME_HEIGHT,
							BufferedImage.TYPE_BYTE_GRAY);
					paintComponent(image.createGraphics());
					image = scale(image, 0.5);
					play();
					repaint();
					break;
				case KeyEvent.VK_SPACE:
					vausIsShooting = true;
					game.releaseBalls();
					break;
				}
			}

			public final void keyReleased(final KeyEvent ev) {
				switch (ev.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					vaus.stopLeft();
					break;
				case KeyEvent.VK_RIGHT:
					vaus.stopRight();
					break;
				case KeyEvent.VK_SPACE:
					vausIsShooting = false;
					break;
				}
			}

			public final void keyTyped(final KeyEvent ev) {
			}
		};

		final MouseInputAdapter mouseListener = new MouseInputAdapter() {
			private Robot r;
			{
				try {
					r = new Robot();
				} catch (final AWTException e2) {
					e2.printStackTrace();
				}
			}

			@Override
			public void mouseMoved(final MouseEvent ev) {
				super.mouseMoved(ev);
				if (running && !levelCleared) {
					r.mouseMove((int) getLocationOnScreen().getX() + getWidth()
							/ 2, (int) getLocationOnScreen().getY()
							+ getHeight() / 2);
					vaus.move(ev.getX() - getWidth() / 2);
				}
			}

			@Override
			public void mousePressed(final MouseEvent ev) {
				super.mousePressed(ev);
				if (!game.isStarted() && !levelCleared) {
					game.startGame();
				}
			}

			@Override
			public void mouseReleased(final MouseEvent ev) {
				super.mouseReleased(ev);
			}

			@Override
			public void mouseEntered(final MouseEvent ev) {
				super.mouseEntered(ev);
				if (running) {
					setCursor(transparentCursor);
				}
			}
		};

		final ActionListener li = new ActionListener() {
			public void actionPerformed(final ActionEvent ev) {
				game.tick();
				if (vausIsShooting) {
					vaus.shoot(game);
				}
				repaint();
			}
		};
		timer = new Timer(TICKS_PER_SECOND, li);

		setBorder(BorderFactory.createLineBorder(Color.black));
		setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
		setFocusable(true);
		addKeyListener(keyListener);
		addMouseListener(mouseListener);
		addMouseMotionListener(mouseListener);

		game.addVausListener(this);

		level = game.getLevel();
		balls = game.getBalls();
		bullets = game.getBullets();
		bonuses = game.getBonuses();
		vaus = game.getVaus();

		// to hide the cursor
		final int[] pixels = new int[16 * 16];
		final Image image = Toolkit.getDefaultToolkit().createImage(
				new MemoryImageSource(16, 16, pixels, 0, 16));
		transparentCursor = Toolkit.getDefaultToolkit().createCustomCursor(
				image, new Point(0, 0), "invisibleCursor");

		firstTimeRun = true;
		play();
	}

	private final static BufferedImage scale(final BufferedImage source,
			final double factor) {
		final BufferedImage bi = new BufferedImage(source.getWidth(), source
				.getHeight(), BufferedImage.TYPE_INT_RGB);
		final Graphics2D g2d = bi.createGraphics();

		final AffineTransform at = AffineTransform.getScaleInstance(factor,
				factor);
		g2d.drawRenderedImage(source, at);
		g2d.dispose();
		return bi;
	}

	private final void setLevel(final Level level) {
		this.level = level;
		level.addLevelListener(new LevelListener() {
			public void bonusReleased(final Bonus bonus) {
				bonus.addBonusListener(new BonusListener() {

					public void bonusTaken(final Bonus bonus) {
						if (bonus.toString().equals("bonus_box")) {
							drawBox = true;
						}
						if (bonus.toString().equals("malus_lightoff")) {
							lightOff = true;
						}
					}

					public void lifeDecreased(final Bonus bonus) {
						if (bonus.getLife() == 0) {
							if (bonus.toString().equals("bonus_box")) {
								drawBox = false;
							} 
							if (bonus.toString().equals("malus_lightoff")) {
								lightOff = false;
							} 
						}
					}
				});
			}

			public void brickHit(final Brick brick) {
			}
		});
	}

	@Override
	public final void paintComponent(final Graphics g) {
		super.paintComponent(g);
		final Graphics2D g2d = (Graphics2D) g;

		if (gameCleared) {
			g2d.drawString("Game cleared, press [ENTER] to continue..", 100,
					100);
			return;
		}

		if (gameOver) {
			play();
			g2d.drawString("GAME OVER YOU n00b!", 100, 100);
			return;
		}

		if (levelCleared) {
			g2d.drawString("Level cleared, press [ENTER] to continue..", 100,
					100);
			return;
		}

		g2d.setColor(new Color(0xb0c4de));
		g2d.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
		final Brick[][] bricks = level.getBricks();

		for (int j = 0; j < bricks.length; j++) {
			for (int i = 0; i < bricks[j].length; i++) {
				final Brick brick = bricks[j][i];
				if (brick != null) {
					g2d.drawImage(ImagesRepository.getImage(brick.toString()),
							BRICK_WIDTH * i, j * BRICK_HEIGHT, this);
				}
			}
		}

		if (lightOff) {
			g2d.setColor(new Color(0,0,0));
			g2d.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
		}
		
		for (final Bonus bonus : bonuses) {
			final int x = bonus.getX();
			final int y = bonus.getY();
			g2d.drawImage(ImagesRepository.getImage(bonus.toString()), x, y,
					this);
		}

		for (final Ball ball : balls) {
			final int x = (int) ball.getX();
			final int y = (int) ball.getY();
			g2d.drawImage(ImagesRepository.getImage(ball.toString()), x, y,
					this);
		}

		for (final Ball ball : bullets) {
			final int x = (int) ball.getX();
			final int y = (int) ball.getY();
			g2d.drawImage(ImagesRepository.getImage(ball.toString()), x, y,
					this);
		}

		final String vausType = vaus.toString();
		if (vausType.toLowerCase().contains("rifle")) {
			g2d.drawImage(ImagesRepository.getImage(vaus.toString()), vaus
					.getX(), VAUS_Y - 23, this);
		} else if (vausType.toLowerCase().contains("cannon")) {
			g2d.drawImage(ImagesRepository.getImage(vaus.toString()), vaus
					.getX(), VAUS_Y - 23, this);
		} else {
			g2d.drawImage(ImagesRepository.getImage(vaus.toString()), vaus
					.getX(), VAUS_Y, this);
		}

		if (drawBox) {
			g2d.setColor(Color.ORANGE);
			g2d.fillRect(0, VAUS_Y + VAUS_HEIGHT + 1, GAME_WIDTH, 3);
		}

		if (!running && !firstTimeRun) {
			g2d.drawImage(image, 0, 0, null);
			g2d.drawImage(ImagesRepository.getImage("gamepaused"), 399, 0, this);
			g2d.drawImage(ImagesRepository.getImage("bonushelp"), 399, 300, this);
			g2d.drawImage(ImagesRepository.getImage("credits"), 0, 300, this);

		}
	}

	/**
	 * Start the main loop (timer)
	 */
	public final void play() {
		if (!running) {
			firstTimeRun = false;
			running = true;
			timer.start();
			setCursor(transparentCursor);
		} else {
			timer.stop();
			running = false;
			setCursor(null);
		}
	}

	public final void setVaus(final Vaus vaus) {
		this.vaus = vaus;
	}

	/**
	 * Draw the Box line
	 */
	public final void drawBoxLine() {
		drawBox = true;
	}

	public final void removeBoxLine() {
		drawBox = false;
	}

}
