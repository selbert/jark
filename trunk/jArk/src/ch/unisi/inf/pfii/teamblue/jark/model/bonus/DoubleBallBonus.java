package ch.unisi.inf.pfii.teamblue.jark.model.bonus;

import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import ch.unisi.inf.pfii.teamblue.jark.model.Game;
import ch.unisi.inf.pfii.teamblue.jark.model.ball.Ball;


/**
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 *
 */

public final class DoubleBallBonus extends Bonus {

	@Override
	public Image getImage() {
		return new ImageIcon(getClass().getResource("../../view/images/bonuses/bonus_doubleball.png")).getImage();
	}
	
	@Override
	public void apply(Game game) {
		ArrayList<Ball> balls = game.getBalls();
		int numberOfBalls = balls.size();
		
		for (int i = 0; i < numberOfBalls; i++) {
			Ball newBall = balls.get(i).copy();
			newBall.setSpeedX(-1 * balls.get(i).getSpeedX());
			newBall.setSpeedY(balls.get(i).getSpeedY());
			game.addBall(newBall);
		}
	}
}
