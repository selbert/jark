package ch.unisi.inf.pfii.teamblue.jark.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.MemoryImageSource;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.Timer;

import ch.unisi.inf.pfii.teamblue.jark.implementation.Constants;
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

public final class GamePanel extends JPanel implements Constants, MouseMotionListener, KeyListener, VausListener {
	private final Timer timer; 
	private final ImagesReference ir;
	
	private final Brick[][] bricks;
	private final ArrayList<Ball> balls;
	private final ArrayList<Bonus> bonuses;
	private Vaus vaus;
	
	private boolean drawBox;
	private boolean running;
	
	public GamePanel(final Game game) {
		
		ActionListener li = new ActionListener() {
            public void actionPerformed(final ActionEvent ev) {
            	game.moveBalls();
                game.moveBonuses();
        		game.getVaus().move();
        		repaint();
            }
        };
        timer = new Timer(TICKS_PER_SECOND, li);
        
		setBorder(BorderFactory.createLineBorder(Color.black));
		setBackground(Color.GRAY);
		setPreferredSize(new Dimension(798, 600));
		setFocusable(true);
		addKeyListener(this);
		addMouseMotionListener(this);
		
        game.addVausListener(this);

        ir = new ImagesReference();
		bricks = game.getBricks();
		balls = game.getBalls();
		bonuses = game.getBonuses();
		vaus = game.getVaus();
		
		//to hide the cursor
		final int[] pixels = new int[16 * 16];
		final Image image = Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(16, 16, pixels, 0, 16));
		final Cursor transparentCursor = Toolkit.getDefaultToolkit().createCustomCursor(image, new Point(0, 0), "invisibleCursor");
		setCursor(transparentCursor);
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
			final int x = (int)ball.getX();
			final int y = (int)ball.getY();
			g2d.drawImage(ir.getImage(ball.toString()), x, y, BALL_RADIUS * 2, BALL_RADIUS * 2, this);
		}
		
		g2d.drawImage(ir.getImage(vaus.toString()), vaus.getX(), VAUS_Y, vaus.getWidth(), VAUS_HEIGHT, this);
		
		if (drawBox) {
			g2d.fillRect(0, VAUS_Y+VAUS_HEIGHT+1, GAME_WIDTH, 3);
		}
	}

	/**
	 * Start the main loop (timer)
	 */
	public final void play() {
		if (!running) {
			running = true;
			timer.start();
		} else {
			timer.stop();
			running = false;
		}
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
	
	//implemented methods
	public final void mouseMoved(MouseEvent e) {
		vaus.move(e.getX() - vaus.getWidth() / 2);
	}
	public final void mouseDragged(MouseEvent e) {
	}

	public final void keyPressed(KeyEvent e) {
		final int keyCode = e.getKeyCode();
		if (keyCode == KeyEvent.VK_LEFT) {
			vaus.moveLeft();
		}
		if (keyCode == KeyEvent.VK_RIGHT) {
			vaus.moveRight();
		}
	}
	public final void keyReleased(KeyEvent e) {
		final int keyCode = e.getKeyCode();
		if (keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_RIGHT) {
			vaus.stop();
		}
	}
	public final void keyTyped(KeyEvent e) {
	}
	
	public final void setVaus(Vaus vaus) {
		this.vaus = vaus;
	}

}
