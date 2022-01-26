package kazantsev.dao;

import kazantsev.entity.Book;
import kazantsev.entity.User;
import kazantsev.entity.UserRole;

import java.sql.SQLException;
import java.util.List;

public interface UsersDao extends  Dao<User,Integer>{
    User getUserByLoginAndPassword(String login, String password) throws SQLException;
    User getUserByLogin(String login) throws SQLException;
    List<User> getUserByNameAndSureName(String name, String sureName) throws SQLException;
    String deleteUserById(Integer id) throws SQLException;
    List<User> getUsersByConfirm(Boolean confirm) throws SQLException;
    String deleteById(int idUser) throws SQLException;
    String setConfirm(int id, boolean confirm) throws SQLException;
    List<User> getUserByName(String name) throws SQLException;

    List<User> getUserBySureName(String sureName) throws SQLException;



}
