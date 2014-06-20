package shaul.games.neworion.engine;

public final class Precondition {

  public static final class PreconditionFailure extends RuntimeException {
    private static final long serialVersionUID = 847019525648714655L;
    public PreconditionFailure(String error) {
      super(error);
    }
  }

  public static void check(boolean condition, String errorMessage) {
    if (!condition) {
      throw (new PreconditionFailure(errorMessage));
    }
  }

  public static <T> T checkNotNull(T t) {
    if (t == null) {
      throw (new PreconditionFailure("Object " + t + "is null"));
    }
    return t;
  }

}
