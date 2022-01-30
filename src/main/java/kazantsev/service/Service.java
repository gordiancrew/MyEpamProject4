package kazantsev.service;

import kazantsev.entity.Book;
import kazantsev.entity.Operation;
import kazantsev.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface Service {
    List<Book> searchBook(String name, String author);

    String getBook(int idBook, int idAuthor);

    Book getBookById(int idBook);

    String deleteBook(int idBook);

    String addBook(String tittle, String author, int year, int number, String description);

    String returnBook(int idOperation);

    User addUser(String name, String sureName, int phone, String address, String login, String password);

    List<User> getNonConfirmUsers();

    List<User> getConfirmUsers();

    User getUserByLogin(String login);

    List<User> searchReader(String name, String sureName);

    String deleteUser(int idUser);

    User getUserById(int id);

    String confirmUser(int id);

    List<Operation> getActiveOperations(int idRiader);

    Operation getOperationById(int id);
}
