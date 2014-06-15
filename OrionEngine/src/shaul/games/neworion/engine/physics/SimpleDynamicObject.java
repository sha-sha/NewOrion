package shaul.games.neworion.engine.physics;

import shaul.games.neworion.engine.math.Vector2;

public class SimpleDynamicObject implements DynamicObject  {

  private float mass;
  private Vector2 position;
  private Vector2 faceVector;
  private Vector2 velocity;
  private Vector2 linearForce;

  public SimpleDynamicObject(
      float mass,
      Vector2 initialPosition,
      Vector2 initialFaceVector) {
    this.mass = mass == 0f ? 0.0001f : mass;
    this.position = initialPosition;
    this.faceVector = initialFaceVector;
  }
  
  @Override
  public float getMass() {
    return mass;
  }

  @Override
  public Vector2 getPosition() {
    return position;
  }

  @Override
  public Vector2 getFaceVector() {
    return faceVector;
  }

  @Override
  public Vector2 getVilocity() {
    return velocity;
  }

  @Override
  public void update(long deltaTimeMsec) {
    if (!linearForce.isZero()) {
      Vector2 linearAcceleration = linearForce.multiply(1 / mass);
      velocity = velocity.add(linearAcceleration);
    }
    position = position.add(velocity, deltaTimeMsec);
  }

  @Override
  public void addLinearForce(Vector2 force) {
    this.linearForce = force;
  }

}
