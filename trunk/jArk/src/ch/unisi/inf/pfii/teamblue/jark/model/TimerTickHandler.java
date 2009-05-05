package ch.unisi.inf.pfii.teamblue.jark.model;

import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;

import ch.unisi.inf.pfii.teamblue.jark.implementation.Constants;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.Vaus;
import ch.unisi.inf.pfii.teamblue.jark.view.GamePanel;

public class TimerTickHandler implements ActionListener, Constants {
    private double tick;
    private Game game;
    private Vaus vaus;
    private GamePanel gamePanel;
    
    public TimerTickHandler(Game game, Vaus vaus, GamePanel gamePanel) {
    	this.game = game;
    	this.vaus = vaus;
    	this.gamePanel = gamePanel;
    }    

    public void actionPerformed(final ActionEvent ev) {
        tick++;
        game.moveBalls();
        game.moveBonuses();
		vaus.move();
		gamePanel.repaint();
    }
}