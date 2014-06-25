package shaul.games.neworion.engine;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import shaul.games.neworion.engine.gameobject.GameObject;

public class GameObjectsCollection {
  List<List<GameObject>> gameObjects;
  private final int maxLayers;

  public GameObjectsCollection(int maxLayers) {
    this.maxLayers = maxLayers;
    gameObjects = new ArrayList<List<GameObject>>();
    for (int i = 0; i < maxLayers; i++) {
      gameObjects.add(new LinkedList<GameObject>());
    }
  }

  public Iterator<GameObject> iterator() {
    return new LocalIterator();
  }

  private class LocalIterator implements Iterator<GameObject> {

    private int layer;
    private Iterator<GameObject> iter;
    private GameObject next;

    public LocalIterator() {
      for (layer = 0; layer < maxLayers; layer++) {
        iter = gameObjects.get(layer).iterator();
        if (iter.hasNext()) {
          next = iter.next();
          break;
        }
      }
    }

    @Override
    public boolean hasNext() {
      return next != null;
    }

    @Override
    public GameObject next() {
      GameObject rv = next;
      next = null;
      if (iter.hasNext()) {
        next = iter.next();
      } else {
        for (layer++; layer < maxLayers; layer++) {
          iter = gameObjects.get(layer).iterator();
          if (iter.hasNext()) {
            next = iter.next();
            break;
          }
        }
      }
      return rv;
    }

    @Override
    public void remove() {
    }

  }

  public void add(GameObject object) {
    add(object, 0);
  }

  public void add(GameObject object, int layer) {
    Precondition.check(layer <= maxLayers, "Invalid layer : " + layer);
    gameObjects.get(layer).add(object);
  }

  public GameObject findByPosition(float x, float y) {
    for (int layer = 0; layer < maxLayers; layer++) {
      ListIterator<GameObject> iterator = gameObjects.get(layer).listIterator();
      while (iterator.hasNext()) {
        if (iterator.next().hitText(x, y)) {
          return iterator.previous();
        }
      }
    }
    return null;
  }

  public void draw(ScreenCanvas screenCanvas) {
    for (int layer = 0; layer < maxLayers; layer++) {
      for (GameObject gameObject : gameObjects.get(layer)) {
        screenCanvas.draw(gameObject.getxPosition(), gameObject.getyPosition(),
            gameObject.getImage());
      }
    }
  }
}
