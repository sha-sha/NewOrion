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
      if (shape.expiration <= currentTime && shape.expiration > 0) {
        it.remove();
      } else {
        shapeDrawer.drawShape(shape.drawableShape, screen);
      }
    }
  }

  public Shape addText(Vector2 position, String text, int color, long durationMSec) {
    return addDrawableShape(new DrawableShape.Builder().setType(DrawableShape.Type.TEXT)
        .setPosition(position).setText(text).setColor(color).build(), durationMSec);
  }

  public Shape addCircle(Vector2 position, double radius, int color, long durationMSec) {
    return addDrawableShape(new DrawableShape.Builder().setType(DrawableShape.Type.CIRCLE)
        .setPosition(position).setRadius(radius).setColor(color).build(), durationMSec);
  }

  public Shape addDrawableShape(DrawableShape drawableShape, long durationMSec) {
    Shape shape = new Shape(drawableShape, durationMSec == 0 ? 0 : currentTime + durationMSec);
    shapes.add(shape);
    return shape;
  }

  public void removeShape(Shape shape) {
    shapes.remove(shape);
  }

  public static class Shape {

    public final DrawableShape drawableShape;
    public final long expiration;

    public Shape(DrawableShape drawableShape, long expiration) {
      this.drawableShape = drawableShape;
      this.expiration = expiration;
    }
  }
}
