package kazantsev.dao;

import kazantsev.entity.Book;
import kazantsev.entity.User;
import kazantsev.entity.UserRole;

import java.sql.SQLException;
import java.util.List;

public interface UsersDao extends Dao<User, Integer> {
    User getUserByLoginAndPassword(String login, String password);

    User getUserByLogin(String login);

    List<User> getUserByNameAndSureName(String name, String sureName);

    String deleteUserById(Integer id);

    List<User> getUsersByConfirm(Boolean confirm);

    String deleteById(int idUser);

    String setConfirm(int id, boolean confirm);

    List<User> getUserByName(String name);

    List<User> getUserBySureName(String sureName);


}
