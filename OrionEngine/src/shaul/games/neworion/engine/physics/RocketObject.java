package shaul.games.neworion.engine.physics;

import shaul.games.neworion.engine.math.Vector2;

public class RocketObject extends SimpleDynamicObject {

  private float thrust;
  
  public RocketObject(float mass, Vector2 initialPosition,
      Vector2 initialFaceVector) {
    super(mass, initialPosition, initialFaceVector);
  }
  
  public void setThrust(float thrust) {
    this.thrust = thrust;
  }
  
  public float getThrust() {
    return thrust;
  }
  
  @Override
  public void update(long deltaTimeMsec) {
    
    
  }

}
