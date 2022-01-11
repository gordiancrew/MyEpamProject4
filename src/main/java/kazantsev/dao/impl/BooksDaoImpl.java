package kazantsev.dao.impl;

import kazantsev.dao.BooksDao;
import kazantsev.database.ConnectionSourse;
import kazantsev.entity.Book;

import java.sql.*;
import java.util.*;

public class BooksDaoImpl implements BooksDao {


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
    public List<Book> getBooksByName(String name) throws SQLException {

        String sql = "select * from books where name=?";
        List<Book> listBooks = new ArrayList<>();
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, name);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            listBooks.add(bookMapping(rs));
        }
        return listBooks;
    }

    @Override
    public List<Book> getBooksByAuthor(String author) throws SQLException {
        String sql = "select * from books where   author=?";
        List<Book> listBooks = new ArrayList<>();
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, author);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            listBooks.add(bookMapping(rs));
        }
        return listBooks;
    }

    @Override
    public List<Book> getBooksByNameAndAuthor(String name, String author) throws SQLException {
        String sql = "select * from books where name=? and author=?";
        List<Book> listBooks = new ArrayList<>();
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, name);
        statement.setString(2, author);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            listBooks.add(bookMapping(rs));
        }
        return listBooks;
    }

    @Override
    public String deleteById(int id) throws SQLException {
        Book bookDel = getById(id);
        if (bookDel != null) {
            String bookString = "id" + bookDel.getId() + " \"" + bookDel.getName() + "\"  автор " + bookDel.getAuthor();
            String sql = "delete from books where id=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
            return "Книга " + bookString + " удалена";
        } else {
            return "Нет такой книги";
        }
    }

    @Override
    public String setNumber(int number,int id) throws SQLException {
        String sql="update books set number=? where id=?";
        PreparedStatement statement=conn.prepareStatement(sql);
        statement.setInt(1,number);
        statement.setInt(2,id);
        statement.executeUpdate();


        return "ok";
    }

    @Override
    public Book getById(Integer Id) throws SQLException {
        String sql = "select * from books where id=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, Id);
        ResultSet rs = statement.executeQuery();
        Book result = null;
        if (rs.next())
            result = bookMapping(rs);
        return result;
    }

    @Override
    public List<Book> getAll() throws SQLException {
        String sql = "select * from books";
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        List<Book> listBooks = new ArrayList<>();

        while (true) {
            try {
                if (!rs.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                listBooks.add(bookMapping(rs));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        Collections.sort(listBooks, Comparator.comparing(Book::getAuthor));
        return listBooks;
    }

    @Override
    public String save(Book book) throws SQLException {
        String sql = "insert books (name,author,year,number) values (?,?,?,?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, book.getName());
        statement.setString(2, book.getAuthor());
        statement.setInt(3, book.getYear());
        statement.setInt(4, book.getNumber());
        statement.executeUpdate();
        return "Книга " + book.getName() + " " + book.getAuthor() + " добавлена!";

    }

    @Override
    public void delete(Book book) throws SQLException {
        deleteById(book.getId());
    }

    public Book bookMapping(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String author = rs.getString("author");
        int year = rs.getInt("year");
        int number = rs.getInt("number");
        return new Book(id, name, author, year, number);
    }
}
