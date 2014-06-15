package shaul.games.neworion.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import shaul.games.neworion.engine.math.Vector2;

public class DebugGraphicLayer {

  private static DebugGraphicLayer instance;

  private List<Shape> shapes;
  private long currentTime;

  public static DebugGraphicLayer get() {
    if (instance == null) {
      instance = new DebugGraphicLayer();
    }
    return instance;
  }

  private DebugGraphicLayer() {
    shapes = new ArrayList<Shape>();
    currentTime = 0;
  }

  public void drawAll(Graphics2D g, Screen screen, long deltaTimeMsec) {
    currentTime += deltaTimeMsec;
    ListIterator<Shape> it = shapes.listIterator();
    while (it.hasNext()) {
      Shape shape = it.next();
      if (shape.expiration <= currentTime) {
        it.remove();
      } else if (shape.expiration <= currentTime + 1000) {
        g.setColor(shape.transparentColor);
      } else {
        g.setColor(shape.color);
      }
      int x = screen.getX(shape.pos);
      int y = screen.getY(shape.pos);
      int radius = screen.getScaledSize(shape.radius);
      g.drawArc(x - radius, y - radius, radius * 2, radius * 2, 0, 360);
    }
  }

  public void addCircle(Vector2 position, double radius, Color color,
      long durationMSec) {
    Shape shape = new Shape();
    shape.type = Shape.Type.CIRCLE;
    shape.pos = position;
    shape.radius = radius;
    shape.color = color;
    shape.transparentColor = new Color(color.getRed(), color.getGreen(),
        color.getBlue(), color.getAlpha() / 2);
    shape.expiration = currentTime + durationMSec;
    shapes.add(shape);
  }

  private static class Shape {
    enum Type {
      CIRCLE
    };

    Vector2 pos;
    Type type;
    double radius;
    Color color;
    Color transparentColor;
    long expiration;

  }

}
