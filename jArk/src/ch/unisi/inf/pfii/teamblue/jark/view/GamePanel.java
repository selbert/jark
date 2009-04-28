package ch.unisi.inf.pfii.teamblue.jark.view;

import java.awt.*;

import javax.swing.*;

import ch.unisi.inf.pfii.teamblue.jark.model.brick.Brick;

/**
 * 
 * The game panel, it should show the bricks the ball and the vaus
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 * 
 */

public class GamePanel extends JPanel {
	
	private Brick[][] bricks;
	
	
	public GamePanel() {
		setBackground(Color.GREEN);
		setPreferredSize(new Dimension(800,600));
		
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (bricks == null) {
			System.out.println("OMGGG");
			JLabel welcomeText = new JLabel("Welcome to Hell");
			welcomeText.setBackground(Color.red);
			add(welcomeText);
		} else {
			for (int j = 0; j < 16; j++) {
				for (int i = 0; i < 14; i++) {
					if(bricks[j][i] != null) {
						if(bricks[j][i].getBonus() != null) {
							g.setColor(Color.red);
							g.fillRect(57 * i, j * 25, 57, 25);
						} else {
							g.setColor(Color.blue);
							g.fillRect(57 * i, j * 25, 57, 25);
						}
					} else {
						g.setColor(Color.green);
						g.fillRect(57 * i, j * 25, 57, 25);
					}

					g.setColor(Color.black);
					g.drawRect(57 * i, j * 25, 57, 25);
				}
			}
		}
	}

	public void setBricks(Brick[][] bricks) {
		this.bricks = bricks;
	}

	
}
