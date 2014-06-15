package shaul.games.neworion.game;

import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Input implements SubSystem {
  private static Input instance;

  public static Input get() {
    if (instance == null) {
      instance = new Input();
    }
    return instance;
  }

  private Component currentView;
  private final MouseHandler mouseHandler;
  public MouseEvent lastMouseClickEvent;

  public Input() {
    mouseHandler = new MouseHandler();
  }

  @Override
  public String getName() {
    return "";
  }

  @Override
  public void init() {
  }

  @Override
  public void shutdown() {
  }

  public MouseEvent getMouseClickEvent() {
    MouseEvent event = lastMouseClickEvent;
    lastMouseClickEvent = null;
    return event;
  }

  public void setView(Component view) {
    if (currentView != view) {
      if (currentView != null) {
        currentView.removeMouseListener(mouseHandler);
      }
      currentView = view;
      if (currentView != null) {
        view.addMouseListener(mouseHandler);
      }
    }
  }

  private class MouseHandler implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent event) {
      lastMouseClickEvent = event;
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
      // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent arg0) {
      // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent arg0) {
      // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
      // TODO Auto-generated method stub

    }

  }

  private class KeyInputHandler extends KeyAdapter {

  }

}
