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

	private final JLabel livesLabel;
	private final JLabel levelLabel;
	private final JLabel scoreLabel;
	private final JLabel timeLabel;

	public InfoPanel(final Game game) {
		setLayout(new BoxLayout(InfoPanel.this, BoxLayout.Y_AXIS));
		setBorder(BorderFactory.createLineBorder(Color.black));

		game.getPlayer().addPlayerListener(new PlayerListener() {
			public void modifiedLives(final int l) {
				livesLabel.setText("" + l);
			}

			public void modifiedScore(final int s) {
				scoreLabel.setText("" + s);
			}

			public void modifiedTime(final int t) {
				final int m = (t / 60);
				final int s = t % 60;
				timeLabel.setText(((m < 10) ? "0" : "") + m + ":"
						+ ((s < 10) ? "0" : "") + s);
			}
		});

		game.addGameListener(new GameListener() {

			public void arcadeCleared() {
			}

			public void bonusErase() {
			}

			public void gameOver() {
			}

			public void levelChanged(final Level level) {
				levelLabel.setText(level.getName());
			}

			public void levelCleared(final Level level) {
			}
		});

		final JToolBar tool = new JToolBar(SwingConstants.VERTICAL);
		tool.setFloatable(false);

		tool.addSeparator(new Dimension(0, 5));

		final JLabel title = new JLabel("Informations");
		title.setForeground(Color.BLUE);
		title.setAlignmentX(CENTER_ALIGNMENT);
		tool.add(title);

		tool.addSeparator(new Dimension(0, 10));

		final JLabel levelTitle = new JLabel("Level");
		levelTitle.setFont(new Font(getFont().getFamily(), Font.PLAIN, 12));
		levelTitle.setAlignmentX(CENTER_ALIGNMENT);
		tool.add(levelTitle);

		tool.addSeparator(new Dimension(0, 5));

		levelLabel = new JLabel(game.getLevel().getName());
		levelLabel.setAlignmentX(CENTER_ALIGNMENT);
		tool.add(levelLabel);

		tool.addSeparator();

		final JLabel livesTitle = new JLabel("Lives");
		livesTitle.setFont(new Font(getFont().getFamily(), Font.PLAIN, 12));
		livesTitle.setAlignmentX(CENTER_ALIGNMENT);
		tool.add(livesTitle);

		tool.addSeparator(new Dimension(0, 5));

		livesLabel = new JLabel("" + game.getPlayer().getLives());
		livesLabel.setAlignmentX(CENTER_ALIGNMENT);
		tool.add(livesLabel);

		tool.addSeparator();

		final JLabel scoreTitle = new JLabel("Score");
		scoreTitle.setFont(new Font(getFont().getFamily(), Font.PLAIN, 12));
		scoreTitle.setAlignmentX(CENTER_ALIGNMENT);
		tool.add(scoreTitle);

		tool.addSeparator(new Dimension(0, 5));

		scoreLabel = new JLabel("" + game.getPlayer().getScore());
		scoreLabel.setAlignmentX(CENTER_ALIGNMENT);
		tool.add(scoreLabel);

		tool.addSeparator();

		final JLabel timeTitle = new JLabel("Time");
		timeTitle.setFont(new Font(getFont().getFamily(), Font.PLAIN, 12));
		timeTitle.setAlignmentX(CENTER_ALIGNMENT);
		tool.add(timeTitle);

		tool.addSeparator(new Dimension(0, 5));

		timeLabel = new JLabel("");
		final int t = (game.getPlayer().getTime() / 1000);
		final int m = (t / 60);
		final int s = t % 60;
		timeLabel.setText(((m < 10) ? "0" : "") + m + ":"
				+ ((s < 10) ? "0" : "") + s);
		timeLabel.setAlignmentX(CENTER_ALIGNMENT);
		tool.add(timeLabel);

		tool.addSeparator(new Dimension(0, 10));

		add(tool);
	}

}
