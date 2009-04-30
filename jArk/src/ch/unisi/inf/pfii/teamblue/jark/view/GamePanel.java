package ch.unisi.inf.pfii.teamblue.jark.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ch.unisi.inf.pfii.teamblue.jark.implementation.Constants;
import ch.unisi.inf.pfii.teamblue.jark.model.ball.Ball;
import ch.unisi.inf.pfii.teamblue.jark.model.brick.Brick;

/**
 * 
 * The game panel, it should show the bricks the ball and the vaus
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 * 
 */

public class GamePanel extends JPanel implements Constants {
	
	private Brick[][] bricks;
	private ArrayList<Ball> balls;
	
	private Image brick2;
	private Image brick;
	private Image ballz;
	
	public GamePanel() {
		brick = new ImageIcon(getClass().getResource("images/brick.png")).getImage();
		brick2 = new ImageIcon(getClass().getResource("images/brick2.png")).getImage();
		ballz = new ImageIcon(getClass().getResource("images/ball.png")).getImage();
		setBackground(Color.GRAY);
		setPreferredSize(new Dimension(798,600));
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		if (bricks == null) {
			System.out.println("OMGGG");
			JLabel welcomeText = new JLabel("Welcome to Hell");
			welcomeText.setBackground(Color.RED);
			add(welcomeText);
		} else {
			for (int j = 0; j < bricks.length; j++) {
				for (int i = 0; i < bricks[j].length; i++) {
					if(bricks[j][i] != null) {
						if(bricks[j][i].getBonus() != null) {
							g2d.drawImage(brick, 57 * i, j * 25, null);
						} else {
							g2d.drawImage(brick2, 57 * i, j * 25, null);
						}
					}
				}
			}
			
			for (Ball ball : balls) {
				int x = ball.getX();
				int y = ball.getY();
				g2d.drawImage(ballz, x, y, BALL_RADIUS*2, BALL_RADIUS*2, null);
				
			}
		}
	}

	public void setBricks(Brick[][] bricks) {
		this.bricks = bricks;
	}
	
	public void setBalls(ArrayList<Ball> balls) {
		this.balls = balls;
	}
}
