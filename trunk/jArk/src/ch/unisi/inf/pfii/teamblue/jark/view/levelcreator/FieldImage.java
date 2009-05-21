package ch.unisi.inf.pfii.teamblue.jark.view.levelcreator;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import ch.unisi.inf.pfii.teamblue.jark.implementation.Constants;

public class FieldImage implements Constants {
	private final FieldPanel field;

	public FieldImage(final FieldPanel field) {
		this.field = field;
	}

	public final BufferedImage getScaledImage() {
		BufferedImage image = new BufferedImage(GAME_WIDTH, FIELD_HEIGHT,
				BufferedImage.TYPE_INT_RGB);
		field.paintComponent(image.createGraphics());
		image = scale(image, 0.5);
		return image;
	}

	public final void saveImage(final String path) {
		try {
			final BufferedImage bi = getScaledImage();
			final File outputfile = new File("levels/" + path + ".png");
			ImageIO.write(bi, "png", outputfile);
		} catch (final IOException e) {
			// do nothing
		}
	}

	private final static BufferedImage scale(final BufferedImage source,
			final double factor) {
		final BufferedImage bi = new BufferedImage(source.getWidth() / 2,
				source.getHeight() / 2, BufferedImage.TYPE_INT_RGB);
		final Graphics2D g2d = bi.createGraphics();

		final AffineTransform at = AffineTransform.getScaleInstance(factor,
				factor);
		g2d.drawRenderedImage(source, at);
		g2d.dispose();
		return bi;
	}

}
