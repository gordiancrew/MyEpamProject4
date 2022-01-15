package kazantsev.dao.impl;

import kazantsev.dao.UsersDao;
import kazantsev.database.ConnectionSourse;
import kazantsev.entity.Book;
import kazantsev.entity.User;
import kazantsev.entity.UserRole;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class UsersDaoImpl implements UsersDao {

    ConnectionSourse sourse = ConnectionSourse.instance();
    Connection conn;
    {
        try {
            conn = sourse.createConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getById(Integer Id) throws SQLException {
        String sql = "select * from users where id=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, Id);
        ResultSet rs = statement.executeQuery();
        User result = null;
        if (rs.next())
            result = userMapping(rs);
        return result;
    }

    @Override
    public List<User> getAll() throws SQLException {
        String sql = "select * from users";
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        List<User> listUsers = new ArrayList<>();
        while (rs.next()) {
            listUsers.add(userMapping(rs));
        }
        Collections.sort(listUsers, Comparator.comparing(User::getSureName));
        return listUsers;
    }

    @Override
    public String save(User user) throws SQLException {
        String sql = "insert users (name,surename,phone,role,address, login,password) values (?,?,?,?,?,?,?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, user.getName());
        statement.setString(2, user.getSureName());
        statement.setInt(3, user.getPhone());
        statement.setString(4, String.valueOf(user.getRole()));
        statement.setString(5,user.getAddress());
        statement.setString(6, user.getLogin());
        statement.setString(7, user.getPassword());
        statement.executeUpdate();
        return "Пользователь " + user.getName() + " " + user.getSureName() + " добавлен!";

    }

    @Override
    public void delete(User user) throws SQLException {
        deleteUserById(user.getId());
    }

    @Override
    public User getUserByLoginAndPassword(String login, String password) throws SQLException {
        String sql="select *from users where login=? and password=?";
        PreparedStatement statement=conn.prepareStatement(sql);
        statement.setString(1,login);
        statement.setString(2,password);
        ResultSet rs=statement.executeQuery();
        User user=null;
        if(rs.next()){
           user=userMapping(rs) ;
        }
        return user;
    }

    @Override
    public User getUserByLogin(String login) throws SQLException {
        String sql="select *from users where login=?";
        PreparedStatement statement=conn.prepareStatement(sql);
        statement.setString(1,login);
        ResultSet rs=statement.executeQuery();
        User user=null;
        if(rs.next()){
            user=userMapping(rs) ;
        }
        return user;
    }

    @Override
    public User getUserByNameAndSurename(String name, String sureName) throws SQLException {
        String sql="select *from users where name=? and surename=?";
        PreparedStatement statement=conn.prepareStatement(sql);
        statement.setString(1,name);
        statement.setString(2,sureName);
        ResultSet rs=statement.executeQuery();
        User user=null;
        if(rs.next()){
            user=userMapping(rs) ;
        }
        return user;
    }

    @Override
    public String deleteUserById(Integer id) throws SQLException {
        User userDel = getById(id);
        if (userDel != null) {
            String userString = "id" + userDel.getId() + " \"" + userDel.getName() + "\"  автор " + userDel.getSureName();
            String sql = "delete from users where id=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
            return "пользователь " + userDel + " удален";
        } else {
            return "Нет такого пользователя";
        }
    }

    public User userMapping(ResultSet rs) throws SQLException {

        String name = rs.getString("name");
        String sureName = rs.getString("surename");
        int phone = rs.getInt("phone");
        UserRole role = UserRole.valueOf(rs.getString("role"));
        String address=rs.getString("address");
        String login = rs.getString("login");
        String password = rs.getString("password");
        int id = rs.getInt("id");
        User user=new User(name, sureName, role, phone, address, login, password);
        user.setId(id);
        return user;
    }
}
