package kazantsev.service;

import kazantsev.dao.*;
import kazantsev.dao.impl.BooksDaoImpl;
import kazantsev.dao.impl.OperationsDaoImpl;
import kazantsev.dao.impl.UsersDaoImpl;
import kazantsev.database.ConnectionSourse;
import kazantsev.entity.Book;
import kazantsev.entity.Operation;
import kazantsev.entity.User;
import kazantsev.entity.UserRole;
import kazantsev.model.actions.actionsimpl.ActionBook;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class ServiceImpl implements Service {
    private static final Logger log = Logger.getLogger(ServiceImpl.class);
    ConnectionSourse sourse = ConnectionSourse.instance();
     Connection conn = sourse.createConnection();
    BooksDao booksDao = new BooksDaoImpl(conn);
    UsersDao usersDao = new UsersDaoImpl(conn);
    OperationsDao operationsDao = new OperationsDaoImpl(conn);

    @Override
    public List<Book> searchBook(String name, String author) {
        List<Book> list = null;
        if (name == "" && author == "") {
            list = booksDao.getAll();
        } else if (author == "") {
            System.out.println(2);
            list = booksDao.getBooksByName(name);
        } else if (name == "") {
            list = booksDao.getBooksByAuthor(author);
        } else {
            list = booksDao.getBooksByNameAndAuthor(name, author);
        }
        return list;
    }

    @Override
    public List<User> searchReader(String name, String sureName) {
        List<User> list = null;
        if (name == "" && sureName == "") {
            list = usersDao.getAll();
        } else if (sureName == "") {
            list = usersDao.getUserByName(name);
        } else if (name == "") {
            list = usersDao.getUserBySureName(sureName);
        } else {
            list = usersDao.getUserByNameAndSureName(name, sureName);
        }
        return list;
    }

    public String getBook(int idBook, int idAuthor) {
        String result = "get.unsuccessfully";
        Book book = null;
        book = booksDao.getById(idBook);
        if (book.getNumber() == 1) {
            operationsDao.createOperation(idBook, idAuthor);
            booksDao.setNumber(0, idBook);
            result = "get.successfully";
        }
        return result;
    }

    @Override
    public Book getBookById(int idBook) {
        Book book = null;
        book = booksDao.getById(idBook);
        return book;
    }

    @Override
    public List<Operation> getActiveOperations(int idRiader) {
        List<Operation> list = null;
        list = operationsDao.getOperationByIdReaderWhereReturnDateNull(idRiader);
        return list;
    }

    @Override
    public Operation getOperationById(int id) {
        Operation operation = null;
        operation = operationsDao.getById(id);
        return operation;
    }

    @Override
    public String returnBook(int idOperation) {
        String result = "return.unsuccessfully";
        Operation operation = null;
        operation = operationsDao.getById(idOperation);

        if (operation.getDateReturn() == null) {
            operationsDao.setDateReturn(Date.valueOf(LocalDate.now()), idOperation);
            booksDao.setNumber(1, operation.getBook().getId());
            result = "return.successfully";
        }
        return result;
    }

    @Override
    public User addUser(String name, String sureName, int phone, String address, String login, String password) {
        User user = null;
        usersDao.save(new User(name, sureName, UserRole.READER, phone, address, login, password));
        user = usersDao.getUserByLogin(login);
        return user;
    }

    @Override
    public User getUserByLogin(String login) {
        User user = null;
        user = usersDao.getUserByLogin(login);
        return user;
    }

    @Override
    public String deleteBook(int idBook) {
        String result = "no";
        if (booksDao.getById(idBook) != null) {
            booksDao.deleteById(idBook);
            result = "ok";
        }
        return result;
    }

    @Override
    public String addBook(String tittle, String author, int year, int number, String description) {
        String result = "no";
        booksDao.save(new Book(tittle, author, year, number, description));
        if (booksDao.getBooksByNameAndAuthor(tittle, author) != null) {
            result = "ok";
        }
        return result;
    }

    @Override
    public List<User> getNonConfirmUsers() {
        List<User> list = null;
        list = usersDao.getUsersByConfirm(Boolean.FALSE);
        return list;
    }

    @Override
    public List<User> getConfirmUsers() {
        List<User> list = null;
        list = usersDao.getUsersByConfirm(Boolean.TRUE);
        return list;
    }

    @Override
    public String deleteUser(int idUser) {
        String result = "no";
        if (usersDao.getById(idUser) != null) {
            usersDao.deleteById(idUser);
            result = "ok";
        }
        return result;
    }

    @Override
    public User getUserById(int id) {
        User user = null;
        user = usersDao.getById(id);
        return user;
    }

    @Override
    public String confirmUser(int id) {
        String result = "no";
        result = usersDao.setConfirm(id, Boolean.TRUE);
        return result;
    }
}
