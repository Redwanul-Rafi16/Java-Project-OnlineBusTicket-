package USER;

public interface UserDaointer {
    void createUser(User user);

    void showProfile(int id);

    void updateUser(int id, String name,String phone,String address);

    void deleteUser(int id);
}
