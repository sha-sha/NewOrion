package shaul.games.neworion.engine;

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

  public void drawAll(ShapeDrawer shapeDrawer, Screen screen, long deltaTimeMsec) {
    currentTime += deltaTimeMsec;
    ListIterator<Shape> it = shapes.listIterator();
    while (it.hasNext()) {
      Shape shape = it.next();
      if (shape.expiration <= currentTime) {
        it.remove();
      } else if (shape.expiration <= currentTime + 1000) {
        shapeDrawer.drawShape(shape.semiTransparentShape, screen);
      } else {
        shapeDrawer.drawShape(shape.visibleShape, screen);
      }
    }
  }

  public void addCircle(Vector2 position, double radius, int color,
      long durationMSec) {
    Shape shape = new Shape();
    shape.visibleShape = new DrawableShape.Builder()
        .setType(DrawableShape.Type.CIRCLE).setPosition(position)
        .setRadius(radius).setColor(color).build();
    shape.semiTransparentShape = shape.visibleShape.buildUpon()
        .setOpacity(0.5f)
        .build();
    shape.expiration = currentTime + durationMSec;
    shapes.add(shape);
  }

  private static class Shape {
    DrawableShape visibleShape;
    DrawableShape semiTransparentShape;
    long expiration;
  }
}
