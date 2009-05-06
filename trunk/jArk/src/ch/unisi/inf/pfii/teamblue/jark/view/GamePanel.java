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
import javax.swing.ImageIcon;
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
 * 
 * The game panel, it should show the bricks the ball and the vaus
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 * 
 */

public class GamePanel extends JPanel implements Constants, MouseMotionListener, KeyListener, VausListener {

	private Brick[][] bricks;
	private ArrayList<Ball> balls;
	private ArrayList<Bonus> bonuses;
	private Vaus vaus;

	private Image brick2;
	private Image brick;
	private Image ballz;

	private boolean drawBox;
	private boolean running;
	
	private Timer ticker; 
	
	public GamePanel(final Game game) {
		
		ActionListener li = new ActionListener() {
            public void actionPerformed(final ActionEvent ev) {
            	game.moveBalls();
                game.moveBonuses();
        		game.getVaus().move();
        		repaint();
            }
        };
        ticker = new Timer(TICKS_PER_SECOND, li);
        
        //to listen to vaus changes
        game.addVausListener(this);
        
		bricks = game.getBricks();
		balls = game.getBalls();
		bonuses = game.getBonuses();
		vaus = game.getVaus();
		
		//to hide the cursor
		int[] pixels = new int[16 * 16];
		Image image = Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(16, 16, pixels, 0, 16));
		Cursor transparentCursor = Toolkit.getDefaultToolkit().createCustomCursor(image, new Point(0, 0), "invisibleCursor");
		setCursor(transparentCursor);
		
		//images
		//brick2 = new ImageIcon(getClass().getResource("images/brick.png")).getImage();
		ballz = new ImageIcon(getClass().getResource("images/testball.png")).getImage();
		
		setBorder(BorderFactory.createLineBorder(Color.black));
		setBackground(Color.GRAY);
		setPreferredSize(new Dimension(798, 600));
		setFocusable(true);
		addKeyListener(this);
		addMouseMotionListener(this);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		for (int j = 0; j < bricks.length; j++) {
			for (int i = 0; i < bricks[j].length; i++) {
				final Brick brick = bricks[j][i];
				if (brick != null) {
					g2d.drawImage(brick.getImage(), 57 * i, j * 25, this);
				}
			}
		}

		for (Ball ball : balls) {
			int x = (int)ball.getX();
			int y = (int)ball.getY();
			g2d.drawImage(ballz, x, y, BALL_RADIUS * 2, BALL_RADIUS * 2, this);

		}
		
		for (Bonus bonus : bonuses) {
			int x = bonus.getX();
			int y = bonus.getY();
			g2d.drawImage(bonus.getImage(), x, y, this);
		}

		g2d.drawImage(vaus.getImage(), vaus.getX(), VAUS_Y, vaus.getWidth(), VAUS_HEIGHT, this);
		
		if (drawBox) {
			g2d.fillRect(0, VAUS_Y+VAUS_HEIGHT+1, GAME_WIDTH, 3);
		}
	}

	public void play() {
		if (!running) {
			running = true;
			ticker.start();
		} else {
			ticker.stop();
			running = false;
		}
	}
	
	public void drawBoxLine() {
		drawBox = true;
	}
	public void removeBoxLine() {
		drawBox = false;
	}
	
	public void mouseMoved(MouseEvent e) {
		vaus.move(e.getX() - vaus.getWidth() / 2);
	}

	public void mouseDragged(MouseEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		vaus.pressedKey(e);
	}

	public void keyReleased(KeyEvent e) {
		vaus.releasedKey(e);
	}

	public void keyTyped(KeyEvent e) {
	}

	public void setVaus(Vaus vaus) {
		this.vaus = vaus;
		
	}

}
