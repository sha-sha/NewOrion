package shaul.games.neworion.game;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;

import shaul.games.neworion.engine.Texture;

public class DefaultTexture implements Texture {

  private final Image image;

  public DefaultTexture(Image image) {
    this.image = image;
  }

  public void draw(Graphics2D g, int x, int y) {
    AffineTransform tx = new AffineTransform();
    tx.translate(x, y);
    g.drawImage(image, tx, null);
  }

  public void draw(Graphics2D g, int x, int y, double rotate) {
    AffineTransform tx = new AffineTransform();
    tx.translate(x, y);
    // tx.rotate(rotate, getWidth() / 2, getHeight() / 2);
    g.drawImage(image, tx, null);
  }

  @Override
  public int getWidth() {
    return image.getWidth(null);
  }

  @Override
  public int getHeight() {
    return image.getHeight(null);
  }

}
