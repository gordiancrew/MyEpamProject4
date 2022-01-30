package kazantsev.dao.impl;

import kazantsev.controller.LibraryServlet;
import kazantsev.dao.BooksDao;
import kazantsev.database.ConnectionSourse;
import kazantsev.entity.Book;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.*;

public class BooksDaoImpl implements BooksDao {

    private static final Logger log = Logger.getLogger(BooksDaoImpl.class);
    //ConnectionSourse sourse = ConnectionSourse.instance();
   // Connection conn = sourse.createConnection();
    Connection conn=null;
    public  BooksDaoImpl(Connection conn){
       this.conn=conn;
    }


    @Override
    public List<Book> getBooksByName(String name) {

        String sql = "select * from books where name=?";
        List<Book> listBooks = new ArrayList<>();
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(sql);
        } catch (SQLException e) {
            log.log(Level.ERROR, "exception:", e);
            e.printStackTrace();
        }
        try {
            statement.setString(1, name);
        } catch (SQLException e) {
            log.log(Level.ERROR, "exception:", e);
            e.printStackTrace();
        }
        ResultSet rs = null;
        try {
            rs = statement.executeQuery();
        } catch (SQLException e) {
            log.log(Level.ERROR, "exception:", e);
            e.printStackTrace();
        }
        while (true) {
            try {
                if (!rs.next()) break;
            } catch (SQLException e) {
                log.log(Level.ERROR, "exception:", e);
                e.printStackTrace();
            }
            listBooks.add(bookMapping(rs));
        }
        return listBooks;
    }

    @Override
    public List<Book> getBooksByAuthor(String author) {
        String sql = "select * from books where   author=?";
        List<Book> listBooks = new ArrayList<>();
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(sql);
        } catch (SQLException e) {
            log.log(Level.ERROR, "exception:", e);
            e.printStackTrace();
        }
        try {
            statement.setString(1, author);
        } catch (SQLException e) {
            log.log(Level.ERROR, "exception:", e);
            e.printStackTrace();
        }
        ResultSet rs = null;
        try {
            rs = statement.executeQuery();
        } catch (SQLException e) {
            log.log(Level.ERROR, "exception:", e);
            e.printStackTrace();
        }
        while (true) {
            try {
                if (!rs.next()) break;
            } catch (SQLException e) {
                log.log(Level.ERROR, "exception:", e);
                e.printStackTrace();
            }
            listBooks.add(bookMapping(rs));
        }
        return listBooks;
    }

    @Override
    public List<Book> getBooksByNameAndAuthor(String name, String author) {
        String sql = "select * from books where name=? and author=?";
        List<Book> listBooks = new ArrayList<>();
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, author);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                listBooks.add(bookMapping(rs));
            }
        } catch (SQLException e) {
            log.log(Level.ERROR, "exception:", e);
            e.printStackTrace();
        }
        return listBooks;
    }

    @Override
    public String deleteById(int id) {
        Book bookDel = null;
        bookDel = getById(id);
        if (bookDel != null) {
            String bookString = "id" + bookDel.getId() + " \"" + bookDel.getName() + "\"  автор " + bookDel.getAuthor();
            String sql = "delete from books where id=?";
            PreparedStatement statement = null;
            try {
                statement = conn.prepareStatement(sql);
                statement.setInt(1, id);
                statement.executeUpdate();
            } catch (SQLException e) {
                log.log(Level.ERROR, "exception:", e);
                e.printStackTrace();
            }
            return "book " + bookString + " delete";
        } else {
            return "not this book";
        }
    }

    @Override
    public String setNumber(int number, int id) {
        String sql = "update books set number=? where id=?";
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(sql);

            statement.setInt(1, number);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            log.log(Level.ERROR, "exception:", e);
            e.printStackTrace();
        }
        return "ok";
    }

    @Override
    public Book getById(Integer Id) {
        String sql = "select * from books where id=?";
        PreparedStatement statement = null;
        Book result = null;
        try {
            statement = conn.prepareStatement(sql);
            statement.setInt(1, Id);
            ResultSet rs = statement.executeQuery();
            if (rs.next())
                result = bookMapping(rs);
        } catch (SQLException e) {
            log.log(Level.ERROR, "exception:", e);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Book> getAll() {
        String sql = "select * from books";
        Statement statement = null;
        List<Book> listBooks = new ArrayList<>();
        ResultSet rs = null;
        try {
            statement = conn.createStatement();

            rs = statement.executeQuery(sql);

        } catch (SQLException e) {
            log.log(Level.ERROR, "exception:", e);
            e.printStackTrace();
        }
        while (true) {
            try {
                if (!rs.next()) break;
            } catch (SQLException e) {
                log.log(Level.ERROR, "exception:", e);
                e.printStackTrace();
            }
            listBooks.add(bookMapping(rs));
        }
        Collections.sort(listBooks, Comparator.comparing(Book::getAuthor));
        return listBooks;
    }

    @Override
    public String save(Book book) {
        String sql = "insert books (name,author,year,number,description) values (?,?,?,?,?)";
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(sql);
            statement.setString(1, book.getName());
            statement.setString(2, book.getAuthor());
            statement.setInt(3, book.getYear());
            statement.setInt(4, book.getNumber());
            statement.setString(5, book.getDescription());
            statement.executeUpdate();
        } catch (SQLException e) {
            log.log(Level.ERROR, "exception:", e);
            e.printStackTrace();
        }
        return "Book " + book.getName() + " " + book.getAuthor() + "add!";

    }

    @Override
    public void delete(Book book) {
        deleteById(book.getId());
    }

    public Book bookMapping(ResultSet rs) {
        int id = 0;
        Book book = null;
        try {
            id = rs.getInt("id");
            String name = rs.getString("name");
            String author = rs.getString("author");
            int year = rs.getInt("year");
            int number = rs.getInt("number");
            String description = rs.getString("description");
            book = new Book(name, author, year, number, description);
            book.setId(id);
        } catch (SQLException e) {
            log.log(Level.ERROR, "exception:", e);
            e.printStackTrace();
        }
        return book;
    }
}
