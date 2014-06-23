package shaul.games.neworion.engine;


public class GameObject {
  private Texture image;
  private float xPosition;
  private float yPosition;
  private double direction;

  public Texture getImage() {
    return image;
  }

  public void setImage(Texture image) {
    this.image = image;
  }

  public float getxPosition() {
    return xPosition;
  }

  public void setxPosition(float xPosition) {
    this.xPosition = xPosition;
  }

  public float getyPosition() {
    return yPosition;
  }

  public void setyPosition(float yPosition) {
    this.yPosition = yPosition;
  }

  public double getDirection() {
    return direction;
  }

  public void setDirection(double direction) {
    this.direction = direction;
  }

  public boolean hitText(float x, float y) {
    if (x < xPosition || y < yPosition) {
      return false;
    }
    if (x > xPosition + image.getWidth() || y > yPosition + image.getHeight()) {
      return false;
    }
    return true;
  }
}
