package kazantsev.service;

import kazantsev.entity.Book;
import kazantsev.entity.Operation;
import kazantsev.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface Service {
    List<Book> searchBook(String name, String author) throws SQLException;
    List<User> searchReader(String name, String sureName) throws SQLException;

    String getBook(int idBook, int idAuthor) throws SQLException;

    List<Operation> getActiveOperations(int idRiader) throws SQLException;

    String returnBook(int idOperation) throws SQLException;

    User addUser(String name, String sureName, int phone, String address, String login, String password) throws SQLException;

    User getUserByLogin(String login) throws SQLException;

    String deleteBook(int idBook) throws SQLException;

    String addBook(String tittle, String author, int year, int number, String description) throws SQLException;

    List<User> getNonConfirmUsers() throws SQLException;
    List<User> getConfirmUsers() throws SQLException;

    String deleteUser(int idUser) throws SQLException;
    User getUserById(int id) throws SQLException;
    String confirmUser(int id) throws SQLException;
}
