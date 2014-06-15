package shaul.games.neworion.engine.math;

public final class Vector2 {

  public static final Vector2 ZERO = new Vector2();

  private final float x;
  private final float y;
  
  private Vector2() {
    this(0, 0);
  }

  public Vector2(float x, float y) {
    this.x = x;
    this.y = y;
  }

  public boolean isZero() {
    return x == 0f && y == 0f;
  }
  
  public float getX() {
    return x;
  }

  public float getY() {
    return y;
  }
  
  public Vector2 add(Vector2 other) {
    return new Vector2(x + other.x, y + other.y);
  }
  
  public float multiply(Vector2 other) {
    return x * other.x + y * other.y;
  }

  public Vector2 multiply(float other) {
    return new Vector2(x * other, y * other);
  }

  public Vector2 add(Vector2 other, float factor) {
    return new Vector2(x + other.x * factor, y + other.y * factor);
  }
  

}
