package ch.unisi.inf.pfii.teamblue.jark.model.bonus;

import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import ch.unisi.inf.pfii.teamblue.jark.model.Game;
import ch.unisi.inf.pfii.teamblue.jark.model.ball.Ball;
import ch.unisi.inf.pfii.teamblue.jark.model.ball.DefaultBall;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.DefaultVaus;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.LongVaus;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.Vaus;


/**
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 *
 */

public final class ResetStatusBonus extends Bonus {
	@Override
	public Image getImage() {
		return new ImageIcon(getClass().getResource("../../view/images/bonuses/neutral_resetstatus.png")).getImage();
	}
	@Override
	public void apply(Game game) {
		ArrayList<Ball> balls = game.getBalls();
		int numberOfBalls = balls.size();
		
		for (int i = 0; i < numberOfBalls; i++) {
			Ball newBall = new DefaultBall(balls.get(i).getVaus(), balls.get(i).getLevel());
			newBall.setSpeedX(balls.get(i).getSpeedX());
			newBall.setSpeedY(balls.get(i).getSpeedY());
			newBall.setX(balls.get(i).getX());
			newBall.setY(balls.get(i).getY());
			
			game.replaceBall(balls.get(i), newBall);
		}
		Vaus newVaus = new DefaultVaus(game.getVaus().getX());
		game.setVaus(newVaus); 
	}
}
