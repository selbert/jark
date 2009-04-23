package ch.unisi.inf.pfii.teamblue.jark.model;

import java.util.ArrayList;

import ch.unisi.inf.pfii.teamblue.jark.model.ball.*;
import ch.unisi.inf.pfii.teamblue.jark.model.bonus.Bonus;
import ch.unisi.inf.pfii.teamblue.jark.model.level.Level;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.Vaus;


/**
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 * 
 */

public class Game {
	private static final int FIELD_WIDTH = 800;
	private static final int FIELD_HEIGHT =  600;
	
	private ArrayList<Ball> balls;
	private ArrayList<Bonus> freeBonuses;
	
	private Vaus vaus;
	private Player player;
	private Level level;
	
	public Game() {
		balls = new ArrayList<Ball>();
		freeBonuses = new ArrayList<Bonus>();
		vaus = new Vaus(0, 10);
		player = new Player("pippo", 3);
		level = new Level(25, freeBonuses);
		balls.add(new DefaultBall(vaus, level));
		
	}
	
	private void moveBalls() {
		for (Ball ball : balls) {
			ball.move();
		}
	}
	
	
	
}
