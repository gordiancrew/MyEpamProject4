package kazantsev.dao;

import kazantsev.entity.User;
import kazantsev.entity.UserRole;

import java.sql.SQLException;
import java.util.List;

public interface UsersDao extends  Dao<User,Integer>{
    User getUserByLoginAndPassword(String login, String password) throws SQLException;
    User getUserByLogin(String login) throws SQLException;
    User getUserByNameAndSurename(String name, String sureName) throws SQLException;
    String deleteUserById(Integer id) throws SQLException;

}
