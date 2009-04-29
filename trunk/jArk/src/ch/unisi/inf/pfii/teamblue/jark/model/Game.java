package ch.unisi.inf.pfii.teamblue.jark.model;

import java.util.ArrayList;

import ch.unisi.inf.pfii.teamblue.jark.implementation.Console;
import ch.unisi.inf.pfii.teamblue.jark.implementation.Constants;
import ch.unisi.inf.pfii.teamblue.jark.model.ball.*;
import ch.unisi.inf.pfii.teamblue.jark.model.bonus.Bonus;
import ch.unisi.inf.pfii.teamblue.jark.model.level.Level;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.DefaultVaus;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.Vaus;
import ch.unisi.inf.pfii.teamblue.jark.view.GamePanel;
import ch.unisi.inf.pfii.teamblue.jark.view.GameFrame;
import ch.unisi.inf.pfii.teamblue.jark.view.MainPanel;

/**
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 * 
 */

public final class Game implements Constants {

	private GamePanel fieldView;

	private ArrayList<Ball> balls;
	private ArrayList<Bonus> freeBonuses;

	private Vaus vaus;
	private Player player;
	private Level level;
	private Console console;

	public Game(GamePanel fieldView) {

		this.fieldView = fieldView;

		console = new Console();
		balls = new ArrayList<Ball>();
		freeBonuses = new ArrayList<Bonus>();
		vaus = new DefaultVaus(GAME_WIDTH / 2 - 20 / 2, 20);
		player = new Player("pippo", 3);
		level = new Level(3, freeBonuses);
		balls.add(new DefaultBall(vaus, level));
		balls.get(0).setSpeedX(-2);
		balls.get(0).setSpeedY(-3);

		fieldView.setBricks(level.getBricks());

	}

	public final void play() {
		while (true) {
			String s = console.readLine();
			if (s.equals("")) {
				for (int i = 0; i < 50; i++) {
					moveBalls();
				}
				printWorld();

			} else {
				break;
			}
		}

	}

	/**
	 * Move all the balls in the game
	 */
	private final void moveBalls() {
		for (Ball ball : balls) {
			ball.move();
		}
	}

	/**
	 * Print the current world, showing only the field at the moment.
	 */
	private final void printWorld() {
		char esc = 27;
		String clear = esc + "[2J";
		System.out.print(clear);
		System.out.println(level.toString());
	}

}