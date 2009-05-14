package ch.unisi.inf.pfii.teamblue.jark.view;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.RGBImageFilter;
import java.io.File;
import java.util.HashMap;

import javax.swing.GrayFilter;
import javax.swing.ImageIcon;

/**
 * This class stores all the images of the game trough its constructor, 
 * and when an image is needed it gives the reference to that image 
 * (so that there is only one object for each image)
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 * 
 */

public final class ImagesReference {
	private static HashMap<String, ImageIcon> images;
	
	public ImagesReference() {
		images = new HashMap<String, ImageIcon>();
		//bricks
		images.put("defaultBrick", setImage("images/bricks/defaultBrick.png"));
		images.put("persistentBrick", setImage("images/bricks/persistentBrick2.png"));
		images.put("resistentBrick1", setImage("images/bricks/resistentBrick1.png"));
		images.put("resistentBrick", setImage("images/bricks/resistentBrick2.png"));
		images.put("veryResistentBrick1", setImage("images/bricks/veryResistentBrick1.png"));
		images.put("veryResistentBrick2", setImage("images/bricks/veryResistentBrick2.png"));
		images.put("veryResistentBrick", setImage("images/bricks/veryResistentBrick3.png"));
		images.put("editorBrick", setImage("images/bricks/editorBrick.png"));
		images.put("removeBrick", setImage("images/bricks/removeBrick.png"));
		//vauses
		images.put("defaultVaus", setImage("images/vauses/defaultVaus.png"));
		images.put("longVaus", setImage("images/vauses/longVaus.png"));
		images.put("shortVaus", setImage("images/vauses/shortVaus.png"));
		images.put("rifleVaus", setImage("images/vauses/defaultVaus.png"));
		images.put("doubleRifleVaus", setImage("images/vauses/defaultVaus.png"));
		images.put("rocketLauncherVaus", setImage("images/vauses/defaultVaus.png"));
		//ammunition
		images.put("rocket", setImage("images/rocket.png"));
		images.put("bullet", setImage("images/bullet.png"));
		//balls
		images.put("defaultBall", setImage("images/balls/defaultBall.png"));
		images.put("boxBall", setImage("images/balls/defaultBall.png"));
		images.put("explosionBall", setImage("images/balls/explosionBall.png"));
		images.put("fastBall", setImage("images/balls/defaultBall.png"));
		images.put("rubberBall", setImage("images/balls/rubberBall.png"));
		images.put("slowBall", setImage("images/balls/defaultBall.png"));
		images.put("stickyBall", setImage("images/balls/defaultBall.png"));
		images.put("ultraBall", setImage("images/balls/ultraBall.png"));
		images.put("ghostBall", setImage("images/balls/ghostBall.png"));
		images.put("startBall", setImage("images/balls/defaultBall.png"));
		//bonuses (+)
		images.put("bonus_addlife", setImage("images/bonuses/bonus_addlife.png"));
		images.put("bonus_box", setImage("images/bonuses/bonus_box.png"));
		images.put("bonus_doubleball", setImage("images/bonuses/bonus_doubleball.png"));
		images.put("bonus_doubleriflevaus", setImage("images/bonuses/bonus_doublelaservaus.png"));
		images.put("bonus_explosionball", setImage("images/bonuses/bonus_explosionball.png"));
		images.put("bonus_riflevaus", setImage("images/bonuses/bonus_laservaus.png"));
		images.put("bonus_longvaus", setImage("images/bonuses/bonus_longvaus.png"));
		images.put("bonus_cannonvaus", setImage("images/bonuses/bonus_missilevaus.png"));
		images.put("bonus_ultraball", setImage("images/bonuses/bonus_ultraball.png"));
		//bonuses (-)
		images.put("malus_falseball", setImage("images/bonuses/malus_falseball.png"));
		images.put("malus_ghostball", setImage("images/bonuses/malus_ghostball.png"));
		images.put("malus_removelife", setImage("images/bonuses/malus_removelife.png"));
		images.put("malus_shortvaus", setImage("images/bonuses/malus_shortvaus.png"));
		//bonuses (=)
		images.put("neutral_fastball", setImage("images/bonuses/neutral_fastball.png"));
		images.put("neutral_resetstatus", setImage("images/bonuses/neutral_resetstatus.png"));
		images.put("neutral_slowball", setImage("images/bonuses/neutral_slowball.png"));
		images.put("neutral_stickyball", setImage("images/bonuses/neutral_stickyball.png"));
		//others
		images.put("pause", setImage("images/pause.png"));
	}
	
	private final ImageIcon setImage(final String path) {
		return new ImageIcon(getClass().getResource("/ch/unisi/inf/pfii/teamblue/jark/view/"+path));
	}
	
	public static final HashMap<String, ImageIcon> getIcons() {
		return images;
	}
	
	public static final Image getImage(final String stringImage) {
		return images.get(stringImage).getImage();
	}
	
	public static final ImageIcon getIcon(final String stringImage) {
		return images.get(stringImage);
	}
	
	public static final ImageIcon getHighlightedIcon(ImageIcon icon) {
		Image image = icon.getImage();
		ImageFilter ifua = new RGBImageFilter() {
			@Override
			public int filterRGB(int x, int y, int rgb) {
				int brightness = 60;

				int r = (rgb >> 16) & 0xff;
				int g = (rgb >> 8) & 0xff;
				int b = (rgb >> 0) & 0xff;

				r += (brightness * r) / 100;
				g += (brightness * g) / 100;
				b += (brightness * b) / 100;

				r = Math.min(Math.max(0, r), 255);
				g = Math.min(Math.max(0, g), 255);
				b = Math.min(Math.max(0, b), 255);

				return (rgb & 0xff000000) | (r << 16) | (g << 8) | (b << 0);
			}
		};
		FilteredImageSource filteredImage = new FilteredImageSource(image.getSource(), ifua);
		Image newImage = Toolkit.getDefaultToolkit().createImage(filteredImage);
	    return new ImageIcon(newImage);
	}
}
