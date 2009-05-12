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
import javax.swing.border.EtchedBorder;
import javax.swing.event.MouseInputAdapter;

import ch.unisi.inf.pfii.teamblue.jark.implementation.Constants;
import ch.unisi.inf.pfii.teamblue.jark.implementation.Utils;
import ch.unisi.inf.pfii.teamblue.jark.model.level.LevelManager;
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
	private boolean paintingAllowed;
	private ButtonGroup group;
	private ButtonModel selected;
	private final String[][] brickField;
	private final String[][] bonusField;
	
	public FieldPanel(final LevelManager levelManager, final ButtonGroup group) {
		this.group = group;
		brickField = levelManager.getBrickField();
		bonusField = levelManager.getBonusField();
		
		setBounds(1,1,798, 400);
		//setPreferredSize(new Dimension(798, 400));
		setFocusable(true);
		//setBorder(new EtchedBorder());
		
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
					if (selected.getActionCommand().contains("Brick")) {
						brickField[brickY][brickX] = getBrickText();
						bonusField[brickY][brickX] = null;
					} else if (brickField[brickY][brickX] != null && !brickField[brickY][brickX].contains("persistent")) {
						bonusField[brickY][brickX] = getBrickText();
					}
				}
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				//printField(brickField);
				//printField(bonusField);
			}
			
			@Override
			public void mouseDragged(MouseEvent ev) {
				super.mouseDragged(ev);
				mouseMoved(ev);
				mousePressed(ev);
			}
			
			@Override
			public void mouseEntered(MouseEvent ev) {
				super.mouseEntered(ev);
				paintingAllowed = true;
			}
			@Override
			public void mouseExited(MouseEvent e) {
				super.mouseExited(e);
				paintingAllowed = false;
				repaint();
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
	
	private void printField(String[][] field) {
		for(final String[] i : field) {
			String line ="";
			for (final String j : i) {
				line += j+" ";
			}
			System.out.println(line);
		}
		System.out.println( );
	}
	
	@Override
	public final void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setColor(new Color(0xb0c4de));
		g2d.fillRect(0,0,getWidth(),getHeight());
		for (int j = 0; j < FIELD_ROWS; j++) {
			for (int i = 0; i < FIELD_COLUMNS; i++) {
				g2d.drawImage(ImagesReference.getImage("editorBrick"), BRICK_WIDTH*i, BRICK_HEIGHT*j,this);
			}
		}

		for (int i = 0; i<FIELD_ROWS; i++) {
			for (int j=0; j<FIELD_COLUMNS; j++) {
				String brick = brickField[i][j];
				String bonus = bonusField[i][j];
				if (brick != null) {
					g2d.drawImage(ImagesReference.getImage(brick), BRICK_WIDTH*j, BRICK_HEIGHT*i,this);
					if (bonus != null) {
						g2d.drawImage(ImagesReference.getImage(bonus), BRICK_WIDTH*j+4, BRICK_HEIGHT*i+4, 34, 15, this);
					}
				}
			}
		}
		
		selected = group.getSelection();
		if (selected != null && paintingAllowed) {
			g2d.drawImage(ImagesReference.getImage(selected.getActionCommand()), BRICK_WIDTH*brickX, BRICK_HEIGHT*brickY, this);
		}
	}

}
