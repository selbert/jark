package ch.unisi.inf.pfii.teamblue.jark.view.levelcreator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JComponent;
import javax.swing.event.MouseInputAdapter;

import ch.unisi.inf.pfii.teamblue.jark.implementation.Constants;
import ch.unisi.inf.pfii.teamblue.jark.implementation.Utils;
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
	private final String[][] brickField;
	
	public FieldPanel(final ButtonGroup group) {
		this.group = group;
		brickField = new String[FIELD_ROWS][FIELD_COLUMNS];
		
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
					brickField[brickY][brickX] = getBrickText();
					printField();
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
	
	private final String getBrickText() {
		String sel = selected.getActionCommand();
		if (sel.equals("removeBrick")) {
			return null;
		} else {
			return sel;
		}
	}
	
	private void printField() {
		for(final String[] i : brickField) {
			String line ="";
			for (final String j : i) {
				line += j+" ";
			}
			System.out.println(line);
		}
	}
	
	@Override
	public final void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		for (int j = 0; j < FIELD_ROWS; j++) {
			//g2d.drawLine(0, j * (BRICK_HEIGHT-1), getWidth(), j * (BRICK_HEIGHT-1));
			for (int i = 0; i < FIELD_COLUMNS; i++) {
				//g2d.drawLine((BRICK_WIDTH-1) * i, 0, (BRICK_WIDTH-1)*i, getHeight());
				g2d.drawImage(ImagesReference.getImage("removeBrick"), BRICK_WIDTH*i, BRICK_HEIGHT*j,this);
			}
		}
		
		for (int i = 0; i<FIELD_ROWS; i++) {
			for (int j=0; j<FIELD_COLUMNS; j++) {
				String brick = brickField[i][j];
				if (brick != null) {
					g2d.drawImage(ImagesReference.getImage(brick), BRICK_WIDTH*j, BRICK_HEIGHT*i,this);
				}
			}
		}
		
		selected = group.getSelection();
		if (selected != null) {
			g2d.drawImage(ImagesReference.getImage(selected.getActionCommand()), BRICK_WIDTH*brickX, BRICK_HEIGHT*brickY, this);
		}
	}

}
