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
    tx.translate(x, x);
    g.drawImage(image, tx, null);
  }

  @Override
  public int getWidth() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int getHeight() {
    // TODO Auto-generated method stub
    return 0;
  }

}
