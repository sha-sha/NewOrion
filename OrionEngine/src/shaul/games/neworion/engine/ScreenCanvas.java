package shaul.games.neworion.engine;

import shaul.games.neworion.engine.math.Vector2;

public class ScreenCanvas {

  private final Canvas canvas;
  private final Screen screen;

  public ScreenCanvas(Canvas canvas, Screen screen) {
    this.canvas = canvas;
    this.screen = screen;
  }

  public void draw(Vector2 position, Texture image) {
    draw(position.getX(), position.getY(), image);
  }

  public void draw(float xPos, float yPos, Texture image) {
    int x = screen.getX(xPos);
    int y = screen.getY(yPos);
    int maxSize = Math.max(image.getWidth(), image.getHeight());
    if (x + maxSize < 0 || y + maxSize < 0) {
      return;
    }
    if (x > screen.getWidth() || y > screen.getHeight()) {
      return;
    }
    canvas.drawImage(x, y, image);
  }
}