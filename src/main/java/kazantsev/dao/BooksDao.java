package kazantsev.dao;

import kazantsev.entity.Book;

import java.sql.SQLException;
import java.util.List;

public interface BooksDao extends Dao<Book, Integer> {
    List<Book> getBooksByName(String name);

    List<Book> getBooksByAuthor(String author);

    List<Book> getBooksByNameAndAuthor(String name, String author);

    String deleteById(int id);

    String setNumber(int number, int id);

}
