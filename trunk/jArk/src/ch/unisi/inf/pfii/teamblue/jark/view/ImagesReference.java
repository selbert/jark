package ch.unisi.inf.pfii.teamblue.jark.view;

import java.awt.Image;
import java.util.HashMap;

import javax.swing.ImageIcon;
	
public class ImagesReference {
	private final HashMap<String, Image> images;
	
	public ImagesReference() {
		images = new HashMap<String, Image>();
		//bricks
		images.put("defaultBrick", setImage("images/bricks/defaultBrick.png"));
		images.put("persistentBrick", setImage("images/bricks/persistentBrick2.png"));
		images.put("resistentBrick1", setImage("images/bricks/resistentBrick1.png"));
		images.put("resistentBrick2", setImage("images/bricks/resistentBrick2.png"));
		images.put("veryResistentBrick1", setImage("images/bricks/veryResistentBrick1.png"));
		images.put("veryResistentBrick2", setImage("images/bricks/veryResistentBrick2.png"));
		images.put("veryResistentBrick3", setImage("images/bricks/veryResistentBrick3.png"));
		//vauses
		images.put("defaultVaus", setImage("images/vauses/defaultVaus.png"));
		images.put("longVaus", setImage("images/vauses/longVaus.png"));
		images.put("shortVaus", setImage("images/vauses/shortVaus.png"));
		//balls
		images.put("defaultBall", setImage("images/balls/defaultBall.png"));
		images.put("boxBall", setImage("images/balls/defaultBall.png"));
		images.put("explosionBall", setImage("images/balls/explosionBall.png"));
		images.put("fastBall", setImage("images/balls/defaultBall.png"));
		images.put("rubberBall", setImage("images/balls/rubberBall.png"));
		images.put("slowBall", setImage("images/balls/defaultBall.png"));
		images.put("stickyBall", setImage("images/balls/defaultBall.png"));
		images.put("ultraBall", setImage("images/balls/ultraBall.png"));
		//bonuses (+)
		images.put("bonus_addlife", setImage("images/bonuses/bonus_addlife.png"));
		images.put("bonus_box", setImage("images/bonuses/bonus_box.png"));
		images.put("bonus_doubleball", setImage("images/bonuses/bonus_doubleball.png"));
		images.put("bonus_doublelaservaus", setImage("images/bonuses/bonus_doublelaservaus.png"));
		images.put("bonus_explosionball", setImage("images/bonuses/bonus_explosionball.png"));
		images.put("bonus_laservaus", setImage("images/bonuses/bonus_laservaus.png"));
		images.put("bonus_longvaus", setImage("images/bonuses/bonus_longvaus.png"));
		images.put("bonus_missilevaus", setImage("images/bonuses/bonus_missilevaus.png"));
		images.put("bonus_ultraball", setImage("images/bonuses/bonus_ultraball.png"));
		//bonuses (-)
		images.put("malus_falseball", setImage("images/bonuses/malus_falseball.png"));
		images.put("malus_ghostball", setImage("images/bonuses/malus_ghostball.png"));
		images.put("malus_removelife", setImage("images/bonuses/malus_removelife.png"));
		images.put("malus_rubberball", setImage("images/bonuses/malus_rubberball.png"));
		images.put("malus_shortvaus", setImage("images/bonuses/malus_shortvaus.png"));
		//bonuses (=)
		images.put("neutral_fastball", setImage("images/bonuses/neutral_fastball.png"));
		images.put("neutral_resetstatus", setImage("images/bonuses/neutral_resetstatus.png"));
		images.put("neutral_slowball", setImage("images/bonuses/neutral_slowball.png"));
		images.put("neutral_stickyball", setImage("images/bonuses/neutral_stickyball.png"));
		
	}
	
	private Image setImage(String path) {
		return new ImageIcon(getClass().getResource(path)).getImage();
	}
	
	public Image getImage(String stringImage) {
		return images.get(stringImage);
	}
}
