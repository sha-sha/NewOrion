package shaul.games.neworion.engine.physics;

import shaul.games.neworion.engine.math.Vector2;

public interface DynamicObject {
  float getMass();
  Vector2 getPosition();
  Vector2 getFaceVector();
  Vector2 getVilocity();
  void addLinearForce(Vector2 force);
  void update(long deltaTimeMsec);
}
