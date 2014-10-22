package kr.sadalmelik.dao;

import kr.sadalmelik.domain.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class UserDao {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.dataSource = dataSource;
    }

    public void add(User user){
        jdbcTemplate.update("insert into users(id, name, password) values(?,?,?)", new Object[]{user.getId(), user.getName(), user.getPassword()});

    }

    public User get(String id){
        return jdbcTemplate.queryForObject("select * from users where id = ?", new Object[]{id}, (resultSet, i) -> {
            User user = new User();
            user.setId(resultSet.getString("id"));
            user.setName(resultSet.getString("name"));
            user.setPassword(resultSet.getString("password"));
            return user;
        });
    }

    public void deleteAll() {
        jdbcTemplate.update("delete from users");
    }

    public int getCount(){
        return jdbcTemplate.queryForObject("select count(*) from users", Integer.class);
    }


    public List<User> getAll() {
        return jdbcTemplate.query("SELECT * FROM USERS", (resultSet, i) -> {
            User user = new User();
            user.setId(resultSet.getString("id"));
            user.setName(resultSet.getString("name"));
            user.setPassword(resultSet.getString("password"));
            return user;
        });
    }
}