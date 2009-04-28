package ch.unisi.inf.pfii.teamblue.jark.view;

import java.awt.*;
import javax.swing.*;

/**
 * 
 * The game panel, it should show the bricks the ball and the vaus
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 * 
 */

public class GamePanel extends JPanel {
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for (int j = 0; j < 16; j++) {
			for (int i = 0; i < 14; i++) {
				g.setColor(Color.blue);
				g.fillRect(57 * i, j * 25, 57, 25);
				g.setColor(Color.black);
				g.drawRect(57 * i, j * 25, 57, 25);
			}
		}
	}
}
