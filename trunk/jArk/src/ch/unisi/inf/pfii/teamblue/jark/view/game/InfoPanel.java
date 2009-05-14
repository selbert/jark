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

import ch.unisi.inf.pfii.teamblue.jark.implementation.PlayerListener;
import ch.unisi.inf.pfii.teamblue.jark.model.Game;

/**
 * The info panel gives the player informations (score, lives, ..)
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 * 
 */

@SuppressWarnings("serial")
public final class InfoPanel extends JPanel {
	
	private JLabel lives;
	private JLabel level;
	private JLabel score;
	
	public InfoPanel(Game game) {
		setLayout(new BoxLayout(InfoPanel.this, BoxLayout.Y_AXIS));
		setBorder(BorderFactory.createLineBorder(Color.black));
		
		game.getPlayer().addPlayerListener(new PlayerListener() {
			public void modifiedLives(int l) {
				lives.setText(""+l);
			}

			public void modifiedScore(int s) {
				score.setText(""+s);
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
		
		level = new JLabel(game.getLevel().getName());
		level.setAlignmentX(CENTER_ALIGNMENT);
		tool.add(level);
		
		tool.addSeparator();

		JLabel livesTitle = new JLabel("Lives");
		livesTitle.setFont(new Font(getFont().getFamily(), Font.PLAIN, 12));
		livesTitle.setAlignmentX(CENTER_ALIGNMENT);
		tool.add(livesTitle);
		
		tool.addSeparator(new Dimension(0,5));
		
		lives = new JLabel(""+game.getPlayer().getLives());
		lives.setAlignmentX(CENTER_ALIGNMENT);
		tool.add(lives);
		
		tool.addSeparator();

		JLabel scoreTitle = new JLabel("Score");
		scoreTitle.setFont(new Font(getFont().getFamily(), Font.PLAIN, 12));
		scoreTitle.setAlignmentX(CENTER_ALIGNMENT);
		tool.add(scoreTitle);
		
		tool.addSeparator(new Dimension(0,5));
		
		score = new JLabel(""+game.getPlayer().getScore());
		score.setAlignmentX(CENTER_ALIGNMENT);
		tool.add(score);
		
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
