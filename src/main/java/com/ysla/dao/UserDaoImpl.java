package com.ysla.dao;

import com.ysla.vo.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class UserDaoImpl implements IUserDao {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public User getUserByUsername(String username) {
        String sql = "select username,password from t_user where username = ?";
        List<User> list = jdbcTemplate.query(sql, new String[]{username}, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user = new User();
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                return user;
            }
        });
        if (CollectionUtils.isEmpty(list)){
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<String> getRoleByUsername(String username) {
        String sql = "select role_name from t_user_role where username = ?";
        List<String> list = jdbcTemplate.query(sql,new String[]{username},new RowMapper<String>(){
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getString("role_name");
            }
        });
        return list;
    }

    @Override
    public List<String> getPermissionByUsername(String username) {
        String sql = "select permission from t_user_permission where username = ?";
        List<String> list = jdbcTemplate.query(sql, new String[]{username}, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getString("permission");
            }
        });
        return list;
    }
}
