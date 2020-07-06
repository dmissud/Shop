package org.dbs.shop.domain.user;

import java.util.List;

public interface IRepositoryUser {
    void save(User user);
    User findByName(String userName);
    List<User> retrieveAllUsers();
}
