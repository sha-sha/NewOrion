package shaul.games.neworion.engine;

import java.util.ArrayList;
import java.util.List;

public class Scene {

  private List<Drawable> drawables;

  public Scene() {
    drawables = new ArrayList<Drawable>();
  }

  public void addDrawable(Drawable drawable) {
    drawables.add(drawable);
  }

  public void updateAll(long deltaTimeMsec) {
    for (Drawable drawable : drawables) {
      drawable.getPhysicObject().update(deltaTimeMsec);
    }
  }

  public void drawAll(Object canvas) {
    for (Drawable drawable : drawables) {
      drawable.draw(canvas);
    }
  }

}
