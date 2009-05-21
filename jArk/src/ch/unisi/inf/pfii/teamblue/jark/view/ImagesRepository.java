package ch.unisi.inf.pfii.teamblue.jark.view;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.RGBImageFilter;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.swing.ImageIcon;

/**
 * Images Repository - lazy loading of the images (after an image is loaded the
 * first time it is stored) Images path mapping is done trough the
 * paths.properties file.
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 * 
 */

public final class ImagesRepository {
	private final static Map<String, ImageIcon> images = new HashMap<String, ImageIcon>();
	private final static Properties properties = new Properties();

	public ImagesRepository() {
		try {
			properties.load(ImagesRepository.class.getResourceAsStream("paths.properties"));
		} catch (final IOException e) {
			System.out.println("Missing paths file.");
		}
	}

	public final static Enumeration<Object> getKeys() {
		return properties.keys();
	}

	public final static ImageIcon getIcon(final String path) {
		if (!images.containsKey(path)) {
			images.put(path, fetchImage(path));
		}
		return images.get(path);
	}

	private final static ImageIcon fetchImage(final String path) {
		final String imagePath = "images/" + properties.getProperty(path);
		try {
			return new ImageIcon(ImagesRepository.class.getResource(imagePath));
		} catch (final NullPointerException ex) {
			try {
				return new ImageIcon(ImagesRepository.class.getResource("images/"+path+".png"));
			} catch (NullPointerException exx) {
				return new ImageIcon(ImagesRepository.class.getResource("images/noimage.png"));
			}
		}
	}

	public final static Image getImage(final String path) {
		return getIcon(path).getImage();
	}

	public final static ImageIcon getHighlightedIcon(final ImageIcon icon) {
		final Image image = icon.getImage();
		final ImageFilter filter = new RGBImageFilter() {
			@Override
			public int filterRGB(final int x, final int y, final int rgb) {
				final int brightness = 60;

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
		final FilteredImageSource filteredImage = new FilteredImageSource(image
				.getSource(), filter);
		final Image newImage = Toolkit.getDefaultToolkit().createImage(
				filteredImage);
		return new ImageIcon(newImage);
	}

}
