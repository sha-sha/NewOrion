package shaul.games.neworion.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

import shaul.games.neworion.engine.Canvas;
import shaul.games.neworion.engine.Clock;
import shaul.games.neworion.engine.DebugGraphicLayer;
import shaul.games.neworion.engine.DrawableShape;
import shaul.games.neworion.engine.DrawableShape.Type;
import shaul.games.neworion.engine.FpsPlugin;
import shaul.games.neworion.engine.GameObjectsCollection;
import shaul.games.neworion.engine.Screen;
import shaul.games.neworion.engine.ScreenCanvas;
import shaul.games.neworion.engine.ShapeDrawer;
import shaul.games.neworion.engine.SystemClock;
import shaul.games.neworion.engine.Texture;
import shaul.games.neworion.engine.gameobject.GameObject;
import shaul.games.neworion.engine.gameobject.PhysicObject;
import shaul.games.neworion.engine.math.Vector2;
import shaul.games.neworion.engine.physics.PhysicEngine;

public class Game implements Screen {
  private boolean gameRunning;
  private RootCanvas canvas;
  private Clock clock;
  private int screenWidth = 1024;
  private int screenHeight = 768;

  public Game() {
    JFrame container = new JFrame("Game");
    JPanel panel = (JPanel) container.getContentPane();
    panel.setPreferredSize(new Dimension(screenWidth, screenHeight));
    panel.setLayout(null);

    canvas = new RootCanvas(screenWidth, screenHeight);
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
    PhysicEngine physicEngine = new PhysicEngine();
    GameObjectsCollection gameObjects = new GameObjectsCollection(2);
    DrawableShape fpsText = new DrawableShape.Builder().setType(Type.TEXT)
        .setColor(Color.red.getRGB()).setPosition(10, 14).build();
    FpsPlugin fpsCalculator = new FpsPlugin(fpsText);
    long lastLoopTime = clock.getTimeMsec();

    Texture image = DefaultResourceLoader.get().loadImage("res/alien.gif");
    Texture image2 = DefaultResourceLoader.get().loadImage("res/speedship.png");
    Texture backGroundImage = DefaultResourceLoader.get().loadImage("res/space-background.jpg");
    double angle = 0.0;

    DefaultCanvas engineCanvas = new DefaultCanvas();
    DebugGraphicLayer.Shape fps = null;
    ScreenCanvas screenCanvas = new ScreenCanvas(engineCanvas, this);

    GameObject spaceship = new GameObject();
    spaceship.setImage(image2);
    spaceship.setPosition(100, 100);
    spaceship.setPhysicObject(new PhysicObject());
    spaceship.getPhysicObject().velocity = new Vector2(0.08f, 0.0f);
    gameObjects.add(spaceship, 1);

    GameObject background = new GameObject();
    background.setImage(backGroundImage);
    gameObjects.add(background, 0);

    Random random = new Random(100);
    int nextX = 30;
    int nextY = 30;
    for (int i = 0; i < 1000; i++) {
      GameObject gameObject = new GameObject();
      gameObject.setPosition(nextX, nextY);
      nextX += 60;
      if (nextX > 2 * getWidth()) {
        nextX = 60;
        nextY += 60;
      }
      gameObject.setImage(image);
      gameObject.setPhysicObject(new PhysicObject());
      gameObject.getPhysicObject().velocity = new Vector2((random.nextFloat() - 0.5f) / 10f,
          (random.nextFloat() - 0.5f) / 10f);
      gameObjects.add(gameObject);
    }

    while (gameRunning) {

      long delta = clock.getTimeMsec() - lastLoopTime;
      fpsCalculator.onFrame(delta);
      lastLoopTime = clock.getTimeMsec();

      Graphics2D g2d = canvas.initFrame();
      engineCanvas.setGraphics(g2d);

      MouseEvent clickEvent = Input.get().getMouseClickEvent();
      if (clickEvent != null) {
        // TODO: Should convert screen pos to game pos.
        GameObject go = gameObjects.findByPosition(clickEvent.getX(), clickEvent.getY());
        if (go != null) {
          go.setPosition(go.getxPosition() + 10.0f, go.getyPosition());
          Vector2 pos = new Vector2(go.getxPosition(), go.getyPosition());
          DebugGraphicLayer.get().addCircle(pos, 20.0, Color.red.getRGB(), 1000);
        }
      }

      KeyEvent keyEvent = Input.get().getKeyEvent();
      if (keyEvent != null) {
        if (keyEvent.getKeyChar() == 'z') {

        }
      }

      if (fps != null) {
        DebugGraphicLayer.get().removeShape(fps);
      }

      physicEngine.onUpdate(gameObjects, delta);

      gameObjects.draw(screenCanvas);
      angle += 0.03;
      for (int i = 0; i < 1; i++) {
        // screenCanvas.draw(new Vector2(30 + (i * 40) % 800, 30 + (i / 20) *
        // 40), image);
        // engineCanvas.drawImage(30 + (i * 40) % 800, 30 + (i / 20) * 40,
        // angle,
        // image);
      }
      // g2d.drawImage(image, tx, null);

      DebugGraphicLayer.get().drawAll(new DefaultShapepDrawer(g2d), this, delta);

      canvas.finishFrame(g2d);

      try {
        Thread.sleep(10);
      } catch (Exception e) {
      }

    }
  }

  @Override
  public int getWidth() {
    return screenWidth;
  }

  @Override
  public int getHeight() {
    return screenHeight;
  }

  @Override
  public int getX(float x) {
    return (int) x;
  }

  @Override
  public int getY(float y) {
    return (int) y;
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

  private static class DefaultShapepDrawer implements ShapeDrawer {

    private final Graphics2D g;

    DefaultShapepDrawer(Graphics2D g) {
      this.g = g;
      
    }

    @Override
    public void drawShape(DrawableShape shape, Screen screen) {
      Color c = new Color(shape.getColor());
      int alpha = Math.min(Math.max(0, (int) (shape.getOpacity() * 255)), 255);
      g.setColor(new Color(c.getRed(), c.getGreen(), c.getBlue(), alpha));
      
      int x = screen.getX(shape.getPosition().getX());
      int y = screen.getY(shape.getPosition().getY());
      switch (shape.getType()) {
        case CIRCLE:
          int radius = screen.getScaledSize(shape.getRadius());
          g.drawArc(x - radius, y - radius, radius * 2, radius * 2, 0, 360);
          break;
        case TEXT:
          g.drawString(shape.getText(), x, y);
          break;
      }
    }

  }

  private static class DefaultCanvas implements Canvas {

    Graphics2D g;

    public void setGraphics(Graphics2D g) {
      this.g = g;
    }

    @Override
    public void drawImage(int x, int y, Texture image) {
      if (image instanceof DefaultTexture) {
        ((DefaultTexture) image).draw(g, x, y);
      }
    }

    @Override
    public void drawImage(int x, int y, double rotate, Texture image) {
      if (image instanceof DefaultTexture) {
        ((DefaultTexture) image).draw(g, x, y, rotate);
      }
    }
  };

}
