package ex05_BusinessLogic;

public class UserIdsGenerator {
  private static UserIdsGenerator generator_;
  private static int id_;

  private UserIdsGenerator() {}

  public static UserIdsGenerator getInstance() {
    if (generator_ == null) {
      id_ = 0;
      generator_ = new UserIdsGenerator();
    }
    return generator_;
  }

  public int generateId() {
    return ++id_;
  }
}
