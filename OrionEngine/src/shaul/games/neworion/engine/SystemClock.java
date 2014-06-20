package shaul.games.neworion.engine;

import shaul.games.neworion.engine.Clock;

public class SystemClock implements Clock {

  private long zeroTime;
  private long pauseTime;

  public SystemClock() {
    zeroTime = System.currentTimeMillis();
  }

  @Override
  public long getTimeMsec() {
    long currTime = pauseTime > 0 ? pauseTime : System.currentTimeMillis();
    return currTime - zeroTime;
  }

  public void setTimeMsec(long time) {
    zeroTime = System.currentTimeMillis() - time;
    if (pauseTime > 0) {
      pauseTime = System.currentTimeMillis();
    }
  }

  public void pause() {
    pauseTime = System.currentTimeMillis();
  }

  public void resume() {
    pauseTime = 0;
  }

}
