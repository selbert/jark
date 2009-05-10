package ch.unisi.inf.pfii.teamblue.jark.view;

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
import java.awt.image.MemoryImageSource;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.Timer;
import javax.swing.event.MouseInputAdapter;

import ch.unisi.inf.pfii.teamblue.jark.implementation.BonusListener;
import ch.unisi.inf.pfii.teamblue.jark.implementation.Constants;
import ch.unisi.inf.pfii.teamblue.jark.implementation.LevelListener;
import ch.unisi.inf.pfii.teamblue.jark.implementation.VausListener;
import ch.unisi.inf.pfii.teamblue.jark.model.Game;
import ch.unisi.inf.pfii.teamblue.jark.model.ball.Ball;
import ch.unisi.inf.pfii.teamblue.jark.model.bonus.Bonus;
import ch.unisi.inf.pfii.teamblue.jark.model.brick.Brick;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.Vaus;

/**
 * The game panel, it should show the bricks the balls and the vaus
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 * 
 */

public final class GamePanel extends JComponent implements Constants, VausListener {
	private final Timer timer;
	
	private final ImagesReference ir;

	private final Brick[][] bricks;
	private final ArrayList<Ball> balls;
	private final ArrayList<Bonus> bonuses;
	private final ArrayList<Ball> bullets;
	private Vaus vaus;

	private boolean drawBox;
	private boolean running;
	private boolean firstTimeRun;

	private String textToDraw;
	private long lastBonusTakenTime;
	private final static long bonusMessageDelay = 1000;
	
	private final Cursor transparentCursor;
	
	public GamePanel(final Game game) {

		game.getLevel().addLevelListener(new LevelListener() {
			public void bonusReleased(Bonus bonus) {
				bonus.addBonusListener(new BonusListener() {
					public void bonusTaken(Bonus bonus) {
						lastBonusTakenTime = System.currentTimeMillis();
						System.out.println("Got: "+bonus.toString());
						textToDraw = bonus.toString();
						if (bonus.toString().equals("bonus_box")) {
							drawBox = true;
						}
					}
				});
			}

			public void brickHit(Brick brick) {
				// TODO Auto-generated method stub
				
			}
		});
		
		KeyListener keyListener = new KeyListener() {
			public final void keyPressed(KeyEvent ev) {
				switch (ev.getKeyCode()) {
				case KeyEvent.VK_LEFT: 
					vaus.moveLeft();
					break;
				case KeyEvent.VK_RIGHT:
					vaus.moveRight();
					break;
				case KeyEvent.VK_ESCAPE:
					play();
					repaint();
					break;
				case KeyEvent.VK_SPACE:
					vaus.shoot(game);
					break;
				}
			}

			public final void keyReleased(KeyEvent ev) {
				switch (ev.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					vaus.stopLeft();
					break;
				case KeyEvent.VK_RIGHT:
					vaus.stopRight();
					break;
				}
			}

			public final void keyTyped(KeyEvent ev) {
			}
		};
		
		MouseInputAdapter mouseListener = new MouseInputAdapter() {
			private Robot r;
			{
				try {
					r = new Robot();
				} catch (AWTException e2) {
					e2.printStackTrace();
				}
			}

			@Override
			public void mouseMoved(MouseEvent ev) {
				super.mouseMoved(ev);
				if (running) {
					r.mouseMove((int) getLocationOnScreen().getX() + getWidth()
							/ 2, (int) getLocationOnScreen().getY()
							+ getHeight() / 2);
					vaus.move(ev.getX() - getWidth() / 2);
				}
			}

			@Override
			public void mousePressed(MouseEvent ev) {
				super.mousePressed(ev);
			}

			@Override
			public void mouseReleased(MouseEvent ev) {
				super.mouseReleased(ev);
			}

			@Override
			public void mouseEntered(MouseEvent ev) {
				super.mouseEntered(ev);
				if (running) {
					setCursor(transparentCursor);
				}
			}
		};

		ActionListener li = new ActionListener() {
			public void actionPerformed(final ActionEvent ev) {
				game.tick();
				repaint();
			}
		};
		timer = new Timer(TICKS_PER_SECOND, li);

		setBorder(BorderFactory.createLineBorder(Color.black));
		setPreferredSize(new Dimension(798, 600));
		setFocusable(true);
		addKeyListener(keyListener);
		addMouseListener(mouseListener);
		addMouseMotionListener(mouseListener);

		game.addVausListener(this);

		ir = new ImagesReference();
		bricks = game.getBricks();
		balls = game.getBalls();
		bullets = game.getBullets();
		bonuses = game.getBonuses();
		vaus = game.getVaus();

		// to hide the cursor
		final int[] pixels = new int[16 * 16];
		final Image image = Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(16, 16, pixels, 0, 16));
	    transparentCursor = Toolkit.getDefaultToolkit().createCustomCursor(image, new Point(0, 0), "invisibleCursor");
	    
	    firstTimeRun = true;
	}

	@Override
	public final void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		for (int j = 0; j < bricks.length; j++) {
			for (int i = 0; i < bricks[j].length; i++) {
				final Brick brick = bricks[j][i];
				if (brick != null) {
					g2d.drawImage(ir.getImage(brick.toString()), BRICK_WIDTH * i, j * BRICK_HEIGHT, this);
				}
			}
		}

		for (Bonus bonus : bonuses) {
			final int x = bonus.getX();
			final int y = bonus.getY();
			g2d.drawImage(ir.getImage(bonus.toString()), x, y, this);
		}

		for (Ball ball : balls) {
			final int x = (int) ball.getX();
			final int y = (int) ball.getY();
			g2d.drawImage(ir.getImage(ball.toString()), x, y, this);
		}
		
		for (Ball ball : bullets) {
			final int x = (int) ball.getX();
			final int y = (int) ball.getY();
			g2d.drawImage(ir.getImage(ball.toString()), x, y, this);
		}

		g2d.drawImage(ir.getImage(vaus.toString()), vaus.getX(), VAUS_Y, vaus.getWidth(), VAUS_HEIGHT, this);

		if (drawBox) {
			g2d.fillRect(0, VAUS_Y + VAUS_HEIGHT + 1, GAME_WIDTH, 3);
		}
		
		if (!running && !firstTimeRun) {
			g2d.drawImage(ir.getImage("pause"), 200, 180, this);
		}
		
		if (textToDraw != null && lastBonusTakenTime+bonusMessageDelay >= System.currentTimeMillis()) {
			g2d.drawString(textToDraw, 650, 560);
		} else {
			textToDraw = null;
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

	public final void setVaus(Vaus vaus) {
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
