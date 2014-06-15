package shaul.games.neworion.engine;

import shaul.games.neworion.engine.physics.DynamicObject;

public interface Drawable {

  void draw(Object canvas);

  DynamicObject getPhysicObject();
}
