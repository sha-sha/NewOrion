package shaul.games.neworion.engine.physics;

import java.util.List;

public class SimpleUpdater {

  public void update(List<DynamicObject> objects, int deltaTimeMsec) {
    for (DynamicObject obj : objects) {
      obj.update(deltaTimeMsec);
    }
  }

}
