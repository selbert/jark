package ch.unisi.inf.pfii.teamblue.jark.model;

import java.util.ArrayList;
import java.util.concurrent.*;

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
	private ExecutorService ex;
	
	private boolean running;

	public Game(GamePanel fieldView) {

		this.fieldView = fieldView;
		
		console = new Console();
		balls = new ArrayList<Ball>();
		freeBonuses = new ArrayList<Bonus>();
		vaus = new DefaultVaus(GAME_WIDTH / 2 - 20 / 2, 20);
		player = new Player("pippo", 3);
		level = new Level(30, freeBonuses);
		balls.add(new DefaultBall(vaus, level));
		balls.get(0).setSpeedX(-1);
		balls.get(0).setSpeedY(-2);
		fieldView.setBricks(level.getBricks());
		fieldView.setBalls(balls);
		ex = Executors.newFixedThreadPool(1);
		running = false;

	}
	
	public void play() {
		if (!running) {
			running = true;
			ex.execute(new GameStart());
		} else {
			running = false;
		}
	}

	class GameStart implements Runnable {
		public final void run() {
			while (running) {
				//String s = console.readLine();
				//if (s.equals("")) {
					moveBalls();
					fieldView.repaint();
					try {
						Thread.sleep(30);
					} catch (InterruptedException e) {
						//donithing
						e.printStackTrace();
					}

			//	} else {
			//		break;
			//	}
			}

		}
	};

	/**
	 * Move all the balls in the game
	 */
	private final void moveBalls() {
		for (Ball ball : balls) {
			ball.move();
		}
	}
}
