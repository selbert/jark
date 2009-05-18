package ch.unisi.inf.pfii.teamblue.jark.view.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import ch.unisi.inf.pfii.teamblue.jark.implementation.GameListener;
import ch.unisi.inf.pfii.teamblue.jark.implementation.PlayerListener;
import ch.unisi.inf.pfii.teamblue.jark.model.Game;
import ch.unisi.inf.pfii.teamblue.jark.model.level.Level;

/**
 * The info panel gives the player informations (score, lives, ..)
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 * 
 */

@SuppressWarnings("serial")
public final class InfoPanel extends JPanel {
	
	private JLabel livesLabel;
	private JLabel levelLabel;
	private JLabel scoreLabel;
	
	public InfoPanel(Game game) {
		setLayout(new BoxLayout(InfoPanel.this, BoxLayout.Y_AXIS));
		setBorder(BorderFactory.createLineBorder(Color.black));
		
		game.getPlayer().addPlayerListener(new PlayerListener() {
			public void modifiedLives(int l) {
				livesLabel.setText(""+l);
			}

			public void modifiedScore(int s) {
				scoreLabel.setText(""+s);
			}
		});
		
		game.addGameListener(new GameListener() {

			public void arcadeCleared() {
			}

			public void bonusErase() {
			}

			public void gameOver() {
			}

			public void levelChanged(Level level) {
				levelLabel.setText(level.getName());
			}

			public void levelCleared(Level level) {
			}
		});
		
		JToolBar tool = new JToolBar(SwingConstants.VERTICAL);
		tool.setFloatable(false);
		
		tool.addSeparator(new Dimension(0,5));
		
	    JLabel title = new JLabel("Informations");
		title.setForeground(Color.BLUE);
		title.setAlignmentX(CENTER_ALIGNMENT);
		tool.add(title);
		
		tool.addSeparator(new Dimension(0,10));
		
		JLabel levelTitle = new JLabel("Level");
		levelTitle.setFont(new Font(getFont().getFamily(), Font.PLAIN, 12));
		levelTitle.setAlignmentX(CENTER_ALIGNMENT);
		tool.add(levelTitle);
		
		tool.addSeparator(new Dimension(0,5));
		
		levelLabel = new JLabel(game.getLevel().getName());
		levelLabel.setAlignmentX(CENTER_ALIGNMENT);
		tool.add(levelLabel);
		
		tool.addSeparator();

		JLabel livesTitle = new JLabel("Lives");
		livesTitle.setFont(new Font(getFont().getFamily(), Font.PLAIN, 12));
		livesTitle.setAlignmentX(CENTER_ALIGNMENT);
		tool.add(livesTitle);
		
		tool.addSeparator(new Dimension(0,5));
		
		livesLabel = new JLabel(""+game.getPlayer().getLives());
		livesLabel.setAlignmentX(CENTER_ALIGNMENT);
		tool.add(livesLabel);
		
		tool.addSeparator();

		JLabel scoreTitle = new JLabel("Score");
		scoreTitle.setFont(new Font(getFont().getFamily(), Font.PLAIN, 12));
		scoreTitle.setAlignmentX(CENTER_ALIGNMENT);
		tool.add(scoreTitle);
		
		tool.addSeparator(new Dimension(0,5));
		
		scoreLabel = new JLabel(""+game.getPlayer().getScore());
		scoreLabel.setAlignmentX(CENTER_ALIGNMENT);
		tool.add(scoreLabel);
		
		tool.addSeparator();
		
		JLabel timeTitle = new JLabel("Time");
		timeTitle.setFont(new Font(getFont().getFamily(), Font.PLAIN, 12));
		timeTitle.setAlignmentX(CENTER_ALIGNMENT);
		tool.add(timeTitle);
		
		tool.addSeparator(new Dimension(0,5));
		
		JLabel time = new JLabel("00:32:12");
		time.setAlignmentX(CENTER_ALIGNMENT);
		tool.add(time);
	
		tool.addSeparator(new Dimension(0,10));
		
	
		add(tool);
	}
	
}
