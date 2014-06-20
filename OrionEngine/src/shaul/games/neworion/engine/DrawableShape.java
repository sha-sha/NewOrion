package shaul.games.neworion.engine;

import shaul.games.neworion.engine.math.Vector2;

public final class DrawableShape {
  public enum Type {
    CIRCLE
  };

  private final Vector2 pos;
  private final Type type;
  private final double radius;
  private final int color;
  private final float opacity;

  private DrawableShape(Builder builder) {
    type = Precondition.checkNotNull(builder.type);
    pos = Precondition.checkNotNull(builder.pos);
    radius = builder.radius;
    color = builder.color;
    opacity = builder.opacity;
  }

  public Builder buildUpon() {
    return new Builder().setType(type).setPosition(pos).setRadius(radius)
        .setColor(color);
  }

  public Vector2 getPosition() {
    return pos;
  }

  public Type getType() {
    return type;
  }

  public double getRadius() {
    return radius;
  }

  public int getColor() {
    return color;
  }

  public float getOpacity() {
    return opacity;
  }

  public static class Builder {
    Vector2 pos;
    Type type;
    double radius;
    int color;
    float opacity = 1.0f;

    Builder setType(Type type) {
      this.type = type;
      return this;
    }

    Builder setPosition(Vector2 pos) {
      this.pos = pos;
      return this;
    }

    Builder setRadius(double radius) {
      this.radius = radius;
      return this;
    }

    Builder setColor(int color) {
      this.color = color;
      return this;
    }

    Builder setOpacity(float opacity) {
      this.opacity = opacity;
      return this;
    }

    DrawableShape build() {
      return new DrawableShape(this);
    }

  }

}
