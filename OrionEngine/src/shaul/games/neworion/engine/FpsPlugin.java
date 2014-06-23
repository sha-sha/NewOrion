package shaul.games.neworion.engine;

import shaul.games.neworion.engine.DebugGraphicLayer.Shape;

public class FpsPlugin implements RenderPlugin {
  private int fps = 0;
  private long accDelta = 0;
  private int accFrames = 0;
  private Shape shape;
  private DebugGraphicLayer debugGraphicLayer;

  public FpsPlugin() {
    this(null);
  }

  public FpsPlugin(DrawableShape textShape) {
    debugGraphicLayer = DebugGraphicLayer.get();
    if (textShape != null) {
      this.shape = debugGraphicLayer.addDrawableShape(
          textShape.buildUpon().setText("").build(), 0);
    }
  }

  @Override
  public void onFrame(long delta) {
    accFrames++;
    accDelta += delta;
    if (accDelta > 1000) {
      fps = accFrames;
      accFrames = 0;
      accDelta -= 1000;
      if (shape != null) {
        debugGraphicLayer.removeShape(shape);
        shape = debugGraphicLayer.addDrawableShape(
            shape.drawableShape.buildUpon().setText("FPS: " + fps).build(), 0);
      }
    }
  }

  int getFps() {
    return fps;
  }


}
