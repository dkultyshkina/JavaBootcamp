package ex04_ListOfTransactions;

public class UsersArrayList implements UsersList {
  User array_[];
  int size_;

  public UsersArrayList() {
    array_ = new User[10];
    size_ = 0;
  }

  public void addUser(User newUser) {
    if (size_ % 10 == 0 && size_ != 0) {
      copyArray();
    }
    array_[size_] = newUser;
    ++size_;
  };

  public User getUserId(int id) {
    int j = 0;
    for (int i = 0; i < size_; ++i) {
      j = array_[i].getId();
      if (j == id) {
        return array_[i];
      }
    }
    throw new UserNotFoundException();
  };

  public User getUserIndex(int index) {
    if (index < 0 || index > size_) {
      throw new UserNotFoundException();
    }
    return array_[index];
  };

  public int getCountUser() {
    return size_;
  };

  private void copyArray() {
    User array[] = new User[size_ * 2];
    for (int i = 0; i < size_; ++i) {
      array[i] = array_[i];
    }
    array_ = array;
  }
}
