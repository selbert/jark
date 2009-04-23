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
	private static final int GAME_WIDTH = 800;
	private static final int GAME_HEIGHT =  600;
	
	private ArrayList<Ball> balls;
	private ArrayList<Bonus> freeBonuses;
	
	private Vaus vaus;
	private Player player;
	private Level level;
	
	public Game() {
		balls = new ArrayList<Ball>();
		freeBonuses = new ArrayList<Bonus>();
		vaus = new Vaus(GAME_WIDTH/2 - 20/2, 20);
		player = new Player("pippo", 3);
		level = new Level(25, freeBonuses);
		balls.add(new DefaultBall(vaus, level));
		balls.get(0).setSpeedX(-2);
		balls.get(0).setSpeedY(-3);
		balls.get(0).print();
		for (int i = 0; i < 10000; i++) {
			moveBalls();
			if (i%50 == 0) {
				printWorld();
			}
		}
		
	}
	
	private void moveBalls() {
		for (Ball ball : balls) {
			ball.move();
		}
	}

	public static int getGAME_WIDTH() {
		return GAME_WIDTH;
	}

	public static int getGAME_HEIGHT() {
		return GAME_HEIGHT;
	}
	
	private void printWorld() {
		System.out.println(level.toString());
	}
}
