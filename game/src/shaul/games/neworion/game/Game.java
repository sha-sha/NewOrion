package shaul.games.neworion.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.AffineTransform;

import javax.swing.JFrame;
import javax.swing.JPanel;

import shaul.games.neworion.engine.Clock;
import shaul.games.neworion.engine.math.Vector2;

public class Game implements Screen {
  private boolean gameRunning;
  private RootCanvas canvas;
  private Clock clock;

  public Game() {
    JFrame container = new JFrame("Game");
    JPanel panel = (JPanel) container.getContentPane();
    panel.setPreferredSize(new Dimension(800, 600));
    panel.setLayout(null);

    canvas = new RootCanvas(800, 600);
    panel.add(canvas);

    container.pack();
    container.setResizable(false);
    container.setVisible(true);

    canvas.init();

    container.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });

    Input.get().init();
    clock = new SystemClock();
    gameRunning = true;

  }

  public void gameLoop() {
    long lastLoopTime = clock.getTimeMsec();

    Image image = ResourceLoader.loadImage("res/alien.gif");
    AffineTransform tx = new AffineTransform();

    while (gameRunning) {

      long delta = clock.getTimeMsec() - lastLoopTime;
      lastLoopTime = clock.getTimeMsec();

      Graphics2D g2d = canvas.initFrame();

      MouseEvent clickEvent = Input.get().getMouseClickEvent();
      if (clickEvent != null) {
        Vector2 pos = new Vector2(clickEvent.getX(), clickEvent.getY());
        DebugGraphicLayer.get().addCircle(pos, 20.0, Color.red, 4000);
      }

      tx.rotate(Math.PI / 100);
      g2d.drawImage(image, tx, null);

      DebugGraphicLayer.get().drawAll(g2d, this, delta);

      canvas.finishFrame(g2d);

      try {
        Thread.sleep(10);
      } catch (Exception e) {
      }

    }
  }

  @Override
  public int getWidth() {
    return 0;
  }

  @Override
  public int getHeight() {
    return 0;
  }

  @Override
  public int getX(Vector2 pos) {
    return (int) pos.getX();
  }

  @Override
  public int getY(Vector2 pos) {
    return (int) pos.getY();
  }

  @Override
  public int getScaledSize(double size) {
    return (int) size;
  }

  @Override
  public int getScaledWidth(Vector2 size) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int getScaledHeight(Vector2 size) {
    // TODO Auto-generated method stub
    return 0;
  }

}
