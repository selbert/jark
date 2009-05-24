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

	/**
	 * Constructor: load the properties with images paths
	 */
	public ImagesRepository() {
		try {
			properties.load(ImagesRepository.class.getResourceAsStream("paths.properties"));
		} catch (final IOException e) {
			System.out.println("Missing paths file.");
		}
	}
	
	/**
	 * Get an enumeration of keys (images names)
	 * @return images names in properties file
	 */
	public final static Enumeration<Object> getKeys() {
		return properties.keys();
	}
	
	/**
	 * Given an image key returns the ImageIcon, 
	 * if the HashMap doesn't contain the image, it puts it in.
	 * @param key
	 * @return ImageIcon
	 */
	public final static ImageIcon getIcon(final String key) {
		if (!images.containsKey(key)) {
			images.put(key, fetchImage(key));
		}
		return images.get(key);
	}
	
	/**
	 * Compute the image complete path and creates the actual imageicon,
	 * This method will not be called twice for the same image.
	 * 
	 * @param image file path
	 * @return ImageIcon to store into HashMap
	 */
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
	
	/**
	 * Get an Image instead of an ImageIcon
	 * @param path
	 * @return Image
	 */
	public final static Image getImage(final String path) {
		return getIcon(path).getImage();
	}
	
	/**
	 * Given an ImageIcon returns the same image with some filter applied.
	 * In this case we increase the brightness.
	 * 
	 * @param icon
	 * @return modified icon
	 */
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
