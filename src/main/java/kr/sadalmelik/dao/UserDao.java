package kr.sadalmelik.dao;

import kr.sadalmelik.domain.User;
import org.springframework.dao.EmptyResultDataAccessException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserDao {
    private DataSource dataSource;
    private PreparedStatement preparedStatement;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void add(User user) throws SQLException {
        Connection c = this.dataSource.getConnection();

        PreparedStatement ps = c.prepareStatement(
                "insert into users(id, name, password) values(?,?,?)"
        );
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();

        ps.close();
        c.close();
    }

    public User get(String id) throws SQLException {
        Connection c = this.dataSource.getConnection();
        PreparedStatement ps = c
                .prepareStatement("select * from users where id = ?");
        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();

        User user = null;
        if (rs.next()) {
            user = new User();
            user.setId(rs.getString("id"));
            user.setName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
        }

        rs.close();
        ps.close();
        c.close();

        if (user == null) throw new EmptyResultDataAccessException(1);

        return user;
    }

    public void deleteAll() throws SQLException {
        jdbcContextStatementStrategy(new DeleteAllStatement());
    }

    public void jdbcContextStatementStrategy(StatementStrategy stmt) throws SQLException{
        Connection c = null;
        PreparedStatement ps = null;

        try {
            c = dataSource.getConnection();
            ps = stmt.makePreparedStatement(c);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            try {
                ps.close();
            } catch (SQLException e) {
            }

            try {
                c.close();
            } catch (SQLException e) {
            }
        }
    }

    public int getCount() throws SQLException {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            int count = 0;
            c = dataSource.getConnection();
            ps = c.prepareStatement("select count(*) from users");

            rs = ps.executeQuery();
            rs.next();
            count = rs.getInt(1);
            return count;
        } catch (SQLException e) {
            throw e;
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
            }
            try {
                ps.close();
            } catch (SQLException e) {
            }
            try {
                c.close();
            } catch (SQLException e) {
            }
        }
    }


}