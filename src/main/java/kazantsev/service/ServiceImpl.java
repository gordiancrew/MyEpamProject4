package kazantsev.service;

import kazantsev.dao.*;
import kazantsev.dao.impl.BooksDaoImpl;
import kazantsev.dao.impl.OperationsDaoImpl;
import kazantsev.dao.impl.UsersDaoImpl;
import kazantsev.entity.Book;
import kazantsev.entity.Operation;
import kazantsev.entity.User;
import kazantsev.entity.UserRole;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class ServiceImpl implements Service {
    BooksDao booksDao = new BooksDaoImpl();
    UsersDao usersDao = new UsersDaoImpl();
    OperationsDao operationsDao = new OperationsDaoImpl();


    @Override
    public List<Book> searchBook(String name, String author) throws SQLException {

        if (name == "" && author == "") {
            System.out.println(1);
            return booksDao.getAll();
        } else if (author == "") {
            System.out.println(2);
            return booksDao.getBooksByName(name);
        } else if (name == "") {
            return booksDao.getBooksByAuthor(author);
        } else {
            return booksDao.getBooksByNameAndAuthor(name, author);
        }
    }

    @Override
    public List<User> searchReader(String name, String sureName) throws SQLException {
        if (name == "" && sureName == "") {
            return usersDao.getAll();
        } else if (sureName == "") {
            return usersDao.getUserByName(name);
        } else if (name == "") {
            return usersDao.getUserBySureName(sureName);
        } else {
            return usersDao.getUserByNameAndSureName(name, sureName);
        }
    }

    public String getBook(int idBook, int idAuthor) throws SQLException {
        Book book = booksDao.getById(idBook);
        if (book.getNumber() == 1) {
            operationsDao.createOperation(idBook, idAuthor);
            booksDao.setNumber(0, idBook);
            return "get.successfully";
        }
        return "get.unsuccessfully";
    }

    @Override
    public List<Operation> getActiveOperations(int idRiader) throws SQLException {

        return operationsDao.getOperationByIdReaderWhereReturnDateNull(idRiader);

    }

    @Override
    public String returnBook(int idOperation) throws SQLException {
        Operation operation = operationsDao.getById(idOperation);
        if (operation.getDateReturn() == null) {
            operationsDao.setDateReturn(Date.valueOf(LocalDate.now()), idOperation);
            booksDao.setNumber(1, operation.getBook().getId());
            return "return.successfully";
        }
        return "return.unsuccessfully";
    }

    @Override
    public User addUser(String name, String sureName, int phone, String address, String login, String password) throws SQLException {

            usersDao.save(new User(name, sureName, UserRole.READER, phone, address, login, password));
            return usersDao.getUserByLogin(login);

    }

    @Override
    public User getUserByLogin(String login) throws SQLException {
       return usersDao.getUserByLogin(login);
    }

    @Override
    public String deleteBook(int idBook) throws SQLException {
        if(booksDao.getById(idBook)!=null) {
            booksDao.deleteById(idBook);
            return "ok";
        }
        return "no";
    }

    @Override
    public String addBook(String tittle, String author, int year, int number, String description) throws SQLException {
        booksDao.save(new Book(tittle,author, year, number, description));
        if(booksDao.getBooksByNameAndAuthor(tittle,author)!=null){
            return "ok";
        }
      return "no";
    }

    @Override
    public List<User> getNonConfirmUsers() throws SQLException {

        return  usersDao.getUsersByConfirm(Boolean.FALSE);

    }

    @Override
    public List<User> getConfirmUsers() throws SQLException {
        return  usersDao.getUsersByConfirm(Boolean.TRUE);
    }

    @Override
    public String deleteUser(int idUser) throws SQLException {
        if(usersDao.getById(idUser)!=null) {
            usersDao.deleteById(idUser);
            return "ok";
        }
        return "no";
    }

    @Override
    public User getUserById(int id) throws SQLException {
      return  usersDao.getById(id);
    }

    @Override
    public String confirmUser(int id) throws SQLException {
        usersDao.setConfirm(id,Boolean.TRUE);
        return "ok";}


}
