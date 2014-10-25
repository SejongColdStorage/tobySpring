package kr.sadalmelik.tobyspring.service;

import kr.sadalmelik.tobyspring.dao.Level;
import kr.sadalmelik.tobyspring.dao.UserDao;
import kr.sadalmelik.tobyspring.domain.User;

import java.util.List;

/**
 * Created by SejongPark on 14. 10. 24..
 */
public class UserService {
    UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void upgradeLevels() {
        List<User> users = userDao.getAll();

        for (User user : users) {
            Boolean changed = null;

            if (user.getLevel() == Level.BASIC && user.getLogin() >= 50) {
                user.setLevel(Level.SILVER);
                changed = true;
            } else if (user.getLevel() == Level.SILVER && user.getRecommend() >= 30) {
                user.setLevel(Level.GOLD);
                changed = true;
            } else if (user.getLevel() == Level.GOLD) {
                changed = false;
            } else {
                changed = false;
            }

            if (changed) {
                userDao.update(user);
            }
        }
    }

}
