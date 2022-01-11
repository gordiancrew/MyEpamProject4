package kazantsev.dao.impl;

import kazantsev.database.ConnectionSourse;
import kazantsev.entity.Book;

import java.sql.*;
import java.util.*;

public class DaoBooks {
    ConnectionSourse sourse = ConnectionSourse.instance();
    Connection conn;

    {
        try {
            conn = sourse.createConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String addBook(String name, String author, int year,int number) throws SQLException {


            String sql = "insert books (name,author,year,number) values (?,?,?,?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, author);
            statement.setInt(3, year);
            statement.setInt(4,number);
            statement.executeUpdate();
            return "Книга "+name+" "+author+" добавлена!";

    }

    public String deleteBookById(int id) throws SQLException {
        Book bookDel = getBookById(id);
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

    public List<Book> getAllBooks() throws SQLException {
        String sql = "select * from books";
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        List<Book> listBooks = new ArrayList<>();

        while (rs.next()) {
            listBooks.add(bookMapping(rs));
        }
        Collections.sort(listBooks, Comparator.comparing(Book::getAuthor));
        return listBooks;
    }

    public List<Book> getBooksByAuthorFirstLetter(String firstLetter) throws SQLException {
        String sql = "select * from books where substring(author,1,1)=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, firstLetter);
        ResultSet rs = statement.executeQuery();
        List<Book> listBooks = new ArrayList<>();
        while (rs.next()) {
            listBooks.add(bookMapping(rs));
        }
        return listBooks;
    }

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


    public Book getBookById(int id) throws SQLException {
        String sql = "select * from books where id=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();
        Book result = null;
        if (rs.next())
            result = bookMapping(rs);
        return result;

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
