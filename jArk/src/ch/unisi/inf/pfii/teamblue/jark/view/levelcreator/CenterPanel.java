package ch.unisi.inf.pfii.teamblue.jark.view.levelcreator;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.image.MemoryImageSource;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.event.MouseInputAdapter;

import ch.unisi.inf.pfii.teamblue.jark.implementation.BonusListener;
import ch.unisi.inf.pfii.teamblue.jark.implementation.Constants;
import ch.unisi.inf.pfii.teamblue.jark.implementation.LevelListener;
import ch.unisi.inf.pfii.teamblue.jark.implementation.VausSetListener;
import ch.unisi.inf.pfii.teamblue.jark.model.Game;
import ch.unisi.inf.pfii.teamblue.jark.model.ball.Ball;
import ch.unisi.inf.pfii.teamblue.jark.model.bonus.Bonus;
import ch.unisi.inf.pfii.teamblue.jark.model.brick.Brick;
import ch.unisi.inf.pfii.teamblue.jark.model.level.LevelManager;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.Vaus;
import ch.unisi.inf.pfii.teamblue.jark.view.ImagesReference;

/**
 * The game panel, it should show the bricks the balls and the vaus
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 * 
 */

public final class CenterPanel extends JComponent implements Constants {
	
	
	public CenterPanel(final LevelManager levelManager, final ButtonGroup group) {
		//setBorder(BorderFactory.createLineBorder(Color.black));
		setLayout(new BorderLayout(0,6));
		JPanel borderPanel = new JPanel();
		borderPanel.setLayout(null);
		borderPanel.setPreferredSize(new Dimension(800,402));
		borderPanel.setBackground(Color.BLACK);
		FieldPanel fp = new FieldPanel(levelManager, group);
		borderPanel.add(fp);
		BonusPanel bp = new BonusPanel(fp, group);
		add(bp, BorderLayout.CENTER);
		add(borderPanel, BorderLayout.NORTH);
	}


}
