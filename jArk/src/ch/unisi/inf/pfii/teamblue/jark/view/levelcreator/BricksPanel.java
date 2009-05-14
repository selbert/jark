package ch.unisi.inf.pfii.teamblue.jark.view.levelcreator;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import ch.unisi.inf.pfii.teamblue.jark.view.ImagesReference;

/**
 * The info panel gives the player informations (score, lives, ..)
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 * 
 */

@SuppressWarnings("serial")
public final class BricksPanel extends JPanel {
	
	public BricksPanel(ButtonGroup group) {
		setLayout(new GridLayout(0,1));
		
		JLabel bricks = new JLabel("Bricks", SwingConstants.CENTER);
		add(bricks);
		
		final String[] str = new String[] { "defaultBrick", "resistentBrick", "veryResistentBrick", "persistentBrick", "removeBrick"  };
	
		for (int i = 0; i<str.length; i++) {
			final ImageIcon im = ImagesReference.getIcon(str[i]);
			final ImageIcon him = ImagesReference.getHighlightedIcon(im);
			 JRadioButton button = new JRadioButton(im);
			button.setSelectedIcon(him);
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//TODO
				}
			});
			button.setHorizontalAlignment(SwingConstants.CENTER);
			button.setActionCommand(str[i]);
			button.setToolTipText(str[i]);
			
			group.add(button);
			add(button);
		}
		
		

	
		
	
	}
	
}
