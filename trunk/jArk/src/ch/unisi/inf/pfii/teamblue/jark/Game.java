package ch.unisi.inf.pfii.teamblue.jark;

import java.util.ArrayList;

/**
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate: 2009-04-13 17:30:41 +0200 (Mon, 13 Apr 2009) $
 * 
 */

public class Game {
	
	private ArrayList<Ball> balls;
	private ArrayList<Bonus> bonuses;
	private Vaus vaus;
	private Player player;
	private Level level;
	
	public Game() {
		balls = new ArrayList<Ball>();
		bonuses = new ArrayList<Bonus>();
		balls.add(new Ball(0, 0));
		vaus = new Vaus(0, 0, 0);
		player = new Player("pippo", 3);
		level = new Level(25);
		
	}
	
}
