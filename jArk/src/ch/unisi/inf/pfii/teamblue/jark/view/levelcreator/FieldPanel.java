package ch.unisi.inf.pfii.teamblue.jark.view.levelcreator;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JComponent;
import javax.swing.event.MouseInputAdapter;

import ch.unisi.inf.pfii.teamblue.jark.implementation.Constants;
import ch.unisi.inf.pfii.teamblue.jark.implementation.Utils;
import ch.unisi.inf.pfii.teamblue.jark.model.level.LevelManager;
import ch.unisi.inf.pfii.teamblue.jark.view.ImagesRepository;

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
	private String[][] brickField;
	private String[][] bonusField;
	private boolean saved;
	private boolean isPrintScreen;
	private boolean noBonus;
	private boolean bricksOverride; 
	
	private final LevelManager levelManager;
	public FieldPanel(final LevelManager levelManager, final ButtonGroup group) {
		saved = true;
		bricksOverride = true;
		this.levelManager = levelManager;
		this.group = group;
		brickField = levelManager.getBrickField();
		bonusField = levelManager.getBonusField();
		
		setBounds(1,1,FIELD_WIDTH, FIELD_HEIGHT);
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
					String tempBrickString = brickField[brickY][brickX];
					if (tempBrickString != null && !bricksOverride && selected.getActionCommand().contains("Brick")) {
						return;
					}
					saved = false;
					if (selected.getActionCommand().contains("Brick")) {
						brickField[brickY][brickX] = getBrickText();
						bonusField[brickY][brickX] = null;
					} else if (tempBrickString != null && !tempBrickString.contains("persistent")) {
						bonusField[brickY][brickX] = getBrickText();
					}
					repaint();
				}
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
			}
			
			@Override
			public void mouseDragged(MouseEvent ev) {
				super.mouseDragged(ev);
				if (paintingAllowed) {
					mouseMoved(ev);
					mousePressed(ev);
				}
			}
			
			@Override
			public void mouseEntered(MouseEvent ev) {
				super.mouseEntered(ev);
				paintingAllowed = true;
				setCursor(new Cursor(Cursor.HAND_CURSOR));
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
	
	
	@Override
	public final void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setColor(new Color(0xb0c4de));
		g2d.fillRect(0,0,getWidth(),getHeight());
		for (int j = 0; j < FIELD_ROWS; j++) {
			for (int i = 0; i < FIELD_COLUMNS; i++) {
				g2d.drawImage(ImagesRepository.getImage("editorBrick"), BRICK_WIDTH*i, BRICK_HEIGHT*j,this);
			}
		}

		for (int i = 0; i<FIELD_ROWS; i++) {
			for (int j=0; j<FIELD_COLUMNS; j++) {
				String brick = brickField[i][j];
				String bonus = bonusField[i][j];
				if (brick != null) {
					g2d.drawImage(ImagesRepository.getImage(brick), BRICK_WIDTH*j, BRICK_HEIGHT*i,this);
					if (bonus != null && !isPrintScreen && !noBonus) {
						g2d.drawImage(ImagesRepository.getImage(bonus), BRICK_WIDTH*j+4, BRICK_HEIGHT*i+4, 34, 15, this);
					}
				}
			}
		}
		
		selected = group.getSelection();
		if (selected != null && paintingAllowed && !isPrintScreen) {
			if (!noBonus || selected.getActionCommand().contains("Brick")) {
				g2d.drawImage(ImagesRepository.getImage(selected.getActionCommand()), BRICK_WIDTH*brickX, BRICK_HEIGHT*brickY, this);
			}
		}
	}

	public void setSaved(final boolean saved) {
		this.saved = saved;
	}
	public Boolean hasBeenSaved() {
		return saved;
	}
	
	public final void reset() { 
		brickField = levelManager.getBrickField();
		bonusField = levelManager.getBonusField();
	}

	public final void togglePanelForPrintScreen() {
		isPrintScreen = (isPrintScreen) ? false : true;
	}
	
	public final void setBonusDisplay(final boolean display) {
		this.noBonus = !display;
	}

	public final void setBricksOverride(boolean bricksOverride) {
		this.bricksOverride = bricksOverride;
	}

}
