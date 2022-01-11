package kazantsev.dao;

import kazantsev.entity.Book;

import java.sql.SQLException;
import java.util.List;

public interface BooksDao extends Dao<Book, Integer> {
    List<Book> getBooksByName(String name) throws SQLException;

    List<Book> getBooksByAuthor(String author) throws SQLException;

    List<Book> getBooksByNameAndAuthor(String name, String author) throws SQLException;



    String deleteById(int id) throws SQLException;

    String setNumber(int number, int id) throws SQLException;

}
