package models.user;

import java.util.List;

public class Users {

    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public User getUserById(int id) {
        for (User user : getUsers()) {
            if (user.getId() == id)
                return user;
        }

        return null;
    }
}
