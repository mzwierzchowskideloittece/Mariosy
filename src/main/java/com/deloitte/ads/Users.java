package com.deloitte.ads;

import java.util.Set;

public class Users {

    private Set<User> users;

    public Users(Set<User> users) {
        this.users = users;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public void deleteUser(User user) {
        this.users.remove(user);
    }
}
