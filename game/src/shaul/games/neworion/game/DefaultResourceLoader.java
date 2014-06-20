package shaul.games.neworion.game;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import shaul.games.neworion.engine.ResourceLoader;
import shaul.games.neworion.engine.Texture;

public class DefaultResourceLoader implements ResourceLoader {
  private static DefaultResourceLoader instance;

  private Map<String, Image> images = new HashMap<String, Image>();

  public static DefaultResourceLoader get() {
    if (instance == null) {
      instance = new DefaultResourceLoader();
    }
    return instance;
  }

  // public static Image loadImage(String ref) {
  // return get().loadImageInternal(ref);
  // }

  @Override
  public Texture loadImage(String resourceId) {
    Image image = loadImageInternal(resourceId);
    return new DefaultTexture(image);
  }

  private Image loadImageInternal(String ref) {
    if (images.get(ref) != null) {
      return images.get(ref);
    }

    BufferedImage sourceImage = null;
    try {
      URL url = this.getClass().getResource(ref);
      if (url == null) {
        fail("Can't find ref: " + ref);
      }
      sourceImage = ImageIO.read(url);
    } catch (IOException e) {
      fail("Failed to load: " + ref);
    }
    GraphicsConfiguration gc = GraphicsEnvironment
        .getLocalGraphicsEnvironment().getDefaultScreenDevice()
        .getDefaultConfiguration();
    Image image = gc.createCompatibleImage(sourceImage.getWidth(),
        sourceImage.getHeight(), Transparency.BITMASK);
    image.getGraphics().drawImage(sourceImage, 0, 0, null);
    images.put(ref, image);

    return image;
  }

  private void fail(String message) {
    System.err.println(message);
    System.exit(0);
  }

}
