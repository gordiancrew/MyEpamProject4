package kazantsev.dao.impl;

import kazantsev.controller.LibraryServlet;
import kazantsev.dao.UsersDao;
import kazantsev.database.ConnectionSourse;
import kazantsev.entity.Book;
import kazantsev.entity.User;
import kazantsev.entity.UserRole;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class UsersDaoImpl implements UsersDao {
    private static final Logger log = Logger.getLogger(UsersDaoImpl.class);
    ConnectionSourse sourse = ConnectionSourse.instance();
    Connection conn;

    {
        try {
            conn = sourse.createConnection();
        } catch (SQLException e) {
            log.log(Level.ERROR, "exception:", e);
            e.printStackTrace();
        }
    }

    @Override
    public User getById(Integer Id) {
        String sql = "select * from users where id=?";
        PreparedStatement statement = null;
        User result = null;
        try {
            statement = conn.prepareStatement(sql);
            statement.setInt(1, Id);
            ResultSet rs = statement.executeQuery();
            if (rs.next())
                result = userMapping(rs);
        } catch (SQLException e) {
            log.log(Level.ERROR, "exception:", e);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<User> getAll() {
        String sql = "select * from users";
        Statement statement = null;
        List<User> listUsers = new ArrayList<>();
        try {
            statement = conn.createStatement();

            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                listUsers.add(userMapping(rs));
            }
        } catch (SQLException e) {
            log.log(Level.ERROR, "exception:", e);
            e.printStackTrace();
        }
        Collections.sort(listUsers, Comparator.comparing(User::getSureName));
        return listUsers;
    }

    @Override
    public String save(User user) {
        String sql = "insert users (name,surename,phone,role,address, login,password,confirm) values (?,?,?,?,?,?,?,?)";
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(sql);

            statement.setString(1, user.getName());
            statement.setString(2, user.getSureName());
            statement.setInt(3, user.getPhone());
            statement.setString(4, String.valueOf(user.getRole()));
            statement.setString(5, user.getAddress());
            statement.setString(6, user.getLogin());
            statement.setString(7, user.getPassword());
            statement.setBoolean(8, Boolean.FALSE);
            statement.executeUpdate();
        } catch (SQLException e) {
            log.log(Level.ERROR, "exception:", e);
            e.printStackTrace();
        }
        return "Пользователь " + user.getName() + " " + user.getSureName() + " добавлен!";

    }

    @Override
    public String deleteById(int id) {
        User userDel = getById(id);
        if (userDel != null) {
            String userString = userDel.getName() + " " + userDel.getSureName();
            String sql = "delete from users where id=?";
            PreparedStatement statement = null;
            try {
                statement = conn.prepareStatement(sql);

                statement.setInt(1, id);
                statement.executeUpdate();
            } catch (SQLException e) {
                log.log(Level.ERROR, "exception:", e);
                e.printStackTrace();
            }
            return "User " + userString + " delete";
        } else {
            return "no";
        }
    }

    @Override
    public String setConfirm(int id, boolean confirm) {
        String sql = "update users set confirm=? where id=?";
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(sql);
            statement.setBoolean(1, confirm);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            log.log(Level.ERROR, "exception:", e);
            e.printStackTrace();
        }
        return "ok";
    }

    @Override
    public List<User> getUserByName(String name) {
        String sql = "select *from users where name=?";
        PreparedStatement statement = null;
        List<User> list = new ArrayList<>();
        try {
            statement = conn.prepareStatement(sql);
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                list.add(userMapping(rs));
            }
        } catch (SQLException e) {
            log.log(Level.ERROR, "exception:", e);
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<User> getUserBySureName(String sureName) {
        String sql = "select *from users where  surename=?";
        PreparedStatement statement = null;
        List<User> list = new ArrayList<>();
        try {
            statement = conn.prepareStatement(sql);
            statement.setString(1, sureName);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                list.add(userMapping(rs));
            }
        } catch (SQLException e) {
            log.log(Level.ERROR, "exception:", e);
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void delete(User user) {
        deleteUserById(user.getId());
    }

    @Override
    public User getUserByLoginAndPassword(String login, String password) {
        String sql = "select *from users where login=? and password=?";
        PreparedStatement statement = null;
        User user = null;
        try {
            statement = conn.prepareStatement(sql);

            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                user = userMapping(rs);
            }
        } catch (SQLException e) {
            log.log(Level.ERROR, "exception:", e);
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User getUserByLogin(String login) {
        String sql = "select *from users where login=?";
        PreparedStatement statement = null;
        User user = null;
        try {
            statement = conn.prepareStatement(sql);
            statement.setString(1, login);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                user = userMapping(rs);
            }
        } catch (SQLException e) {
            log.log(Level.ERROR, "exception:", e);
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> getUserByNameAndSureName(String name, String sureName) {
        String sql = "select *from users where name=? and surename=?";
        PreparedStatement statement = null;
        List<User> list = new ArrayList<>();
        try {
            statement = conn.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, sureName);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                list.add(userMapping(rs));
            }
        } catch (SQLException e) {
            log.log(Level.ERROR, "exception:", e);
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public String deleteUserById(Integer id) {
        User userDel = getById(id);
        if (userDel != null) {
            String userString = "id" + userDel.getId() + " \"" + userDel.getName() + "\"  автор " + userDel.getSureName();
            String sql = "delete from users where id=?";
            PreparedStatement statement = null;
            try {
                statement = conn.prepareStatement(sql);
                statement.setInt(1, id);
                statement.executeUpdate();
            } catch (SQLException e) {
                log.log(Level.ERROR, "exception:", e);
                e.printStackTrace();
            }
            return "user " + userDel + " delete";
        } else {
            return "not this user";
        }
    }

    @Override
    public List<User> getUsersByConfirm(Boolean confirm) {
        String sql = "select *from users where confirm=?";
        List<User> list = new ArrayList<>();
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(sql);
            statement.setBoolean(1, confirm);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                list.add(userMapping(rs));
            }
        } catch (SQLException e) {
            log.log(Level.ERROR, "exception:", e);
            e.printStackTrace();
        }
        return list;
    }


    public User userMapping(ResultSet rs) {

        String name = null;
        User user = null;
        try {
            name = rs.getString("name");
            String sureName = rs.getString("surename");
            int phone = rs.getInt("phone");
            UserRole role = UserRole.valueOf(rs.getString("role"));
            String address = rs.getString("address");
            String login = rs.getString("login");
            String password = rs.getString("password");
            boolean confirm = rs.getBoolean("confirm");
            int id = rs.getInt("id");
            user = new User(name, sureName, role, phone, address, login, password);
            user.setId(id);
            user.setConfirm(confirm);
        } catch (SQLException e) {
            log.log(Level.ERROR, "exception:", e);
            e.printStackTrace();
        }
        return user;
    }
}
