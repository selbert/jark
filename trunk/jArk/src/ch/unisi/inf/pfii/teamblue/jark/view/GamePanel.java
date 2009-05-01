package ch.unisi.inf.pfii.teamblue.jark.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ch.unisi.inf.pfii.teamblue.jark.implementation.Constants;
import ch.unisi.inf.pfii.teamblue.jark.model.ball.Ball;
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

public class GamePanel extends JPanel implements Constants, MouseMotionListener {

	private Brick[][] bricks;
	private ArrayList<Ball> balls;
	private Vaus vaus;
	
	private Image brick2;
	private Image brick;
	private Image ballz;
	private Image vausz;
	
	public GamePanel() {

		brick = new ImageIcon(getClass().getResource("images/brick.png")).getImage();
		brick2 = new ImageIcon(getClass().getResource("images/brick2.png")).getImage();
		ballz = new ImageIcon(getClass().getResource("images/ball.png")).getImage();
		vausz = new ImageIcon(getClass().getResource("images/vaus.png")).getImage();
		
        //setFocusable(true);

		setBackground(Color.GRAY);
		setPreferredSize(new Dimension(798, 600));
		addMouseMotionListener(this);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		for (int j = 0; j < bricks.length; j++) {
			for (int i = 0; i < bricks[j].length; i++) {
				if (bricks[j][i] != null) {
					if (bricks[j][i].getBonus() != null) {
						g2d.drawImage(brick, 57 * i, j * 25, this);
					} else {
						g2d.drawImage(brick2, 57 * i, j * 25, this);
					}
				}
			}
		}

		for (Ball ball : balls) {
			int x = ball.getX();
			int y = ball.getY();
			g2d.drawImage(ballz, x, y, BALL_RADIUS * 2, BALL_RADIUS * 2, this);
			
		}
		
		g2d.drawImage(vausz, vaus.getX(), VAUS_Y, VAUS_WIDTH, VAUS_HEIGHT, this);

	}
	
	
	public void mouseMoved(MouseEvent e) {
        vaus.move(e.getX() - VAUS_WIDTH/2);
    }
    
    public void mouseDragged(MouseEvent e) {
        
    }

	public void setBricks(Brick[][] bricks) {
		this.bricks = bricks;
	}

	public void setBalls(ArrayList<Ball> balls) {
		this.balls = balls;
	}
	
	public void setVaus(Vaus vaus) {
		this.vaus = vaus;
	}
}