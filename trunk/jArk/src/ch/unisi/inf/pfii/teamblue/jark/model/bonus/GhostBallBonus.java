package ch.unisi.inf.pfii.teamblue.jark.model.bonus;

import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import ch.unisi.inf.pfii.teamblue.jark.model.Game;
import ch.unisi.inf.pfii.teamblue.jark.model.ball.Ball;
import ch.unisi.inf.pfii.teamblue.jark.model.ball.GhostBall;
import ch.unisi.inf.pfii.teamblue.jark.model.ball.UltraBall;


/**
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 *
 */

public final class GhostBallBonus extends Bonus {
	public Image getImage() {
		return new ImageIcon(getClass().getResource("../../view/images/bonuses/malus_ghostball.png")).getImage();
	}
	public void apply(Game game) {
		ArrayList<Ball> balls = game.getBalls();
		int numberOfBalls = balls.size();
		
		for (int i = 0; i < numberOfBalls; i++) {
			Ball newBall = new GhostBall(balls.get(i).getVaus(), balls.get(i).getLevel());
			newBall.setSpeedX(balls.get(i).getSpeedX());
			newBall.setSpeedY(balls.get(i).getSpeedY());
			newBall.setX(balls.get(i).getX());
			newBall.setY(balls.get(i).getY());
			
			balls.set(i,newBall);
		}
	}
}
