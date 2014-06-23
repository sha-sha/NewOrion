package shaul.games.neworion.engine;

import shaul.games.neworion.engine.math.Vector2;

public interface Screen {

  public interface Zoom {
    double getZoom();

    int getVerticalShift();

    int getHorizontalShift();
  }

  int getWidth();

  int getHeight();

  // Zoom getZoom();

  int getX(float x);

  int getY(float y);

  int getScaledSize(double size);

  int getScaledWidth(Vector2 size);

  int getScaledHeight(Vector2 size);

}
