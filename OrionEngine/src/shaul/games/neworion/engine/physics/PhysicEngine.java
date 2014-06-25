package shaul.games.neworion.engine.physics;

import java.util.Iterator;

import shaul.games.neworion.engine.GameObjectsCollection;
import shaul.games.neworion.engine.gameobject.GameObject;
import shaul.games.neworion.engine.gameobject.PhysicObject;
import shaul.games.neworion.engine.math.Vector2;

public class PhysicEngine {

  public void onUpdate(GameObjectsCollection gameObjectsCollection, long delta) {
    Iterator<GameObject> iter = gameObjectsCollection.iterator();
    while (iter.hasNext()) {
      GameObject gameObject = iter.next();
      PhysicObject phyObj = gameObject.getPhysicObject();
      if (phyObj != null) {
        Vector2 newPos = gameObject.getPosition().add(phyObj.velocity, delta);
        gameObject.setPosition(newPos);
      }
    }
  }
}
