package kr.sadalmelik.tobyspring.dao;

import kr.sadalmelik.tobyspring.domain.User;

import java.util.List;

/**
 * Created by SejongPark on 14. 10. 24..
 */
public interface UserDao {
    void add(User user);

    User get(String id);

    void deleteAll();

    int getCount();

    List<User> getAll();

    void update(User user);
}
