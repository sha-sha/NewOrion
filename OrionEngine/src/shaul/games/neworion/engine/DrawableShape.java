package shaul.games.neworion.engine;

import shaul.games.neworion.engine.math.Vector2;

public final class DrawableShape {
  public enum Type {
    CIRCLE, TEXT
  };

  private final Vector2 pos;
  private final Type type;
  private final double radius;
  private final int color;
  private final float opacity;
  private final String text;

  private DrawableShape(Builder builder) {
    type = Precondition.checkNotNull(builder.type);
    pos = Precondition.checkNotNull(builder.pos);
    text = builder.text;
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

  public String getText() {
    return text;
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
    private String text;

    public Builder setType(Type type) {
      this.type = type;
      return this;
    }

    public Builder setPosition(Vector2 pos) {
      this.pos = pos;
      return this;
    }

    public Builder setPosition(float x, float y) {
      this.pos = new Vector2(x, y);
      return this;
    }

    public Builder setRadius(double radius) {
      this.radius = radius;
      return this;
    }

    public Builder setColor(int color) {
      this.color = color;
      return this;
    }

    public Builder setOpacity(float opacity) {
      this.opacity = opacity;
      return this;
    }

    public Builder setText(String text) {
      this.text = text;
      return this;
    }

    public DrawableShape build() {
      return new DrawableShape(this);
    }

  }

}
