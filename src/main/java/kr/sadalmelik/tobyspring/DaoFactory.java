package kr.sadalmelik.tobyspring;

import kr.sadalmelik.tobyspring.dao.UserDao;
import kr.sadalmelik.tobyspring.dao.UserDaoJdbc;
import kr.sadalmelik.tobyspring.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
public class DaoFactory {
    @Bean
    public UserService userService(){
        UserService userService = new UserService();
        userService.setUserDao(userDao());

        return userService;
    }

    @Bean
    public UserDao userDao() {
        UserDaoJdbc userDao = new UserDaoJdbc();
        userDao.setDataSource(dataSource());

        return userDao;
    }

    @Bean
    public DataSource dataSource() {
        EmbeddedDatabase dataSource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .setScriptEncoding("UTF-8")
                .ignoreFailedDrops(true)
                .addScript("sql/user.sql")
                .build();

        return dataSource;
    }


}
