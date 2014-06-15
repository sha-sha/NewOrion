package shaul.games.neworion.engine.math;

public final class Vector3 {
  private final float x;
  private final float y;
  private final float z;
  
  public Vector3() {
    this(0, 0, 0);
  }

  public Vector3(float x, float y, float z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }
  
  public float getX() {
    return x;
  }

  public float getY() {
    return y;
  }
  
  public float getZ() {
    return z;
  }
  
  public Vector3 add(Vector3 other) {
    return new Vector3(x + other.x, y + other.y, z + other.z);
  }
  
  public float multiply(Vector3 other) {
    return x * other.x + y * other.y + z * other.z;
  }
  
}
