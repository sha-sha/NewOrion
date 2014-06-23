package shaul.games.neworion.game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.image.BufferStrategy;

public class RootCanvas extends Canvas {

  private static final long serialVersionUID = 1L;

  private BufferStrategy strategy;

  private int width;

  private int height;

  public RootCanvas(int w, int h) {
    width = w;
    height = h;
    setBounds(0, 0, w, h);
  }

  public void init() {
    setIgnoreRepaint(true);

    addKeyListener(new KeyInputHandler());

    requestFocus();

    createBufferStrategy(2);
    strategy = getBufferStrategy();

    initEntities();

    Input.get().setView(this);
  }


  private void initEntities() {
    // TODO Auto-generated method stub

  }

  private class KeyInputHandler extends KeyAdapter {

  }

  public Graphics2D initFrame() {
    Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
    g.setColor(Color.black);
    g.fillRect(0, 0, width, height);
    return g;
  }

  public void finishFrame(Graphics2D g) {
    g.dispose();
    strategy.show();
  }

}
