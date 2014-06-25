package shaul.games.neworion.engine.gameobject;

import shaul.games.neworion.engine.Texture;
import shaul.games.neworion.engine.math.Vector2;


public class GameObject implements IObject {

  private Texture image;
  private Vector2 position;
  private double direction;
  private PhysicObject physicObject;

  public GameObject() {
    position = Vector2.ZERO;
  }

  @Override
  public void onUpdate(long deltaMsec) {
  }

  public Texture getImage() {
    return image;
  }

  public void setImage(Texture image) {
    this.image = image;
  }

  public float getxPosition() {
    return position.getX();
  }

  public float getyPosition() {
    return position.getY();
  }

  public void setPosition(Vector2 position) {
    this.position = position;
  }

  public Vector2 getPosition() {
    return position;
  }

  public void setPosition(float x, float y) {
    this.position = new Vector2(x, y);
  }

  public double getDirection() {
    return direction;
  }

  public void setDirection(double direction) {
    this.direction = direction;
  }

  public boolean hitText(float x, float y) {
    if (x < getxPosition() || y < getyPosition()) {
      return false;
    }
    if (x > getxPosition() + image.getWidth() || y > getyPosition() + image.getHeight()) {
      return false;
    }
    return true;
  }

  public PhysicObject getPhysicObject() {
    return physicObject;
  }

  public void setPhysicObject(PhysicObject physicObject) {
    this.physicObject = physicObject;
  }

}
