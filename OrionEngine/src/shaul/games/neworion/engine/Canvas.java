package shaul.games.neworion.engine;

public interface Canvas {

  void drawImage(int x, int y, Texture image);

  void drawImage(int x, int y, double rotate, Texture image);

}
