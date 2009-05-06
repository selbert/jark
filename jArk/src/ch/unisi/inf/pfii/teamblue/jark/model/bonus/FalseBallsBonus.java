package ch.unisi.inf.pfii.teamblue.jark.model.bonus;

import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import ch.unisi.inf.pfii.teamblue.jark.model.Game;
import ch.unisi.inf.pfii.teamblue.jark.model.ball.Ball;
import ch.unisi.inf.pfii.teamblue.jark.model.ball.RubberBall;


/**
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 *
 */

public final class FalseBallsBonus extends Bonus {
	@Override
	public Image getImage() {
		return new ImageIcon(getClass().getResource("../../view/images/bonuses/malus_falseball.png")).getImage();
	}
	
	@Override
	public void apply(Game game) {
		ArrayList<Ball> balls = game.getBalls();
		int numberOfBalls = balls.size();
		
		for (int i = 0; i < numberOfBalls;i++) {
			Ball newBall = new RubberBall(balls.get(i).getVaus(), balls.get(i).getLevel());
			newBall.setSpeedX(balls.get(i).getSpeedX());
			newBall.setSpeedY(balls.get(i).getSpeedY());
			newBall.setX(balls.get(i).getX());
			newBall.setY(balls.get(i).getY());
			game.addVausListener(newBall);
			game.removeVausListener(balls.get(i));
			balls.set(i,newBall);
			for (int j = 0; j < 5; j++) {
				Ball newBall1 = balls.get(i).copy();
				newBall1.setSpeedX(-1 * balls.get(i).getSpeedX());
				newBall1.setSpeedY(balls.get(i).getSpeedY());
				game.addBall(newBall1);
			}
		}
	}
}
