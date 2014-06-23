package shaul.games.neworion.engine;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class GameObjectsCollection {
  List<GameObject> gameObjects;

  public GameObjectsCollection() {
    gameObjects = new LinkedList<GameObject>();
  }

  public void add(GameObject object) {
    gameObjects.add(object);
  }

  public ListIterator<GameObject> findByPosition(float x, float y) {
    ListIterator<GameObject> iterator = getAll();
    while (iterator.hasNext()) {
      if (iterator.next().hitText(x, y)) {
        return iterator;
      }
    }
    return null;
  }

  public ListIterator<GameObject> getAll() {
    return gameObjects.listIterator();
  }

  public void draw(ScreenCanvas screenCanvas) {
    for (GameObject gameObject : gameObjects) {
      screenCanvas.draw(gameObject.getxPosition(), gameObject.getyPosition(), gameObject.getImage());
    }
  }
}
