package ch.unisi.inf.pfii.teamblue.jark.view.levelcreator;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
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
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.Timer;
import javax.swing.event.MouseInputAdapter;

import ch.unisi.inf.pfii.teamblue.jark.implementation.BonusListener;
import ch.unisi.inf.pfii.teamblue.jark.implementation.Constants;
import ch.unisi.inf.pfii.teamblue.jark.implementation.LevelListener;
import ch.unisi.inf.pfii.teamblue.jark.implementation.Utils;
import ch.unisi.inf.pfii.teamblue.jark.implementation.VausListener;
import ch.unisi.inf.pfii.teamblue.jark.model.Game;
import ch.unisi.inf.pfii.teamblue.jark.model.ball.Ball;
import ch.unisi.inf.pfii.teamblue.jark.model.bonus.Bonus;
import ch.unisi.inf.pfii.teamblue.jark.model.brick.Brick;
import ch.unisi.inf.pfii.teamblue.jark.model.vaus.Vaus;
import ch.unisi.inf.pfii.teamblue.jark.view.ImagesReference;

/**
 * The game panel, it should show the bricks the balls and the vaus
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 * 
 */

public final class FieldPanel extends JComponent implements Constants {
	private int brickX;
	private int brickY;
	private ButtonGroup group;
	private ButtonModel selected;
	
	public FieldPanel(final ButtonGroup group) {
		this.group = group;
		//setBorder(BorderFactory.createLineBorder(Color.black));
		setPreferredSize(new Dimension(799, 401));
		setFocusable(true);
		
		MouseInputAdapter mouseListener = new MouseInputAdapter() {
			@Override
			public void mouseMoved(MouseEvent ev) {
				super.mouseMoved(ev);
				int[] pos = Utils.getFieldPosition(ev.getX(), ev.getY());
				brickX = pos[0];
				brickY = pos[1];
				repaint();
			}

			@Override
			public void mousePressed(MouseEvent ev) {
				if (selected != null) {
					System.out.println("X: "+brickX+" Y: "+brickY+" Type: "+selected.getActionCommand());
				}
			}

			@Override
			public void mouseEntered(MouseEvent ev) {
				super.mouseEntered(ev);
			}
		};
		
		addMouseListener(mouseListener);
		addMouseMotionListener(mouseListener);
	}

	@Override
	public final void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		for (int j = 0; j <= FIELD_ROWS; j++) {
			g2d.drawLine(0, j * BRICK_HEIGHT, getWidth(), j * BRICK_HEIGHT);
			for (int i = 0; i <= FIELD_COLUMNS; i++) {
				g2d.drawLine(BRICK_WIDTH * i, 0, BRICK_WIDTH*i, getHeight());
			}
		}
		
		selected = group.getSelection();
		if (selected != null) {
			g2d.drawImage(ImagesReference.getImage(selected.getActionCommand()), BRICK_WIDTH*brickX, BRICK_HEIGHT*brickY, this);
		}
	}

}
